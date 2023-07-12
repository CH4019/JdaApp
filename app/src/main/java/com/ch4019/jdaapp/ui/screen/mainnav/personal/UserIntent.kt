package com.ch4019.jdaapp.ui.screen.mainnav.personal

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch4019.jdaapp.model.DataStoreCont.IS_LOGIN
import com.ch4019.jdaapp.model.DataStoreCont.USER_NAME
import com.ch4019.jdaapp.model.DataStoreCont.USER_QQ
import com.ch4019.jdaapp.model.DataStoreCont.USER_STUDENT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

sealed class UserIntent {
    data class ChangeUserState(
        val userName: String,
        val qqNumber: String,
        val studentNumber: String,
        val isLogin: Boolean
    ) : UserIntent()
}

@HiltViewModel
class UserViewModel @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : ViewModel() {
    private val _userState = MutableStateFlow(UserState())
    val userState = _userState.asStateFlow()

    init {
        initUser()
        viewModelScope.launch {
            initUserState()
        }
    }

    private suspend fun getIsLogin() = getValue(IS_LOGIN) ?: false
    private suspend fun setIsLogin(newValue: Boolean) = setValue(IS_LOGIN, newValue)
    private suspend fun getUserName(): String = getValue(USER_NAME) ?: ""
    private suspend fun setUserName(newUserName: String) = setValue(USER_NAME, newUserName)
    private suspend fun getUserQQ(): String = getValue(USER_QQ) ?: ""
    private suspend fun setUserQQ(newUserQQ: String) = setValue(USER_QQ, newUserQQ)
    private suspend fun getUserStudent(): String = getValue(USER_STUDENT) ?: ""
    private suspend fun setUserStudent(studentNumber: String) = setValue(USER_STUDENT, studentNumber)

    private suspend fun initUserState() = withContext(Dispatchers.IO) {
        val getUserName = getUserName().ifBlank { "请绑定QQ昵称" }
        val getUserQQ = getUserQQ().ifBlank { "" }
        val getUserStudent = getUserStudent().ifBlank { "" }
        _userState.value = _userState.value.copy(
            isLogin = getIsLogin(),
            userName = getUserName,
            qqNumber = getUserQQ,
            studentNumber = getUserStudent
        )
    }

    fun refreshUserIntent(userIntent: UserIntent) {
        when (userIntent) {
            is UserIntent.ChangeUserState ->
                viewModelScope.launch(Dispatchers.IO) {
                    setIsLogin(userIntent.isLogin)
                    setUserName(userIntent.userName)
                    setUserQQ(userIntent.qqNumber)
                    setUserStudent(userIntent.studentNumber)
                    initUserState()
                }
        }
    }

    private fun initUser() {
        _userState.apply {
            value = userState.value.copy(
                userName = "请绑定QQ昵称",
                qqNumber = "",
                studentNumber = "",
            )
        }
    }

    private fun updateUserState() {
        viewModelScope.launch(Dispatchers.IO) {
            _userState.apply {
                while (true) {
                    value = value.copy(
                        userName = value.userName,
                        qqNumber = value.qqNumber,
                        studentNumber = value.studentNumber
                    )
                    delay(1000)
                }
            }
        }
    }

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
