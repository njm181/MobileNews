package com.njm.mobilenewsapp.data.repositoryImpl

import com.njm.mobilenewsapp.BuildConfig
import com.njm.mobilenewsapp.data.apiManager.processResponse
import com.njm.mobilenewsapp.data.service.ApiService
import com.njm.mobilenewsapp.domain.model.MobileNewsDomain
import com.njm.mobilenewsapp.domain.repository.ApiRepository
import com.njm.mobilenewsapp.domain.utils.NetworkError
import com.njm.mobilenewsapp.domain.utils.NetworkResult
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Named

class NewYorkTimesRepositoryImpl @Inject constructor(@Named("newyorktimes-service") private val apiService: ApiService): ApiRepository {
    override suspend fun getNews(): NetworkResult<MobileNewsDomain> {
        return try {
            val result = apiService.getNewsFromApiNewYorkTimes(apiKey = BuildConfig.API_KEY_NEW_YORK_TIMES)
            processResponse(result)
        } catch (e: HttpException){
            NetworkResult.Error(message = "Http Exception", networkError = NetworkError.NETWORK_UNAVAILABLE)
        }
    }
}