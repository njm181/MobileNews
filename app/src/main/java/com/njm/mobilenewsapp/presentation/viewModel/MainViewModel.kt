package com.njm.mobilenewsapp.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.njm.mobilenewsapp.domain.usecase.GetNewYorkTimesUseCase
import com.njm.mobilenewsapp.domain.usecase.GetNewsUseCase
import com.njm.mobilenewsapp.domain.usecase.GetTheGuardianUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTheGuardianUseCase: GetTheGuardianUseCase,
    private val getNewsUseCase: GetNewsUseCase,
    private val getNewYorkTimesUseCase: GetNewYorkTimesUseCase
): ViewModel() {


    fun getNews(){
        viewModelScope.launch(Dispatchers.IO) {
            val result = getNewsUseCase.invoke()
            println("============== $result ================")
        }
    }
}