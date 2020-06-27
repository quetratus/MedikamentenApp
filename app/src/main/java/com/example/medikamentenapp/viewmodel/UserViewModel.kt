package com.example.medikamentenapp.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medikamentenapp.Event
import com.example.medikamentenapp.Repository.UserRepository
import com.example.medikamentenapp.entities.User
import kotlinx.coroutines.launch


class UserViewModel(private val repository: UserRepository) : ViewModel(), Observable {
// UserviewModel observes live data

    val users = repository.users

    @Bindable
    val inputName = MutableLiveData<String>()

    @Bindable
    val inputPassword = MutableLiveData<String>()

    @Bindable
    val loginButton = MutableLiveData<String>()

    @Bindable
    val registerButton = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()

    val message : LiveData<Event<String>>
        get() = statusMessage

    init {
        loginButton.value = "EINLOGGEN"
        registerButton.value = "REGISTRIEREN"
    }

    fun register(){
        if(inputName.value==null){
            statusMessage.value = Event("Bitte den Namen eingeben")
        }
        else if(inputPassword.value==null) {
            statusMessage.value = Event("Bitte das Passwort eingeben")
        }
        else {

            val name: String = inputName.value!!
            val password = inputPassword.value!!
            inputName.value = null
            inputPassword.value = null
            insertNewUser(User(0, name, password))
        }
    }

    fun login(){
        if(inputName.value==null){
            statusMessage.value = Event("Bitte den Namen eingeben")
        }
        else if(inputPassword.value==null) {
            statusMessage.value = Event("Bitte das Passwort eingeben")
        }

        else {
            val name = inputName.value!!
            val password = inputPassword.value!!
            loginUser(User(0, name, password))
            inputName.value = null
            inputPassword.value = null
        }
    }


    fun insertNewUser(user: User) = viewModelScope.launch {
        for (i in users.value!!) {
            if (user.name.equals(inputName.value, true)) {
                statusMessage.value = Event("User existiert bereits")
                break
            } else {
                val newRowId: Long = repository.insert(user)
                if (newRowId > -1) {
                    statusMessage.value = Event("Erfolgreich hinzugefügt")
                } else {
                    statusMessage.value = Event("Fehler aufgetreten")
                }
            }
        }
    }

    private var _navigateLoggedInEvent = MutableLiveData<Boolean>()

    val navigateLoggedInEvent: LiveData<Boolean>
        get() = _navigateLoggedInEvent


    fun doneNavigateLoggedInEvent() {
        _navigateLoggedInEvent.value = false
    }


    fun loginUser(user: User) = viewModelScope.launch {
        for (i in users.value!!) {
                if (user.name.equals(inputName.value, true)) {
                    _navigateLoggedInEvent.value = true
                }
             else {
                    statusMessage.value = Event("Bitte registrieren Sie sich")
                }
            }
        }


    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }



}

