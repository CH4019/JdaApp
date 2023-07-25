package com.ch4019.jdaapp.ui.screen.navigation.grades

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.ch4019.jdaapp.model.GridList
import com.ch4019.jdaapp.ui.components.ChipList

@Composable
fun GradesPage() {
    val gridLists = listOf(
        GridList("DX121", "测试课程1", "必修", "3.0", "80", "3.0", "正常考试", "否", "否", "123", "考试"),
        GridList("DX120", "测试课程2", "必修", "2.0", "90", "4.0", "正常考试", "否", "否", "123", "考试"),
        GridList("DX125", "测试课程3", "必修", "2.0", "80", "3.0", "正常考试", "否", "否", "123", "考试"),
        GridList("DX125", "测试课程4", "必修", "3.0", "80", "3.0", "正常考试", "否", "否", "123", "考试"),
        GridList("DX129", "测试课程5", "必修", "3.0", "78", "2.8", "正常考试", "否", "否", "123", "考试"),
        GridList("DX124", "测试课程6", "选修", "2.0", "80", "3.0", "正常考试", "否", "否", "123", "考试"),
        GridList("DX163", "测试课程7", "必修", "3.0", "80", "3.0", "正常考试", "否", "否", "123", "考试"),
        GridList("DX133", "测试课程8", "必修", "1.0", "75", "2.5", "正常考试", "否", "否", "123", "考试"),
        GridList("DX113", "测试课程9", "必修", "2.0", "99", "4.9", "正常考试", "否", "否", "123", "考试"),
        GridList("DX103", "测试课程10", "校选修", "1.0", "80", "3.0", "正常考试", "否", "否", "123", "考试"),
        GridList("DX523", "测试课程11", "必修", "3.0", "80", "3.0", "正常考试", "否", "否", "123", "考试"),
        GridList("DX923", "测试课程12", "必修", "1.0", "70", "2.0", "正常考试", "否", "否", "123", "考试"),
    )
    val selected1 = remember{ mutableStateOf(false) }
    val selected2 = remember{ mutableStateOf(false) }
    val isShow = remember{ mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
            ) {
            SelectGrades(selected1, selected2)
            if (isShow.value){
                ListShow(gridLists)
            }
        }
        FloatButton(selected1.value, selected2.value, isShow)
    }

}

@Composable
fun SelectGrades(
    selected1: MutableState<Boolean>,
    selected2: MutableState<Boolean>,
) {
    val text1 = remember{ mutableStateOf("选择学年") }
    val menuItems1 = listOf("2022-2023", "2023-2024")
    val text2 = remember{ mutableStateOf("选择学期") }
    val menuItems2 = listOf("1", "2")

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.weight(1f))
        ChipList(selected1, text1.value, menuItems1)
        Spacer(modifier = Modifier.weight(1f))
        ChipList(selected2, text2.value, menuItems2)
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun ListShow(
    gridLists: List<GridList>
) {
    gridLists.forEach {
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .fillMaxWidth(),
        ) {
            Text(text = "${ it.kcmc }/${it.kcdm}")
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            shape = RoundedCornerShape(15.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalArrangement  = Arrangement.Center
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "学分:${it.xf}")
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "成绩:${it.cj}")
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "绩点:${it.jd}")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = it.kcxz)
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = it.cjxz)
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = it.khfsmc)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "学位课程:")
                        Text(text = it.sfxwkc)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "成绩作废:")
                        Text(text = it.sfcjzf)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "任课教师:")
                        Text(text = it.rkjs)
                    }

                }
            }
        }
    }
    Spacer(modifier = Modifier.height(56.dp))
}
@Composable
fun FloatButton(
    selected1: Boolean,
    selected2: Boolean,
    isShow: MutableState<Boolean>,
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(onClick = {
            if (selected1&&selected2){
                isShow.value = true
            }else if (selected1){
                Toast.makeText(context, "请选择学期", Toast.LENGTH_SHORT).show()
            }else if (selected2){
                Toast.makeText(context, "请选择学年", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, "请选择学年和学期", Toast.LENGTH_SHORT).show()
            }
        }) {
            Icon(imageVector = Icons.Default.Check, contentDescription = null)
        }
    }
}