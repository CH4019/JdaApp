package com.ch4019.jdaapp.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch4019.jdaapp.ui.screen.mainnav.personal.UserState
import com.ch4019.jdaapp.ui.screen.other.about.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    data class ChangeAppState(
        val versionCode: Long,
    ) : UserIntent()
}
@HiltViewModel
class UserViewModel @Inject constructor(
//    private val dataStore: DataStore<Preferences>,
    private val preferencesRepository: PreferencesRepository,
):ViewModel() {
    private val _userState = MutableStateFlow(UserState())
    val userState = _userState.asStateFlow()

    private  val _appState = MutableStateFlow(AppState())
    val appState = _appState.asStateFlow()

    init {
        initUser()
        viewModelScope.launch {
            initUserState()
        }
    }

    fun updateAppState(userIntent: UserIntent){
        val newVersionCode = 4
        val newVersionName = "2.0.0"
        when(userIntent){
            is UserIntent.ChangeAppState ->
                viewModelScope.launch (Dispatchers.IO){
                    if (userIntent.versionCode<newVersionCode){
                        _appState.value = _appState.value.copy(
                            isUpdateApp = true,
                            versionCode = userIntent.versionCode,
                            newVersionName = newVersionName
                        )
                    }else{
                        _appState.value = _appState.value.copy(
                            isUpdateApp = false,
                            versionCode = userIntent.versionCode,
                            newVersionName = newVersionName
                        )
                    }
                    Log.d("TAG", "UpDateView: ${appState.value.isUpdateApp}")
                }
            else -> {}
        }
    }

    private suspend fun initUserState() = withContext(Dispatchers.IO){
        val getUserName = preferencesRepository.getUserName().ifBlank { "请绑定QQ昵称" }
        val getUserQQ = preferencesRepository.getUserQQ().ifBlank { "" }
        val getUserStudent = preferencesRepository.getUserStudent().ifBlank { "" }
        _userState.value =  _userState.value.copy(
            isLogin = preferencesRepository.getIsLogin(),
            userName = getUserName,
            qqNumber = getUserQQ,
            studentNumber = getUserStudent
        )
    }

    fun refreshUserIntent(userIntent: UserIntent){
        when(userIntent){
            is UserIntent.ChangeUserState ->
                viewModelScope.launch (Dispatchers.IO){
                    preferencesRepository.setIsLogin(userIntent.isLogin)
                    preferencesRepository.setUserName(userIntent.userName)
                    preferencesRepository.setUserQQ(userIntent.qqNumber)
                    preferencesRepository.setUserStudent(userIntent.studentNumber)
                    initUserState()
                }

            else -> {}
        }
    }

    private fun initUser(){

        _userState.apply {
            value= userState.value.copy(
                userName = "请绑定QQ昵称",
                qqNumber = "",
                studentNumber = "",
            )
        }
    }
}
