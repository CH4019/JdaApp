package com.ch4019.jdaapp.viewmodel.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch4019.jdaapp.util.B64
import com.ch4019.jdaapp.util.RSAEncoder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import java.math.BigInteger
import java.security.SecureRandom
import kotlin.random.Random


class LoginViewModel : ViewModel(){
    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()

    private val _rsaKey = MutableStateFlow(RsaKey())
    private val rsaKey = _rsaKey.asStateFlow()

    private val _requestCookie = MutableStateFlow(RequestCookie())
    private val requestCookie = _requestCookie.asStateFlow()
    init {
        initLoginState()
    }
    private fun initLoginState() {
        viewModelScope.launch(Dispatchers.IO) {
            _loginState.update {
                it.copy(
                    userName = "",
                    passWord = "",
                    cookie = ""
                )
            }
            _rsaKey.update {
                it.copy(
                    keyN = BigInteger.ZERO,
                    keyE = BigInteger.ZERO
                )
            }
            _requestCookie.update {
                it.copy(
                    cookie = "SERVERID2=Server3"
                )
            }
        }
    }

    fun login(loginIntent: LoginState){
        viewModelScope.launch(Dispatchers.IO) {
            val cookie = loginCookie(loginIntent)
            Log.d("loginPage",cookie)
            delay(500)
            val loginPage = loginClass(cookie)
            Log.d("loginPage",loginPage)
            _loginState.update {
                it.copy(
                    userName = loginIntent.userName,
                    passWord = loginIntent.passWord,
                    cookie = loginPage
                )
            }
        }
    }



//    TODO 从getCsrfToken()拿到csrfToken
//    从getTime()获得时间戳
//    从getPassWord()获得加密后的密码
    private fun loginClass(key : String) :String {
        try {
            val formBody = FormBody.Builder()
                .add("xnm", "2023")
                .add("xqm", "3")
                .build()
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("https://219-231-0-156.webvpn.ahjzu.edu.cn/kbcx/xskbcx_cxXsKb.html")
                .header("gnmkdm", "N2151")
                .header("User-Agent","Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Mobile Safari/537.36 Edg/115.0.1901.203")
                .header("Accept","*/*")
                .header("Content-Type","application/x-www-form-urlencoded;charset=UTF-8")
                .header("Accept-Language","zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6")
                .header("Cookie",key)
                .post(formBody)
                .build()
            val response = client.newCall(request).execute()
            return if (response.isSuccessful) {
                response.body?.string() ?: ""
            }else{
                ""
            }
        }catch (e: Exception) {
            e.printStackTrace()
            return ""
        }
    }

    private fun loginCookie(loginIntent: LoginState) : String{
        val client = OkHttpClient()
        val time = getTime()
        val yhm = loginIntent.userName
        val csrfToken = getCsrfToken(client)
        Log.d("csrfToken",csrfToken)
        val passWord = getPassWord(client, loginIntent.passWord)
        Log.d("passWord",passWord)
        val formBody = FormBody.Builder()
            .add("csrfToken", csrfToken)
            .add("yhm", yhm)
            .add("mm", passWord)
            .add("mm", passWord)
            .build()
        val requestCookies = requestCookie.value.cookie
        val request = Request.Builder()
            .url("https://219-231-0-156.webvpn.ahjzu.edu.cn/xtgl/login_slogin.html?time=$time")
            .header("Cookie",requestCookies)
            .post(formBody)
            .build()
        val response = client.newCall(request).execute()
        Log.d("httpCode", response.code.toString())
        Log.d("response",response.toString())
        return if (response.isSuccessful) {
            val setCookie = response.headers("Set-Cookie")
            val jsEsSionId = setCookie.map { it.split(";")[0] }
                .firstOrNull { it.startsWith("JSESSIONID=") }
                ?.substringAfter("JSESSIONID=")
            "SERVERID2=Server3;JSESSIONID=$jsEsSionId"
        } else {
            println("请求失败,状态码:${response.code}")
            "SERVERID2=Server3"
        }
    }
    private fun getTime() : String {
        return System.currentTimeMillis().toString()
    }

