package com.njm.mobilenewsapp.domain.model.newYorkTimes

import com.google.gson.annotations.SerializedName
import com.njm.mobilenewsapp.domain.model.MobileNewsDomain

data class NewYorkTimes(
    val results: List<Result>,
    val section: String,
    val status: String
): MobileNewsDomain()
