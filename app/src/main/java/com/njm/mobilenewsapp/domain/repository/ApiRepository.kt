package com.njm.mobilenewsapp.domain.repository

interface ApiRepository {
    suspend fun getNews()
}