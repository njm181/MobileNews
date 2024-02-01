package com.njm.mobilenewsapp.domain.di.network

import com.njm.mobilenewsapp.data.apiManager.AuthInterceptor
import com.njm.mobilenewsapp.data.repositoryImpl.NewsRepositoryImpl
import com.njm.mobilenewsapp.data.service.ApiService
import com.njm.mobilenewsapp.domain.repository.ApiRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NewsNetworkModule {


    @Singleton
    @Provides
    fun provideLogginInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    @Singleton
    @Provides
    fun provideNewsHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor("08cda04fa4f34524bace7a7d9db31851"))
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiNewsService(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/everything/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

//    @Singleton
//    @Provides
//    fun providesNewsRepository(apiService: ApiService): NewsRepositoryImpl {
//        return NewsRepositoryImpl(apiService)
//    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class NewsApiRepositoryModule {

    @Binds
    internal abstract fun provideApiRepository(newsRepositoryImpl: NewsRepositoryImpl): ApiRepository<Any>
}

