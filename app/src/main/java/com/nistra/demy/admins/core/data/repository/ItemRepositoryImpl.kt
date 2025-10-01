package com.nistra.demy.admins.core.data.repository

import com.nistra.demy.admins.core.data.local.ItemDao
import com.nistra.demy.admins.core.data.remote.ApiService
import com.nistra.demy.admins.core.domain.repository.ItemRepository
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val dao: ItemDao
) : ItemRepository {
//    override suspend fun sync() {
//        val remote = api.getItems()
//        val entities = remote.map { ItemEntity(it.id, it.title) }
//        dao.upsertAll(entities)
//    }
//
//    override suspend fun items(): List<Item> =
//        dao.getAll().map { Item(id = it.id, title = it.title) }
}
