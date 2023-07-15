package com.ch4019.jdaapp.model

import com.google.gson.annotations.SerializedName

data class QuoteList(
    @SerializedName("url")
    val url: String,
    @SerializedName("assets_url")
    val assets_url: String,
    @SerializedName("upload_url")
    val upload_url: String,
    @SerializedName("html_url")
    val html_url: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("author")
    val author: Author,
    @SerializedName("node_id")
    val node_id: String,
    @SerializedName("tag_name")
    val tag_name: String,
    @SerializedName("target_commitish")
    val target_commitish: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("draft")
    val draft: Boolean,
    @SerializedName("prerelease")
    val prerelease: Boolean,
    @SerializedName("created_at")
    val created_at: String,
    @SerializedName("published_at")
    val published_at: String,
    @SerializedName("assets")
    val assets: List<Assets>,
    @SerializedName("tarball_url")
    val tarball_url: String,
    @SerializedName("zipball_url")
    val zipball_url: String,
    @SerializedName("body")
    val body: String,
){
    data class Author(
        @SerializedName("login")
        val login: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("node_id")
        val node_id: String,
        @SerializedName("avatar_url")
        val avatar_url: String,
        @SerializedName("gravatar_id")
        val gravatar_id: String,
        @SerializedName("url")
        val url: String,
        @SerializedName("html_url")
        val html_url: String,
        @SerializedName("followers_url")
        val followers_url: String,
        @SerializedName("following_url")
        val following_url: String,
        @SerializedName("gists_url")
        val gists_url: String,
        @SerializedName("starred_url")
        val starred_url: String,
        @SerializedName("subscriptions_url")
        val subscriptions_url: String,
        @SerializedName("organizations_url")
        val organizations_url: String,
        @SerializedName("repos_url")
        val repos_url: String,
        @SerializedName("events_url")
        val events_url: String,
        @SerializedName("received_events_url")
        val received_events_url: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("site_admin")
        val site_admin: Boolean
    )
    data class Assets(
        @SerializedName("url")
        val url: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("node_id")
        val node_id: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("label")
        val label: String,
        @SerializedName("uploader")
        val uploader: Uploader,
        @SerializedName("content_type")
        val content_type: String,
        @SerializedName("state")
        val state: String,
        @SerializedName("size")
        val size: Int,
        @SerializedName("download_count")
        val download_count: Int,
        @SerializedName("created_at")
        val created_at: String,
        @SerializedName("updated_at")
        val updated_at: String,
        @SerializedName("browser_download_url")
        val browser_download_url: String,
    ){
        data class Uploader(
            @SerializedName("login")
            val login: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("node_id")
            val node_id: String,
            @SerializedName("avatar_url")
            val avatar_url: String,
            @SerializedName("gravatar_id")
            val gravatar_id: String,
            @SerializedName("url")
            val url: String,
            @SerializedName("html_url")
            val html_url: String,
            @SerializedName("followers_url")
            val followers_url: String,
            @SerializedName("following_url")
            val following_url: String,
            @SerializedName("gists_url")
            val gists_url: String,
            @SerializedName("starred_url")
            val starred_url: String,
            @SerializedName("subscriptions_url")
            val subscriptions_url: String,
            @SerializedName("organizations_url")
            val organizations_url: String,
            @SerializedName("repos_url")
            val repos_url: String,
            @SerializedName("events_url")
            val events_url: String,
            @SerializedName("received_events_url")
            val received_events_url: String,
            @SerializedName("type")
            val type: String,
            @SerializedName("site_admin")
            val site_admin: Boolean
        )
    }
}