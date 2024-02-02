package com.njm.mobilenewsapp.domain.usecase

import com.njm.mobilenewsapp.domain.repository.ApiRepository
import com.njm.mobilenewsapp.domain.utils.NetworkResult
import javax.inject.Inject
import javax.inject.Named

class GetNewYorkTimesUseCase @Inject constructor(@Named("newyorktimes-repository") private val repository: ApiRepository) {
    suspend operator fun invoke(): NetworkResult<Any> {
        return repository.getNews()
    }
}