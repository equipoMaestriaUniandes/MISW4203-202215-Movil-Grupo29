package com.example.misw4203_202215_movil_grupo29.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.misw4203_202215_movil_grupo29.models.Track
import com.example.misw4203_202215_movil_grupo29.network.NetworkServiceAdapter
import org.json.JSONObject

class TrackRepository(val application: Application) {
    fun refreshData(albumID : Int, callback: (List<Track>)-> Unit, onError: (VolleyError)->Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        NetworkServiceAdapter.getInstance(application).getTracks(albumID,{
            //Guardar los albumes de la variable it en un almacén de datos local para uso futuro
            callback(it)
        },
            onError
        )
    }

    suspend fun createTrack(album: JSONObject, albumId: Int):Track{
        return NetworkServiceAdapter.getInstance(application).createTrack(album, albumId)
    }
}