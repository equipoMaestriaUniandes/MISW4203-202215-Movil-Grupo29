package com.example.misw4203_202215_movil_grupo29.repositories

import android.app.Application
import com.example.misw4203_202215_movil_grupo29.models.Band
import com.example.misw4203_202215_movil_grupo29.network.NetworkServiceAdapter

class BandRepository (val application: Application){

    suspend fun refreshData(): List<Band> {
       return NetworkServiceAdapter.getInstance(application).getBands()
    }
}