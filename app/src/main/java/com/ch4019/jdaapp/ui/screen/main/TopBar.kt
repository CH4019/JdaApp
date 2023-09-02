package com.ch4019.jdaapp.ui.screen.main

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.web.WebViewNavigator
import com.google.accompanist.web.WebViewState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun TopBar(
    pagerState: PagerState,
    navigator: WebViewNavigator,
    webViewState1: WebViewState,
    webViewState2: WebViewState
) {
    val context = LocalContext.current
    val titles = listOf("信息门户", "教务系统", "我的")
    TopAppBar(
        title = { Text(titles[pagerState.currentPage]) },
        actions = {
            if (pagerState.currentPage != pagerState.pageCount - 1) {
                IconButton(onClick = {
                    navigator.reload()
                }) { Icon(Icons.Default.Refresh, null) }
                IconButton(onClick = {
                    val sendIntent: Intent = when (pagerState.currentPage){
                        0 ->{
                            Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, webViewState1.lastLoadedUrl)
                                type = "text/plain"
                            }
                        }
                        else ->{
                             Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, webViewState2.lastLoadedUrl)
                                type = "text/plain"
                            }
                        }
                    }
                    val shareIntent = Intent.createChooser(sendIntent, "share")
                    context.startActivity(shareIntent)
                }) { Icon(Icons.Default.Share, null) }
                IconButton(onClick = {
                    val webpage: Uri = when (pagerState.currentPage){
                        0 ->{ Uri.parse(webViewState1.lastLoadedUrl) }
                        else ->{Uri.parse(webViewState2.lastLoadedUrl)}
                    }
                    val intent = Intent(Intent.ACTION_VIEW, webpage)
                    if (intent.resolveActivity(context.packageManager) != null) {
                        try {
                            Toast.makeText(context, "正在跳转浏览器", Toast.LENGTH_SHORT).show()
                            context.startActivity(intent)
                        } catch (e: ActivityNotFoundException) {
                            Toast.makeText(context, "无软件可以打开", Toast.LENGTH_SHORT).show()
                        }
                    }
                }) { Icon(Icons.Default.Send, null) }
            }
        },
        modifier = Modifier.background(MaterialTheme.colorScheme.onPrimary)
    )
}
