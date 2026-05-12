package com.github.maintlog_android.view.main

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.github.maintlog_android.R
import com.github.maintlog_android.databinding.ActivityMainBinding
import com.github.util.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        binding?.bottomNavigation?.setupWithNavController(getNavHostFragment().navController)
    }

    fun onTitleBackClick() {
        val navController = getNavHostFragment().navController
        if (!navController.navigateUp()) {
            finish()
        }
    }

    private fun getNavHostFragment(): NavHostFragment {
        return supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }
}
