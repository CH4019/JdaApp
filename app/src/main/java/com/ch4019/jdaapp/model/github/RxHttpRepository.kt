package com.ch4019.jdaapp.model.github

import android.util.Log
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.retry
import rxhttp.toFlow
import rxhttp.wrapper.coroutines.CallFlow
import rxhttp.wrapper.param.RxHttp
import javax.inject.Inject

// 从指定api获取json数据并转为kotlin,传递给viewModel的检查更新部分
class RxHttpRepository @Inject constructor() {
//    无版本更新，
    private val baseUrl="https://mock.apifox.cn/m1/3019870-0-default/start/list"
//    有版本更新，
    private val baseUrl1="https://jdaassistant.ch4019.fun/newApp.json"
//    版本信息
    private val githubUrl="https://api.github.com/repos/CH4019/xinximenhu/releases/latest"

    suspend fun getNewVersionCode(): AppVersion {
        return RxHttp.get(baseUrl)
            .toFlow<AppVersion>()
            .catch {
                Log.e("RxHttpRepository", "getNewVersionCode: ${it.message}")
            }
            .retry(5)
            .first()
    }

    fun getRxHttp(): CallFlow<QuoteList> {
        return RxHttp.get(githubUrl)
            .toFlow()
    }
}
