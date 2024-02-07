package com.njm.mobilenewsapp.framework

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.njm.mobilenewsapp.domain.usecase.GetNewYorkTimesUseCase
import com.njm.mobilenewsapp.domain.usecase.GetNewsUseCase
import com.njm.mobilenewsapp.domain.usecase.GetTheGuardianUseCase
import com.njm.mobilenewsapp.domain.usecase.UpdateNewsByWorkerUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

@HiltWorker
class NewsUpdateWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val getTheGuardianUseCase: GetTheGuardianUseCase,
    private val getNewsUseCase: GetNewsUseCase,
    private val getNewYorkTimesUseCase: GetNewYorkTimesUseCase,
    private val updateNewsByWorkerUseCase: UpdateNewsByWorkerUseCase
    //call use case to save data into room
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        return try {
            updateNews()
            Result.success()
        } catch (e: Exception){
            Result.failure()
        }
    }

    private suspend fun updateNews(){
        withContext(Dispatchers.IO){
            try {
                val deferredResults = listOf(
                    async { getNewsUseCase.invoke() },
                    async { getNewYorkTimesUseCase.invoke() },
                    async { getTheGuardianUseCase.invoke() },
                )

                val results = awaitAll(*deferredResults.toTypedArray())
                println(results)
                updateNewsByWorkerUseCase.invoke(results = results)
                NotificationHelper.showNotification(
                    applicationContext,
                    "Tarea completada",
                    "La tarea ha finalizado exitosamente"
                )
//                results.forEachIndexed { index, result ->
//                    println("${index}. Received data from ${result.data}")
//                }

                //withContext(Dispatchers.Main){
                    //sharedViewModel.setDataModel(dataModel = "Resultsssss")
                   // sharedViewModel.setDataModel("Resultsssss")
               // }

            }catch (e: Exception) {

            }
        }
    }
}