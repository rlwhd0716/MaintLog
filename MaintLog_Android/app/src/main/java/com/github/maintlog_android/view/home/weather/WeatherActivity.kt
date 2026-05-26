package com.github.maintlog_android.view.home.weather

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.github.domain.model.action.ActionData
import com.github.maintlog_android.R
import com.github.maintlog_android.databinding.ActivityMainBinding
import com.github.maintlog_android.databinding.ActivityWeatherBinding
import com.github.maintlog_android.view.improvement.detail.ImprovementDetailActivity
import com.github.maintlog_android.view.main.MainViewModel
import com.github.util.base.BaseActivity
import com.github.util.base.BaseRecyclerViewAdapter
import com.github.util.extension.startActivityIntent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WeatherActivity : BaseActivity<ActivityWeatherBinding, WeatherViewModel>(R.layout.activity_weather) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override var vmApply: (WeatherViewModel.() -> Unit)? = {
//        lifecycleScope.launch {
//            delay(1000)
//            getWeatherForecast()
//            getImprovementActions()
//        }
    }


}
