package com.ch4019.jdaapp.ui.screen.mainnav.second

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ch4019.jdaapp.ui.components.WebComponent
import com.google.accompanist.web.LoadingState
import com.google.accompanist.web.WebViewNavigator
import com.google.accompanist.web.WebViewState

@Composable
fun SecondPage(
    urlState2: WebViewState,
    navigator2: WebViewNavigator,
) {
    Column (
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
            //.verticalScroll(rememberScrollState()),
        ){
            val loadingState = urlState2.loadingState
            if (loadingState is LoadingState.Loading) {
                LinearProgressIndicator(
                    progress = loadingState.progress,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            WebComponent(urlState2, navigator2)
        }
    }
}