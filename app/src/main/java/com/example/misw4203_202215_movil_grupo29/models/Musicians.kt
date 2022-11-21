package com.example.misw4203_202215_movil_grupo29.models

data class Musicians(
    val musicianId:Int,
    val name:String,
    val image:String,
    val description:String,
    val birthDate:String,
    val albums:List<Album> = emptyList()
)
