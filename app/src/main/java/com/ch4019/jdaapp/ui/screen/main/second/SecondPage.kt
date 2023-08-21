package com.ch4019.jdaapp.ui.screen.main.second

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ch4019.jdaapp.ui.components.CardButton

@Composable
fun SecondPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        CardButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "课表查询")
        }
        CardButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "成绩查询")
        }
        CardButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "考试信息查询")
        }
    }
}