package com.ch4019.jdaapp.ui.screen.navigation.personal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.outlined.Assignment
import androidx.compose.material.icons.outlined.AssignmentLate
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.BugReport
import androidx.compose.material.icons.outlined.Cable
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.DesktopWindows
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.ch4019.jdaapp.R
import com.ch4019.jdaapp.config.MainNavRoute
import com.ch4019.jdaapp.model.NavigationItem
import com.ch4019.jdaapp.ui.components.CardListView

@Composable
fun PersonalPage(
    mainNavController: NavHostController,
    userState: UserState
) {
    val cardListSchoolItems = createSchoolItem()
    val cardListPersonalItems = createSettingItem()
    Column {
        UserCard(mainNavController, userState)
        CardListView(mainNavController, cardListSchoolItems)
        CardListView(mainNavController, cardListPersonalItems)
    }
}

@Composable
private fun createSettingItem() = listOf(
    NavigationItem(
        "软件官网",
        Icons.Outlined.DesktopWindows,
        MainNavRoute.SETTING_PAGE
    ),
    NavigationItem(
        "问题反馈",
        Icons.Outlined.BugReport,
        MainNavRoute.SETTING_PAGE
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
        "教务系统备用入口",
        Icons.Outlined.Bookmarks,
        MainNavRoute.SETTING_PAGE
    ),
)

@Composable
private fun UserCard(
    mainNavController: NavHostController,
    userState: UserState
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(20.dp))
            .clickable {
                mainNavController.navigate(MainNavRoute.USER_PAGE) {
                    // 重新选择同一项目时避免同一目标的多个副本
                    launchSingleTop = true
                    // 重新选择先前选定的项目时恢复状态
                    restoreState = true
                }
            },
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                UserAvatar(userState.qqNumber, userState.isLogin)
                Spacer(modifier = Modifier.size(32.dp))
                Column {
                    Text(
                        text = "用户名:${userState.userName}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "QQ号:${userState.qqNumber}",
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
