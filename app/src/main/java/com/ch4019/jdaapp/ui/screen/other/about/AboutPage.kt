package com.ch4019.jdaapp.ui.screen.other.about

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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.pm.PackageInfoCompat.getLongVersionCode
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ch4019.jdaapp.R
import com.ch4019.jdaapp.model.github.AppState
import com.ch4019.jdaapp.model.github.GithubViewModel
import com.ch4019.jdaapp.ui.theme.JdaAppTheme
import com.ch4019.jdaapp.util.getPackageInfoCompat

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
    showDialog: MutableState<Boolean>,
    appState: AppState,
    versionName: String
) {
    DynamicHeightDialog(onDismissRequest = { showDialog.value = !showDialog.value }) {
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
                        // TODO 执行下载更新操作，url为：appState.downloadUrl
                        showDialog.value = !showDialog.value
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

@Preview(showBackground = true)
@Composable
fun AboutPreview() {
    JdaAppTheme {
        UpDataAppDialog(
            showDialog = remember {
                mutableStateOf(true)
            },
            appState = AppState(
                isUpdateApp = false,
                versionCode = 0,
                newVersionName = "2.0.0",
                downloadUrl = "",
                upDataLog = "测试测试测试测试测试测试测试测试测试测试测试测试测试测试",
                downloadSize = "2.45"
            ),
            versionName = "1.0.0"
        )
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
private fun CurrentVersion() {
    val context = LocalContext.current
    val packageInfo = context.packageManager.getPackageInfoCompat(context.packageName, 0)
    val versionName = packageInfo.versionName
    val versionCode = getLongVersionCode(packageInfo)
    val githubViewModel: GithubViewModel = hiltViewModel()
    val appState by githubViewModel.appState.collectAsState()
    val showDialog = remember { mutableStateOf(false) }
    val showToast = remember { mutableStateOf(false) }
    val count = remember { mutableStateOf(false) }

//    TODO 这里需要对逻辑进行处理
//    监听是否有新版本更新
//    当前问题：
//      有更新后取消更新后并不能再次显示弹窗(无法再次触发更新弹窗)
//      如果没有更新无法判断(没有返回无更新的值，因为appState.isUpdateApp默认为false)
//      接口有每小时60次访问限制，还需有无网络时提示
//     解决方案一：
//        获取githubViewModel.updateAppState(versionCode)是否执行完成，当执行完后可以
//        通过appState.isUpdateApp判断是否有无更新

//    当前解决方案：对appState.isUpdateApp进行监听，并使用count来排除初始情况
//    出现无版本更新时无提示出现
    LaunchedEffect(appState.isUpdateApp){
//        此协程当无更新时只执行一次，导致无法出现toast提示
        if (appState.isUpdateApp){
//            若有更新则点击出现弹窗，取消后点击仍需可点击显示
            showDialog.value = true
            showToast.value = false
        }else {
//            若无更新，则出现提示无版本更新
            Log.d("count", count.value.toString())
            if (!count.value){
                count.value = true
            }else if (count.value){
                showToast.value = true
            }
            Log.d("showToast", showToast.value.toString())
        }
    }
    Card(
        onClick = {
//          需要实现先执行数据获取，再执行isShow0赋值显示
            githubViewModel.updateAppState(versionCode)
//          需要确定上面事件执行完后再执行下面操作
            showDialog.value = appState.isUpdateApp
            if (showToast.value){
                Toast.makeText(
                    context,
                    "无新版本可用",
                    Toast.LENGTH_SHORT
                ).show()
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
    if (showDialog.value) {
        UpDataAppDialog(showDialog, appState = appState, versionName = versionName)
    }
}
