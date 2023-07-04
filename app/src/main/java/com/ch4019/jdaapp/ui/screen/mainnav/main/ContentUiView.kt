package com.ch4019.jdaapp.ui.screen.mainnav.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Dns
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Upcoming
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.navigation.NavHostController
import com.ch4019.jdaapp.model.NavigationBarItem
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ContentUiView(
    mainNavController: NavHostController,
    currentPage: MutableIntState
) {
    val navigationBarItems = listOf(
        NavigationBarItem(title = "信息门户", icon = Icons.Outlined.Upcoming),
        NavigationBarItem(title = "教务系统", icon = Icons.Outlined.Dns),
        NavigationBarItem(title = "我的", icon = Icons.Outlined.Person),
    )
    val titles = listOf("信息门户","教务系统","我的")
    val topIcon = listOf(Icons.Default.Refresh, Icons.Default.Refresh,null)
    val topIcon1 = listOf(Icons.Default.Share, Icons.Default.Share,null)
    val topIcon2 = listOf(Icons.Default.Send, Icons.Default.Send,null)
    val pagerState =  rememberPagerState(0) {3}

    val urlState1 = rememberWebViewState("https://portal.ahjzu.edu.cn/web/guest")
    val urlState2 = rememberWebViewState("https://219-231-0-156.webvpn.ahjzu.edu.cn/xtgl/login_slogin.html")
    val navigator1 = rememberWebViewNavigator()
    val navigator2 = rememberWebViewNavigator()

    Scaffold(
        topBar = {
            TopBar(pagerState, titles ,urlState1,urlState2,navigator1,navigator2,topIcon,topIcon1,topIcon2)
        },
        content = {
            ViewPager(it, pagerState,urlState1,urlState2,navigator1,navigator2,mainNavController)
        },
        bottomBar = {
            BottomBar(pagerState, navigationBarItems,currentPage)
        }
    )
}