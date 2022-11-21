package com.example.misw4203_202215_movil_grupo29.repositories


import android.app.Application
import com.android.volley.VolleyError
import com.example.misw4203_202215_movil_grupo29.models.Band
import com.example.misw4203_202215_movil_grupo29.network.NetworkServiceAdapter

class BandRepository (val application: Application){

    fun refreshData(callback: (List<Band>)->Unit, onError: (VolleyError)->Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        NetworkServiceAdapter.getInstance(application).getBands({
            //Guardar los albumes de la variable it en un almacén de datos local para uso futuro
            callback(it)
        },
            onError
        )
    }
}