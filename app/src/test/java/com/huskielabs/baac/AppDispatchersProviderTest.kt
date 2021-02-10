package com.huskielabs.baac

import com.huskielabs.baac.shared.DispatchersProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher

class AppDispatchersProviderTest : DispatchersProvider {
  val test: TestCoroutineDispatcher = TestCoroutineDispatcher()
  override val ui: CoroutineDispatcher = test
  override val io: CoroutineDispatcher = test
  override val default: CoroutineDispatcher = test
}
