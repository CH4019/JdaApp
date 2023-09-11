package com.ch4019.jdaapp.util

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.shouldShowRationale
import kotlinx.coroutines.Dispatchers

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Permission() {
    val context = LocalContext.current
    val locationPermissionsState = rememberMultiplePermissionsState(
        when {
            Build.VERSION.SDK_INT > Build.VERSION_CODES.S_V2
            -> listOf(android.Manifest.permission.READ_MEDIA_IMAGES)
            else
            -> listOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
    )

    locationPermissionsState.permissions.forEach {
        when(it.status){
            PermissionStatus.Granted -> { }
            is PermissionStatus.Denied -> {
                if (it.status.shouldShowRationale){
                    Toast.makeText(context, "权限已被禁止", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context, "请在设置中手动授予权限", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    LaunchedEffect(Dispatchers.IO) {
        locationPermissionsState.launchMultiplePermissionRequest()
    }
}