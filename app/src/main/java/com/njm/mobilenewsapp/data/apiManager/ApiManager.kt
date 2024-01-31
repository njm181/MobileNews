package com.njm.mobilenewsapp.data.apiManager

import com.njm.mobilenewsapp.data.service.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {

    private val BASE_URL_API_NEWS = "https://newsapi.org/v2/everything/"
    private val BASE_URL_API_NEW_YORK_TIMES = "https://api.nytimes.com/svc/topstories/v2/"
    private val BASE_URL_API_THE_GUARDIAN = "https://content.guardianapis.com/"

    private fun provideLogginInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    private fun provideHttpClient(interceptor: HttpLoggingInterceptor, value: String = ""): OkHttpClient {
        return if (value.isNullOrEmpty()){
            OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
        } else {
            OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor(value))
                .addInterceptor(interceptor)
                .build()
        }
    }

    fun createApiNewsService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_API_NEWS)
            //Build Config
            .client(provideHttpClient(provideLogginInterceptor(), value = "08cda04fa4f34524bace7a7d9db31851"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    fun createApiNewYorkTimesService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_API_NEW_YORK_TIMES)
            .client(provideHttpClient(provideLogginInterceptor()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    fun createApiTheGuardianService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_API_THE_GUARDIAN)
            .client(provideHttpClient(provideLogginInterceptor(), value = "9b7bec85-cd8c-4543-a31d-6aa226d1eda7"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

internal class AuthInterceptor(private val value: String): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val newRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $value")
            .build()

        return chain.proceed(newRequest)
    }

}