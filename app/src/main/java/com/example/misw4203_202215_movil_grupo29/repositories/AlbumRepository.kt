package com.example.misw4203_202215_movil_grupo29.repositories

import android.app.Application
import com.example.misw4203_202215_movil_grupo29.models.Album
import com.example.misw4203_202215_movil_grupo29.network.NetworkServiceAdapter
import org.json.JSONObject

class AlbumRepository (val application: Application){
    suspend fun refreshData(): List<Album> {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        return NetworkServiceAdapter.getInstance(application).getAlbums()
    }

    suspend fun createData(data: JSONObject):Album{
        return NetworkServiceAdapter.getInstance(application).createAlbum(data)
    }
}