package com.ch4019.jdaapp.model.github

data class AppState(
    val isUpdateApp: Boolean = false,
    val versionCode: Long = 0,
    val newVersionName: String = "",
    val downloadUrl: String = "",
    val upDataLog: String = "",
    val downloadSize: String = "",
)
