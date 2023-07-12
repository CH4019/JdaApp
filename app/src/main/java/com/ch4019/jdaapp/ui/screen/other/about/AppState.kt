package com.ch4019.jdaapp.ui.screen.other.about

data class AppState(
    val isUpdateApp : Boolean = false,
    val versionCode : Long = 0,
    val newVersionName : String = ""
)