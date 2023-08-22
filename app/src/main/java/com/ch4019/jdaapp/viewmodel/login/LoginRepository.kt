package com.ch4019.jdaapp.viewmodel.login

import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

class LoginRepository() {
   suspend fun loginLoad(userName: String, passWord: String): List<String> = getCookie(userName, passWord)

    private fun getCookie(
        userName: String,
        passWord: String
    ): List<String> {
        val parse: Document = Jsoup
            .connect("https://sso.webvpn.ahjzu.edu.cn/sso/login?service=https://webvpn.ahjzu.edu.cn/users/auth/cas/callback?url").get()
        val lt: String = parse.getElementsByAttributeValue("type", "hidden").first()?.attr("value") ?:""
        //password加密
        try {
            val formBody = FormBody.Builder()
                    .add("uname", userName)
                    .add("password", hexMd5(passWord))
                    .add("It", lt)
                    .add("isMobile", "y")
                    .add("_eventId", "submit")
                    .build()
            // 创建OkHttpClient
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("https://sso.webvpn.ahjzu.edu.cn/sso/login?service=https://webvpn.ahjzu.edu.cn/users/auth/cas/callback?url")
                .post(formBody)
                .build()
            val response = client.newCall(request).execute()
            return if (response.isSuccessful) {
                // 获取Set-Cookie header
                val setCookie = response.header("Set-Cookie").toString()
                // 自己解析和选择需要的Cookie
                setCookie.split(";")
                    .filter { it.startsWith("cookieName1") }
                    .filter { it.startsWith("JSESSIONID") }
            } else {
                println("请求失败,状态码:${response.code}")
                listOf("")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return listOf("")
        }
    }

    private fun hexMd5(s: String): String {
        try {
            val md5 = MessageDigest.getInstance("MD5")
            val stringBuilder = StringBuilder()
            val bytes = md5.digest(s.toByteArray(StandardCharsets.UTF_8))
            for (b in bytes) {
                val hex = Integer.toHexString(0xFF and b.toInt())
                if (hex.length == 1) {
                    stringBuilder.append("0")
                }
                stringBuilder.append(hex)
            }
            return stringBuilder.toString()
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }
    }
}