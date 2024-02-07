package com.njm.mobilenewsapp.data.repositoryImpl

import com.njm.mobilenewsapp.domain.model.MobileNewsDomain
import com.njm.mobilenewsapp.domain.repository.WorkerRepository
import com.njm.mobilenewsapp.domain.utils.NetworkResult
import javax.inject.Inject

class WorkerRepositoryImpl @Inject constructor(): WorkerRepository {

    companion object {
        var myData: List<NetworkResult<MobileNewsDomain>> = listOf()
    }

    override suspend fun startEmitting(results: List<NetworkResult<MobileNewsDomain>>) {
        myData = results
        println(myData)
    }

    override fun returnData(): List<NetworkResult<MobileNewsDomain>> {
        return myData
    }


}