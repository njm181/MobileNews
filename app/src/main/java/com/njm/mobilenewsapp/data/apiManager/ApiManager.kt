package com.njm.mobilenewsapp.data.apiManager

import com.njm.mobilenewsapp.data.service.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
internal class AuthInterceptor(private val value: String): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val newRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $value")
            .build()

        return chain.proceed(newRequest)
    }

}