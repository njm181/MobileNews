package com.njm.mobilenewsapp.domain.di.usecase

import com.njm.mobilenewsapp.data.repositoryImpl.NewsRepositoryImpl
import com.njm.mobilenewsapp.domain.repository.ApiRepository
import com.njm.mobilenewsapp.domain.repository.NewYorkTimesRepository
import com.njm.mobilenewsapp.domain.repository.NewsRepository
import com.njm.mobilenewsapp.domain.repository.TheGuardianRepository
import com.njm.mobilenewsapp.domain.usecase.GetNewYorkTimesUseCase
import com.njm.mobilenewsapp.domain.usecase.GetNewsUseCase
import com.njm.mobilenewsapp.domain.usecase.GetTheGuardianUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetNewsUseCase(repository: NewsRepository): GetNewsUseCase {
        return GetNewsUseCase(repository = repository)
    }

    @Singleton
    @Provides
    fun provideGetNewYorkTimesUseCase(repository: NewYorkTimesRepository): GetNewYorkTimesUseCase {
        return GetNewYorkTimesUseCase(repository = repository)
    }

    @Singleton
    @Provides
    fun provideGetTheGuardianUseCase(repository: TheGuardianRepository): GetTheGuardianUseCase {
        return GetTheGuardianUseCase(repository = repository)
    }
}