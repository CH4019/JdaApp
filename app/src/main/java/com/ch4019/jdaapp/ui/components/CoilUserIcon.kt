package com.ch4019.jdaapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ch4019.jdaapp.R

@Composable
fun UserIcon(
    content:String,
    isLogin:Boolean
) {
    Column (
        modifier = Modifier
            .size(50.dp)
            .clip(shape = CircleShape)
    ){
        if (isLogin){
            AsyncImage(
                //model = "https://q1.qlogo.cn/g?b=qq&nk=$content&s=100",
                model = "https://api.kuizuo.cn/api/qqimg?qq=$content",
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp),
                contentScale = ContentScale.Crop,
            )
        }else{
            AsyncImage(
                model = R.drawable.app_icon,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp),
                contentScale = ContentScale.Crop,
            )
        }
    }
}