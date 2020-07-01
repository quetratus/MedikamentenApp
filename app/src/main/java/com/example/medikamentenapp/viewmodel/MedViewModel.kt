package com.example.medikamentenapp.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.*
import com.example.medikamentenapp.Event
import com.example.medikamentenapp.Repository.MedicamentRepository
import com.example.medikamentenapp.entities.Medicament
import com.example.medikamentenapp.entities.UserWithMed
import com.example.medikamentenapp.formatMeds
import kotlinx.coroutines.launch

class MedViewModel(private val repository: MedicamentRepository) : ViewModel(), Observable {
    val application = requireNotNull(this).application
    val loggedInUser = Model.displayName

    val meds = getAllMed(loggedInUser)

    suspend fun getAllMed(username: String): LiveData<List<Medicament>> {
        return dao.getAllMed(username)
    }

    @Bindable
    val inputMedName = MutableLiveData<String>()

    @Bindable
    val inputMedDosis = MutableLiveData<String>()

    private val inputMedTime1 = MutableLiveData<String>()
    private var medTime1Min: Int = 0
    private var medTime1Hour: Int = 0


    private val inputMedTime2 = MutableLiveData<String>()
    private var medTime2Min: Int = 0
    private var medTime2Hour: Int = 0


    private val inputMedTime3 = MutableLiveData<String>()
    private var medTime3Min: Int = 0
    private var medTime3Hour: Int = 0

    @Bindable
    val saveMedButton = MutableLiveData<String>()

    val timeString = MutableLiveData<String>()


    private val statusMessage = MutableLiveData<Event<String>>()

    val message: LiveData<Event<String>>
        get() = statusMessage

    val medsListString = Transformations.map(meds) { meds ->
        formatMeds(meds, application.resources)
    }

    init {
        saveMedButton.value = "SPEICHERN"
        timeString.value = ""
    }

    fun addTime(hour: Int, minute: Int) {
        if (inputMedTime1.value == null) {
            inputMedTime1.value = "$hour:$minute"
            medTime1Hour = hour
            medTime1Min = minute
            if (hour < 10 && minute < 10) {
                timeString.value = "1: 0$hour:0$minute"
            } else if (hour < 10) {
                timeString.value = "1: 0$hour:$minute"
            } else if (minute < 10) {
                timeString.value = "1: $hour:0$minute"
            } else {
                timeString.value = "1: $hour:$minute"
            }
        } else if (inputMedTime2.value == null) {
            inputMedTime2.value = "$hour:$minute"
            medTime2Hour = hour
            medTime2Min = minute
            if (hour < 10 && minute < 10) {
                timeString.value = "${timeString.value} 2: 0$hour:0$minute"
            } else if (hour < 10) {
                timeString.value = "${timeString.value} 2: 0$hour:$minute"
            } else if (minute < 10) {
                timeString.value = "${timeString.value} 2: $hour:0$minute"
            } else {
                timeString.value = "${timeString.value} 2: $hour:$minute"
            }
        } else if (inputMedTime3.value == null) {
            inputMedTime3.value = "$hour:$minute"
            medTime3Hour = hour
            medTime3Min = minute
            if (hour < 10 && minute < 10) {
                timeString.value = "${timeString.value} 3: 0$hour:0$minute"
            } else if (hour < 10) {
                timeString.value = "${timeString.value} 3: 0$hour:$minute"
            } else if (minute < 10) {
                timeString.value = "${timeString.value} 3: $hour:0$minute"
            } else {
                timeString.value = "${timeString.value} 3: $hour:$minute"
            }
        } else {
            statusMessage.value = Event("Alle drei Uhrzeiten bereits vergeben!")
        }
    }

    suspend fun saveMed(Model: LoggedInUserView) {
        if (inputMedName.value == null) {
            statusMessage.value = Event("Bitte den Namen eingeben!")
        } else if (inputMedDosis.value == null) {
            statusMessage.value = Event("Bitte die Dosis eingeben!")
        } else if (inputMedTime1.value == null) {
            statusMessage.value = Event("Bitte bis zu drei Uhrzeiten eingeben!")
        }

        val medName = inputMedName.value!!
        val medUsername = Model.displayName
        val medDosis = inputMedDosis.value!!
        val medTime1 = inputMedTime1.value!!
        val medTime2 = inputMedTime2.value!!
        val medTime3 = inputMedTime3.value!!

        insertMed(Medicament(0, medName, medUsername, medDosis, medTime1, medTime2, medTime3))
        inputMedName.value = null
        inputMedDosis.value = null
        inputMedTime1.value = null
        inputMedTime2.value = null
        inputMedTime3.value = null

    }

    private suspend fun insertMed(med: Medicament) = viewModelScope.launch {
        val newRowId: Long = repository.insertMed(med)
        if (newRowId > -1) {
            statusMessage.value = Event("Erfolgreich hinzugefügt")
        } else {
            statusMessage.value = Event("Fehler aufgetreten")
        }
    }


    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}


