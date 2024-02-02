package com.njm.mobilenewsapp.domain.di.network

import com.njm.mobilenewsapp.data.apiManager.AuthInterceptor
import com.njm.mobilenewsapp.data.repositoryImpl.NewsRepositoryImpl
import com.njm.mobilenewsapp.data.service.ApiService
import com.njm.mobilenewsapp.domain.repository.ApiRepository
import com.njm.mobilenewsapp.domain.repository.NewsRepository
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
            .addInterceptor(AuthInterceptor("08cda04fa4f34524bace7a7d9db31851"))
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    @Named("news-service")
    fun provideApiNewsService(@Named("news-client") okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/everything/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
@Named("news-repository")
abstract class NewsApiRepositoryModule {

    @Binds
    abstract fun provideNewsApiRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository
}

