package com.ch4019.jdaapp.components

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.util.Log
import android.webkit.WebView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.WebViewNavigator
import com.google.accompanist.web.WebViewState

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebComponent(
    urlState: WebViewState,
    navigator: WebViewNavigator,
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        // A custom WebViewClient and WebChromeClient can be provided via subclassing
        val webClient = remember {
            object : AccompanistWebViewClient() {
                override fun onPageStarted(
                    view: WebView,
                    url: String?,
                    favicon: Bitmap?
                ) {
                    super.onPageStarted(view, url, favicon)
                    Log.d("Accompanist WebView", "Page started loading for $url")
                }
            }
        }
        WebView(
            state = urlState,
            modifier = Modifier
                .weight(1f),
            navigator = navigator,
            onCreated = { webView ->
                webView.settings.javaScriptEnabled = true
            },
            client = webClient
        )
    }
}