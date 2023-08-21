package com.ch4019.jdaapp.ui.screen.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.web.WebViewNavigator

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun TopBar(
    pagerState: PagerState,
    navigator: WebViewNavigator,
) {
    val titles = listOf("信息门户", "教务系统", "我的")
    TopAppBar(
        title = { Text(titles[pagerState.currentPage]) },
        actions = {
            if (pagerState.currentPage == pagerState.pageCount - 3) {
                IconButton(onClick = {
                    navigator.reload()
                }) { Icon(Icons.Default.Refresh, null) }
                IconButton(onClick = {
                }) { Icon(Icons.Default.Share, null) }
                IconButton(onClick = {
                }) { Icon(Icons.Default.Send, null) }
            }
        },
        modifier = Modifier.background(MaterialTheme.colorScheme.onPrimary)
    )
}
