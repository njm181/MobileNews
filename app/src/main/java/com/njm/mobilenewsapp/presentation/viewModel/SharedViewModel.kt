package com.njm.mobilenewsapp.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.njm.mobilenewsapp.domain.model.MobileNewsDomain
import com.njm.mobilenewsapp.domain.model.newYorkTimes.NewYorkTimes
import com.njm.mobilenewsapp.domain.model.news.News
import com.njm.mobilenewsapp.domain.model.theGuardian.TheGuardian
import com.njm.mobilenewsapp.domain.usecase.UpdateNewsByWorkerUseCase
import com.njm.mobilenewsapp.domain.utils.NetworkResult
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

    private val _newState = MutableLiveData<News?>()
    val newsState: LiveData<News?>
        get() = _newState

    private val _newYorkTimesState = MutableLiveData<NewYorkTimes?>()
    val newYorkTimesState: LiveData<NewYorkTimes?>
        get() = _newYorkTimesState

    private val _theGuardianState = MutableLiveData<TheGuardian?>()
    val theGuardianState: LiveData<TheGuardian?>
        get() = _theGuardianState

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean>
        get() = _isRefreshing

    private val _isUpdateDone = MutableLiveData<Boolean>(false)
    val isUpdateDone: LiveData<Boolean>
        get() = _isUpdateDone

    fun resetUpdateDone(value: Boolean){
        _isUpdateDone.value = value
    }

    private fun getData(){
        viewModelScope.launch(Dispatchers.IO) {
            val result = updatedNewsByWorkerUseCase.getValue()
            println(result)
            result.forEachIndexed { index, result ->
                filterNetworkResults(result)
            }
            _isRefreshing.postValue(false)
            _isUpdateDone.postValue(true)
        }
    }

    private fun filterNetworkResults(result: NetworkResult<MobileNewsDomain>) {
        when(result){
            is NetworkResult.Success -> {
                filterDataResult(result.data)
            }
            is NetworkResult.Error -> {

            }
        }
    }

    private fun filterDataResult(data: MobileNewsDomain?) {
        data?.let {
            when(data){
                is News -> {
                    _newState.postValue(data)
                }
                is TheGuardian -> {
                    _theGuardianState.postValue(data)
                }
                is NewYorkTimes -> {
                    _newYorkTimesState.postValue(data)
                }
                else -> {}
            }
        }
    }

    fun startMyWorker() {
        _isRefreshing.value = true
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

            supervisorScope {
                launch {
                    Log.d("TAG", "WorkRequest - state observation - start")
                    workManager.getWorkInfoByIdLiveData(workRequest.id).asFlow().collect {
                        Log.d("TAG", "WorkRequest - ${it?.state?.name!!}")
                        if (it.state.isFinished) {
                            Log.d("TAG", "WorkRequest - Work finished")
                            getData()
                            cancel()
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