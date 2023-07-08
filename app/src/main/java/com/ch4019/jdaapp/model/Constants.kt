package com.ch4019.jdaapp.model

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object DataStoreCont{
    const val DATA_STORE_NAME = "app"
    val IS_LOGIN = booleanPreferencesKey("isLogin")
//    val USER_NAME = stringPreferencesKey("user_Name")
//    val USER_QQ = stringPreferencesKey("user_QQ")
//    val USER_STUDENT = stringPreferencesKey("user_Student")
}