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

    //private var _updatedNewsFlow = MutableStateFlow<String>("")
    //private var getData = ""

    override suspend fun startEmitting(results: String) {
        myData = results
        println(myData)
//        _updatedNewsFlow.emit(results)
//        _updatedNewsFlow.value = results
//        println(_updatedNewsFlow.value)
        //println(updatedNewsFlow)
    }

    override fun returnData(): String {
        //return getData
        return myData
    }

//    override val latestNews: Flow<String> = flow {
//        while(true) {
//            //val latestNews = newsApi.fetchLatestNews()
//            emit("_updatedNewsFlow.value") // Emits the result of the request to the flow
//            //delay(refreshIntervalMs) // Suspends the coroutine for some time
//        }
//    }

}