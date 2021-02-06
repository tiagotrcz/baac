package com.huskielabs.baac.domain.usecase

import com.huskielabs.baac.domain.datasource.EmojiDataSource
import com.huskielabs.baac.domain.model.EmojiModel
import com.huskielabs.baac.domain.usecase.shared.NoParams
import com.huskielabs.baac.domain.usecase.shared.UseCase
import javax.inject.Inject

class GetRandomEmojiUseCase @Inject constructor(
  private val emojiDataSource: EmojiDataSource,
) : UseCase<NoParams, EmojiModel> {

  override suspend fun invoke(params: NoParams): EmojiModel {
    return emojiDataSource.getRandomEmoji()
  }

}