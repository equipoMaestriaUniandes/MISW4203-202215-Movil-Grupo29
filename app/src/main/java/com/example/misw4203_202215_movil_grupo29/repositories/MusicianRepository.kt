package com.example.misw4203_202215_movil_grupo29.repositories

import android.app.Application
import com.example.misw4203_202215_movil_grupo29.models.Musicians
import com.example.misw4203_202215_movil_grupo29.network.NetworkServiceAdapter

class MusicianRepository (val application: Application){

    suspend fun refreshData(): List<Musicians> {
        return NetworkServiceAdapter.getInstance(application).getMusicians()
    }
}