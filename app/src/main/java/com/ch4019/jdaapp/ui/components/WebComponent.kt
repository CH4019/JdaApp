package com.ch4019.jdaapp.ui.components

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
    //val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
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
            modifier = Modifier.fillMaxSize(),
            navigator = navigator,
            onCreated = { webView ->
                webView.settings.javaScriptEnabled = true
                webView.settings.setSupportZoom(true)
                webView.settings.builtInZoomControls = true
                webView.settings.displayZoomControls = false
//                webView.setDownloadListener { url, userAgent, contentDisposition, mimeType, contentLength ->
//                    Log.d("下载地址","下载地址:$url")
//                    val fileName = contentDisposition?.substringAfter("filename=")?.removeSurrounding("\"")
//                    val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
//                    val request = DownloadManager.Request(Uri.parse(url))
//                    request.setMimeType(mimeType)
//                    val cookies: String = CookieManager.getInstance().getCookie(url)
//                    request.addRequestHeader("cookie", cookies)
//                    request.addRequestHeader("User-Agent", userAgent)
//                    request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimeType))
//                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
//                    if (fileName != null) {
//                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
//                    }else{
//                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "JdaApp下载文件")
//                    }
//                    downloadManager.enqueue(request)
//                }
            },
            client = webClient
        )
    }
}