    private fun getCsrfToken(
        client : OkHttpClient
    ): String {
        Log.d("getCsrfToken","start")
        val connection  = Request.Builder()
                .url("https://219-231-0-156.webvpn.ahjzu.edu.cn/xtgl/login_slogin.html")
                .header("User-Agent","Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Mobile Safari/537.36 Edg/115.0.1901.203")
                .header("Cookie","SERVERID2=Server3")
                .build()
        val response = client.newCall(connection).execute()
//        val jsEsSionId = response.headers("Cookie").map { it.split(";")[0] }
//            .firstOrNull { it.startsWith("JSESSIONID=") }
//            ?.substringAfter("JSESSIONID=")
//        Log.d("jsEsSionId","$jsEsSionId")
        _requestCookie.update {
            it.copy(
                cookie = "SERVERID2=Server3"
            )
        }
        val document = response.body?.string()?.let { Jsoup.parse(it) }
        return if (document != null) {
            document.select("#csrftoken").`val`()
        }else{
            ""
        }
    }

    private fun getPassWord(client:OkHttpClient, passWord: String): String {
        val time = getTime()
        val time2 = (time.toLong()-50).toString()
        val url = "https://219-231-0-156.webvpn.ahjzu.edu.cn/xtgl/login_getPublicKey.html?time=$time&_=$time2"
        Log.d("url",url)
        val requestCookies = requestCookie.value.cookie
        val request = Request.Builder()
            .url(url)
            .header("User-Agent","Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Mobile Safari/537.36 Edg/115.0.1901.203")
            .header("Cookie",requestCookies)
            .build()
        val response = client.newCall(request).execute()
        val responseBody = response.body?.string() ?: "{}"
        Log.d("response",response.toString())
        Log.d("responseBody",responseBody)
       val data = Json.decodeFromString<PublicKeyList>(responseBody)
        Log.d("modulus",data.modulus)
        //setPublic(b64ToHex(data.modulus), b64ToHex(data.exponent))
        Log.d("setPublic","end")
        return B64.hexToB64(RSAEncoder.rsaEncrypt(passWord,B64.b64ToHex(data.modulus),B64.b64ToHex(data.exponent)))
        //hex2b64(rSAEncrypt(passWord))
    }

    private fun setPublic(key1 : String, key2 : String){
        if (key1.isNotEmpty() && key2.isNotEmpty()){
                _rsaKey.update {
                    it.copy(
                        keyN = key1.toBigInteger(16),
                        keyE = key2.toBigInteger(16)
                    )
                }
                Log.d("setPublic","start")
        }else{
            println("初始化失败")
        }
    }

    private fun rSAEncrypt(
        passWord: String
    ): String{
        val m = pKcs1Pad2(passWord, ((rsaKey.value.keyN?.bitLength()?:0) + 7) shr 3)
        val c = doPublic(m)
        val h = c.toString(16)
        return if (h.length % 2 == 0) h else "0$h"
    }

//    Todo 当前加密函数待解决：pKcs1Pad2()函数和doPublic()函数，
//    pKcs1Pad2()函数需要解决类型是否正确
//    doPublic()函数需要重头编写
    private fun doPublic(
        key: BigInteger
    ):BigInteger {
        return key.modPow(
            rsaKey.value.keyE!!,
            rsaKey.value.keyN!!,
        )
    }

