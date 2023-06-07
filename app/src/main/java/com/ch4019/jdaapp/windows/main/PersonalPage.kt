package com.ch4019.jdaapp.windows.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material.icons.outlined.AssignmentLate
import androidx.compose.material.icons.outlined.BugReport
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.UploadFile
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ch4019.jdaapp.model.SettingListItem
import com.ch4019.jdaapp.ui.theme.JdaAppTheme

@Composable
fun PersonalPage() {
    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        UserIcon()
        SettingCard()
    }
}

@Composable
fun UserIcon() {
    val userName = remember{ "昵称"}
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
                Image(
                    imageVector = Icons.Default.Person,
                    modifier = Modifier
                        .size(50.dp),
                    contentDescription = null
                )
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
                Image(
                    imageVector = Icons.Default.ArrowForwardIos,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun SettingCard() {
    Card (
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(20.dp))
    ){
        SettingList()
    }
}

@Composable
fun SettingList() {
    val settingListItems= listOf(
        SettingListItem(stringResourceId = "检查更新",imageResourceId = Icons.Outlined.UploadFile),
        SettingListItem(stringResourceId = "设置",imageResourceId = Icons.Outlined.Settings),
        SettingListItem(stringResourceId = "问题反馈",imageResourceId = Icons.Outlined.BugReport),
        SettingListItem(stringResourceId = "关于",imageResourceId = Icons.Outlined.AssignmentLate),
    )
    LazyColumn {
        items(settingListItems) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { }
            ) {
                Row (
                    modifier = Modifier
                        .padding(16.dp, 16.dp, 16.dp, 16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Image(
                        imageVector = it.imageResourceId,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(text = it.stringResourceId)
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        imageVector = Icons.Outlined.ArrowForwardIos,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PersonalPreview() {
    JdaAppTheme {
        PersonalPage()
    }
}