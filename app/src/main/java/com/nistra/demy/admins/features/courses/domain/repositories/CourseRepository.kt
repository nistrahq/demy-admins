package com.nistra.demy.admins.features.courses.domain.repositories

import com.nistra.demy.admins.features.courses.domain.models.Course

interface CourseRepository {
    // Queries
    suspend fun getAllCourses(): List<Course>

    suspend fun getCourseById(id: Long): Course?

    // Commands
    suspend fun createCourse(name: String, code: String, description: String): Course?
    suspend fun updateCourse(id: Long, name: String, code: String, description: String): Course?
    suspend fun deleteCourse(id: Long)
}
