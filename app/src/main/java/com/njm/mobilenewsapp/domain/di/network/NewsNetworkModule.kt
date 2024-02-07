package com.njm.mobilenewsapp.domain.di.network

import com.njm.mobilenewsapp.BuildConfig
import com.njm.mobilenewsapp.data.apiManager.AuthInterceptor
import com.njm.mobilenewsapp.data.repositoryImpl.NewsRepositoryImpl
import com.njm.mobilenewsapp.data.service.ApiService
import com.njm.mobilenewsapp.domain.repository.ApiRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NewsNetworkModule {


    @Provides
    @Singleton
    @Named("news-interceptor")
    fun provideLogginInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    @Provides
    @Singleton
    @Named("news-client")
    fun provideNewsHttpClient(@Named("news-interceptor") interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(BuildConfig.API_KEY_NEWS))
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    @Named("news-service")
    fun provideApiNewsService(@Named("news-client") okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_NEWS)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class NewsApiRepositoryModule {

    @Binds
    @Named("news-repository")
    abstract fun provideNewsApiRepository(newsRepositoryImpl: NewsRepositoryImpl): ApiRepository
}

