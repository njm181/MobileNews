package com.njm.mobilenewsapp.data.repositoryImpl

import com.njm.mobilenewsapp.data.service.ApiService
import com.njm.mobilenewsapp.domain.repository.ApiRepository

class ApiNewYorkTimesRepositoryImpl(private val apiService: ApiService): ApiRepository {
    override suspend fun getNews() {
        apiService.getNewsFromApiNewYorkTimes(apiKey = "EalqiYQ1vykAveT5IPbXUvIqgXnRT4wb")
    }
}