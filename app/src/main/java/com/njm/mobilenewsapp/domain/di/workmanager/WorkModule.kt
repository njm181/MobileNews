package com.njm.mobilenewsapp.domain.di.workmanager

import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.WorkManager
import com.njm.mobilenewsapp.data.repositoryImpl.WorkerRepositoryImpl
import com.njm.mobilenewsapp.domain.repository.WorkerRepository
import com.njm.mobilenewsapp.presentation.viewModel.SharedViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object WorkModule {

    @Provides
    @Singleton
    internal fun providesWorkManager(
        @ApplicationContext context: Context
    ): WorkManager = WorkManager.getInstance(context)

    @Singleton
    @Provides
    fun provideWorkManagerConfiguration(
        workerFactory: HiltWorkerFactory
    ): Configuration {
        return Configuration.Builder().apply {
            setWorkerFactory(workerFactory)
        }.build()
    }
}


@Module
@InstallIn(SingletonComponent::class)
abstract class WorkerApiRepositoryModule {

    @Binds
    @Named("worker-repository")
    abstract fun provideWorkerApiRepository(workerRepositoryImpl: WorkerRepositoryImpl): WorkerRepository
}