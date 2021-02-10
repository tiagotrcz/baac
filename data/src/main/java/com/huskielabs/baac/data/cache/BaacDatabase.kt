package com.huskielabs.baac.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.huskielabs.baac.data.cache.dao.EmojiDAO
import com.huskielabs.baac.data.cache.dao.UserDAO
import com.huskielabs.baac.data.cache.dbo.EmojiDBO
import com.huskielabs.baac.data.cache.dbo.UserAvatarDBO

@Database(entities = [EmojiDBO::class, UserAvatarDBO::class], version = 1)
abstract class BaacDatabase : RoomDatabase() {

  abstract fun emojiDao(): EmojiDAO

  abstract fun userDao(): UserDAO

}
