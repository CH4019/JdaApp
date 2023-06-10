package com.ch4019.jdaapp.windows.main.personal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ch4019.jdaapp.ui.theme.JdaAppTheme

@Composable
fun SettingPage() {
    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        SettingView()
    }
}

@Composable
fun SettingView() {
    //待编写
    Card (
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(20.dp))
    ){
        Text(text = "测试")
    }
}

@Preview(showBackground = true)
@Composable
fun SettingPagePreview() {
    JdaAppTheme {
        SettingPage()
    }
}