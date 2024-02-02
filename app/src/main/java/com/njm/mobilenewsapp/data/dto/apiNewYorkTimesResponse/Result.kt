package com.njm.mobilenewsapp.data.dto.apiNewYorkTimesResponse

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("abstract")
    val `abstract`: String,
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("multimedia")
    val multimedia: List<Multimedia>,
    @SerializedName("published_date")
    val publishedDate: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("updated_date")
    val updatedDate: String,
    @SerializedName("url")
    val url: String
)