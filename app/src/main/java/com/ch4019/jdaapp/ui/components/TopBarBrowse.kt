package com.ch4019.jdaapp.ui.components

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
import com.google.accompanist.web.WebViewNavigator
import com.google.accompanist.web.WebViewState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarBrowseView(
    navController: NavHostController,
    title: String,
    urlState: WebViewState,
    navigator: WebViewNavigator
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = title)
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.navigateUp() }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBackIosNew,
                            contentDescription = null
                        )
                    }
                },
            )
        }
    ) { paddingValues ->
        LoadWeb(
            urlState,
            navigator,
            Modifier
                .padding(paddingValues)
        )
    }
}
