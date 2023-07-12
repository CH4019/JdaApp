package com.ch4019.jdaapp.model

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferencesRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    suspend fun getIsLogin() = getValue(DataStoreCont.IS_LOGIN) ?: false
    suspend fun setIsLogin(newValue: Boolean) = setValue(DataStoreCont.IS_LOGIN, newValue)
    suspend fun getUserName(): String = getValue(DataStoreCont.USER_NAME) ?: ""
    suspend fun setUserName(newUserName: String) = setValue(DataStoreCont.USER_NAME, newUserName)
    suspend fun getUserQQ(): String = getValue(DataStoreCont.USER_QQ) ?: ""
    suspend fun setUserQQ(newUserQQ: String) = setValue(DataStoreCont.USER_QQ, newUserQQ)
    suspend fun getUserStudent(): String = getValue(DataStoreCont.USER_STUDENT) ?: ""
    suspend fun setUserStudent(studentNumber: String) = setValue(DataStoreCont.USER_STUDENT, studentNumber)

    private suspend fun <T : Any> getValue(key: Preferences.Key<T>): T? {
        return dataStore.data.map { it[key] }.first()
    }

    private suspend fun <T : Any> setValue(
        key: Preferences.Key<T>,
        value: T
    ) {
        dataStore.edit {
            it[key] = value
        }
    }
}
