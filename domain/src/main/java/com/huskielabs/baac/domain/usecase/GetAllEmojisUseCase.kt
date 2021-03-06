package com.huskielabs.baac.domain.usecase

import com.huskielabs.baac.domain.datasource.EmojiDataSource
import com.huskielabs.baac.domain.usecase.shared.NoParams
import com.huskielabs.baac.domain.usecase.shared.UseCase
import javax.inject.Inject

class GetAllEmojisUseCase @Inject constructor(
  private val emojiDataSource: EmojiDataSource,
) : UseCase<NoParams, List<String>> {

  override suspend fun invoke(params: NoParams): List<String> {
    return emojiDataSource.getAll()
  }

}
