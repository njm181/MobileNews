package com.njm.mobilenewsapp.domain.di.network

import com.njm.mobilenewsapp.data.apiManager.AuthInterceptor
import com.njm.mobilenewsapp.data.repositoryImpl.NewYorkTimesRepositoryImpl
import com.njm.mobilenewsapp.data.repositoryImpl.TheGuardianRespositoryImpl
import com.njm.mobilenewsapp.data.service.ApiService
import com.njm.mobilenewsapp.domain.repository.ApiRepository
import com.njm.mobilenewsapp.domain.repository.TheGuardianRepository
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

//@Module
//@InstallIn(ActivityComponent::class)
//abstract class TheGuardianApiRepositoryModule {
//    @Binds
//    abstract fun bindApiRepository(theGuardianRespositoryImpl: TheGuardianRespositoryImpl): ApiRepository<TheGuardianRespositoryImpl>
//}

@Module
@InstallIn(SingletonComponent::class)
object TheGuardianNetworkModule {

    @Singleton
    @Provides
    @Named("theguardian-interceptor")
    fun provideLogginInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    @Singleton
    @Provides
    @Named("theguardian-client")
    fun provideNewsHttpClient(@Named("theguardian-interceptor") interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor("08cda04fa4f34524bace7a7d9db31851"))
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    @Named("theguardian-service")
    fun provideApiTheGuardianService(@Named("theguardian-client") okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://content.guardianapis.com/")
            //Build Config
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

}

@Module
@InstallIn(SingletonComponent::class)
@Named("theguardian-repository")
abstract class TheGuardianApiRepositoryModule {

    @Binds
    abstract fun provideTheGuardianApiRepository(theGuardianRespositoryImpl: TheGuardianRespositoryImpl): TheGuardianRepository
}