package com.ch4019.jdaapp.ui.screen.mainnav.personal.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ch4019.jdaapp.ui.theme.JdaAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingPage() {
    Scaffold {
        SettingView(it)
    }
}

@Composable
fun SettingView(
    paddingValues: PaddingValues,
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
    ) {
        SettingTitle()
        CardList()
    }
}

@Composable
fun SettingTitle() {
    Column (
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            .fillMaxWidth()
    ){
        Text(text = "测试")
    }
}
//编写一个设置卡片，要求第一个图标和文字间距32.dp，文字和第二个图标使用空白填充满宽度
@Composable
fun CardList() {
    Card (
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(20.dp))
            .clickable {
//                           实现点击功能
            }
    ){
        Row (
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ){
            Image(imageVector = Icons.Default.Settings, contentDescription = null)
            Spacer(modifier = Modifier.size(32.dp))
            Text(text = "测试")
            //填充满宽度，使第二个图标在最右边
            Spacer(modifier = Modifier.weight(1f))
            Icon(imageVector = Icons.Default.ArrowForwardIos, contentDescription = null)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun SettingPagePreview() {
    JdaAppTheme {
        SettingPage()
    }
}