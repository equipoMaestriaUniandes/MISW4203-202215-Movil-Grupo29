package com.example.misw4203_202215_movil_grupo29.viewmodels

import android.app.Application
import android.app.SharedElementCallback
import android.util.Log
import androidx.lifecycle.*
import com.example.misw4203_202215_movil_grupo29.models.Band
import com.example.misw4203_202215_movil_grupo29.models.Track
import com.example.misw4203_202215_movil_grupo29.repositories.TrackRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

class TrackViewModel (application: Application) :  AndroidViewModel(application) {

    private val trackRepository = TrackRepository(application)

    private val _tracks = MutableLiveData<List<Track>>()

    private val tracks: LiveData<List<Track>>
        get() = _tracks

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    var trackList:List<Track> = emptyList()

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown


    fun createTrackFromNetwork(track: JSONObject, albumId: Int):Int {
        var id:Int=0
        try {
            viewModelScope.launch(Dispatchers.Default){
                withContext(Dispatchers.IO){
                    var data = trackRepository.createTrack(track,albumId)
                    _tracks.postValue(listOf(data))
                    id=data.trackId
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        }
        catch (e:Exception){
            _eventNetworkError.value = true
        }
            return id
    }

    fun refreshDataFromNetwork(albumId: Int, callback: (List<Track>)->Unit) {
        trackRepository.refreshData(albumId,{
            callback(it)
        },{
            _eventNetworkError.value = true
        })
    }



    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TrackViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return TrackViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}