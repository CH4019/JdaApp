package com.ch4019.jdaapp.ui.screen.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.outlined.AssignmentLate
import androidx.compose.material.icons.outlined.BugReport
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.UploadFile
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ch4019.jdaapp.config.MainNavRoute
import com.ch4019.jdaapp.model.NavigationItem
import com.ch4019.jdaapp.ui.components.CardListView
import com.ch4019.jdaapp.ui.theme.JdaAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingPage(
    mainNavController: NavHostController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("设置") },
                navigationIcon = {
                    IconButton(
                        onClick = { mainNavController.navigateUp() }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBackIosNew,
                            contentDescription = null
                        )
                    }
                },
            )
        }
    ) {
        SettingView(it, mainNavController)
    }
}

@Composable
fun SettingView(
    paddingValues: PaddingValues,
    mainNavController: NavHostController
) {
    val cardListSettingItems = listOf(
        NavigationItem(
            title = "检查更新",
            icon = Icons.Outlined.UploadFile,
            routerId = MainNavRoute.ABOUT_PAGE
        ),
        NavigationItem(
            title = "语言",
            icon = Icons.Outlined.Language,
            routerId = MainNavRoute.ABOUT_PAGE
        ),
        NavigationItem(
            title = "问题反馈",
            icon = Icons.Outlined.BugReport,
            routerId = MainNavRoute.ABOUT_PAGE
        ),
        NavigationItem(
            title = "关于",
            icon = Icons.Outlined.AssignmentLate,
            routerId = MainNavRoute.ABOUT_PAGE
        ),
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        CardListView(mainNavController, cardListSettingItems)
    }
}

@Preview(showBackground = true)
@Composable
fun SettingPagePreview() {
    JdaAppTheme {
        SettingPage(
            mainNavController = rememberNavController()
        )
    }
}
