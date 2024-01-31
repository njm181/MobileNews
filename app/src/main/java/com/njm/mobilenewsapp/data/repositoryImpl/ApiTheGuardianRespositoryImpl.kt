package com.njm.mobilenewsapp.data.repositoryImpl

import com.njm.mobilenewsapp.data.service.ApiService
import com.njm.mobilenewsapp.domain.repository.ApiRepository

class ApiTheGuardianRespositoryImpl(private val apiService: ApiService): ApiRepository {
    override suspend fun getNews() {
        apiService.getNewsFromApiTheGuardian(topic = "economy")
    }
}