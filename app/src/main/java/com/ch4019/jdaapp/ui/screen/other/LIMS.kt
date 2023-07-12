package com.ch4019.jdaapp.ui.screen.other

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.ch4019.jdaapp.ui.components.TopBarBrowseView
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState

@Composable
fun LIMSPage(
    mainNavController: NavHostController
) {
    val urlState = rememberWebViewState(
        "http://sso.ahjzu.edu.cn/sso/login?service=http://219.231.0.154/sjjx/index.aspx"
    )
    val navigator = rememberWebViewNavigator()
    TopBarBrowseView(mainNavController, "实验系统", urlState, navigator)
}
