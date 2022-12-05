package com.example.misw4203_202215_movil_grupo29.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Collector (
    val collectorId: Int,
    val name:String,
    val telephone:String,
    val email:String
): Parcelable
