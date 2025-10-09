package com.nistra.demy.admins.features.courses.data.repositories

import com.nistra.demy.admins.features.courses.domain.models.Course
import com.nistra.demy.admins.features.courses.domain.repositories.CourseRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

// Simula la espera de red para ver el estado de carga
private const val FAKE_DELAY = 500L

class FakeCourseRepository @Inject constructor() : CourseRepository {

    private val fakeCourses = listOf(
        Course(
            id = 101,
            name = "Introducción a Kotlin",
            code = "KOT101",
            description = "Curso básico para aprender los fundamentos del lenguaje Kotlin y su aplicación en Android."
        ),
        Course(
            id = 102,
            name = "Jetpack Compose Avanzado",
            code = "JC205",
            description = "Técnicas modernas para construir UIs declarativas con Compose, incluyendo animaciones y estados complejos."
        ),
        Course(
            id = 103,
            name = "Arquitectura Modular y DI",
            code = "ARCH300",
            description = "Diseño de aplicaciones escalables con Clean Architecture y Dagger Hilt."
        )
    )

    override suspend fun getAllCourses(): List<Course> {
        delay(FAKE_DELAY) // Simula el tiempo de carga de la red
        return fakeCourses
    }

    // Para el resto de funciones, puede devolver nulo o lanzar una excepción para probar errores.
    override suspend fun getCourseById(id: Long): Course? {
        delay(FAKE_DELAY)
        return fakeCourses.find { it.id == id }
    }

    override suspend fun createCourse(name: String, code: String, description: String): Course? = null
    override suspend fun updateCourse(id: Long, name: String, code: String, description: String): Course? = null
    override suspend fun deleteCourse(id: Long) = Unit
}
