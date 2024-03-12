package com.example.comics.data.di

import com.example.comics.BuildConfig
import com.example.comics.data.repository.ComicsRepository
import com.example.comics.data.repository.ComicsRepositoryImpl
import com.example.comics.data.service.ComicsService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private inline fun <reified T> createUnauthService(): T {
    val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .readTimeout(2, TimeUnit.MINUTES)
        .connectTimeout(2, TimeUnit.MINUTES).writeTimeout(2, TimeUnit.MINUTES).build()
    val gson = GsonBuilder().create()
    return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(client)
        .addConverterFactory(GsonConverterFactory.create(gson)).build().create(T::class.java)
}

val servicesModule = module {
    createUnauthService<ComicsService>()
}

val repositories = module {
    single <ComicsRepository> { ComicsRepositoryImpl(get())}
}

val dataModule = listOf(servicesModule,repositories)

