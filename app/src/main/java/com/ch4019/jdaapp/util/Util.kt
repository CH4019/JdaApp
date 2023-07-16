package com.ch4019.jdaapp.util

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import okhttp3.internal.format

fun PackageManager.getPackageInfoCompat(packageName: String, flags: Int = 0): PackageInfo =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(flags.toLong()))
    } else {
        @Suppress("DEPRECATION")
        getPackageInfo(packageName, flags)
    }

/**
 * 文件大小格式转换
 * */
fun bytesToMegabytes(bytes: Int): String {
    val megabytes = bytes / (1024 * 1024).toFloat()
    return format("%.2f", megabytes)
}
