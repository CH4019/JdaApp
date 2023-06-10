package com.ch4019.jdaapp.windows.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dns
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Upcoming
import androidx.compose.material.icons.outlined.Dns
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Upcoming
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.ch4019.jdaapp.model.NavigationBarItem
import com.ch4019.jdaapp.ui.theme.JdaAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.accompanist.web.WebViewNavigator
import com.google.accompanist.web.WebViewState
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //设置全屏
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            JdaAppTheme {

                val systemUiController = rememberSystemUiController()
                val useDarkIcons = !isSystemInDarkTheme()
                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = useDarkIcons,
                        isNavigationBarContrastEnforced = false,
                    )
                }

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ContentUiView()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ContentUiView() {
    val navigationBarItems = listOf(
        NavigationBarItem(title = "信息门户", icon = Icons.Outlined.Upcoming,selectedIcons = Icons.Default.Upcoming),
        NavigationBarItem(title = "教务系统", icon = Icons.Outlined.Dns,selectedIcons = Icons.Default.Dns),
        NavigationBarItem(title = "我的", icon = Icons.Outlined.Person,selectedIcons = Icons.Default.Person),
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
            ViewPager(it, pagerState,urlState1,urlState2,navigator1,navigator2)
        },
        bottomBar = {
            BottomBar(pagerState, navigationBarItems)
        }
    )
}

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
        actions={
            AnimatedVisibility(visible = pagerState.currentPage == 0) {
                Row {
                    IconButton(onClick = {
                        urlState1.lastLoadedUrl?.let {
                            //刷新当前网页
                            navigator1.loadUrl(it)
                        }
                    }) {
                        topIcon[pagerState.currentPage]?.let {
                            Icon(
                                imageVector = it,
                                contentDescription = null
                            )
                        }
                    }
                    IconButton(onClick = {
                        urlState1.lastLoadedUrl?.let {
                            //分享当前网址
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
                        //跳转浏览器打开
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
            AnimatedVisibility(visible = pagerState.currentPage == 1) {
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
            AnimatedVisibility(visible = pagerState.currentPage == 2) {
                Row {
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
        },
        modifier = Modifier.background(MaterialTheme.colorScheme.onPrimary)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ViewPager(
    paddingValues: PaddingValues,
    pagerState: PagerState,
    urlState1: WebViewState,
    urlState2: WebViewState,
    navigator1: WebViewNavigator,
    navigator2: WebViewNavigator,
) {
//    Column {
        //尝试解决滑动冲突问题
//        var pagerScrollState by remember { mutableStateOf(false) }
//        val webPageScrollDistance by remember { mutableStateOf("") }
//        LaunchedEffect(this) {
//            while (webPageScrollDistance.toInt() == 0) {
//                pagerScrollState = true
//            }
//            while (webPageScrollDistance.toInt() != 0) {
//                pagerScrollState = false
//            }
//        }
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
                2 -> PersonalPage()
            }
        }
//   }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomBar(
    pagerState: PagerState,
    navigationBarItems: List<NavigationBarItem>
) {
    val scope = rememberCoroutineScope()

    BottomAppBar {
        navigationBarItems.onEachIndexed { index, navigationBarItem ->
            val selected = index == pagerState.currentPage
            val durationMillis = 400
            val animationSpec = TweenSpec<Color>(
                durationMillis = durationMillis,
                easing = FastOutLinearInEasing
            )
            val tint by animateColorAsState(
                targetValue = if (selected)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.primary.copy(alpha = .5f),
                animationSpec = animationSpec,
                label = "tint"
            )
            NavigationBarItem(
                alwaysShowLabel = false,
                selected = selected,
                onClick = {
                    scope.launch {
                        pagerState.scrollToPage(index)
                    }
                },
                icon = {
                    Icon(
                        imageVector = navigationBarItem.icon,
                        contentDescription = null,
                        tint = tint
                    )
                },
//                selectedIcon = {
//                    Icon(
//                        imageVector = navigationBarItem.selectedIcons,
//                        contentDescription = null,
//                    )
//                },
                label = {
                    Text(
                        text = navigationBarItem.title
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JdaAppTheme {

    }
}