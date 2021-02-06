package com.huskielabs.baac.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.huskielabs.baac.data.cache.dao.EmojiDAO
import com.huskielabs.baac.data.cache.dao.UserDAO

@Database(entities = [EmojiDAO::class, UserDAO::class], version = 1)
abstract class BaacDatabase : RoomDatabase() {

  abstract fun emojiDao(): EmojiDAO

  abstract fun userDao(): UserDAO

}