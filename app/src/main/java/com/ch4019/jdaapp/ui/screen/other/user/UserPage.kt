package com.ch4019.jdaapp.ui.screen.other.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ch4019.jdaapp.ui.screen.mainnav.personal.UserIntent
import com.ch4019.jdaapp.ui.screen.mainnav.personal.UserState
import com.ch4019.jdaapp.ui.screen.mainnav.personal.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserPage(
    mainNavController: NavHostController,
    userViewModel: UserViewModel,
    userState: UserState
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "用户信息")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            mainNavController.popBackStack()
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
        UserView(it, mainNavController, userViewModel, userState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserView(
    paddingValues: PaddingValues,
    mainNavController: NavHostController,
    userViewModel: UserViewModel,
    userState: UserState
) {
    val userName1 = remember {
        mutableStateOf(userState.userName)
    }
    val qqNumber1 = remember {
        mutableStateOf(userState.qqNumber)
    }
    val studentNumber1 = remember {
        mutableStateOf(userState.studentNumber)
    }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = userName1.value,
                onValueChange = {
                    userName1.value = it
                },
                modifier = Modifier
                    .fillMaxWidth(),
                label = {
                    Text(text = "昵称")
                },
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = qqNumber1.value,
                onValueChange = {
                    qqNumber1.value = it
                },
                modifier = Modifier
                    .fillMaxWidth(),
                label = {
                    Text(text = "QQ号")
                },
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = studentNumber1.value,
                onValueChange = {
                    studentNumber1.value = it
                },
                modifier = Modifier
                    .fillMaxWidth(),
                label = {
                    Text(text = "学号")
                },
            )
            Spacer(modifier = Modifier.height(64.dp))
            FilledTonalButton(
                onClick = {
                    if (qqNumber1.value.isBlank()) {
                        userViewModel.refreshUserIntent(
                            UserIntent.ChangeUserState(
                                userName1.value,
                                qqNumber1.value,
                                studentNumber1.value,
                                false
                            )
                        )
                    } else {
                        userViewModel.refreshUserIntent(
                            UserIntent.ChangeUserState(
                                userName1.value,
                                qqNumber1.value,
                                studentNumber1.value,
                                true
                            )
                        )
                    }
                    mainNavController.popBackStack()
                },
                modifier = Modifier
                    .width(150.dp)
            ) {
                Text(text = "确认")
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "本页面信息用于显示头像,昵称等",
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}
