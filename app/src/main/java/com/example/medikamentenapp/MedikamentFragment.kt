package com.example.medikamentenapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.medikamentenapp.databinding.FragmentMedikamentBinding

class MedikamentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentMedikamentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_medikament, container, false)
        binding.buttonMedFinish.setOnClickListener {
            Navigation.createNavigateOnClickListener(R.id.action_medikament_to_time)
        }
        return binding.root
    }

}