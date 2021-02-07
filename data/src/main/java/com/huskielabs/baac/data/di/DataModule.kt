package com.huskielabs.baac.data.di

import android.content.Context
import androidx.room.Room
import com.huskielabs.baac.data.EmojiDataSourceImpl
import com.huskielabs.baac.data.UserDataSourceImpl
import com.huskielabs.baac.data.cache.BaacDatabase
import com.huskielabs.baac.data.cache.dao.EmojiDAO
import com.huskielabs.baac.data.cache.dao.UserDAO
import com.huskielabs.baac.data.cache.repository.EmojiCacheRepositoryImpl
import com.huskielabs.baac.data.cache.repository.UserCacheRepositoryImpl
import com.huskielabs.baac.data.remote.RemoteRepositoryImpl
import com.huskielabs.baac.data.remote.service.GitHubService
import com.huskielabs.baac.data.repository.EmojiCacheRepository
import com.huskielabs.baac.data.repository.RemoteRepository
import com.huskielabs.baac.data.repository.UserCacheRepository
import com.huskielabs.baac.domain.datasource.EmojiDataSource
import com.huskielabs.baac.domain.datasource.UserDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Suppress("TooManyFunctions")
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

  private const val CONNECT_TIMEOUT = 10000L
  private const val WRITE_TIMEOUT = 10000L
  private const val READ_TIMEOUT = 30000L

  @Provides
  @Singleton
  fun provideRetrofit(): Retrofit {
    val logger = HttpLoggingInterceptor.Logger { message ->
      Platform.get().log(message, Platform.INFO, null)
    }
    val loggingInterceptor = HttpLoggingInterceptor(logger)
      .setLevel(HttpLoggingInterceptor.Level.BODY)

    val okHttpClient: OkHttpClient =
      OkHttpClient.Builder().apply {
        addInterceptor(loggingInterceptor)
        connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
        writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
        readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
      }.build()

    return Retrofit.Builder()
      .baseUrl("https://api.github.com/")
      .client(okHttpClient)
      .addConverterFactory(MoshiConverterFactory.create())
      .build()
  }

  @Provides
  @Singleton
  fun provideGitHubService(retrofit: Retrofit) = retrofit.create(GitHubService::class.java)

  @Provides
  @Singleton
  fun provideAppDatabase(@ApplicationContext appContext: Context): BaacDatabase {
    return Room.databaseBuilder(
      appContext,
      BaacDatabase::class.java,
      "baac_database"
    ).build()
  }

  @Provides
  fun provideEmojiDao(database: BaacDatabase) = database.emojiDao()

  @Provides
  fun provideUserDao(database: BaacDatabase) = database.userDao()

  @Provides
  @Singleton
  fun provideRemoteRepository(service: GitHubService): RemoteRepository {
    return RemoteRepositoryImpl(service)
  }

  @Provides
  @Singleton
  fun provideEmojiCacheRepository(userDao: UserDAO): UserCacheRepository {
    return UserCacheRepositoryImpl(userDao)
  }

  @Provides
  @Singleton
  fun provideUserCacheRepository(emojiDao: EmojiDAO): EmojiCacheRepository {
    return EmojiCacheRepositoryImpl(emojiDao)
  }

  @Provides
  @Singleton
  fun provideEmojiDataSource(
    remoteRepository: RemoteRepository,
    emojiCacheRepository: EmojiCacheRepository,
  ): EmojiDataSource {
    return EmojiDataSourceImpl(remoteRepository, emojiCacheRepository)
  }

  @Provides
  @Singleton
  fun provideUserDataSource(
    remoteRepository: RemoteRepository,
    userCacheRepository: UserCacheRepository,
  ): UserDataSource {
    return UserDataSourceImpl(remoteRepository, userCacheRepository)
  }
}
