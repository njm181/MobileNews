package com.njm.mobilenewsapp.data.repositoryImpl

import com.njm.mobilenewsapp.data.apiManager.processResponse
import com.njm.mobilenewsapp.data.service.ApiService
import com.njm.mobilenewsapp.domain.repository.ApiRepository
import com.njm.mobilenewsapp.domain.utils.NetworkError
import com.njm.mobilenewsapp.domain.utils.NetworkResult
import retrofit2.HttpException
import javax.inject.Inject

class TheGuardianRespositoryImpl @Inject constructor(private val apiService: ApiService): ApiRepository<Any> {
    override suspend fun getNews(): NetworkResult<Any> {
        return try {
            val result = apiService.getNewsFromApiTheGuardian(topic = "economy", apiKey = "9b7bec85-cd8c-4543-a31d-6aa226d1eda7")
            processResponse(result)
        } catch (e: HttpException){
            NetworkResult.Error(message = "Http Exception", networkError = NetworkError.NETWORK_UNAVAILABLE)
        }
    }
}