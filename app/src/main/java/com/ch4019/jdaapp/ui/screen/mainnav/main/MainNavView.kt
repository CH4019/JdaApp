package com.ch4019.jdaapp.ui.screen.mainnav.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ch4019.jdaapp.config.MainNavRoute
import com.ch4019.jdaapp.ui.screen.mainnav.personal.UserViewModel
import com.ch4019.jdaapp.ui.screen.other.LIMSPage
import com.ch4019.jdaapp.ui.screen.other.OneCardPage
import com.ch4019.jdaapp.ui.screen.other.SportsPage
import com.ch4019.jdaapp.ui.screen.other.about.AboutPage
import com.ch4019.jdaapp.ui.screen.other.setting.SettingPage
import com.ch4019.jdaapp.ui.screen.other.user.UserPage

@Composable
fun MainNavController() {
    val mainNavController = rememberNavController()
    val currentPage = remember { mutableIntStateOf(0) }
    val userViewModel: UserViewModel = hiltViewModel()
    // val userViewModel: UserViewModel = viewModel()
    val userState by userViewModel.userState.collectAsState()
    // 指定起始页面 startDestination = MainNavRoute.MAIN_NAV
    NavHost(
        navController = mainNavController,
        startDestination = MainNavRoute.MAIN_NAV
    ) {
        composable(
            MainNavRoute.MAIN_NAV
        ) {
            ContentUiView(mainNavController, userState, currentPage)
        }
        composable(
            MainNavRoute.SETTING_PAGE
        ) {
            SettingPage(mainNavController)
        }
        composable(
            MainNavRoute.ABOUT_PAGE
        ) {
            AboutPage(mainNavController)
        }
        composable(
            MainNavRoute.ONE_CARD_PAGE
        ) {
            OneCardPage(mainNavController)
        }
        composable(
            MainNavRoute.SPORTS_PAGE
        ) {
            SportsPage(mainNavController)
        }
        composable(
            MainNavRoute.LIMS_PAGE
        ) {
            LIMSPage(mainNavController)
        }
        composable(
            MainNavRoute.USER_PAGE
        ) {
            UserPage(mainNavController, userViewModel, userState)
        }
    }
}
