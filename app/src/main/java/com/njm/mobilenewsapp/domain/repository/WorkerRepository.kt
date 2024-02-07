package com.njm.mobilenewsapp.domain.repository

import com.njm.mobilenewsapp.domain.model.MobileNewsDomain
import com.njm.mobilenewsapp.domain.utils.NetworkResult

interface WorkerRepository {
    //val latestNews: Flow<String>
    suspend fun startEmitting(results: List<NetworkResult<MobileNewsDomain>>)
    fun returnData(): List<NetworkResult<MobileNewsDomain>>
}