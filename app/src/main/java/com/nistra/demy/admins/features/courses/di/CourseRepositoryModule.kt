package com.nistra.demy.admins.features.courses.di

import com.nistra.demy.admins.features.courses.data.repositories.CourseRepositoryImpl
import com.nistra.demy.admins.features.courses.data.repositories.FakeCourseRepository
import com.nistra.demy.admins.features.courses.domain.repositories.CourseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface CourseRepositoryModule {

//    @Binds
//    fun provideCourseRepository(impl: CourseRepositoryImpl): CourseRepository

    @Binds
    // CAMBIAR 'CourseRepositoryImpl' por 'FakeCourseRepository' para usar data falsa:
    fun provideCourseRepository(impl: FakeCourseRepository): CourseRepository
}
