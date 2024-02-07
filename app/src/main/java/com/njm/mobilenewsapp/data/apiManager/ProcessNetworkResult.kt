package com.njm.mobilenewsapp.data.apiManager

import com.njm.mobilenewsapp.data.dto.apiNewYorkTimesResponse.ApiNewYorkTimesResponse
import com.njm.mobilenewsapp.data.dto.apiNewsResponse.ApiNewsResponse
import com.njm.mobilenewsapp.data.dto.apiTheGuardianResponse.ApiTheGuardianResponse
import com.njm.mobilenewsapp.data.utils.toNewYorkTimesDomain
import com.njm.mobilenewsapp.data.utils.toNewsDomain
import com.njm.mobilenewsapp.data.utils.toTheGuardianDomain
import com.njm.mobilenewsapp.domain.model.MobileNewsDomain
import com.njm.mobilenewsapp.domain.model.news.News
import com.njm.mobilenewsapp.domain.utils.NetworkError
import com.njm.mobilenewsapp.domain.utils.NetworkResult
import retrofit2.HttpException
import retrofit2.Response

fun <T : Any> processResponse(data: Response<T>): NetworkResult<MobileNewsDomain>{
    return try {
        if (data.isSuccessful){
            val dataMapped = mapperData(data.body())
            NetworkResult.Success(data = dataMapped as MobileNewsDomain)
        } else {
            NetworkResult.Error(
                message = "Service response was not successful",
                networkError = NetworkError.RESPONSE_IS_NOT_SUCCESSFUL)
        }
    } catch (e: HttpException){
        NetworkResult.Error(message = "Http Exception", networkError = NetworkError.NETWORK_UNAVAILABLE)
    }
}

fun <T> mapperData(body: T?): MobileNewsDomain? {
    return when(body){
        is ApiNewsResponse -> {
            body.toNewsDomain()
        }
        is ApiNewYorkTimesResponse -> {
           body.toNewYorkTimesDomain()
        }
        is ApiTheGuardianResponse -> {
            body.toTheGuardianDomain()
        }

        else -> { null }
    }
}
