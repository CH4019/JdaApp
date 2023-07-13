package com.ch4019.jdaapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.web.LoadingState
import com.google.accompanist.web.WebViewNavigator
import com.google.accompanist.web.WebViewState

@Composable
fun LoadWeb(urlState: WebViewState, navigator: WebViewNavigator) {
    Column(modifier = Modifier.fillMaxSize()) {
        val loadingState = urlState.loadingState
        if (loadingState is LoadingState.Loading) {
            LinearProgressIndicator(
                progress = loadingState.progress,
                modifier = Modifier.fillMaxWidth()
            )
        }
        WebComponent(urlState, navigator)
    }
}
