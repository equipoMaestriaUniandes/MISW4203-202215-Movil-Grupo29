package com.example.misw4203_202215_movil_grupo29.repositories

import android.app.Application
import android.util.Log
import com.example.misw4203_202215_movil_grupo29.models.Comment
import com.example.misw4203_202215_movil_grupo29.network.CacheManager
import com.example.misw4203_202215_movil_grupo29.network.NetworkServiceAdapter

class CommentsRepository (val application: Application){
    suspend fun refreshData(albumId: Int): List<Comment> {
        var potentialResp = CacheManager.getInstance(application.applicationContext).getComments(albumId)
        if(potentialResp.isEmpty()){
            Log.d("Cache decision", "get from network")
            var comments = NetworkServiceAdapter.getInstance(application).getComments(albumId)
            CacheManager.getInstance(application.applicationContext).addComments(albumId, comments)
            return comments
        }
        else{
            Log.d("Cache decision", "return ${potentialResp.size} elements from cache")
            return potentialResp
        }
    }
}