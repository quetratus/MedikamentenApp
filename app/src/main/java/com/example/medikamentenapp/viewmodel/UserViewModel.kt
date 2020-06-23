package com.example.medikamentenapp.viewmodel

import android.app.Application
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.*
import com.example.medikamentenapp.Event
import com.example.medikamentenapp.Repository.UserRepository
import com.example.medikamentenapp.db.UserDatabase
import com.example.medikamentenapp.entities.User
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel(), Observable {
// UserviewModel observes live data

    val users = repository.users

    @Bindable
    val inputName = MutableLiveData<String>()

    @Bindable
    val inputPassword = MutableLiveData<String>()

    @Bindable
    val loginOrRegisterButtonText = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()

    val message : LiveData<Event<String>>
        get() = statusMessage

    init {
        loginOrRegisterButtonText.value = "EINLOGGEN/REGISTRIEREN"
    }

    fun loginOrRegister(){
        if(inputName.value==null){
            statusMessage.value = Event("Bitte den Namen eingeben")
        }
        else if(inputPassword.value==null) {
            statusMessage.value = Event("Bitte das Passwort eingeben")
        }

        val name = inputName.value!!
        val password = inputPassword.value!!
        insert(User(0, name, password ))
        inputName.value = null
        inputPassword.value = null
    }

    fun insert(user: User) = viewModelScope.launch {
        val newRowId: Long = repository.insert(user)
        if (newRowId > -1) {
            statusMessage.value = Event( "Erfolgreich hinzugefügt")
        } else {
            statusMessage.value = Event("Fehler aufgetreten")
        }
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}

