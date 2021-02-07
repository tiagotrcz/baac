package com.huskielabs.baac.util

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun <T> Fragment.viewBinding(bindingFactory: (View) -> T): ReadOnlyProperty<Fragment, T> =
  object : ReadOnlyProperty<Fragment, T>, DefaultLifecycleObserver {

    private var binding: T? = null

    init {
      this@viewBinding
        .viewLifecycleOwnerLiveData
        .observe(this@viewBinding, { owner ->
          owner?.lifecycle?.addObserver(this)
        })
    }

    override fun onDestroy(owner: LifecycleOwner) {
      binding = null
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
      return binding ?: bindingFactory(requireView()).also { newBinding ->
        binding = newBinding
      }
    }
  }
