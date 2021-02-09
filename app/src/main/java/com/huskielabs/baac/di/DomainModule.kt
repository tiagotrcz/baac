package com.huskielabs.baac.di

import com.huskielabs.baac.domain.datasource.EmojiDataSource
import com.huskielabs.baac.domain.datasource.UserDataSource
import com.huskielabs.baac.domain.usecase.DeleteUserAvatarUseCase
import com.huskielabs.baac.domain.usecase.GetAllEmojisUseCase
import com.huskielabs.baac.domain.usecase.GetAllUsersAvatarUseCase
import com.huskielabs.baac.domain.usecase.GetRandomEmojiUseCase
import com.huskielabs.baac.domain.usecase.GetUserAvatarUseCase
import com.huskielabs.baac.domain.usecase.GetUserRepoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

  @Provides
  @Singleton
  fun provideGetAllEmojiUseCase(emojiDataSource: EmojiDataSource): GetAllEmojisUseCase {
    return GetAllEmojisUseCase(emojiDataSource)
  }

  @Provides
  @Singleton
  fun provideGetAllUsersUseCase(userDataSource: UserDataSource): GetAllUsersAvatarUseCase {
    return GetAllUsersAvatarUseCase(userDataSource)
  }

  @Provides
  @Singleton
  fun provideGetRandomEmojiUseCase(emojiDataSource: EmojiDataSource): GetRandomEmojiUseCase {
    return GetRandomEmojiUseCase(emojiDataSource)
  }

  @Provides
  @Singleton
  fun provideGetUserAvatarUseCase(userDataSource: UserDataSource): GetUserAvatarUseCase {
    return GetUserAvatarUseCase(userDataSource)
  }

  @Provides
  @Singleton
  fun provideDeleteUserAvatarUseCase(userDataSource: UserDataSource): DeleteUserAvatarUseCase {
    return DeleteUserAvatarUseCase(userDataSource)
  }

  @Provides
  @Singleton
  fun provideGetUserRepoUseCase(userDataSource: UserDataSource): GetUserRepoUseCase {
    return GetUserRepoUseCase(userDataSource)
  }

}
