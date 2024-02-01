package com.njm.mobilenewsapp.domain.di.network

import com.njm.mobilenewsapp.data.repositoryImpl.NewYorkTimesRepositoryImpl
import com.njm.mobilenewsapp.data.repositoryImpl.NewsRepositoryImpl
import com.njm.mobilenewsapp.data.service.ApiService
import com.njm.mobilenewsapp.domain.repository.ApiRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ActivityComponent::class)
object NewYorkTimesNetworkModule {

    @Singleton
    @Provides
    fun provideNewYorkTimesHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiNewYorkTimesService(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/topstories/v2/")
            //Build Config
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesNewYorkTimesRepository(apiService: ApiService): NewYorkTimesRepositoryImpl {
        return NewYorkTimesRepositoryImpl(apiService)
    }

//    @Binds
//    fun bindApiRepository(newYorkTimesRepositoryImpl: NewYorkTimesRepositoryImpl): ApiRepository<NewYorkTimesRepositoryImpl>
}

//@Module
//@InstallIn(ActivityComponent::class)
//abstract class NewYorkTimesApiRepositoryModule {
//
//    @Binds
//    abstract fun bindApiRepository(newYorkTimesRepositoryImpl: NewYorkTimesRepositoryImpl): ApiRepository<NewYorkTimesRepositoryImpl>
//}