package com.ch4019.jdaapp.model

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationBarItem(
//    底栏
    val title: String,
    val icon: ImageVector,
)

data class NavigationItem(
//    卡片列表
    val title: String,
    val icon: ImageVector,
    val routerId: String
)

data class GridList(
//    成绩
    val kcdm: String, // 课程代码
    val kcmc: String, // 课程名称
    val kcxz: String, // 课程性质
    val xf: String,   // 学分
    val cj: String,   // 成绩
    val jd: String,   // 绩点
    val cjxz: String, // 成绩性质
    val sfcjzf: String,// 是否成绩作废
    val sfxwkc: String,// 是否学位课程
    val rkjs: String, // 任课教师
    val khfsmc: String,// 考核方式
)
