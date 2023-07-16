package com.ch4019.jdaapp.model

import kotlinx.coroutines.flow.first
import rxhttp.toFlow
import rxhttp.wrapper.coroutines.CallFlow
import rxhttp.wrapper.param.RxHttp
import java.text.DecimalFormat
import javax.inject.Inject

// 从指定api获取json数据并转为kotlin,传递给viewModel的检查更新部分
class RxHttpRepository @Inject constructor() {

    private val baseUrl="https://mock.apifox.cn/m1/3019870-0-default/start/list"
    private val baseUrl1="https://jdaassistant.ch4019.fun/newApp.json"
    private val githubUrl="https://api.github.com/repos/CH4019/xinximenhu/releases/latest"

    suspend fun getNewVersionCode(): VersionCode{
        return RxHttp.get(baseUrl)
            .toFlow<VersionCode>()
            .first()
    }

    fun getRxHttp(): CallFlow<QuoteList> {
        return RxHttp.get(githubUrl)
            .toFlow()
    }

    // 文件大小格式转换
    fun bytesToMegabytes(bytes: Int): String {
        val megabyte:Double = (1024 * 1024).toDouble()
        val megabytes = bytes.toDouble() / megabyte
        val formatter = DecimalFormat("#0.00")
        return formatter.format(megabytes)
    }
}


