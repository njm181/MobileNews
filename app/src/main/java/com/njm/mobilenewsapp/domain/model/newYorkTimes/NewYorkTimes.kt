package com.njm.mobilenewsapp.domain.model.newYorkTimes

import com.google.gson.annotations.SerializedName

data class NewYorkTimes(
    val results: List<Result>,
    val section: String,
    val status: String
)
