package com.njm.mobilenewsapp.domain.model.news

import com.google.gson.annotations.SerializedName
import com.njm.mobilenewsapp.domain.model.MobileNewsDomain

data class News(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String
): MobileNewsDomain()
