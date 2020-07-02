package com.example.medikamentenapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.medikamentenapp.Repository.MedicamentRepository
import com.example.medikamentenapp.databinding.FragmentMedikamentBinding
import com.example.medikamentenapp.db.UserDatabase
import com.example.medikamentenapp.notify.ReminderBroadcast
import com.example.medikamentenapp.viewmodel.LoggedInUserView
import com.example.medikamentenapp.viewmodel.MedViewModel
import com.example.medikamentenapp.viewmodel.MedViewModelFactory
import kotlinx.coroutines.*
import java.util.*

class MedikamentFragment : Fragment() {

    private lateinit var medViewModel: MedViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentMedikamentBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_medikament, container, false)
        val application = requireNotNull(this.activity).application
        val viewModelJob = Job()
        val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
        binding.timePicker.setIs24HourView(true)
        val dao = UserDatabase.getDatabase(application)!!.daoAccess
        val repository = MedicamentRepository(dao)
        val model = LoggedInUserView(displayName = "Sebastian")
        val factory = MedViewModelFactory(repository, application, model)
        medViewModel = ViewModelProvider(this, factory).get(MedViewModel::class.java)
        //binding.medViewModel = medViewModel
        binding.lifecycleOwner = this

        medViewModel.message.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(getActivity(), it, Toast.LENGTH_LONG).show()
            }
        })

        binding.buttonAddTime.setOnClickListener {
            val hour: Int = binding.timePicker.currentHour
            val minute: Int = binding.timePicker.currentMinute
            if(medViewModel.medTime1Set == false){
                medViewModel.medTime1Set = true
                medViewModel.medTime1Hour = hour
                medViewModel.medTime1Min = minute
                if (hour < 10 && minute <10){
                    binding.medTime.setText("1: 0$hour:0$minute")
                    medViewModel.inputMedTime1.value = "0$hour:0$minute"
                } else if (minute <10) {
                    binding.medTime.setText("1: $hour:0$minute")
                    medViewModel.inputMedTime1.value = "$hour:0$minute"
                } else if (hour < 10) {
                    binding.medTime.setText("1: 0$hour:$minute")
                    medViewModel.inputMedTime1.value = "0$hour:$minute"
                } else {
                    binding.medTime.setText("1: $hour:$minute")
                    medViewModel.inputMedTime1.value = "$hour:$minute"
                }
            } else if (medViewModel.medTime2Set == false){
                medViewModel.medTime2Set = true
                medViewModel.medTime2Hour = hour
                medViewModel.medTime2Min = minute
                if (hour < 10 && minute <10){
                    binding.medTime.setText("${binding.medTime.text} 2: 0$hour:0$minute")
                    medViewModel.inputMedTime2.value = "0$hour:0$minute"
                } else if (minute <10) {
                    binding.medTime.setText("${binding.medTime.text} 2: $hour:0$minute")
                    medViewModel.inputMedTime2.value = "$hour:0$minute"
                } else if (hour < 10) {
                    binding.medTime.setText("${binding.medTime.text} 2: 0$hour:$minute")
                    medViewModel.inputMedTime2.value = "0$hour:$minute"
                } else {
                    binding.medTime.setText("${binding.medTime.text} 2: $hour:$minute")
                    medViewModel.inputMedTime2.value = "$hour:$minute"
                }
            } else if (medViewModel.medTime3Set == false) {
                medViewModel.medTime3Set = true
                medViewModel.medTime3Hour = hour
                medViewModel.medTime3Min = minute
                if (hour < 10 && minute < 10) {
                    binding.medTime.setText("${binding.medTime.text} 3: 0$hour:0$minute")
                    medViewModel.inputMedTime3.value = "0$hour:0$minute"
                } else if (minute < 10) {
                    binding.medTime.setText("${binding.medTime.text} 3: $hour:0$minute")
                    medViewModel.inputMedTime3.value = "$hour:0$minute"
                } else if (hour < 10) {
                    binding.medTime.setText("${binding.medTime.text} 3: 0$hour:$minute")
                    medViewModel.inputMedTime3.value = "0$hour:$minute"
                } else {
                    binding.medTime.setText("${binding.medTime.text} 3: $hour:$minute")
                    medViewModel.inputMedTime3.value = "$hour:$minute"
                }
            } else {
                Toast.makeText(this.context, "Alle drei Uhrzeiten belegt!", Toast.LENGTH_LONG)
                    .show()
            }
        }

        binding.buttonMedFinish.setOnClickListener {
            if (binding.medName.text == null) {
                Toast.makeText(this.context, "Bitte den Namen eingeben!", Toast.LENGTH_SHORT)
            } else if (binding.medDose.text == null) {
                Toast.makeText(this.context, "Bitte die Dosis eingeben!", Toast.LENGTH_SHORT)
            } else if (binding.medTime.text == null) {
                Toast.makeText(
                    this.context,
                    "Bitte bis zu drei Uhreziten eingeben!",
                    Toast.LENGTH_SHORT
                )
            } else {
                uiScope.launch(Dispatchers.Main) {
                    withContext(Dispatchers.IO) {
                        medViewModel.saveMed(
                            binding.medName.text.toString(),
                            "Sebastian",
                            binding.medDose.text.toString(),
                            medViewModel.inputMedTime1.value!!.toString(),
                            medViewModel.inputMedTime2.value!!.toString(),
                            medViewModel.inputMedTime3.value!!.toString()
                        )
                    }

                    val pendingIntent1 = createPendingIntent(
                        medViewModel.inputMedName.toString(),
                        medViewModel.inputMedTime1.toString()
                    )

                    val alarmManager =
                        context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager

                    val calendar1: Calendar =
                        prepareTime(medViewModel.medTime1Hour, medViewModel.medTime1Min)
                alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar1.timeInMillis,
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent1
                )
                if (medViewModel.medTime2Set == true) {
                    val pendingIntent2 = createPendingIntent(
                        medViewModel.inputMedName.toString(),
                        medViewModel.inputMedTime2.toString()
                    )
                    val calendar2: Calendar =
                        prepareTime(medViewModel.medTime2Hour, medViewModel.medTime2Min)
                    alarmManager.setRepeating(
                        AlarmManager.RTC_WAKEUP,
                        calendar2.timeInMillis,
                        AlarmManager.INTERVAL_DAY,
                        pendingIntent2
                    )
                }
                if (medViewModel.medTime3Set == true) {
                    val pendingIntent3 = createPendingIntent(
                        medViewModel.inputMedName.toString(),
                        medViewModel.inputMedTime3.toString()
                    )
                    val calendar3: Calendar =
                        prepareTime(medViewModel.medTime3Hour, medViewModel.medTime3Min)
                    alarmManager.setRepeating(
                        AlarmManager.RTC_WAKEUP,
                        calendar3.timeInMillis,
                        AlarmManager.INTERVAL_DAY,
                        pendingIntent3
                    )
                }
                Navigation.createNavigateOnClickListener(R.id.action_medikament_to_overview)
        }
        }
        }

        return binding.root
    }



    private fun createPendingIntent(medName: String, medTime: String): PendingIntent? {
        val intent = Intent(this.context, ReminderBroadcast::class.java).apply {
            putExtra(medName, medName)
            type = "$medName - $medTime"
        }
        return PendingIntent.getBroadcast(this.context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun prepareTime(h: Int, m:Int): Calendar {
        var hour: Int = h
        var minute: Int = m

        if (minute < 15) {
            hour -= 1
            minute += 45
        } else {
            minute -= 15
        }
        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
        }
        return calendar

    }

}
