package com.njm.mobilenewsapp.domain.usecase

import com.njm.mobilenewsapp.domain.repository.WorkerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named

class UpdateNewsByWorkerUseCase @Inject constructor(
    @Named("worker-repository") private val repository: WorkerRepository
) {
    suspend operator fun invoke(results: String){
            repository.startEmitting(results = results)
    }

    fun getValue(): String = repository.returnData()
}