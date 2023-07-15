package com.ch4019.jdaapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VersionCode(
    @SerialName("newVersionCode")
    val newVersionCode: Int = 0
)

@Serializable
data class QuoteList(
    @SerialName("assets")
    val assets: List<Asset> = listOf(),
    @SerialName("assets_url")
    val assetsUrl: String = "",
    @SerialName("author")
    val author: Author = Author(),
    @SerialName("body")
    val body: String = "", // 更新日志部分
    @SerialName("created_at")
    val createdAt: String = "", // 2023-03-08T08:11:55Z
    @SerialName("draft")
    val draft: Boolean = false,
    @SerialName("html_url")
    val htmlUrl: String = "",
    @SerialName("id")
    val id: Int = 0, //
    @SerialName("name")
    val name: String = "", //name
    @SerialName("node_id")
    val nodeId: String = "",
    @SerialName("prerelease")
    val prerelease: Boolean = false,
    @SerialName("published_at")
    val publishedAt: String = "",
    @SerialName("tag_name")
    val tagName: String = "", // 版本
    @SerialName("tarball_url")
    val tarballUrl: String = "",
    @SerialName("target_commitish")
    val targetCommitish: String = "",
    @SerialName("upload_url")
    val uploadUrl: String = "",
    @SerialName("url")
    val url: String = "",
    @SerialName("zipball_url")
    val zipballUrl: String = ""
) {
    @Serializable
    data class Asset(
        @SerialName("browser_download_url")
        val browserDownloadUrl: String = "", // 下载链接
        @SerialName("content_type")
        val contentType: String = "",
        @SerialName("created_at")
        val createdAt: String = "",
        @SerialName("download_count")
        val downloadCount: Int = 0, // 下载量
        @SerialName("id")
        val id: Int = 0,
        @SerialName("label")
        val label: String? = "",
        @SerialName("name")
        val name: String = "", // 文件名
        @SerialName("node_id")
        val nodeId: String = "",
        @SerialName("size")
        val size: Int = 0, // 文件大小
        @SerialName("state")
        val state: String = "",
        @SerialName("updated_at")
        val updatedAt: String = "",
        @SerialName("uploader")
        val uploader: Uploader = Uploader(),
        @SerialName("url")
        val url: String = ""
    ) {
        @Serializable
        data class Uploader(
            @SerialName("avatar_url")
            val avatarUrl: String = "",
            @SerialName("events_url")
            val eventsUrl: String = "",
            @SerialName("followers_url")
            val followersUrl: String = "",
            @SerialName("following_url")
            val followingUrl: String = "",
            @SerialName("gists_url")
            val gistsUrl: String = "",
            @SerialName("gravatar_id")
            val gravatarId: String = "",
            @SerialName("html_url")
            val htmlUrl: String = "",
            @SerialName("id")
            val id: Int = 0,
            @SerialName("login")
            val login: String = "",
            @SerialName("node_id")
            val nodeId: String = "",
            @SerialName("organizations_url")
            val organizationsUrl: String = "",
            @SerialName("received_events_url")
            val receivedEventsUrl: String = "",
            @SerialName("repos_url")
            val reposUrl: String = "",
            @SerialName("site_admin")
            val siteAdmin: Boolean = false,
            @SerialName("starred_url")
            val starredUrl: String = "",
            @SerialName("subscriptions_url")
            val subscriptionsUrl: String = "",
            @SerialName("type")
            val type: String = "",
            @SerialName("url")
            val url: String = ""
        )
    }

    @Serializable
    data class Author(
        @SerialName("avatar_url")
        val avatarUrl: String = "",
        @SerialName("events_url")
        val eventsUrl: String = "",
        @SerialName("followers_url")
        val followersUrl: String = "",
        @SerialName("following_url")
        val followingUrl: String = "",
        @SerialName("gists_url")
        val gistsUrl: String = "",
        @SerialName("gravatar_id")
        val gravatarId: String = "",
        @SerialName("html_url")
        val htmlUrl: String = "",
        @SerialName("id")
        val id: Int = 0,
        @SerialName("login")
        val login: String = "",
        @SerialName("node_id")
        val nodeId: String = "",
        @SerialName("organizations_url")
        val organizationsUrl: String = "",
        @SerialName("received_events_url")
        val receivedEventsUrl: String = "",
        @SerialName("repos_url")
        val reposUrl: String = "",
        @SerialName("site_admin")
        val siteAdmin: Boolean = false,
        @SerialName("starred_url")
        val starredUrl: String = "",
        @SerialName("subscriptions_url")
        val subscriptionsUrl: String = "",
        @SerialName("type")
        val type: String = "",
        @SerialName("url")
        val url: String = ""
    )
}
