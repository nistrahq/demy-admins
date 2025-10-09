package com.nistra.demy.admins.features.schedules.di

import com.nistra.demy.admins.features.schedules.data.remote.services.ScheduleService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ScheduleDataModule {

    @Provides
    @Singleton
    fun provideScheduleService(retrofit: Retrofit): ScheduleService {
        return retrofit.create(ScheduleService::class.java)
    }
}
