package com.njm.mobilenewsapp.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.njm.mobilenewsapp.domain.usecase.GetNewYorkTimesUseCase
import com.njm.mobilenewsapp.domain.usecase.GetNewsUseCase
import com.njm.mobilenewsapp.domain.usecase.GetTheGuardianUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTheGuardianUseCase: GetTheGuardianUseCase,
    private val getNewsUseCase: GetNewsUseCase,
    private val getNewYorkTimesUseCase: GetNewYorkTimesUseCase,
   //private val workManager: WorkManager
): ViewModel() {
//    private val context = getApplication<Application>().applicationContext
//    private val workManager = WorkManager.getInstance(context)

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean>
        get() = _loadingState


//    fun activatePeriodicWorker() {
//        val workRequest = PeriodicWorkRequestBuilder<NewsUpdateWorker>(
//            repeatInterval = 1, // Intervalo de repetición en minutos
//            repeatIntervalTimeUnit = TimeUnit.MINUTES
//        ).build()
//
//        workManager.enqueue(workRequest)
//    }

    fun updateLoadingState(isLoading: Boolean) {
        _loadingState.postValue(isLoading)
    }

    fun getNews(){
        _loadingState.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val deferredResults = listOf(
                async { getNewsUseCase.invoke() },
                async { getNewYorkTimesUseCase.invoke() },
                async { getTheGuardianUseCase.invoke() },
            )

            val results = awaitAll(*deferredResults.toTypedArray())
            results.forEachIndexed { index, result ->
                println("${index}. Received data from ${result.data}")
            }
            _loadingState.postValue(false)
        }
    }

}