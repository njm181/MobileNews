package com.njm.mobilenewsapp.data.dto.apiNewsResponse

import com.google.gson.annotations.SerializedName

data class ApiNewsResponse(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String
)