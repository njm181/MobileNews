package com.njm.mobilenewsapp.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.njm.mobilenewsapp.domain.model.MobileNewsDomain
import com.njm.mobilenewsapp.domain.model.newYorkTimes.NewYorkTimes
import com.njm.mobilenewsapp.domain.model.news.News
import com.njm.mobilenewsapp.domain.model.theGuardian.TheGuardian
import com.njm.mobilenewsapp.domain.usecase.GetNewYorkTimesUseCase
import com.njm.mobilenewsapp.domain.usecase.GetNewsUseCase
import com.njm.mobilenewsapp.domain.usecase.GetTheGuardianUseCase
import com.njm.mobilenewsapp.domain.utils.NetworkResult
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
    private val getNewYorkTimesUseCase: GetNewYorkTimesUseCase
): ViewModel() {

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean>
        get() = _loadingState

    private val _newState = MutableLiveData<News?>()
    val newsState: LiveData<News?>
        get() = _newState

    private val _newYorkTimesState = MutableLiveData<NewYorkTimes?>()
    val newYorkTimesState: LiveData<NewYorkTimes?>
        get() = _newYorkTimesState

    private val _theGuardianState = MutableLiveData<TheGuardian?>()
    val theGuardianState: LiveData<TheGuardian?>
        get() = _theGuardianState

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
                filterNetworkResults(result)
            }
            _loadingState.postValue(false)
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

    fun updateNews(
        newsUpdatedState: News?,
        newYorkTimesUpdatedState: NewYorkTimes?,
        theGuardianUpdatedState: TheGuardian?
    ) {
        _loadingState.value = true
        viewModelScope.launch {
            _newState.value = newsUpdatedState
            _newYorkTimesState.value = newYorkTimesUpdatedState
            _theGuardianState.value = theGuardianUpdatedState
            _loadingState.value = false
        }
    }

}