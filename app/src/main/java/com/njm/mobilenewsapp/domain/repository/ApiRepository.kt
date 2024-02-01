package com.njm.mobilenewsapp.domain.repository

import com.njm.mobilenewsapp.domain.utils.NetworkResult

interface ApiRepository<T> {
    suspend fun getNews(): NetworkResult<T>
}