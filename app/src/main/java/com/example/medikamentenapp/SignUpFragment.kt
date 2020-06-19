package com.example.medikamentenapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.medikamentenapp.Repository.UserDetailsRepository
import com.example.medikamentenapp.databinding.FragmentSignUpBinding
import com.example.medikamentenapp.db.UserDatabase
import com.example.medikamentenapp.entities.User


class SignUpFragment : Fragment() {

    var isExist = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentSignUpBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)

        val application = requireNotNull(this.activity).application

        return binding.root

        login.setOnClickListener {
            val intent = Intent(this@SignUpFragment, LoginFragment::class.java)
            // start your next activity
            startActivity(intent)
        }

        sign_up.setOnClickListener {
            if (validation()) {
                UserDetailsRepository.getGetAllData().observe(this, object : Observer<List<User>> {
                    override fun onChanged(t: List<User>) {
                        var userObject = t

                        if (isExist) {
                            Toast.makeText(this@SignUpFragment, " User Already Registered !!! ", Toast.LENGTH_LONG)
                                .show()

                        } else {

                            val user = User()

                            user.name = name.text.toString()

                            user.password = password.text.toString()
                            val userDatabase = UserDatabase
                            userDatabase.getInstance(this?.daoAccess()?.insertUserData(user)
                            Toast.makeText(this@SignUpFragment, " User  Registered Successfully", Toast.LENGTH_LONG)
                                .show()


                        }

                    }

                })
            }

        }


    }

    /**
     * Attempts to register in  the account specified by the registration form.
     * If there are form errors (invalid name, missing fields, etc.), the
     * errors are presented in form of toast and no actual login attempt is made.
     */
    private fun validation(): Boolean {
        if (name.text.isNullOrEmpty()) {
            Toast.makeText(this@SignUpFragment, " Enter Full Name ", Toast.LENGTH_LONG).show()
            return false
        }
        if (password.text.isNullOrEmpty()) {
            Toast.makeText(this@SignUpFragment, " Enter Password ", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

}

}



}