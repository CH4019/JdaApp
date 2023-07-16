package com.ch4019.jdaapp.model.github

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch4019.jdaapp.util.bytesToMegabytes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(private val githubRepository: RxHttpRepository) : ViewModel() {
    private val _appState = MutableStateFlow(AppState())
    val appState = _appState.asStateFlow()

    init {
        initAppState()
    }

    /**
     * 软件启动时获取最新版版本号
     */
    private fun initAppState() {
        viewModelScope.launch(Dispatchers.IO) {
            _appState.update {
                it.copy(versionCode = githubRepository.getNewVersionCode().newVersionCode)
//                it.copy(versionCode = 100)
            }
        }
    }

    /**
     *检查更新
     * */
    fun updateAppState(nowVersionCode: Long) {
        if (appState.value.versionCode > nowVersionCode) {
            getGithubAppInfo()
        } else {
            _appState.update { it.copy(isUpdateApp = false) }
        }
        Log.d("TAG", "UpDateView: ${appState.value.isUpdateApp}")
    }

//    private fun getGithubAppInfo(app: AppState) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val newVersionCode = _appState.value.versionCode
//            if (app.versionCode < newVersionCode) {
////                      TODO 需要实现执行玩数据获取后才执行后续步骤
//                getGithubAppInfo()
//                Log.d("TAG", "UpDateView: ${appState.value.isUpdateApp}")
//            } else {
//                _appState.update {
//                    it.copy(
//                        isUpdateApp = false,
//                    )
//                }
//            }
//        }
//    }

    private fun getGithubAppInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            githubRepository.getRxHttp().collect { quoteList ->
                _appState.update {
                    it.copy(
                        isUpdateApp = true,
                        newVersionName = quoteList.tagName,
                        downloadUrl = quoteList.assets.first().browserDownloadUrl,
                        upDataLog = quoteList.body,
                        downloadSize = bytesToMegabytes(quoteList.assets.first().size),
                    )
                }
            }
        }
    }
}
