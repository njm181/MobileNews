package com.njm.mobilenewsapp.domain.di.network

import com.njm.mobilenewsapp.data.repositoryImpl.NewYorkTimesRepositoryImpl
import com.njm.mobilenewsapp.data.repositoryImpl.NewsRepositoryImpl
import com.njm.mobilenewsapp.data.service.ApiService
import com.njm.mobilenewsapp.domain.repository.ApiRepository
import com.njm.mobilenewsapp.domain.repository.NewYorkTimesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NewYorkTimesNetworkModule {

    @Provides
    @Singleton
    @Named("newyorktimes-interceptor")
    fun provideLogginInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }
    @Provides
    @Singleton
    @Named("newyorktimes-client")
    fun provideNewYorkTimesHttpClient(@Named("newyorktimes-interceptor") interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    @Named("newyorktimes-service")
    fun provideApiNewYorkTimesService(@Named("newyorktimes-client") okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/topstories/v2/")
            //Build Config
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }


}

@Module
@InstallIn(SingletonComponent::class)
@Named("newyorktimes-repository")
abstract class NewYorkTimesApiRepositoryModule {

    @Binds
    abstract fun provideNewYorkTimesApiRepository(newYorkTimesRepositoryImpl: NewYorkTimesRepositoryImpl): NewYorkTimesRepository
}