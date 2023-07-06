package com.ch4019.jdaapp.ui.screen.other

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.ch4019.jdaapp.ui.components.TopBarBrowseView
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState

@Composable
fun SportsPage(
    mainNavController: NavHostController
) {
    val urlState = rememberWebViewState("https://www.ahjzu.edu.cn/tyb/zxgg/list.htm")
    val navigator = rememberWebViewNavigator()
    TopBarBrowseView(mainNavController, "体育部官网", urlState, navigator)
}