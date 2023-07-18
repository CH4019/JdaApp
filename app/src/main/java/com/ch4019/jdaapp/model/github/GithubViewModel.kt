package com.ch4019.jdaapp.model.github

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch4019.jdaapp.util.bytesToMegabytes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
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
            }
        }
    }

    /**
     *检查更新
     * */
    fun updateAppState(nowVersionCode: Long) {
        viewModelScope.launch {
            if (appState.value.versionCode > nowVersionCode) {
                Log.d("TAG", "getGithubAppInfo: 开始执行版本信息获取")
                val quoteList = githubRepository.getRxHttp().first()
                _appState.update {
                    it.copy(
                        isUpdateApp = IsUpdateApp.UPDATE,
                        newVersionName = quoteList.tagName,
                        downloadUrl = quoteList.assets.first().browserDownloadUrl,
                        upDataLog = quoteList.body,
                        downloadSize = bytesToMegabytes(quoteList.assets.first().size),
                    )
                }
                Log.d("TAG", "getGithubAppInfo: 结束执行版本信息获取")
            } else {
                _appState.update { it.copy(isUpdateApp = IsUpdateApp.NO) }
            }
        }
    }
}
