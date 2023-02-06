package com.okdap.razorpaydemo.data.model

import com.google.gson.annotations.SerializedName

data class AssigmentModel(
    @SerializedName("heading-text")
    val headingText: String? = null,
    @SerializedName("logo-url")
    val logoUrl: String? = null,
    val uidata: List<Uidata?>? = null
)