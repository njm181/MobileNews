package com.njm.mobilenewsapp.domain.repository

import com.njm.mobilenewsapp.domain.utils.NetworkResult

interface NewsRepository {
    suspend fun getNews(): NetworkResult<Any>
}