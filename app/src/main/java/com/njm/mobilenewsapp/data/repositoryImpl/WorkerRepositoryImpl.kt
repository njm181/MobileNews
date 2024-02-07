package com.njm.mobilenewsapp.data.repositoryImpl

import com.njm.mobilenewsapp.domain.repository.WorkerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WorkerRepositoryImpl @Inject constructor(): WorkerRepository {

    companion object {
        var myData = ""
    }

    override suspend fun startEmitting(results: String) {
        myData = results
        println(myData)
    }

    override fun returnData(): String {
        return myData
    }


}