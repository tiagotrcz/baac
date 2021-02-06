package com.huskielabs.baac.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.huskielabs.baac.data.cache.dbo.EmojiDBO

@Dao
abstract class EmojiDAO : BaseDAO<EmojiDBO>() {

  @Insert
  abstract suspend fun insertAll(emojis: List<EmojiDBO>)

  @Query("SELECT * FROM emoji")
  abstract suspend fun getAll(): List<EmojiDBO>

  @Query("SELECT count(*) FROM emoji")
  abstract suspend fun getSize(): Int

  @Query("SELECT * FROM emoji WHERE uid = :id")
  abstract fun getById(id: Int): EmojiDBO

}
