package com.njm.mobilenewsapp.data.utils

import com.njm.mobilenewsapp.data.dto.apiNewYorkTimesResponse.ApiNewYorkTimesResponse
import com.njm.mobilenewsapp.data.dto.apiNewYorkTimesResponse.Multimedia
import com.njm.mobilenewsapp.data.dto.apiNewYorkTimesResponse.Result
import com.njm.mobilenewsapp.domain.model.newYorkTimes.NewYorkTimes

fun ApiNewYorkTimesResponse.toNewYorkTimesDomain(): NewYorkTimes {
    return NewYorkTimes(results = results.map { it.toResultDomain() }.take(5), section = section, status = status)
}

fun Result.toResultDomain(): com.njm.mobilenewsapp.domain.model.newYorkTimes.Result {
    return com.njm.mobilenewsapp.domain.model.newYorkTimes.Result(
        abstract = abstract,
        createdDate = createdDate,
        multimedia = multimedia.map { it.toMultimediaDomain() },
        publishedDate = publishedDate,
        title = title,
        updatedDate = updatedDate,
        url = url
    )
}

fun Multimedia.toMultimediaDomain(): com.njm.mobilenewsapp.domain.model.newYorkTimes.Multimedia {
    return com.njm.mobilenewsapp.domain.model.newYorkTimes.Multimedia(
        caption = caption,
        copyright = copyright,
        format = format,
        height = height,
        subtype = subtype,
        type = type,
        url = url,
        width = width
    )
}