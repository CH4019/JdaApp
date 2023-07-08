package com.ch4019.jdaapp.ui.screen.mainnav.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.ch4019.jdaapp.ui.screen.mainnav.home.HomePage
import com.ch4019.jdaapp.ui.screen.mainnav.personal.PersonalPage
import com.ch4019.jdaapp.ui.screen.mainnav.personal.UserState
import com.ch4019.jdaapp.ui.screen.mainnav.second.SecondPage
import com.google.accompanist.web.WebViewNavigator
import com.google.accompanist.web.WebViewState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ViewPager(
    paddingValues: PaddingValues,
    pagerState: PagerState,
    urlState1: WebViewState,
    urlState2: WebViewState,
    navigator1: WebViewNavigator,
    navigator2: WebViewNavigator,
    mainNavController: NavHostController,
    userState: UserState
) {
    HorizontalPager(
        state = pagerState,
        beyondBoundsPageCount = 3,
        //关闭页面滑动(防止页面滑动冲突，暂时未解决该问题)
        userScrollEnabled = false,
        modifier = Modifier
            .padding(paddingValues),
    ) {
        when (it) {
            0 -> HomePage(urlState1, navigator1)
            1 -> SecondPage(urlState2, navigator2)
            2 -> PersonalPage(mainNavController,userState)
        }
    }
}