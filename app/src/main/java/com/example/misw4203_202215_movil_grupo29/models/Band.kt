package com.example.misw4203_202215_movil_grupo29.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Band (
    val BandId:Int,
    val name:String,
    val image:String,
    val description:String,
    val creationDate:String,
    val albums:List<Album> = emptyList(),
    val musicians:List<Musicians> = emptyList()
): Parcelable