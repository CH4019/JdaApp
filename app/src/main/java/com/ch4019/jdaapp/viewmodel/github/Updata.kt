package com.ch4019.jdaapp.viewmodel.github

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpDataList(
    val assets: List<Asset> = listOf(),
    val body: String = "", // 更新日志部分
    @SerialName("created_at")
    val createdAt: String = "", // 2023-03-08T08:11:55Z
    val name: String = "", //name
    @SerialName("tag_name")
    val tagName: String = "", // 版本
){
    @Serializable
    data class Asset(
        @SerialName("browser_download_url")
        val browserDownloadUrl: String = "",
        val name: String = "", // 文件名
        val size: Int = 0, // 文件大小
    )
}