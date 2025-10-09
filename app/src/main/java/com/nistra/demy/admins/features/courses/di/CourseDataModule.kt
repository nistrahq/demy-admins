package com.nistra.demy.admins.features.courses.di

import com.nistra.demy.admins.features.courses.data.remote.services.CourseService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CourseDataModule {

    // Se asume que Retrofit se provee globalmente o en un CoreModule, pero se declara aquí
    // para coherencia con el patrón de EasyMovie/movies/di/RemoteModule.kt
    @Provides
    @Singleton
    fun provideCourseService(retrofit: Retrofit): CourseService {
        // En una app real, la base URL del backend "scheduling" se usaría aquí
        return retrofit.create(CourseService::class.java)
    }
}
