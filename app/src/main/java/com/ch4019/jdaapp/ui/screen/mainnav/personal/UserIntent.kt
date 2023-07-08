package com.ch4019.jdaapp.ui.screen.mainnav.personal

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.prefs.Preferences
import javax.inject.Inject

sealed class UserIntent {
  data class ChangeUserState(
      val userName: String,
      val qqNumber: String,
      val studentNumber: String,
  ) : UserIntent()
}
//@HiltViewModel
//class UserViewModel @Inject constructor(
//    private val dataStore: DataStore<Preferences>
//):ViewModel() {
    class UserViewModel :ViewModel() {
    private val _userState = MutableStateFlow(UserState())
    val userState = _userState
    init {
        initUserState()
    }

    fun refreshUserIntent(userIntent: UserIntent){
        when(userIntent){
           is UserIntent.ChangeUserState -> _userState.apply {
                value = value.copy(
                    userName = userIntent.userName,
                    qqNumber = userIntent.qqNumber,
                    studentNumber = userIntent.studentNumber,
                    isLogin = true
                )
            }
        }

    }

    private fun initUserState(){
        _userState.apply {
            userState.value = userState.value.copy(
                userName = "请绑定QQ昵称",
                qqNumber = "",
                studentNumber = "",
            )
        }
        updateUserState()
    }

    private fun  updateUserState() {
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
}
