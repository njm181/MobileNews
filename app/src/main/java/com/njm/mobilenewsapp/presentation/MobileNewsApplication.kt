package com.njm.mobilenewsapp.presentation

import android.app.Application
import androidx.activity.viewModels
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.njm.mobilenewsapp.presentation.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MobileNewsApplication: Application(), Configuration.Provider {

    // Allows injecting CoroutineWorkers
    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject lateinit var workerConfiguration: Configuration

    override fun getWorkManagerConfiguration(): Configuration {
        return workerConfiguration
    }

}