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
import com.example.medikamentenapp.Repository.UserRepository
import com.example.medikamentenapp.databinding.FragmentLoginBinding
import com.example.medikamentenapp.db.UserDatabase
import com.example.medikamentenapp.viewmodel.UserViewModel
import com.example.medikamentenapp.viewmodel.UserViewModelFactory


class LoginFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentLoginBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        val application = requireNotNull(this.activity).application

        val dao = UserDatabase.getDatabase(application)!!.daoAccess
        val repository = UserRepository(dao)
        val factory = UserViewModelFactory(repository)
        userViewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)
        binding.userViewModel = userViewModel
        binding.lifecycleOwner = this
        displayUsersList()

        userViewModel.message.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(getActivity(),it, Toast.LENGTH_LONG).show()
            }
        })


            return binding.root

    }

        private fun displayUsersList() {
        userViewModel.users.observe(viewLifecycleOwner, Observer{ Log.i("MYTAG", it.toString() )} )

        }


}

