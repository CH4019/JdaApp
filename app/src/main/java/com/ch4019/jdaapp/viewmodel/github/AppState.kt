package com.ch4019.jdaapp.viewmodel.github

data class AppState(
    val isUpdateApp: IsUpdateApp = IsUpdateApp.NULL,
    val versionCode: Long = 0,
    val newVersionName: String = "",
    val downloadUrl: String = "",
    val upDataLog: String = "",
    val downloadSize: String = "",
)
