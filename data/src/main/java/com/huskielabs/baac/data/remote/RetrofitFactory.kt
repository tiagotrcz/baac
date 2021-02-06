package com.huskielabs.baac.data.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitFactory {

  private const val CONNECT_TIMEOUT = 10000L
  private const val WRITE_TIMEOUT = 10000L
  private const val READ_TIMEOUT = 30000L

  fun getInstance(
    url: String,
    moshiConverterFactory: MoshiConverterFactory,
    okHttpClient: OkHttpClient,
  ): Retrofit = Retrofit.Builder()
    .baseUrl(url)
    .client(okHttpClient)
    .addConverterFactory(moshiConverterFactory)
    .build()

  fun getOkHttpClient(vararg interceptors: Interceptor): OkHttpClient =
    OkHttpClient.Builder().apply {
      interceptors.forEach { addInterceptor(it) }
      connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
      writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
      readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
    }.build()

  fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val logger = HttpLoggingInterceptor.Logger { message ->
      Platform.get().log(message, Platform.INFO, null)
    }
    return HttpLoggingInterceptor(logger).setLevel(HttpLoggingInterceptor.Level.BODY)
  }

  fun createMoshiConverterFactory() = MoshiConverterFactory.create()

}
