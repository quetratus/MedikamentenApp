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
import androidx.navigation.Navigation
import com.example.medikamentenapp.Repository.MedicamentRepository
import com.example.medikamentenapp.databinding.FragmentTimeBinding
import com.example.medikamentenapp.db.UserDatabase
import com.example.medikamentenapp.viewmodel.MedViewModel
import com.example.medikamentenapp.viewmodel.MedViewModelFactory


class TimeFragment : Fragment() {

    private lateinit var medViewModel: MedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentTimeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_time, container, false)
        binding.buttonTimeFinish.setOnClickListener {
            Navigation.createNavigateOnClickListener(R.id.action_time_to_overview)
        }

        val application = requireNotNull(this.activity).application
        val dao = UserDatabase.getDatabase(application)!!.daoAccess
        val repository = MedicamentRepository(dao)
        val factory = MedViewModelFactory(repository)
        medViewModel = ViewModelProvider(this, factory).get(MedViewModel::class.java)
        binding.medViewModel = medViewModel
        binding.lifecycleOwner = this
        displayMedList()

        medViewModel.message.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(getActivity(), it, Toast.LENGTH_LONG).show()
            }
        })
        return binding.root
    }

    private fun displayMedList() {
        medViewModel.meds.observe(viewLifecycleOwner, Observer { Log.i("MYTAG", it.toString()) })

    }
}


