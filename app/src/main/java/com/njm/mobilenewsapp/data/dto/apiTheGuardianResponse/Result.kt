package com.njm.mobilenewsapp.data.dto.apiTheGuardianResponse

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("id")
    val id: String,
    @SerializedName("sectionName")
    val sectionName: String,
    @SerializedName("webTitle")
    val webTitle: String,
    @SerializedName("webUrl")
    val webUrl: String
)