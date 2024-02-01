package com.njm.mobilenewsapp.data.repositoryImpl

import com.njm.mobilenewsapp.data.service.ApiService
import com.njm.mobilenewsapp.domain.repository.ApiRepository

class ApiTheGuardianRespositoryImpl(private val apiService: ApiService): ApiRepository {
    override suspend fun getNews() {
        val resp = apiService.getNewsFromApiTheGuardian(topic = "economy", apiKey = "9b7bec85-cd8c-4543-a31d-6aa226d1eda7")
        println("======================================= ${resp.body()} ==========================================")
    }
}