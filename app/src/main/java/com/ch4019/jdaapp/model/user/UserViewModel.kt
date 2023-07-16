package com.ch4019.jdaapp.model.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {
    private val _userState = MutableStateFlow(UserState())
    val userState = _userState.asStateFlow()

    init {
        initUserState()
    }

    private fun initUserState() {
        viewModelScope.launch(Dispatchers.IO) {
            val userName = userRepository.getUserName().ifBlank { "请绑定QQ昵称" }
            val userQQ = userRepository.getUserQQ().ifBlank { "" }
            val userStudent = userRepository.getUserStudent().ifBlank { "" }
            _userState.update {
                it.copy(
                    isLogin = userRepository.getIsLogin(),
                    userName = userName,
                    qqNumber = userQQ,
                    studentNumber = userStudent
                )
            }
        }
    }

    fun refreshUserIntent(userIntent: UserState) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.setIsLogin(userIntent.isLogin)
            userRepository.setUserName(userIntent.userName)
            userRepository.setUserQQ(userIntent.qqNumber)
            userRepository.setUserStudent(userIntent.studentNumber)
            _userState.update {
                it.copy(
                    isLogin = userIntent.isLogin,
                    userName = userIntent.userName,
                    qqNumber = userIntent.qqNumber,
                    studentNumber = userIntent.studentNumber
                )
            }
        }
    }
}
