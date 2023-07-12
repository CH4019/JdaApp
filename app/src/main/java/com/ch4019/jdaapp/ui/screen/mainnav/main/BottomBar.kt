package com.ch4019.jdaapp.ui.screen.mainnav.main

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import com.ch4019.jdaapp.model.NavigationBarItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomBar(
    pagerState: PagerState,
    navigationBarItems: List<NavigationBarItem>,
    currentPage: MutableIntState
) {
    val scope = rememberCoroutineScope()
    BottomAppBar {
        navigationBarItems.forEachIndexed { index, navigationBarItem ->
            NavigationItem(index, navigationBarItem, pagerState, currentPage, scope)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RowScope.NavigationItem(
    index: Int,
    navigationBarItem: NavigationBarItem,
    pagerState: PagerState,
    currentPage: MutableIntState,
    scope: CoroutineScope
) {
    val selected = currentPage.intValue == index
    // val selected = pagerState.currentPage == index
    val durationMillis = 400
    val animationSpec = TweenSpec<Color>(
        durationMillis = durationMillis,
        easing = FastOutLinearInEasing
    )
    val tint by animateColorAsState(
        targetValue = if (selected) {
            MaterialTheme.colorScheme.primary
        } else
            MaterialTheme.colorScheme.primary.copy(alpha = .5f),
        animationSpec = animationSpec,
        label = "tint"
    )
    NavigationBarItem(
        alwaysShowLabel = false,
        selected = selected,
        onClick = {
            scope.launch(scope.coroutineContext) {
                currentPage.intValue = index
                // pagerState.scrollToPage(index)
                // 增加切换页面支持显示切换动画
                pagerState.animateScrollToPage(index)
            }
        },
        icon = {
            Icon(
                imageVector = navigationBarItem.icon,
                contentDescription = null,
                tint = tint
            )
        },
        label = {
            Text(
                text = navigationBarItem.title
            )
        }
    )
}
