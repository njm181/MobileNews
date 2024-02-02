package com.njm.mobilenewsapp.domain.model.news

import com.google.gson.annotations.SerializedName

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)