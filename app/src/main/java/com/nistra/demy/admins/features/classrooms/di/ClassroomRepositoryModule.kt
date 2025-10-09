package com.nistra.demy.admins.features.classrooms.di

import com.nistra.demy.admins.features.classrooms.data.repositories.ClassroomRepositoryImpl
import com.nistra.demy.admins.features.classrooms.domain.repositories.ClassroomRepository
import com.nistra.demy.admins.features.classrooms.domain.repositories.FakeClassroomRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface ClassroomRepositoryModule {

//    @Binds
//    fun provideClassroomRepository(impl: ClassroomRepositoryImpl): ClassroomRepository

    @Binds
    fun provideClassroomRepository(impl: FakeClassroomRepository): ClassroomRepository

}
