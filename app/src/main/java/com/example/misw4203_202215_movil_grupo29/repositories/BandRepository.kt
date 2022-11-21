package com.example.misw4203_202215_movil_grupo29.repositories


import android.app.Application
import com.android.volley.VolleyError
import com.example.misw4203_202215_movil_grupo29.models.Album
import com.example.misw4203_202215_movil_grupo29.models.Band
import com.example.misw4203_202215_movil_grupo29.network.NetworkServiceAdapter

class BandRepository (val application: Application){

    suspend fun refreshData(): List<Band> {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        return NetworkServiceAdapter.getInstance(application).getBands()
    }
}