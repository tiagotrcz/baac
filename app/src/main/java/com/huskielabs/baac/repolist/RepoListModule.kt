package com.huskielabs.baac.repolist

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
object RepoListModule {

  @Provides
  @FragmentScoped
  fun providesRepoListAdapter() = RepoListAdapter()

}
