package com.njm.mobilenewsapp.data.dto.apiTheGuardianResponse

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("status")
    val status: String
)