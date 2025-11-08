package com.nistra.demy.admins.features.classrooms.domain.repositories

import com.nistra.demy.admins.features.classrooms.domain.models.Classroom

interface ClassroomRepository {
    // Queries
    suspend fun getAllClassrooms(): List<Classroom>
    suspend fun getClassroomById(id: Long): Classroom?

    // Commands
    suspend fun createClassroom(code: String, capacity: Int, campus: String): Classroom?
    suspend fun updateClassroom(id: Long, code: String, capacity: Int, campus: String): Classroom?
    suspend fun deleteClassroom(id: Long)
}
