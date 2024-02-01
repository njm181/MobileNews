package com.njm.mobilenewsapp.domain.model.newYorkTimes

import com.google.gson.annotations.SerializedName

data class Multimedia(
    val caption: String,
    val copyright: String,
    val format: String,
    val height: Int,
    val subtype: String,
    val type: String,
    val url: String,
    val width: Int
)
