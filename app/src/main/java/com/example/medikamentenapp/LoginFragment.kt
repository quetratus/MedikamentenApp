package com.example.medikamentenapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.medikamentenapp.Repository.UserDetailsRepository
import com.example.medikamentenapp.entities.User


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    login.setOnClickListener {
        if (validation()) {
            UserDetailsRepository.getGetAllData().observe(this, object : Observer<List<User>> {
                override fun onChanged(t: List<User>) {
                    var userObject = t

                    for (i in userObject.indices) {
                            if (userObject[i].password?.equals(password.text.toString())!!) {

                                val intent = Intent(this@LoginFragment, MainActivity::class.java)
                                    .putExtra("UserDetials", userObject[i])
                                // start your next activity
                                startActivity(intent)

                            } else {
                                Toast.makeText(this@LoginFragment, " Password is Incorrect ", Toast.LENGTH_LONG)
                                    .show()
                            }
                            isExist = true
                            break

                        } else {
                            isExist = false
                        }
                    }

                    if (isExist) {

                    } else {

                        Toast.makeText(this@LoginFragment, " User Not Registered ", Toast.LENGTH_LONG).show()
                    }

                }

            })
        }
    }


}

/**
 * Attempts to sign in  the account specified by the login form.
 * If there are form errors the
 * errors are presented in form of toast and no actual login attempt is made.
 */
private fun validation(): Boolean {

    if (password.text.isNullOrEmpty()) {
        Toast.makeText(this@LoginFragment, " Enter Password ", Toast.LENGTH_LONG).show()
        return false
    }
    return true
}







}