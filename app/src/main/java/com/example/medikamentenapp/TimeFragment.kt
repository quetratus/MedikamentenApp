package com.example.medikamentenapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.medikamentenapp.databinding.FragmentTimeBinding
import kotlinx.android.synthetic.main.fragment_time.*


class TimeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentTimeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_time, container, false)
        binding.buttonTimeFinish.setOnClickListener {
            Navigation.createNavigateOnClickListener(R.id.action_time_to_overview)
        }

        binding.buttonAddTime.setOnClickListener{ view: View ->

            if (remember_time != null){

                //add time to database
            }
            else{

            }


        }
        return binding.root
    }

}