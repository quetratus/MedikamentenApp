package com.example.medikamentenapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.room.Dao
import com.example.medikamentenapp.databinding.FragmentFirstScreenBinding
import com.example.medikamentenapp.databinding.FragmentSignUpBinding
import com.example.medikamentenapp.db.UserDatabase
import com.example.medikamentenapp.viewmodel.UserViewModel
import com.example.medikamentenapp.viewmodel.UserViewModelFactory
import java.lang.reflect.Array.get


class FirstScreenFragment : Fragment() {
    //Inflating and Returning the View with DataBindingUtil
    //Inflating and Returning the View with DataBindingUtil

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentFirstScreenBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_first_screen, container, false)

        binding.buttonFirstSignup.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_first_screen_to_sign_up)
        )

        binding.buttonFirstSignup.setOnClickListener (
                    Navigation.createNavigateOnClickListener(R.id.action_first_screen_to_login)
        )
       val application = requireNotNull(this.activity).application

        val dataSource = UserDatabase.getInstance(application).DaoAccess

        val viewModelFactory = UserViewModelFactory(dataSource, application)

        val UserViewModel = UserViewModelFactory.get(UserViewModel::class.java)

        binding.UserViewModel = UserViewModel

        binding.selfLifeCycleOwner

        return binding.root
    }
}


