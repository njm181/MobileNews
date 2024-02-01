package com.njm.mobilenewsapp.domain.model.newYorkTimes

import com.google.gson.annotations.SerializedName

data class Result(
    val `abstract`: String,
    val createdDate: String,
    val multimedia: List<Multimedia>,
    val publishedDate: String,
    val title: String,
    val updatedDate: String,
    val url: String
)
