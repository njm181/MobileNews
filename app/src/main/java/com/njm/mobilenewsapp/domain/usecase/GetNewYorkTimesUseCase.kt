package com.njm.mobilenewsapp.domain.usecase

import com.njm.mobilenewsapp.domain.repository.ApiRepository
import com.njm.mobilenewsapp.domain.repository.NewYorkTimesRepository
import com.njm.mobilenewsapp.domain.utils.NetworkResult
import javax.inject.Inject

class GetNewYorkTimesUseCase @Inject constructor(private val repository: NewYorkTimesRepository) {
    suspend operator fun invoke(): NetworkResult<Any> {
        return repository.getNews()
    }
}