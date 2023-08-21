package com.ch4019.jdaapp.ui.screen.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ch4019.jdaapp.ui.components.CardButton

@Composable
fun LoginPage() {
    var userName by remember { mutableStateOf("") }
    var passWord by remember { mutableStateOf("") }
    val cookie = remember{ mutableStateOf("")}
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = userName,
            onValueChange = { userName = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("用户名") },
            placeholder = { Text("请输入昵称") }
        )
        OutlinedTextField(
            value = passWord,
            onValueChange = { passWord = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            label = { Text("密码") },
        )
        CardButton(
            modifier = Modifier.padding(top = 16.dp),
            onClick = {

            }
        ) {
            Text(text = "登录")
        }
        Text(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            text = cookie.value
        )
    }
}