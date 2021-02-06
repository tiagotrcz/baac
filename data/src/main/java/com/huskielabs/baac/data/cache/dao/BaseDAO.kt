package com.huskielabs.baac.data.cache.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import androidx.room.Update

@Dao
abstract class BaseDAO<in Entity> {
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  abstract suspend fun insert(entity: Entity): Long

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  abstract suspend fun insert(entities: List<Entity>): List<Long>

  @Update
  abstract suspend fun update(entity: Entity)

  @Update
  abstract suspend fun update(entity: List<Entity>)

  @Delete
  abstract suspend fun delete(entity: Entity)

  @Transaction
  open suspend fun upsert(entity: Entity) {
    val result = insert(entity)
    if (result == ROW_NOT_INSERTED) update(entity)
  }

  @Transaction
  open suspend fun upsert(entities: List<Entity>) {
    val insertResult = insert(entities)
    val updateList = mutableListOf<Entity>()

    insertResult.forEachIndexed { index, result ->
      if (result == ROW_NOT_INSERTED) updateList.add(entities[index])
    }

    if (updateList.isNotEmpty()) update(updateList)
  }
}

const val ROW_NOT_INSERTED = -1L
