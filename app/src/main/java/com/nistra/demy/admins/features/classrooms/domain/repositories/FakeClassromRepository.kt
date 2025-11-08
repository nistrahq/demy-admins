package com.nistra.demy.admins.features.classrooms.domain.repositories

import com.nistra.demy.admins.features.classrooms.domain.models.Classroom
import kotlinx.coroutines.delay
import javax.inject.Inject


// Simula la espera de red para ver el estado de carga
private const val FAKE_DELAY = 500L

class FakeClassroomRepository @Inject constructor() : ClassroomRepository {

    private val fakeClassrooms = listOf(
        Classroom(
            id = 201,
            code = "A101",
            capacity = 30,
            campus = "Campus Central"
        ),
        Classroom(
            id = 202,
            code = "B205",
            capacity = 50,
            campus = "Campus Sur"
        ),
        Classroom(
            id = 203,
            code = "T300",
            capacity = 20,
            campus = "Campus Virtual"
        )
    )

    override suspend fun getAllClassrooms(): List<Classroom> {
        delay(FAKE_DELAY) // Simula el tiempo de carga de la red
        return fakeClassrooms
    }

    override suspend fun getClassroomById(id: Long): Classroom? {
        delay(FAKE_DELAY)
        return fakeClassrooms.find { it.id == id }
    }

    // Para el resto de funciones, devolvemos nulo o Unit para simplificar el mock.
    override suspend fun createClassroom(code: String, capacity: Int, campus: String): Classroom? = null
    override suspend fun updateClassroom(id: Long, code: String, capacity: Int, campus: String): Classroom? = null
    override suspend fun deleteClassroom(id: Long) = Unit
}
