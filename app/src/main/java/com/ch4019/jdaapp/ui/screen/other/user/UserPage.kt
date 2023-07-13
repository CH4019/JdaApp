package com.ch4019.jdaapp.ui.screen.other.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ch4019.jdaapp.model.UserIntent
import com.ch4019.jdaapp.model.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserPage(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("用户信息") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBackIosNew,
                            contentDescription = null
                        )
                    }
                },
            )
        }
    ) {
        UserView(it, navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UserView(
    paddingValues: PaddingValues,
    navController: NavHostController,
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(start = 32.dp, top = 32.dp, end = 32.dp)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var userName by remember { mutableStateOf("") }
        OutlinedTextField(
            value = userName,
            onValueChange = { userName = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("昵称") },
            placeholder = { Text("请绑定QQ昵称") }
        )

        var qqNumber by remember { mutableStateOf("") }
        OutlinedTextField(
            value = qqNumber,
            onValueChange = { qqNumber = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            label = { Text("QQ号") },
        )

        var studentNumber by remember { mutableStateOf("") }
        OutlinedTextField(
            value = studentNumber,
            onValueChange = { studentNumber = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            label = { Text("学号") },
        )
        ConfirmButton(qqNumber, userName, studentNumber, navController)
        Spacer(modifier = Modifier.weight(1f))
        Text(
            "本页面信息用于显示头像,昵称等",
            Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
private fun ConfirmButton(
    qqNumber: String,
    userName: String,
    studentNumber: String,
    mainNavController: NavHostController
) {
    val userViewModel: UserViewModel = hiltViewModel()
    FilledTonalButton(
        onClick = {
            if (qqNumber.isBlank()) {
                userViewModel.refreshUserIntent(
                    UserIntent.ChangeUserState(
                        userName,
                        qqNumber,
                        studentNumber,
                        false
                    )
                )
            } else {
                userViewModel.refreshUserIntent(
                    UserIntent.ChangeUserState(
                        userName,
                        qqNumber,
                        studentNumber,
                        true
                    )
                )
            }
            mainNavController.navigateUp()
        },
        modifier = Modifier
            .width(150.dp)
            .padding(top = 64.dp)
    ) {
        Text("确认")
    }
}
