package com.ch4019.jdaapp.ui.screen.start

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ch4019.jdaapp.R
import com.ch4019.jdaapp.config.AppRoute
import com.ch4019.jdaapp.ui.theme.JdaAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StartPageView(appNavHostController: NavHostController) {
    var contentVisibleShow by remember {
        mutableStateOf(false)
    }
    var iconVisibleShow by remember {
        mutableStateOf(false)
    }
    val logo = ImageBitmap.imageResource(R.drawable.logo)
    LaunchedEffect(Unit) {
//        进入调度器，子协程
        withContext(Dispatchers.IO) {
            delay(500)
            iconVisibleShow = true
        }
        withContext(Dispatchers.IO) {
            delay(500)
            contentVisibleShow = true
        }
        delay(1000)
        appNavHostController.navigate(AppRoute.MAIN_NAV) {
            // 弹出到图表的起始目的地
            // 避免建立大量目标
            // 在用户选择项目时的后退堆栈上
            popUpTo(AppRoute.START_SCREEN) {
                saveState = true
                inclusive = true
            }
            // 重新选择同一项目时避免同一目标的多个副本
            launchSingleTop = true
            // 重新选择先前选定的项目时恢复状态
            restoreState = true
        }
    }
    JdaAppTheme {
        Scaffold {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                AnimatedVisibility(
                    visible = iconVisibleShow,
                    enter = fadeIn(
                        animationSpec = tween(500),
                        initialAlpha = 0.2f
                    ),
                ) {
                    Image(
                        bitmap = logo,
                        contentDescription = null,
                        modifier = Modifier
                            .size(120.dp)
                    )
                }
                AnimatedVisibility(
                    visible = contentVisibleShow,
                    enter = expandVertically(
                        animationSpec = tween(1000),
                        expandFrom = Alignment.Bottom
                    ) + fadeIn(
                        initialAlpha = 0.1f
                    ),
                ) {
                    Text(
                        text = "Jda助手",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StartPreview() {
    JdaAppTheme {
        StartPageView(
            appNavHostController = rememberNavController()
        )
    }
}
