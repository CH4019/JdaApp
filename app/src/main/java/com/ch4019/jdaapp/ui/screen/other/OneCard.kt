package com.ch4019.jdaapp.ui.screen.other

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.ch4019.jdaapp.ui.components.TopBarBrowseView
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState

@Composable
fun OneCardPage(
    mainNavController: NavHostController
) {
    val urlState = rememberWebViewState("https://10-8-0-6.webvpn.ahjzu.edu.cn/casLogin.jsp")
    val webViewNavigator = rememberWebViewNavigator()
    TopBarBrowseView(mainNavController, "一卡通官网", urlState, webViewNavigator)
}