package com.ch4019.jdaapp.model

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DataStoreCont.DATA_STORE_NAME)
object DataStoreCont{
    const val DATA_STORE_NAME = "app"
    val IS_LOGIN = booleanPreferencesKey("isLogin")
    val USER_NAME = stringPreferencesKey("user_Name")
    val USER_QQ = stringPreferencesKey("user_QQ")
    val USER_STUDENT = stringPreferencesKey("user_Student")
}