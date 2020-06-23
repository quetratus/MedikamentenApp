package com.example.medikamentenapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.medikamentenapp.databinding.FragmentFirstScreenBinding


class FirstScreenFragment : Fragment() {
    //Inflating and Returning the View with DataBindingUtil

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentFirstScreenBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_first_screen, container, false)

        binding.buttonFirstLogin.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_first_screen_to_login)
        )

        return binding.root

    }


}


