package com.ch4019.jdaapp.ui.screen.other

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.ch4019.jdaapp.ui.components.TopBarBrowseView
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState

@Composable
fun OfficialWebsite(
    mainNavController: NavHostController
) {
    val urlState = rememberWebViewState(
        "https://jdaassistant.ch4019.fun/docs/AppUpdateLog"
    )
    val webViewNavigator = rememberWebViewNavigator()
    TopBarBrowseView(mainNavController, "软件官网", urlState, webViewNavigator)
}