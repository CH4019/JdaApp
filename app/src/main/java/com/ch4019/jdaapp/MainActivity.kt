package com.ch4019.jdaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ch4019.jdaapp.config.AppRoute
import com.ch4019.jdaapp.ui.screen.navigation.main.MainNavController
import com.ch4019.jdaapp.ui.screen.start.StartPageView
import com.ch4019.jdaapp.ui.theme.JdaAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //设置全屏
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            JdaAppTheme {
                val appNavHostController = rememberNavController()
                //指定起始页面 startDestination = AppRoute.START_SCREEN
                NavHost(
                    navController = appNavHostController,
                    startDestination = AppRoute.START_SCREEN
                ) {
                    composable(AppRoute.START_SCREEN) {
                        StartPageView(appNavHostController)
                    }
                    composable(AppRoute.MAIN_NAV) {
                        MainNavController()
                    }
                }
            }
        }
    }
}