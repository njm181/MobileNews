package com.njm.mobilenewsapp.data.apiManager

import com.njm.mobilenewsapp.domain.utils.NetworkError
import com.njm.mobilenewsapp.domain.utils.NetworkResult
import retrofit2.HttpException
import retrofit2.Response

fun <T : Any> processResponse(data: Response<T>): NetworkResult<Any>{
    return try {
        if (data.isSuccessful){
            NetworkResult.Success(data = data.body() as Any)
        } else {
            NetworkResult.Error(
                message = "Service response was not successful",
                networkError = NetworkError.RESPONSE_IS_NOT_SUCCESSFUL)
        }
    } catch (e: HttpException){
        NetworkResult.Error(message = "Http Exception", networkError = NetworkError.NETWORK_UNAVAILABLE)
    }
}