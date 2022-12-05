package com.example.misw4203_202215_movil_grupo29.repositories

import android.app.Application
import com.example.misw4203_202215_movil_grupo29.models.Collector
import com.example.misw4203_202215_movil_grupo29.network.NetworkServiceAdapter

class CollectorRepository  (val application: Application){
    suspend fun refreshData(): List<Collector> {
        return NetworkServiceAdapter.getInstance(application).getCollectors()
    }
}