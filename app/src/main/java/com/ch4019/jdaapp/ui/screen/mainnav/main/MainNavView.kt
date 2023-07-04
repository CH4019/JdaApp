package com.ch4019.jdaapp.ui.screen.mainnav.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ch4019.jdaapp.config.MainNavRoute
import com.ch4019.jdaapp.ui.screen.mainnav.personal.setting.SettingPage

@Composable
fun MainNavController() {
    val mainNavController = rememberNavController()
    val currentPage = remember { mutableIntStateOf(0) }
    //val context = LocalContext.current
    //指定起始页面 startDestination = NavScreen.AScreen.route
    NavHost(
        navController = mainNavController,
        startDestination = MainNavRoute.MAIN_NAV
    ) {
        composable(
            MainNavRoute.MAIN_NAV
        ) {
            ContentUiView (mainNavController,currentPage)
        }
        composable(
            MainNavRoute.SETTING_PAGE
        ) {
            SettingPage ()
        }

    }
}