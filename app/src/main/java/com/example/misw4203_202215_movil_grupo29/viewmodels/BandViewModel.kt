package com.example.misw4203_202215_movil_grupo29.viewmodels


import android.app.Application
import androidx.lifecycle.*
import com.example.misw4203_202215_movil_grupo29.models.Band
import com.example.misw4203_202215_movil_grupo29.repositories.BandRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BandViewModel (application:Application) :  AndroidViewModel(application) {

    private val bandsRepository = BandRepository(application)

    private val _bands = MutableLiveData<List<Band>>()

    val bands: LiveData<List<Band>>
        get() = _bands

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        try {
            viewModelScope.launch (Dispatchers.Default){
                withContext(Dispatchers.IO){
                    var data = bandsRepository.refreshData()
                    _bands.postValue(data)
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        }
        catch (e:Exception){
            _eventNetworkError.value = true
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(BandViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return BandViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
