package com.njm.mobilenewsapp.domain.model.theGuardian

import com.google.gson.annotations.SerializedName

data class Response(
    val results: List<Result>,
    val status: String
)
