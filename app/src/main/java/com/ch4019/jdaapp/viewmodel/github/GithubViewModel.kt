package com.ch4019.jdaapp.viewmodel.github

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch4019.jdaapp.util.bytesToMegabytes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(private val githubRepository: GithubRepository) : ViewModel() {
    private val _appState = MutableStateFlow(AppState())
    val appState = _appState.asStateFlow()
    init {
        initAppState()
    }

    /**
     * 软件启动时获取最新版版本号
     */
    private fun initAppState() {
//        更换okhttp来获取数据
        viewModelScope.launch(Dispatchers.IO) {
            _appState.update {
                it.copy(versionCode = githubRepository.getNewVersionCode().newVersionCode)
            }
            if (appState.value.isUpdateApp == IsUpdateApp.NULL){
                Log.i("GithubViewModel", "No new version available")
                val upDataList = githubRepository.upData()
                _appState.update {
                    it.copy(
                        newVersionName = upDataList.tagName,
                        downloadUrl = upDataList.assets.first().browserDownloadUrl,
                        upDataLog = upDataList.body,
                        downloadSize = bytesToMegabytes(upDataList.assets.first().size),
                    )
                }
            }
        }
    }

    fun upDateDownload(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val url = appState.value.downloadUrl
            val fileName = appState.value.newVersionName
            val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val request = DownloadManager.Request(Uri.parse(url))
            request.setTitle(fileName)
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
            downloadManager.enqueue(request)
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
