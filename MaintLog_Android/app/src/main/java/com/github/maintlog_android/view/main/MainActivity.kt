package com.github.maintlog_android.view.main

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.github.domain.model.action.ActionData
import com.github.maintlog_android.R
import com.github.maintlog_android.databinding.ActivityMainBinding
import com.github.maintlog_android.view.improvement.detail.ImprovementDetailActivity
import com.github.util.base.BaseActivity
import com.github.util.base.BaseRecyclerViewAdapter
import com.github.util.extension.startActivityIntent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main),
BaseRecyclerViewAdapter.OnClickEvent<ActionData>{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBottomNavigation()
    }

    override var vmApply: (MainViewModel.() -> Unit)? = {
        lifecycleScope.launch {
            delay(1000)
            getWeatherForecast()
            getImprovementActions()
        }
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

    override fun itemClick(item: ActionData) {
        startActivityIntent<ImprovementDetailActivity>()
    }
}
