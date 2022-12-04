package com.example.misw4203_202215_movil_grupo29.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Album (
    val albumId:Int,
    val name:String,
    val cover:String,
    val releaseDate:String,
    val description:String,
    val genre:String,
    val recordLabel:String
):Parcelable
