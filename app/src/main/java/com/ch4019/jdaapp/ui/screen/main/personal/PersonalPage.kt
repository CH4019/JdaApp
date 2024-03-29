package com.ch4019.jdaapp.ui.screen.main.personal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.outlined.Assignment
import androidx.compose.material.icons.outlined.AssignmentLate
import androidx.compose.material.icons.outlined.BugReport
import androidx.compose.material.icons.outlined.Cable
import androidx.compose.material.icons.outlined.ConfirmationNumber
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.DesktopWindows
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.ch4019.jdaapp.R
import com.ch4019.jdaapp.config.MainNavRoute
import com.ch4019.jdaapp.model.NavigationItem
import com.ch4019.jdaapp.viewmodel.user.UserState
import com.ch4019.jdaapp.ui.components.CardListView
import com.ch4019.jdaapp.ui.theme.JdaAppTheme

@Composable
fun PersonalPage(
    userState: UserState,
    navHostController: NavHostController
) {
    val cardListSchoolItems = createSchoolItem()
    val cardListPersonalItems = createSettingItem()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        UserCard(userState, navHostController)
        ListTitle("其他官网")
        CardListView(navHostController, cardListSchoolItems)
        ListTitle("设置")
        CardListView(navHostController, cardListPersonalItems)
    }
}

@Composable
fun ListTitle(
    title: String
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 32.dp)
            .fillMaxWidth(),
    ) {
        Text(text = title)
    }
}

@Composable
private fun createSettingItem() = listOf(
    NavigationItem(
        "软件官网",
        Icons.Outlined.DesktopWindows,
        MainNavRoute.OFFICIAL_WEBSITE
    ),
    NavigationItem(
        "问题反馈",
        Icons.Outlined.BugReport,
        MainNavRoute.FEEDBACK
    ),
    NavigationItem(
        "设置",
        Icons.Outlined.AssignmentLate,
        MainNavRoute.SETTING_PAGE
    ),
)

@Composable
private fun createSchoolItem() = listOf(
    NavigationItem(
        "一卡通官网",
        Icons.Outlined.CreditCard,
        MainNavRoute.ONE_CARD_PAGE
    ),
    NavigationItem(
        "体育部通知",
        Icons.Outlined.Assignment,
        MainNavRoute.SPORTS_PAGE
    ),
    NavigationItem(
        "实验系统入口",
        Icons.Outlined.Cable,
        MainNavRoute.LIMS_PAGE
    ),
    NavigationItem(
        "成绩查询",
        Icons.Outlined.ConfirmationNumber,
        MainNavRoute.GRADES_PAGE
    ),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UserCard(
    userState: UserState,
    navController: NavHostController
) {
    Card(
        onClick = { navController.navigate(MainNavRoute.USER_PAGE) },
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            UserAvatar(userState.qqNumber, userState.isLogin)
            Spacer(modifier = Modifier.width(32.dp))
            Column {
                Text(
                    text = "用户名:${userState.userName}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "学号:${userState.studentNumber}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = null,
            )
        }
    }
}

@Composable
private fun UserAvatar(
    content: String,
    isLogin: Boolean
) {
    if (isLogin) {
        Avatar("https://api.kuizuo.cn/api/qqimg?qq=$content")
    } else {
        Avatar(R.drawable.app_icon)
    }
}

@Composable
private fun Avatar(avatar: Any) {
    AsyncImage(
        model = avatar,
        contentDescription = null,
        modifier = Modifier
            .size(50.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop,
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    JdaAppTheme {
        PersonalPage(
            userState = UserState(
                userName = "CH4019",
                qqNumber = "123456789",
                studentNumber = "123456789",
                isLogin = false,
            ),
            navHostController = rememberNavController()
        )
    }
}