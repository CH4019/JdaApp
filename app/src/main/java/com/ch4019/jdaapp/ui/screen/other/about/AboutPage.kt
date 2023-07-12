package com.ch4019.jdaapp.ui.screen.other.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ButtonDefaults.buttonColors
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.content.pm.PackageInfoCompat.getLongVersionCode
import androidx.navigation.NavHostController
import com.ch4019.jdaapp.R
import com.ch4019.jdaapp.model.UserIntent
import com.ch4019.jdaapp.model.UserViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutPage(
    userViewModel: UserViewModel,
    appState: AppState,
    mainNavController: NavHostController
) {
    val context = LocalContext.current
    val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
    val versionName = packageInfo.versionName
    val versionCode=  getLongVersionCode(packageInfo)
    val isShow0 = remember {
        mutableStateOf(false)
    }
    UpDataAppDialog(appState,versionName,isShow0)
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "关于")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            mainNavController.popBackStack()
                        }
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
    ){
        AboutView(it,userViewModel,appState,versionName,versionCode,isShow0)
    }
}

@Composable
fun UpDataAppDialog(
    appState: AppState,
    versionName: String,
    isShow0: MutableState<Boolean>
) {
    if (isShow0.value) {
        Dialog(onDismissRequest = {isShow0.value=false}) {
            Card(
                shape=RoundedCornerShape(15.dp),
                modifier = Modifier
                    .padding(8.dp)
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
                        verticalAlignment = Alignment.CenterVertically
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
                                Text(
                                    text = "2.45MB",
                                )
                                Text(
                                    text = " | ",
                                )
                                Text(
                                    text = "$versionName->${appState.newVersionName}",
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        FilledTonalButton(
                            onClick = { isShow0.value=false},
                            colors = buttonColors()
                        ) {
                            Text(text = "取消")
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        FilledTonalButton(
                            onClick = { isShow0.value=false },
                            colors = buttonColors()
                        ) {
                            Text(text = "确认")
                        }
                    }
                }
            }
        }
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
    val logo = ImageBitmap.imageResource(R.drawable.app_icon)
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(32.dp))
        Image(
            bitmap = logo,
            contentDescription = "logo"
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = "JdaApp",
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.size(32.dp))
        UpDateView(userViewModel,appState,versionName,versionCode,isShow0)
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
        Text(
            text = "开源许可证",
            color = MaterialTheme.colorScheme.inversePrimary,
            modifier = Modifier
                .clickable {
                }
        )
        Text(text = "和")
        Text(
            text = "隐私政策",
            color = MaterialTheme.colorScheme.inversePrimary,
            modifier = Modifier
                .clickable {
                }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpDateView(
    userViewModel: UserViewModel,
    appState: AppState,
    versionName: String,
    versionCode: Long,
    isShow0: MutableState<Boolean>
) {
    val scope = rememberCoroutineScope()
    Card(
        onClick = {
            scope.launch {
                userViewModel.updateAppState(UserIntent.ChangeAppState(versionCode))
                isShow0.value=appState.isUpdateApp
            }
        },
        shape=RoundedCornerShape(15.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Row (
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = "当前版本")
            Spacer(modifier = Modifier.weight(1f))
            Text(text = versionName)
        }
    }
}
