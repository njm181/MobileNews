package com.njm.mobilenewsapp.domain.usecase

import com.njm.mobilenewsapp.domain.repository.ApiRepository
import com.njm.mobilenewsapp.domain.repository.NewsRepository
import com.njm.mobilenewsapp.domain.utils.NetworkResult
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val repository: NewsRepository) {
    suspend operator fun invoke(): NetworkResult<Any> {
        return repository.getNews()
    }
}