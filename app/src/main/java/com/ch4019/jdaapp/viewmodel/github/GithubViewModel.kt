package com.ch4019.jdaapp.viewmodel.github

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch4019.jdaapp.util.bytesToMegabytes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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
//          TODO 这里需要解决当获取到值时下一次不重新获取数据，而是复用本次软件启动在当前页面获取到的数据，
//          即当获取到数据后再次打开这个页面不再获取数据，而是复用数据，
//          只有当软件清理后台后重新启动后才重新获取数据，
//          但获取数据必须在本页面加载后才获取
            if (appState.value.isUpdateApp == IsUpdateApp.NULL){
                Log.i("GithubViewModel", "No new version available")
                val quoteList = githubRepository.getRxHttp().first()
                _appState.update {
                    it.copy(
                        newVersionName = quoteList.tagName,
                        downloadUrl = quoteList.assets.first().browserDownloadUrl,
                        upDataLog = quoteList.body,
                        downloadSize = bytesToMegabytes(quoteList.assets.first().size),
                    )
                }
            }
        }
    }

    /**
     *检查更新
     * */
    fun updateAppState(nowVersionCode: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            delay(500)
            if (appState.value.versionCode > nowVersionCode) {
                _appState.update {it.copy(isUpdateApp = IsUpdateApp.UPDATE)}
            } else {
                _appState.update { it.copy(isUpdateApp = IsUpdateApp.NO) }
            }
        }
    }
}
