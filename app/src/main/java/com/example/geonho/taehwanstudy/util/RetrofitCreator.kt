package com.example.geonho.taehwanstudy.util

import com.example.geonho.taehwanstudy.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val addHeaderInterceptor : Interceptor = Interceptor { chain ->
        val request = chain.request()
        val newRequest = request.newBuilder()
                .addHeader("X-Naver-Client-Id", BuildConfig.NAVER_CLIENT_KEY)
                .addHeader("X-Naver-Client-Secret", BuildConfig.NAVER_SECRET_KEY)
                .build()
        chain.proceed(newRequest)
    }

    fun createRetrofit(): Retrofit {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(addHeaderInterceptor)
                .build()

        return Retrofit.Builder()
                .baseUrl("https://openapi.naver.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
