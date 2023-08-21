package com.ch4019.jdaapp.ui.screen.main

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.ExperimentalFoundationApi
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
import com.ch4019.jdaapp.model.NavigationBarItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomBar(
    pagerState: PagerState,
    navigationBarItems: List<NavigationBarItem>,
    currentPage: MutableIntState
) {
    BottomAppBar {
        val scope = rememberCoroutineScope()
        navigationBarItems.forEachIndexed { index, navigationBarItem ->
            val selected = currentPage.intValue == index
            val tint by animateColorAsState(
                targetValue = if (selected) {
                    MaterialTheme.colorScheme.primary
                } else
                    MaterialTheme.colorScheme.primary.copy(alpha = .5f),
                animationSpec = TweenSpec(
                    durationMillis = 400,
                    easing = FastOutLinearInEasing
                ),
                label = "tint"
            )
            NavigationBarItem(
                alwaysShowLabel = false,
                selected = selected,
                onClick = {
                    scope.launch(scope.coroutineContext) {
//                        使用animateScrollToPage时如果使用pagerState.currentPage则会出现选项问题
                        currentPage.intValue = index
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
                label = { Text(navigationBarItem.title) }
            )
        }
    }
}
