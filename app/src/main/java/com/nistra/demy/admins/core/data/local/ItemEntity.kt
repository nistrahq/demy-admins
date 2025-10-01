package com.nistra.demy.admins.core.data.local

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity(tableName = "items")
data class ItemEntity(
    @PrimaryKey val id: String,
    val title: String
)

@Dao
interface ItemDao {
    @Query("SELECT * FROM items")
    suspend fun getAll(): List<ItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(items: List<ItemEntity>)
}
