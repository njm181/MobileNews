package com.njm.mobilenewsapp.domain.utils

sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null,
    val networkError: NetworkError? = null
) {
    class Success<T>(data: T): NetworkResult<T>(data)
    class Error<T>(message: String?, data: T? = null, networkError: NetworkError?) : NetworkResult<T>(data, message, networkError)
}
