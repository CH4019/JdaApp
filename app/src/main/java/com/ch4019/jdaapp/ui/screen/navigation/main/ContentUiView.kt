package com.ch4019.jdaapp.ui.screen.navigation.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Dns
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Upcoming
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.ch4019.jdaapp.model.NavigationBarItem
import com.ch4019.jdaapp.model.user.UserState
import com.ch4019.jdaapp.ui.components.LoadWeb
import com.ch4019.jdaapp.ui.screen.navigation.grades.GradesPage
import com.ch4019.jdaapp.ui.screen.navigation.personal.PersonalPage
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ContentUiView(
    userState: UserState,
    navController: NavHostController,
    currentPage: MutableIntState
) {
    val navigationBarItems = listOf(
        NavigationBarItem(title = "信息门户", icon = Icons.Outlined.Upcoming),
        NavigationBarItem(title = "教务系统", icon = Icons.Outlined.Dns),
        NavigationBarItem(title = "我的", icon = Icons.Outlined.Person),
    )
    val pagerState = rememberPagerState { navigationBarItems.size }

    val webViewNavigator = rememberWebViewNavigator()

    Scaffold(
        topBar = { TopBar(pagerState, webViewNavigator) },
        content = { paddingValues ->
            HorizontalPager(
                state = pagerState,
                beyondBoundsPageCount = pagerState.pageCount,
                // 关闭页面滑动(防止页面滑动冲突，暂时未解决该问题)
                userScrollEnabled = false,
                modifier = Modifier.padding(paddingValues),
            ) {
                when (it) {
                    0 -> LoadWeb(
                        rememberWebViewState("https://portal.ahjzu.edu.cn/web/guest"),
                        webViewNavigator
                    )

                    1 -> GradesPage()
//                        LoadWeb(
//                        rememberWebViewState("https://219-231-0-156.webvpn.ahjzu.edu.cn/xtgl/login_slogin.html"),
//                        webViewNavigator
//                    )

                    2 -> PersonalPage(userState, navController)
                }
            }
        },
        bottomBar = {
            BottomBar(pagerState, navigationBarItems, currentPage)
        }
    )
}
