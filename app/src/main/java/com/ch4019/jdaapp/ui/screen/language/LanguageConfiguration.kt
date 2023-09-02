package com.ch4019.jdaapp.ui.screen.language

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageConfiguration(
    mainNavController: NavHostController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("语言") },
                navigationIcon = {
                    IconButton(
                        onClick = { mainNavController.navigateUp() }
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
        LanguageView(it)
    }
}

@Composable
fun LanguageView(
    paddingValues: PaddingValues
) {
    Column (
        modifier = Modifier.padding(paddingValues)
    ){
        Text(text = "待补充")
    }
}