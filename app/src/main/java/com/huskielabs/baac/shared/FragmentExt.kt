package com.huskielabs.baac.shared

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

inline fun <T> Fragment.watch(source: StateFlow<T>, crossinline result: (T) -> Unit) {
  source.onEach { if (it != null) result(it) }.launchIn(lifecycleScope)
}
