package com.example.misw4203_202215_movil_grupo29.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Musicians(
    val musicianId:Int,
    val name:String,
    val image:String,
    val description:String,
    val birthDate:String,
    val albums:List<Album> = emptyList()
): Parcelable
