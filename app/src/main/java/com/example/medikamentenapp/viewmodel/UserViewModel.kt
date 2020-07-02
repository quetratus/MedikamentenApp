package com.example.medikamentenapp.viewmodel

import android.app.Application
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.*
import androidx.room.Dao
import com.example.medikamentenapp.Event
import com.example.medikamentenapp.Repository.UserRepository
import com.example.medikamentenapp.dao.DaoAccess
import com.example.medikamentenapp.db.UserDatabase
import com.example.medikamentenapp.entities.Medicament
import com.example.medikamentenapp.entities.User
import kotlinx.coroutines.*


class UserViewModel(private val repository: UserRepository, private val application: Application) : ViewModel(), Observable {
// UserviewModel observes live data
    val users = repository.users

    private var loggedInUser = MutableLiveData<User?>()

    @Bindable
    val inputName = MutableLiveData<String>()

    @Bindable
    val inputPassword = MutableLiveData<String>()

    @Bindable
    val loginButton = MutableLiveData<String>()

    @Bindable
    val registerButton = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()

    val message: LiveData<Event<String>>
        get() = statusMessage

    init {
        loginButton.value = "EINLOGGEN"
        registerButton.value = "REGISTRIEREN"
    }

    private var _navigateLoggedInEvent = MutableLiveData<Boolean>()

    val navigateLoggedInEvent: LiveData<Boolean>
        get() = _navigateLoggedInEvent

    fun doneNavigateLoggedInEvent() {
        _navigateLoggedInEvent.value = false
    }

    fun validation():Boolean {
        if (inputName.value == null || inputPassword.value == null) {
            statusMessage.value = Event("Bitte Namen und Passwort eingeben")
            return false
        }
        return true
    }

    fun loginUser() {
        if (validation()) {
            val name = inputName.value!!
            val password = inputPassword.value!!
            getUser(name, password)
            inputName.value = null
            inputPassword.value = null
        }
    }

    fun getUser(name:String, password: String){
        viewModelScope.launch(Dispatchers.Main) {
            val user = getUserFromDB(name, password)
            if(user != null){
                // user existiert
                LoggedInUserView(displayName = name)
                _navigateLoggedInEvent.value = true
            } else {
                // user existiert nicht
                statusMessage.value = Event("Bitte registrieren Sie sich zuerst")
            }
        }
    }

    suspend fun getUserFromDB(name: String, password: String): User {
        // Move the execution of the coroutine to the I/O dispatcher
        return withContext(Dispatchers.IO) {
            val user = repository.getUser(name, password)
            user
        }
    }

    suspend fun registerUser() {
        if (validation()) {
            val name = inputName.value!!
            val password = inputPassword.value!!
            insertUser(User(name, password))
            _navigateLoggedInEvent.value = true
            LoggedInUserView(displayName = name)
            inputName.value = null
            inputPassword.value = null
        }
        else {
            statusMessage.value = Event("Bitte versuchen Sie es nochmal")
        }
    }

    suspend fun insertUser(user: User) = viewModelScope.launch {
        val newRowId: Long = repository.insertUser(user)
        if (newRowId == -1L) {
            statusMessage.value = Event( "Erfolgreich hinzugefügt")
            _navigateLoggedInEvent.value = true
        } else {
            statusMessage.value = Event("Fehler aufgetreten")
        }
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}


