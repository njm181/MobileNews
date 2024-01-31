package com.njm.mobilenewsapp.data.dto.apiNewYorkTimesResponse

import com.google.gson.annotations.SerializedName

data class ApiNewYorkTimesResponse(
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("last_updated")
    val lasUpdated: String,
    @SerializedName("num_results")
    val numResults: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("section")
    val section: String,
    @SerializedName("status")
    val status: String
)