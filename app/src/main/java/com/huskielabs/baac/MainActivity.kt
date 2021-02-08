package com.huskielabs.baac

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.huskielabs.baac.shared.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  @Inject
  lateinit var navigator: Navigator

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val navHostFragment = supportFragmentManager
      .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    val navController = navHostFragment.navController

    navigator.bind(navController, lifecycleScope)
  }

  override fun onDestroy() {
    super.onDestroy()
    navigator.unbind()
  }

}
