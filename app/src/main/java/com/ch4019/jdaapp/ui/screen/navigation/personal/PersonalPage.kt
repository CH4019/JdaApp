package com.ch4019.jdaapp.ui.screen.navigation.personal

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
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.BugReport
import androidx.compose.material.icons.outlined.Cable
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.DesktopWindows
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.ch4019.jdaapp.R
import com.ch4019.jdaapp.config.MainNavRoute
import com.ch4019.jdaapp.model.NavigationItem
import com.ch4019.jdaapp.ui.components.CardListView

@Composable
fun PersonalPage(
    userState:UserState,
    navHostController: NavHostController
) {
    val cardListSchoolItems = createSchoolItem()
    val cardListPersonalItems = createSettingItem()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        UserCard(userState,navHostController)
        CardListView(navHostController, cardListSchoolItems)
        CardListView(navHostController, cardListPersonalItems)
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
    // todo 这里错了
    NavigationItem(
        "教务系统备用入口",
        Icons.Outlined.Bookmarks,
        MainNavRoute.SETTING_PAGE
    ),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UserCard(
    userState:UserState,
    navController: NavHostController
) {
    val lifecycleState = LocalLifecycleOwner.current.lifecycle.observeAsState()

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
                    text = "QQ号:${userState.qqNumber}",
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
fun Lifecycle.observeAsState(): State<Lifecycle.Event> {
    val state = remember { mutableStateOf(Lifecycle.Event.ON_ANY) }
    DisposableEffect(this) {
        val observer = LifecycleEventObserver { _, event ->
            state.value = event
        }
        this@observeAsState.addObserver(observer)
        onDispose {
            this@observeAsState.removeObserver(observer)
        }
    }
    return state
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
