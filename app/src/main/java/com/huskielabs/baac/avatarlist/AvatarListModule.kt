package com.huskielabs.baac.avatarlist

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
object AvatarListModule {

  @Provides
  @FragmentScoped
  fun providesAvatarListAdapter() = AvatarListAdapter()

}
