package com.ch4019.jdaapp.ui.screen.navigation.main

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
import com.ch4019.jdaapp.model.UserViewModel
import com.ch4019.jdaapp.ui.screen.other.LIMSPage
import com.ch4019.jdaapp.ui.screen.other.OneCardPage
import com.ch4019.jdaapp.ui.screen.other.SportsPage
import com.ch4019.jdaapp.ui.screen.other.about.AboutPage
import com.ch4019.jdaapp.ui.screen.other.setting.SettingPage
import com.ch4019.jdaapp.ui.screen.other.user.UserPage

@Composable
fun MainNavController() {
    val navController = rememberNavController()
    val currentPage = remember { mutableIntStateOf(0) }
    val userViewModel: UserViewModel = hiltViewModel()
    val userState by userViewModel.userState.collectAsState()
    val appState by userViewModel.appState.collectAsState()
    NavHost(
        navController = navController,
        startDestination = MainNavRoute.MAIN_NAV
    ) {
        composable(MainNavRoute.MAIN_NAV) {
            ContentUiView(navController, userState, currentPage)
        }
        composable(MainNavRoute.SETTING_PAGE) {
            SettingPage(navController)
        }
        composable(MainNavRoute.ABOUT_PAGE) {
            AboutPage(userViewModel, appState, navController)
        }
        composable(MainNavRoute.ONE_CARD_PAGE) {
            OneCardPage(navController)
        }
        composable(MainNavRoute.SPORTS_PAGE) {
            SportsPage(navController)
        }
        composable(MainNavRoute.LIMS_PAGE) {
            LIMSPage(navController)
        }
        composable(MainNavRoute.USER_PAGE) {
            UserPage(navController)
        }
    }
}
