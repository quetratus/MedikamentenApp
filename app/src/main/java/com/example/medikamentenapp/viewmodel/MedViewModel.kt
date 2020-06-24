package com.example.medikamentenapp.viewmodel

class MedViewModel
/*
IN BEARBEITUNG
class MedViewModel(private val repository: MedicamentRepository) : ViewModel(), Observable {


// View Model observes live data

    val meds = repository.meds

    @Bindable
    val inputMedName = MutableLiveData<String>()

    @Bindable
    val inputMedDosis = MutableLiveData<String>()

    @Bindable
    val inputMedTime1 = MutableLiveData<String>()

    @Bindable
    val inputMedTime2 = MutableLiveData<String>()

    @Bindable
    val inputMedTime3 = MutableLiveData<String>()

    @Bindable
    val saveMedButton = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()

    val message : LiveData<Event<String>>
        get() = statusMessage

    init {
        saveMedButton.value = "SPEICHERN"
    }

    fun saveMed(){
        if(inputMedName.value==null){
            statusMessage.value = Event("Bitte den Namen eingeben")
        }
        else if(inputMedDosis.value==null) {
            statusMessage.value = Event("Bitte die Dosis eingeben")
        }
            else if(inputMedTime1.value==null) {
                statusMessage.value = Event("Bitte die gewünschte Uhrzeit eingeben")
            }
        }

    val medName = inputMedName.value!!
    val medDosis = inputMedDosis.value!!
    val medTime1 = inputMedTime1.value!!
    val medTime2 = inputMedTime2.value
    val medTime3 = inputMedTime3.value


    insert(Medicament(0, medName, medDosis, medTime1, medTime2, medTime3 ))
        inputMedName.value = null
        inputMedDosis.value = null

    }

    fun insert(med: Medicament) = viewModelScope.launch {
        val newRowId: Long = repository.insert(med)
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

*/