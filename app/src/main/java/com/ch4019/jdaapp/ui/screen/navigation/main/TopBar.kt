package com.ch4019.jdaapp.ui.screen.navigation.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.google.accompanist.web.WebViewNavigator
import com.google.accompanist.web.WebViewState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun TopBar(
    pagerState: PagerState,
    titles: List<String>,
    urlState1: WebViewState,
    urlState2: WebViewState,
    navigator1: WebViewNavigator,
    navigator2: WebViewNavigator,
    topIcon: List<ImageVector?>,
    topIcon1: List<ImageVector?>,
    topIcon2: List<ImageVector?>
) {
    TopAppBar(
        title = {
            Text(
                text = titles[pagerState.currentPage],
            )
        },
        actions = {
            Actions(
                pagerState = pagerState,
                urlState1,
                urlState2,
                navigator1,
                navigator2,
                topIcon,
                topIcon1,
                topIcon2,
            )
        },
        modifier = Modifier.background(MaterialTheme.colorScheme.onPrimary)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RowScope.Actions(
    pagerState: PagerState,
    urlState1: WebViewState,
    urlState2: WebViewState,
    navigator1: WebViewNavigator,
    navigator2: WebViewNavigator,
    topIcon: List<ImageVector?>,
    topIcon1: List<ImageVector?>,
    topIcon2: List<ImageVector?>
) {
    // val context = LocalContext.current
    val isShow0 by remember {
        derivedStateOf {
            pagerState.currentPage == 0
        }
    }
    val isShow1 by remember {
        derivedStateOf {
            pagerState.currentPage == 1
        }
    }
    AnimatedVisibility(visible = isShow0) {
        Row {
            IconButton(
                onClick = {
                    urlState1.lastLoadedUrl?.let {
                        // 刷新当前网页
                        navigator1.loadUrl(it)
                    }
                }
            ) {
                topIcon[pagerState.currentPage]?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = null
                    )
                }
            }
            IconButton(onClick = {
                urlState1.lastLoadedUrl?.let {
                    // 分享当前网址
                }
            }) {
                topIcon1[pagerState.currentPage]?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = null
                    )
                }
            }
            IconButton(onClick = {
                // 跳转浏览器打开
            }) {
                topIcon2[pagerState.currentPage]?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = null
                    )
                }
            }
        }
    }
    AnimatedVisibility(visible = isShow1) {
        Row {
            IconButton(onClick = {
                urlState2.lastLoadedUrl?.let {
                    navigator2.loadUrl(it)
                }
            }) {
                topIcon[pagerState.currentPage]?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = null
                    )
                }
            }
            IconButton(onClick = { /*TODO*/ }) {
                topIcon1[pagerState.currentPage]?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = null
                    )
                }
            }
            IconButton(onClick = { /*TODO*/ }) {
                topIcon2[pagerState.currentPage]?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = null
                    )
                }
            }
        }
    }
}
