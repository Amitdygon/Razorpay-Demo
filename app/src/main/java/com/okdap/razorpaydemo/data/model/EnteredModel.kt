package com.okdap.razorpaydemo.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EnteredModel(
    var name:String?=null,
    var phone:String?=null,
    var city:String?=null
):Parcelable
