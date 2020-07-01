package com.example.medikamentenapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.medikamentenapp.Repository.MedicamentRepository
import com.example.medikamentenapp.databinding.FragmentMedikamentBinding
import com.example.medikamentenapp.db.UserDatabase
import com.example.medikamentenapp.viewmodel.MedViewModel
import com.example.medikamentenapp.viewmodel.MedViewModelFactory

class MedikamentFragment : Fragment() {

    private lateinit var medViewModel: MedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentMedikamentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_medikament, container, false)
        val application = requireNotNull(this.activity).application
        binding.timePicker.setIs24HourView(true)
        val dao = UserDatabase.getDatabase(application)!!.daoAccess
        val repository = MedicamentRepository(dao)
        val factory = MedViewModelFactory(repository)
        medViewModel = ViewModelProvider(this, factory).get(MedViewModel::class.java)
        binding.medViewModel = medViewModel
        binding.lifecycleOwner = this

        medViewModel.message.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(getActivity(), it, Toast.LENGTH_LONG).show()
            }
        })

        binding.buttonAddTime.setOnClickListener {
            val hour: Int = binding.timePicker.currentHour
            val minute: Int = binding.timePicker.currentMinute
            medViewModel.addTime(hour, minute)
        }


        return binding.root
    }

}
