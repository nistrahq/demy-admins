package com.nistra.demy.admins.features.schedules.di

import com.nistra.demy.admins.features.schedules.data.repositories.FakeScheduleRepository
import com.nistra.demy.admins.features.schedules.data.repositories.ScheduleRepositoryImpl
import com.nistra.demy.admins.features.schedules.domain.repositories.ScheduleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface ScheduleRepositoryModule {

//    @Binds
//    fun provideScheduleRepository(impl: ScheduleRepositoryImpl): ScheduleRepository

    @Binds
    // CAMBIO CLAVE: Usamos FakeScheduleRepository
    fun provideScheduleRepository(impl: FakeScheduleRepository): ScheduleRepository
}
