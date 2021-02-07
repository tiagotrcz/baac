package com.huskielabs.baac.di

import com.huskielabs.baac.util.AppDispatchersProvider
import com.huskielabs.baac.util.DispatchersProvider
import com.huskielabs.baac.util.Navigator
import com.huskielabs.baac.util.NavigatorImpl
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
