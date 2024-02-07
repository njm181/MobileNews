package com.njm.mobilenewsapp.data.utils

import com.njm.mobilenewsapp.data.dto.apiTheGuardianResponse.ApiTheGuardianResponse
import com.njm.mobilenewsapp.data.dto.apiTheGuardianResponse.Result
import com.njm.mobilenewsapp.domain.model.theGuardian.Response
import com.njm.mobilenewsapp.domain.model.theGuardian.TheGuardian

fun ApiTheGuardianResponse.toTheGuardianDomain(): TheGuardian {
    return TheGuardian(response = response.toGuardianDomain())
}

fun com.njm.mobilenewsapp.data.dto.apiTheGuardianResponse.Response.toGuardianDomain(): Response {
    return Response(results = results.map { it.toResultDomain() }.take(5), status = status)
}

fun Result.toResultDomain(): com.njm.mobilenewsapp.domain.model.theGuardian.Result {
    return com.njm.mobilenewsapp.domain.model.theGuardian.Result(
        id = id,
        sectionName = sectionName,
        webTitle = webTitle,
        webUrl = webUrl
    )
}