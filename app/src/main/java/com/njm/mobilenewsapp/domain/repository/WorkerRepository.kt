package com.njm.mobilenewsapp.domain.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

interface WorkerRepository {
    //val latestNews: Flow<String>
    suspend fun startEmitting(results: String)
    fun returnData(): String
}