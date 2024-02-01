package com.njm.mobilenewsapp.domain.di.network

import com.njm.mobilenewsapp.data.repositoryImpl.NewYorkTimesRepositoryImpl
import com.njm.mobilenewsapp.data.repositoryImpl.TheGuardianRespositoryImpl
import com.njm.mobilenewsapp.data.service.ApiService
import com.njm.mobilenewsapp.domain.repository.ApiRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//@Module
//@InstallIn(ActivityComponent::class)
//abstract class TheGuardianApiRepositoryModule {
//    @Binds
//    abstract fun bindApiRepository(theGuardianRespositoryImpl: TheGuardianRespositoryImpl): ApiRepository<TheGuardianRespositoryImpl>
//}

@Module
@InstallIn(ActivityComponent::class)
object TheGuardianNetworkModule {

    @Singleton
    @Provides
    fun provideApiTheGuardianService(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://content.guardianapis.com/")
            //Build Config
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesTheGuardianRepository(apiService: ApiService): TheGuardianRespositoryImpl {
        return TheGuardianRespositoryImpl(apiService)
    }

}