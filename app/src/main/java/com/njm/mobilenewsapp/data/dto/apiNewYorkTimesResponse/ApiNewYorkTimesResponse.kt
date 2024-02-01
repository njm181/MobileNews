package com.njm.mobilenewsapp.data.dto.apiNewYorkTimesResponse

import com.google.gson.annotations.SerializedName

data class ApiNewYorkTimesResponse(
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("section")
    val section: String,
    @SerializedName("status")
    val status: String
)