package com.njm.mobilenewsapp.domain.model.theGuardian

import com.google.gson.annotations.SerializedName

data class Result(
    val id: String,
    val sectionName: String,
    val webTitle: String,
    val webUrl: String
)
