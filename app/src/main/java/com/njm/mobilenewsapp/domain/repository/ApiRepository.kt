package com.njm.mobilenewsapp.domain.repository

import com.njm.mobilenewsapp.domain.utils.NetworkResult

interface ApiRepository {
    suspend fun getNews(): NetworkResult<Any>
}