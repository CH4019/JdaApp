package com.ch4019.jdaapp.ui.screen.mainnav.personal.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.outlined.AssignmentLate
import androidx.compose.material.icons.outlined.BugReport
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
import com.ch4019.jdaapp.model.CardListItem
import com.ch4019.jdaapp.ui.components.CardListView
import com.ch4019.jdaapp.ui.theme.JdaAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingPage(
    mainNavController: NavHostController
) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                        Text(text = "设置")
                },
                navigationIcon={
                    IconButton(
                        onClick = {
                            mainNavController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBackIosNew,
                            contentDescription = null
                        )
                    }
                },
            )
        }
    ){
        SettingView(it,mainNavController)
    }
}

@Composable
fun SettingView(
    paddingValues: PaddingValues,
    mainNavController: NavHostController
) {
    val cardListSettingItems= listOf(
        CardListItem(stringResourceId = "检查更新",imageResourceId = Icons.Outlined.UploadFile,routerPageId = MainNavRoute.ABOUT_PAGE),
        CardListItem(stringResourceId = "问题反馈",imageResourceId = Icons.Outlined.BugReport,routerPageId = MainNavRoute.ABOUT_PAGE),
        CardListItem(stringResourceId = "关于",imageResourceId = Icons.Outlined.AssignmentLate,routerPageId = MainNavRoute.ABOUT_PAGE),
    )
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .verticalScroll(rememberScrollState()),
    ) {
        CardListView(mainNavController,cardListSettingItems)
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