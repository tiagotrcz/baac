package com.huskielabs.baac.domain.datasource

interface EmojiDataSource {

  suspend fun getRandomEmoji(): String

  suspend fun getAll(): List<String>

}
