package com.nistra.demy.admins.features.classrooms.data.repositories
import com.nistra.demy.admins.features.classrooms.data.remote.models.ClassroomDto
import com.nistra.demy.admins.features.classrooms.data.remote.models.CreateUpdateClassroomRequest
import com.nistra.demy.admins.features.classrooms.data.remote.services.ClassroomService
import com.nistra.demy.admins.features.classrooms.domain.models.Classroom
import com.nistra.demy.admins.features.classrooms.domain.repositories.ClassroomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ClassroomRepositoryImpl @Inject constructor( // 🚨 Constructor inyectable para Hilt
    private val service: ClassroomService
) : ClassroomRepository {

    private fun ClassroomDto.toDomain(): Classroom? {
        return if (id != null && code != null && capacity != null && campus != null) {
            Classroom(id, code, capacity, campus)
        } else {
            null
        }
    }

    override suspend fun getAllClassrooms(): List<Classroom> = withContext(Dispatchers.IO) {
        val response = service.getAllClassrooms()
        return@withContext if (response.isSuccessful) {
            response.body()?.mapNotNull { it.toDomain() } ?: emptyList()
        } else {
            emptyList()
        }
    }

    // ... Implementaciones de get, create, update y delete ...
    override suspend fun getClassroomById(id: Long): Classroom? = withContext(Dispatchers.IO) {
        val response = service.getClassroomById(id)
        return@withContext if (response.isSuccessful) { response.body()?.toDomain() } else { null }
    }

    override suspend fun createClassroom(code: String, capacity: Int, campus: String): Classroom? = withContext(Dispatchers.IO) {
        val request = CreateUpdateClassroomRequest(code, capacity, campus)
        val response = service.createClassroom(request)
        return@withContext if (response.isSuccessful) { response.body()?.toDomain() } else { null }
    }

    override suspend fun updateClassroom(id: Long, code: String, capacity: Int, campus: String): Classroom? = withContext(Dispatchers.IO) {
        val request = CreateUpdateClassroomRequest(code, capacity, campus)
        val response = service.updateClassroom(id, request)
        return@withContext if (response.isSuccessful) { response.body()?.toDomain() } else { null }
    }

    override suspend fun deleteClassroom(id: Long) = withContext(Dispatchers.IO) {
        service.deleteClassroom(id)
        Unit
    }
}
