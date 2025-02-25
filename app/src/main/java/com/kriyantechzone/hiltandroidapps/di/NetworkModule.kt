package com.kriyantechzone.hiltandroidapps.di

import com.google.gson.GsonBuilder
import com.kriyantechzone.hiltandroidapps.BuildConfig
import com.kriyantechzone.hiltandroidapps.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOKHttpClient(): OkHttpClient = if(BuildConfig.DEBUG) {
        val loggingInterceptor =  HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        OkHttpClient
            .Builder()
            .writeTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(loggingInterceptor)
            .build()
    } else{
        OkHttpClient
            .Builder()
            .writeTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory =
         GsonConverterFactory.create(GsonBuilder().excludeFieldsWithoutExposeAnnotation()
         .create())


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, converterFactory:GsonConverterFactory):Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(converterFactory)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit:Retrofit):ApiService = retrofit.create(ApiService::class.java)



}