    private fun pKcs1Pad2(
        key1: String,
        key2: Int
    ): BigInteger {
        var n = key2
        if (key2 < key1.length + 11) {
            return BigInteger.ZERO
        }
        val ba = ByteArray(n)
        var i = key1.length - 1
        while (i >= 0 && n > 0) {
            val c = key1.codePointAt(i--) // key1[i--].code
            if (c < 128) { // encode using utf-8
                ba[--n] = c.toByte()
            } else if (c in 128..2047) {
                ba[--n] = ((c and 63) or 128).toByte()
                ba[--n] = ((c shr 6) or 192).toByte()
            } else {
                ba[--n] = ((c and 63) or 128).toByte()
                ba[--n] = (((c shr 6) and 63) or 128).toByte()
                ba[--n] = ((c shr 12) or 224).toByte()
            }
        }
        ba[--n] = 0.toByte()
        val x = ByteArray(1)
        val rdm = Random(47L)
//        val rng = SecureRandom()
        while (n > 2) { // random non-zero pad
            x[0] = 0
            while (x[0] == 0.toByte()) rdm.nextBytes(x)
            ba[--n] = x[0]
        }
        ba[--n] = 2
        ba[--n] = 0
        return BigInteger(ba)
    }


    private fun pkCs1Pad2(key1: String, key2: Int): BigInteger? {
        if (key2 < key1.length + 11) {
            println("Message too long for RSA")
            return null
        }
        val ba = ByteArray(key2)
        var i = key1.length - 1
        var j = key2 - 1
        while (i >= 0 && j >= 0) {
            val c = key1[i--].code
            when {
                c < 128 -> ba[j--] = c.toByte()
                c in 128..2047 -> {
                    ba[j--] = (c and 63 or 128).toByte()
                    ba[j--] = (c shr 6 or 192).toByte()
                }
                else -> {
                    ba[j--] = (c and 63 or 128).toByte()
                    ba[j--] = (c shr 6 and 63 or 128).toByte()
                    ba[j--] = (c shr 12 or 224).toByte()
                }
            }
        }
        ba[j--] = 0
        val rng = SecureRandom()
        val x = ByteArray(1)
        while (j > 1) {
            x[0] = 0
            while (x[0] == 0.toByte()) rng.nextBytes(x)
            ba[j--] = x[0]
        }
        ba[j--] = 2
        ba[j] = 0
        return BigInteger(ba)
    }

//  类型转换函数
    private val b64map = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
    private val b64pad = "="
     private fun hex2b64(key: String): String {
        var i = 0
        var c: Int
        var ret = ""
        while (i + 3 <= key.length) {
            c = key.substring(i, i + 3).toInt(16)
            ret += b64map[c shr 6].toString() + b64map[c and 63].toString()
            i += 3
        }
        if (i + 1 == key.length) {
            c = key.substring(i, i + 1).toInt(16)
            ret += b64map[c shl 2]
        } else if (i + 2 == key.length) {
            c = key.substring(i, i + 2).toInt(16)
            ret += b64map[c shr 2].toString() + b64map[(c and 3) shl 4].toString()
        }
        while (ret.length % 4 != 0) ret += b64pad
        return ret
    }

    private fun b64ToHex(key: String): String {
        var ret = ""
        var i = 0
        var k = 0 // b64 state, 0-3
        var slop = 0
        while (i < key.length){
            if (key[i].toString() == b64pad) break
            val v = b64map.indexOf(key[i])
            if (v < 0) continue
            when (k) {
                0 -> {
                    ret += int2char(v shr 2)
                    slop = v and 3
                    k = 1
                }
                1 -> {
                    ret += int2char((slop shl 2) or (v shr 4))
                    slop = v and 0xf
                    k = 2
                }
                2 -> {
                    ret += int2char(slop)
                    ret += int2char(v shr 2)
                    slop = v and 3
                    k = 3
                }
                else -> {
                    ret += int2char((slop shl 2) or (v shr 4))
                    ret += int2char(v and 0xf)
                    k = 0
                }
            }
            i++
        }
        if (k == 1)
            ret += int2char(slop shl 2)
        return ret
    }

    private val hexCode = "0123456789abcdefghijklmnopqrstuvwxyz"
    private fun int2char(n: Int): Char {
       return hexCode[n]
    }
}