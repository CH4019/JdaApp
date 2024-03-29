package com.ch4019.jdaapp.ui.screen.about

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.pm.PackageInfoCompat.getLongVersionCode
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ch4019.jdaapp.R
import com.ch4019.jdaapp.util.getPackageInfoCompat
import com.ch4019.jdaapp.viewmodel.github.AppState
import com.ch4019.jdaapp.viewmodel.github.GithubViewModel
import com.ch4019.jdaapp.viewmodel.github.IsUpdateApp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutPage(mainNavController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("关于") },
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
        },
        bottomBar = {
            AboutBottomPromise()
        }
    ) { paddingValues ->
        AboutView(modifier = Modifier.padding(paddingValues))
    }
}

@Composable
private fun UpDataAppDialog(
    onDismiss: () -> Unit,
    showDialog: Boolean,
    githubViewModel:GithubViewModel,
    appState: AppState,
    versionName: String
) {
    val context = LocalContext.current
    if (showDialog) {
        DynamicHeightDialog(onDismissRequest = onDismiss) {
            Card(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "发现新版本")
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.app_icon),
                            contentDescription = null,
                            modifier = Modifier
                                .size(50.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column(
                            modifier = Modifier
                                .height(50.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "JdaApp",
                                fontWeight = FontWeight.Bold
                            )
                            Row {
                                Text(text = "${appState.downloadSize}MB")
                                Text(text = " | ")
                                Text(text = "$versionName -> ${appState.newVersionName}")
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .fillMaxHeight(0.25f)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Text(text = "更新日志:")
                        Text(text = appState.upDataLog)
                    }
                    FilledTonalButton(
                        onClick = {
                            onDismiss()
                            Log.d("onDismiss", "下载链接${appState.downloadUrl}")
                            githubViewModel.upDateDownload(context)
                        },
                        colors = ButtonDefaults.elevatedButtonColors(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    ) {
                        Text(
                            text = "确认更新",
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun DynamicHeightDialog(
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxHeight()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onDismissRequest,
                )
        ) {
            content()
        }
    }
}

@Composable
private fun AboutView(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            bitmap = ImageBitmap.imageResource(R.drawable.app_icon),
            contentDescription = "logo",
            Modifier.padding(top = 32.dp)
        )
        Text(
            text = "JdaApp",
            Modifier.padding(top = 16.dp, bottom = 32.dp),
            fontWeight = FontWeight.Bold
        )
        CurrentVersion()
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "纵然世间黑暗      仍有一点星光",
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun AboutBottomPromise() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        val annotatedString = buildAnnotatedString {
            pushStringAnnotation(tag = "policy", annotation = "https://google.com/policy")
            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.inversePrimary)) {
                append("开源许可证")
            }
            pop()

            append("和")

            pushStringAnnotation(tag = "terms", annotation = "https://google.com/terms")
            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.inversePrimary)) {
                append("隐私政策")
            }

            pop()
        }
        ClickableText(annotatedString) { offset ->
            annotatedString.getStringAnnotations(tag = "policy", start = offset, end = offset).firstOrNull()?.let {
                Log.d("policy URL", it.item)
                Toast.makeText(context, "用户点击开源许可证", Toast.LENGTH_SHORT).show()
            }

            annotatedString.getStringAnnotations(tag = "terms", start = offset, end = offset).firstOrNull()?.let {
                Log.d("terms URL", it.item)
                Toast.makeText(context, "用户点击隐私政策", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CurrentVersion() {
    val context = LocalContext.current
    val packageInfo = context.packageManager.getPackageInfoCompat(context.packageName, 0)
    val versionName = packageInfo.versionName
    val versionCode = getLongVersionCode(packageInfo)
    val githubViewModel: GithubViewModel = hiltViewModel()
    val appState by githubViewModel.appState.collectAsState()
    var showDialog by remember(appState.isUpdateApp) { mutableStateOf(appState.isUpdateApp == IsUpdateApp.UPDATE ) }
    /**
     * 第一次运行无更新使用此提示
    **/
    SideEffect {
        if (appState.isUpdateApp == IsUpdateApp.NO){
            Toast.makeText(context, "无新版本可用", Toast.LENGTH_SHORT).show()
        }
    }
    Card(
        onClick = {
            githubViewModel.updateAppState(versionCode)
//          第二次及以后点击时通过下面来实现提示
            if (appState.isUpdateApp == IsUpdateApp.UPDATE){
                showDialog = true
            }else if (appState.isUpdateApp == IsUpdateApp.NO) {
                Toast.makeText(context, "无新版本可用", Toast.LENGTH_SHORT).show()
            }
        },
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            Text("当前版本")
            Spacer(Modifier.weight(1f))
            Text(versionName)
        }
    }
    UpDataAppDialog({ showDialog = false }, showDialog, githubViewModel,appState = appState, versionName = versionName)
}


