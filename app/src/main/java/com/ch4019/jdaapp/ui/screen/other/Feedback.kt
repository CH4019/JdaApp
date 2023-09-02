package com.ch4019.jdaapp.ui.screen.other

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ch4019.jdaapp.R
import com.ch4019.jdaapp.ui.components.CardButton
import com.ch4019.jdaapp.ui.theme.JdaAppTheme
import java.util.Base64

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Feedback(
    mainNavController: NavHostController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("问题反馈") },
                navigationIcon = {
                    IconButton(
                        onClick = { mainNavController.navigateUp() }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBackIosNew,
                            contentDescription = null
                        )
                    }
                },
            )
        }
    ) {
        FeedbackView(it)
    }
}

@Composable
fun FeedbackView(
    paddingValues: PaddingValues
) {
    val context = LocalContext.current
    val url = "https://pd.qq.com/s/gk82ietee"
    //val url1 = "mqqapi://forward/url?src_type=web&style=default&plg_auth=1&version=1&url_prefix=${Base64.getEncoder().encodeToString(url.toByteArray())}"
    val webpage: Uri = Uri.parse(url)
    val intent = Intent(Intent.ACTION_VIEW, webpage)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Card (
            shape = RoundedCornerShape(30.dp),
        ){
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.app_icon),
                contentDescription = "logo",
                Modifier
                    .padding(16.dp)
                    .size(150.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        CardButton(
            modifier = Modifier.padding(horizontal = 32.dp),
            onClick = {
            if (intent.resolveActivity(context.packageManager) != null) {
                try {
                    Toast.makeText(context, "正在跳转", Toast.LENGTH_SHORT).show()
                    context.startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(context, "无软件可以打开", Toast.LENGTH_SHORT).show()
                }
            }
        }) {
            Text(text = "前往QQ频道反馈")
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "暂用QQ频道进行反馈",
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun FeedbackPreview() {
    JdaAppTheme {
        Feedback(rememberNavController())
    }
}