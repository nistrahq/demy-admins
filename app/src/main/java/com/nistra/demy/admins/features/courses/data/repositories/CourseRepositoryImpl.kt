package com.nistra.demy.admins.features.courses.data.repositories

import com.nistra.demy.admins.features.courses.data.remote.models.CourseDto
import com.nistra.demy.admins.features.courses.data.remote.models.CreateUpdateCourseRequest
import com.nistra.demy.admins.features.courses.data.remote.services.CourseService
import com.nistra.demy.admins.features.courses.domain.models.Course
import com.nistra.demy.admins.features.courses.domain.repositories.CourseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class CourseRepositoryImpl @Inject constructor(
    private val service: CourseService
) : CourseRepository {

    private fun CourseDto.toDomain(): Course? {
        return if (id != null && name != null && code != null && description != null) {
            Course(id, name, code, description)
        } else {
            null
        }
    }

    override suspend fun getAllCourses(): List<Course> = withContext(Dispatchers.IO) {
        val response = service.getAllCourses()

        if (response.isSuccessful) {
            return@withContext response.body()
                ?.mapNotNull { it.toDomain() }
                ?: emptyList()
        }
        return@withContext emptyList()
    }

    override suspend fun getCourseById(id: Long): Course? = withContext(Dispatchers.IO) {
        val response = service.getCourseById(id)

        if (response.isSuccessful) {
            return@withContext response.body()?.toDomain()
        }
        return@withContext null
    }

    override suspend fun createCourse(name: String, code: String, description: String): Course? = withContext(Dispatchers.IO) {
        val request = CreateUpdateCourseRequest(name, code, description)
        val response = service.createCourse(request)

        if (response.isSuccessful) {
            return@withContext response.body()?.toDomain()
        }
        return@withContext null
    }

    override suspend fun updateCourse(id: Long, name: String, code: String, description: String): Course? = withContext(Dispatchers.IO) {
        val request = CreateUpdateCourseRequest(name, code, description)
        val response = service.updateCourse(id, request)

        if (response.isSuccessful) {
            return@withContext response.body()?.toDomain()
        }
        return@withContext null
    }

    override suspend fun deleteCourse(id: Long) = withContext(Dispatchers.IO) {
        service.deleteCourse(id)

        //QUE DEVUELVE???? HARDCODEADO
        Unit
    }
}
