package com.ch4019.jdaapp.ui.screen.other.about

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.content.pm.PackageInfoCompat.getLongVersionCode
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ch4019.jdaapp.R
import com.ch4019.jdaapp.model.UserIntent
import com.ch4019.jdaapp.model.UserViewModel
import com.ch4019.jdaapp.ui.theme.JdaAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutPage(mainNavController: NavHostController) {
    val context = LocalContext.current
    val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
    val versionName = packageInfo.versionName
    val versionCode = getLongVersionCode(packageInfo)
    val isShow0 = remember {
        mutableStateOf(false)
    }
    val userViewModel: UserViewModel = hiltViewModel()
    val appState by userViewModel.appState.collectAsState()
    UpDataAppDialog(appState, versionName, isShow0)
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("关于") },
                navigationIcon = {
                    IconButton(
                        onClick = { mainNavController.popBackStack() }
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
    ) {
        AboutView(it, userViewModel, appState, versionName, versionCode, isShow0)
    }
}

@Composable
fun UpDataAppDialog(
    appState: AppState,
    versionName: String,
    isShow0: MutableState<Boolean>
) {
    if (isShow0.value) {
        Dialog(onDismissRequest = { isShow0.value = false }) {
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
                            .padding(horizontal = 8.dp,vertical = 4.dp)
                    ) {
                        Text(text = "更新日志:")
                        Text(text = appState.upDataLog)
                    }
                    FilledTonalButton(
                        onClick = {
                            isShow0.value = false
//                          TODO 执行下载更新操作，url为：appState.downloadUrl
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
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun AboutPreview() {
    JdaAppTheme {
        UpDataAppDialog(
            appState = AppState(
                isUpdateApp = false,
                versionCode = 0,
                newVersionName = "2.0.0",
                downloadUrl = "",
                upDataLog = "测试测试测试测试测试测试测试测试测试测试测试测试测试测试",
                downloadSize = "2.45"
            ),
            versionName = "1.0.0",
            isShow0 = remember { mutableStateOf(true) }
        )
    }
}

@Composable
fun AboutView(
    paddingValues: PaddingValues,
    userViewModel: UserViewModel,
    appState: AppState,
    versionName: String,
    versionCode: Long,
    isShow0: MutableState<Boolean>
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
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
            Modifier.padding(top = 16.dp),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.size(32.dp))
        UpDateView(userViewModel, appState, versionName, versionCode, isShow0)
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "纵然世间黑暗      仍有一点星光",
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun AboutBottomPromise() {
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
            }

            annotatedString.getStringAnnotations(tag = "terms", start = offset, end = offset).firstOrNull()?.let {
                Log.d("terms URL", it.item)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UpDateView(
    userViewModel: UserViewModel,
    appState: AppState,
    versionName: String,
    versionCode: Long,
    isShow0: MutableState<Boolean>
) {
    Card(
        onClick = {
            userViewModel.updateAppState(UserIntent.ChangeAppState(versionCode))
            isShow0.value = appState.isUpdateApp
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
}
