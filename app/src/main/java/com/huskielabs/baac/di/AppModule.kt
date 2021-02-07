package com.huskielabs.baac.di

import com.huskielabs.baac.shared.AppDispatchersProvider
import com.huskielabs.baac.shared.DispatchersProvider
import com.huskielabs.baac.shared.Navigator
import com.huskielabs.baac.shared.NavigatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Provides
  @Singleton
  fun provideNavigator(): Navigator = NavigatorImpl()

  @Provides
  @Singleton
  fun provideDispatchers(): DispatchersProvider = AppDispatchersProvider()

}
