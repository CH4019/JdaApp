package com.ch4019.jdaapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuoteList(
    @SerialName("assets")
    val assets: List<Asset> = listOf(),
    @SerialName("assets_url")
    val assetsUrl: String = "", // https://api.github.com/repos/CH4019/xinximenhu/releases/101030833/assets
    @SerialName("author")
    val author: Author = Author(),
    @SerialName("body")
    val body: String = "", // 1.重新设计了检查更新的弹窗页面，更加符合现代化2.更新软件图标3.更换指示器图标以及部分页面组件配色
    @SerialName("created_at")
    val createdAt: String = "", // 2023-03-08T08:11:55Z
    @SerialName("draft")
    val draft: Boolean = false, // false
    @SerialName("html_url")
    val htmlUrl: String = "", // https://github.com/CH4019/xinximenhu/releases/tag/2.4.7
    @SerialName("id")
    val id: Int = 0, // 101030833
    @SerialName("name")
    val name: String = "", // 建大助手2.4.7版本
    @SerialName("node_id")
    val nodeId: String = "", // RE_kwDOF80V_84GBZux
    @SerialName("prerelease")
    val prerelease: Boolean = false, // false
    @SerialName("published_at")
    val publishedAt: String = "", // 2023-04-27T01:35:17Z
    @SerialName("tag_name")
    val tagName: String = "", // 2.4.7
    @SerialName("tarball_url")
    val tarballUrl: String = "", // https://api.github.com/repos/CH4019/xinximenhu/tarball/2.4.7
    @SerialName("target_commitish")
    val targetCommitish: String = "", // main
    @SerialName("upload_url")
    val uploadUrl: String = "", // https://uploads.github.com/repos/CH4019/xinximenhu/releases/101030833/assets{?name,label}
    @SerialName("url")
    val url: String = "", // https://api.github.com/repos/CH4019/xinximenhu/releases/101030833
    @SerialName("zipball_url")
    val zipballUrl: String = "" // https://api.github.com/repos/CH4019/xinximenhu/zipball/2.4.7
) {
    @Serializable
    data class Asset(
        @SerialName("browser_download_url")
        val browserDownloadUrl: String = "", // https://github.com/CH4019/xinximenhu/releases/download/2.4.7/_2.4.7_release.apk
        @SerialName("content_type")
        val contentType: String = "", // application/vnd.android.package-archive
        @SerialName("created_at")
        val createdAt: String = "", // 2023-04-27T01:34:48Z
        @SerialName("download_count")
        val downloadCount: Int = 0, // 42
        @SerialName("id")
        val id: Int = 0, // 105501760
        @SerialName("label")
        val label: String? = "",
        @SerialName("name")
        val name: String = "", // _2.4.7_release.apk
        @SerialName("node_id")
        val nodeId: String = "", // RA_kwDOF80V_84GSdRA
        @SerialName("size")
        val size: Int = 0, // 7432447
        @SerialName("state")
        val state: String = "", // uploaded
        @SerialName("updated_at")
        val updatedAt: String = "", // 2023-04-27T01:34:59Z
        @SerialName("uploader")
        val uploader: Uploader = Uploader(),
        @SerialName("url")
        val url: String = "" // https://api.github.com/repos/CH4019/xinximenhu/releases/assets/105501760
    ) {
        @Serializable
        data class Uploader(
            @SerialName("avatar_url")
            val avatarUrl: String = "", // https://avatars.githubusercontent.com/u/77958033?v=4
            @SerialName("events_url")
            val eventsUrl: String = "", // https://api.github.com/users/CH4019/events{/privacy}
            @SerialName("followers_url")
            val followersUrl: String = "", // https://api.github.com/users/CH4019/followers
            @SerialName("following_url")
            val followingUrl: String = "", // https://api.github.com/users/CH4019/following{/other_user}
            @SerialName("gists_url")
            val gistsUrl: String = "", // https://api.github.com/users/CH4019/gists{/gist_id}
            @SerialName("gravatar_id")
            val gravatarId: String = "",
            @SerialName("html_url")
            val htmlUrl: String = "", // https://github.com/CH4019
            @SerialName("id")
            val id: Int = 0, // 77958033
            @SerialName("login")
            val login: String = "", // CH4019
            @SerialName("node_id")
            val nodeId: String = "", // MDQ6VXNlcjc3OTU4MDMz
            @SerialName("organizations_url")
            val organizationsUrl: String = "", // https://api.github.com/users/CH4019/orgs
            @SerialName("received_events_url")
            val receivedEventsUrl: String = "", // https://api.github.com/users/CH4019/received_events
            @SerialName("repos_url")
            val reposUrl: String = "", // https://api.github.com/users/CH4019/repos
            @SerialName("site_admin")
            val siteAdmin: Boolean = false, // false
            @SerialName("starred_url")
            val starredUrl: String = "", // https://api.github.com/users/CH4019/starred{/owner}{/repo}
            @SerialName("subscriptions_url")
            val subscriptionsUrl: String = "", // https://api.github.com/users/CH4019/subscriptions
            @SerialName("type")
            val type: String = "", // User
            @SerialName("url")
            val url: String = "" // https://api.github.com/users/CH4019
        )
    }

    @Serializable
    data class Author(
        @SerialName("avatar_url")
        val avatarUrl: String = "", // https://avatars.githubusercontent.com/u/77958033?v=4
        @SerialName("events_url")
        val eventsUrl: String = "", // https://api.github.com/users/CH4019/events{/privacy}
        @SerialName("followers_url")
        val followersUrl: String = "", // https://api.github.com/users/CH4019/followers
        @SerialName("following_url")
        val followingUrl: String = "", // https://api.github.com/users/CH4019/following{/other_user}
        @SerialName("gists_url")
        val gistsUrl: String = "", // https://api.github.com/users/CH4019/gists{/gist_id}
        @SerialName("gravatar_id")
        val gravatarId: String = "",
        @SerialName("html_url")
        val htmlUrl: String = "", // https://github.com/CH4019
        @SerialName("id")
        val id: Int = 0, // 77958033
        @SerialName("login")
        val login: String = "", // CH4019
        @SerialName("node_id")
        val nodeId: String = "", // MDQ6VXNlcjc3OTU4MDMz
        @SerialName("organizations_url")
        val organizationsUrl: String = "", // https://api.github.com/users/CH4019/orgs
        @SerialName("received_events_url")
        val receivedEventsUrl: String = "", // https://api.github.com/users/CH4019/received_events
        @SerialName("repos_url")
        val reposUrl: String = "", // https://api.github.com/users/CH4019/repos
        @SerialName("site_admin")
        val siteAdmin: Boolean = false, // false
        @SerialName("starred_url")
        val starredUrl: String = "", // https://api.github.com/users/CH4019/starred{/owner}{/repo}
        @SerialName("subscriptions_url")
        val subscriptionsUrl: String = "", // https://api.github.com/users/CH4019/subscriptions
        @SerialName("type")
        val type: String = "", // User
        @SerialName("url")
        val url: String = "" // https://api.github.com/users/CH4019
    )
}
