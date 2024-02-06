package com.njm.mobilenewsapp.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.njm.mobilenewsapp.domain.usecase.UpdateNewsByWorkerUseCase
import com.njm.mobilenewsapp.framework.NewsUpdateWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val workManager: WorkManager,
    private val updatedNewsByWorkerUseCase: UpdateNewsByWorkerUseCase
): ViewModel() {

    private val _newsState = MutableStateFlow<String>("")
    val newsState: StateFlow<String> = _newsState

    private fun getData(){
        viewModelScope.launch(Dispatchers.IO) {
            val result = updatedNewsByWorkerUseCase.getValue()
            println(result)
            _newsState.value = result
        }
    }

    fun startMyWorker() {
        viewModelScope.launch {
              val workRequest = OneTimeWorkRequestBuilder<NewsUpdateWorker>()
                .addTag("My-Worker")
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build()
                )
                .build()
            workManager.enqueueUniqueWork(
                "uniqueWorkName",
                ExistingWorkPolicy.REPLACE,
                workRequest
            )
            //workManager.enqueue(workRequest)

            supervisorScope {
                launch {
                    Log.d("TAG", "WorkRequest - state observation - start")
                    workManager.getWorkInfoByIdLiveData(workRequest.id).asFlow().collect {
                        Log.d("TAG", "WorkRequest - ${it?.state?.name!!}")
                        if (it.state.isFinished) {
                            Log.d("TAG", "WorkRequest - Work finished")
                            getData()
                            cancel() // cancel the supervisorScope, it is now redundant
                        }
                    }
                }
            }.invokeOnCompletion {
                Log.d("TAG", "WorkRequest - state observation - end")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        workManager.cancelUniqueWork("uniqueWorkName")
    }

}