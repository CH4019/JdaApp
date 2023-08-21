package com.ch4019.jdaapp.ui.screen.main.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ch4019.jdaapp.ui.components.CardButton

@Composable
fun HomePage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        CardButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "校历")
        }
        CardButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "夏冬季时间表")
        }
        CardButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "校车时间")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview(){
    HomePage()
}