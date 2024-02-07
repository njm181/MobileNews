package com.njm.mobilenewsapp.domain.usecase

import com.njm.mobilenewsapp.domain.model.MobileNewsDomain
import com.njm.mobilenewsapp.domain.repository.WorkerRepository
import com.njm.mobilenewsapp.domain.utils.NetworkResult
import javax.inject.Inject
import javax.inject.Named

class UpdateNewsByWorkerUseCase @Inject constructor(
    @Named("worker-repository") private val repository: WorkerRepository
) {
    suspend operator fun invoke(results: List<NetworkResult<MobileNewsDomain>>){
            repository.startEmitting(results = results)
    }

    fun getValue(): List<NetworkResult<MobileNewsDomain>> = repository.returnData()
}