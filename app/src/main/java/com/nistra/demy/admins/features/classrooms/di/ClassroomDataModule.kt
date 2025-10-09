package com.nistra.demy.admins.features.classrooms.di

import com.nistra.demy.admins.features.classrooms.data.remote.services.ClassroomService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ClassroomDataModule {

    @Provides
    @Singleton
    fun provideClassroomService(retrofit: Retrofit): ClassroomService {
        return retrofit.create(ClassroomService::class.java)
    }
}
