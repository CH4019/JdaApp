package com.ch4019.jdaapp.ui.screen.mainnav.personal

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.outlined.AssignmentLate
import androidx.compose.material.icons.outlined.BugReport
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.UploadFile
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ch4019.jdaapp.R
import com.ch4019.jdaapp.config.MainNavRoute
import com.ch4019.jdaapp.model.CardListItem
import com.ch4019.jdaapp.ui.components.CardListView
import com.ch4019.jdaapp.ui.theme.JdaAppTheme

@Composable
fun PersonalPage(
    mainNavController: NavHostController
) {

    val cardListItems= listOf(
        CardListItem(stringResourceId = "检查更新",imageResourceId = Icons.Outlined.UploadFile, settingRouterPage = MainNavRoute.SETTING_PAGE),
        CardListItem(stringResourceId = "设置",imageResourceId = Icons.Outlined.Settings,settingRouterPage = MainNavRoute.SETTING_PAGE),
        CardListItem(stringResourceId = "问题反馈",imageResourceId = Icons.Outlined.BugReport,settingRouterPage = MainNavRoute.SETTING_PAGE),
        CardListItem(stringResourceId = "关于",imageResourceId = Icons.Outlined.AssignmentLate,settingRouterPage = MainNavRoute.SETTING_PAGE),
    )

    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        UserIcon()
        CardListView(mainNavController,cardListItems)
    }
}

@Composable
fun UserIcon() {
    val userName = remember{ "UserId"}
    val qqNumber = remember { "**********" }
    Card (
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(20.dp))
            .clickable { },
    ){
        Column (
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ){
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Column (
                    modifier = Modifier
                        .size(50.dp)
                        .clip(shape = CircleShape)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.app_icon),
                        modifier = Modifier,
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.size(32.dp))
                Column {
                    Text(
                        text = "用户名:$userName",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "QQ号:$qqNumber",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.ArrowForwardIos,
                    contentDescription = null
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PersonalPreview() {
    JdaAppTheme {
        PersonalPage(
            mainNavController = rememberNavController()
        )
    }
}