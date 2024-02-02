package com.njm.mobilenewsapp.data.utils

import com.njm.mobilenewsapp.data.dto.apiNewsResponse.ApiNewsResponse
import com.njm.mobilenewsapp.data.dto.apiNewsResponse.Article
import com.njm.mobilenewsapp.data.dto.apiNewsResponse.Source
import com.njm.mobilenewsapp.domain.model.news.News

fun ApiNewsResponse.toNewsDomain(): News {
    return News(
        articles = articles.map { it.toArticleDomain() },
        status = status
    )
}

fun Article.toArticleDomain(): com.njm.mobilenewsapp.domain.model.news.Article {
    return com.njm.mobilenewsapp.domain.model.news.Article(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = source.toSourceDomain(),
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}

fun Source.toSourceDomain(): com.njm.mobilenewsapp.domain.model.news.Source {
    return com.njm.mobilenewsapp.domain.model.news.Source(
        id = id,
        name = name
    )
}