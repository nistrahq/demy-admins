package com.nistra.demy.admins.core.di

import com.nistra.demy.admins.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Named
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * NetworkModule is a Dagger Hilt module that provides network-related dependencies such as
 * Retrofit, OkHttpClient, and Moshi.
 * @author Salim Ramirez
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides @Singleton
    @Named("apiBaseUrl")
    fun provideBaseUrl(): String = BuildConfig.API_BASE_URL

    @Provides @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.LOG_HTTP)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }

    @Provides @Singleton
    fun provideHeadersInterceptor(): Interceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
            // TODO: Add auth token if needed (.addHeader("Authorization", "Bearer ...")
            .addHeader("Accept", "application/json")
            .build()
        chain.proceed(request)
    }

    @Provides @Singleton
    fun provideOkHttp(
        logging: HttpLoggingInterceptor,
        headers: Interceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(headers)
        .addInterceptor(logging)
        .build()

    @Provides @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    @Provides @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
        @Named("apiBaseUrl") baseUrl: String
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    /**
     * This is just an example of how to provide an ApiService.
     * You can create your own ApiService interface and provide it here.
     */
    // @Provides @Singleton
    // fun provideApiService(retrofit: Retrofit): ApiService =
    //    retrofit.create(ApiService::class.java)
}
