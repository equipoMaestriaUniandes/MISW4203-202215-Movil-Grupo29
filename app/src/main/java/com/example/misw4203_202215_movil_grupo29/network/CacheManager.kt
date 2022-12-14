package com.example.misw4203_202215_movil_grupo29.network

import android.content.Context
import com.example.misw4203_202215_movil_grupo29.models.Comment
import com.example.misw4203_202215_movil_grupo29.models.Track

class CacheManager(context: Context) {
    companion object{
        var instance: CacheManager? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: CacheManager(context).also {
                    instance = it
                }
            }
    }
    private var comments: HashMap<Int, List<Comment>> = hashMapOf<Int, List<Comment>>()
    //private var comments: ArrayMap<Int, List<Comment>> = arrayMapOf<Int, List<Comment>>()
    fun addComments(albumId: Int, comment: List<Comment>){
        if (comments.containsKey(albumId)){
            comments[albumId] = comment
        }
    }
    fun getComments(albumId: Int) : List<Comment>{
        return if (comments.containsKey(albumId)) comments[albumId]!! else listOf<Comment>()
    }

    private var tracks: HashMap<Int, List<Track>> = hashMapOf<Int, List<Track>>()
    //private var comments: ArrayMap<Int, List<Comment>> = arrayMapOf<Int, List<Comment>>()
    fun addTracks(albumId: Int, track: List<Track>){
        if (tracks.containsKey(albumId)){
            tracks[albumId] = track
        }
    }
    fun getTracks(albumId: Int) : List<Track>{
        return if (tracks.containsKey(albumId)) tracks[albumId]!! else listOf<Track>()
    }
}