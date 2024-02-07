package com.njm.mobilenewsapp.domain.usecase

import com.njm.mobilenewsapp.domain.model.MobileNewsDomain
import com.njm.mobilenewsapp.domain.repository.ApiRepository
import com.njm.mobilenewsapp.domain.utils.NetworkResult
import javax.inject.Inject
import javax.inject.Named

class GetNewsUseCase @Inject constructor(@Named("news-repository") private val repository: ApiRepository) {
    suspend operator fun invoke(): NetworkResult<MobileNewsDomain> {
        return repository.getNews()
    }
}