package com.example.medikamentenapp.viewmodel

import android.app.Application
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.*
import com.example.medikamentenapp.Event
import com.example.medikamentenapp.Repository.MedicamentRepository
import com.example.medikamentenapp.entities.Medicament
import com.example.medikamentenapp.formatMeds
import kotlinx.coroutines.launch

class MedViewModel(
    private val repository: MedicamentRepository,
    private val application: Application,
    private val model: LoggedInUserView
) :
    ViewModel(), Observable {
    //val application = requireNotNull(this).application
    val loggedInUser = model.displayName


    val meds = repository.getAllMed(loggedInUser)

    /*
    fun getAllMed(username: String): LiveData<List<Medicament>> {
        viewModelScope.launch(Dispatchers.Main) {
            return repository.getAllMed(username)
        }
    }

     */

    @Bindable
    val inputMedName = MutableLiveData<String>()

    @Bindable
    val inputMedDosis = MutableLiveData<String>()

    val inputMedTime1 = MutableLiveData<String>()
    var medTime1Min: Int = 0
    var medTime1Hour: Int = 0
    var medTime1Set: Boolean = false


    val inputMedTime2 = MutableLiveData<String>()
    var medTime2Min: Int = 0
    var medTime2Hour: Int = 0
    var medTime2Set: Boolean = false


    val inputMedTime3 = MutableLiveData<String>()
    var medTime3Min: Int = 0
    var medTime3Hour: Int = 0
    var medTime3Set: Boolean = false

    var timeString: String = ""


    private val statusMessage = MutableLiveData<Event<String>>()

    val message: LiveData<Event<String>>
        get() = statusMessage


    val medsListString = Transformations.map(meds) { meds ->
        formatMeds(meds, application.resources)
    }


    suspend fun saveMed(
        medName: String,
        medUsername: String,
        medDosis: String,
        medTime1: String,
        medTime2: String,
        medTime3: String
    ) {

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


