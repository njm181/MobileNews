package com.njm.mobilenewsapp.data.service

import com.njm.mobilenewsapp.data.dto.apiNewYorkTimesResponse.ApiNewYorkTimesResponse
import com.njm.mobilenewsapp.data.dto.apiNewsResponse.ApiNewsResponse
import com.njm.mobilenewsapp.data.dto.apiTheGuardianResponse.ApiTheGuardianResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET(" ")
    suspend fun getNewsFromApiNews(
        @Query("q") topic: String
    ): Response<ApiNewsResponse>

    @GET("sports.json")
    suspend fun getNewsFromApiNewYorkTimes(@Query("api-key") apiKey: String): Response<ApiNewYorkTimesResponse>

    @GET()
    suspend fun getNewsFromApiTheGuardian(
        //@Header("api-key") apiKey: String,
        @Query("q") topic: String
    ): Response<ApiTheGuardianResponse>

}