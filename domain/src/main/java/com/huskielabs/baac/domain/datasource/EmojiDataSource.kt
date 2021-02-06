package com.huskielabs.baac.domain.datasource

import com.huskielabs.baac.domain.model.EmojiModel

interface EmojiDataSource {

  suspend fun getRandomEmoji(): EmojiModel

  suspend fun getAll(): List<EmojiModel>

}