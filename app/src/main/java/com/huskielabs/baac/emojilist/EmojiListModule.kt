package com.huskielabs.baac.emojilist

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
object EmojiListModule {

  @Provides
  @FragmentScoped
  fun providesEmojiListAdapter() = EmojiListAdapter()

}
