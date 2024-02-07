package com.njm.mobilenewsapp.domain.repository

import com.njm.mobilenewsapp.domain.model.MobileNewsDomain
import com.njm.mobilenewsapp.domain.utils.NetworkResult

interface ApiRepository {
    suspend fun getNews(): NetworkResult<MobileNewsDomain>
}