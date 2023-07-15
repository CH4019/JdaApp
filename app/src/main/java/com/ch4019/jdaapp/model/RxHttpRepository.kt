package com.ch4019.jdaapp.model


import kotlinx.coroutines.flow.first
import rxhttp.toFlow
import rxhttp.wrapper.param.RxHttp
import javax.inject.Inject

//从指定api获取json数据并转为kotlin,传递给viewmodel的检查更新部分
class RxHttpRepository @Inject constructor(){
    suspend fun getRxHttp(): QuoteList {
        return RxHttp.get("https://api.github.com/repos/CH4019/xinximenhu/releases/latest")
            .toFlow<QuoteList>()
            .first()
    }
}


