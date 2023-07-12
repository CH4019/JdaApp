package com.ch4019.jdaapp.ui.screen.other.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ch4019.jdaapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutPage(
    mainNavController: NavHostController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "关于")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            mainNavController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBackIosNew,
                            contentDescription = null
                        )
                    }
                },
            )
        },
        bottomBar = {
            AboutBottomPromise()
        }
    ) {
        AboutView(it)
    }
}

@Composable
fun AboutView(
    paddingValues: PaddingValues,
) {
    val logo = ImageBitmap.imageResource(R.drawable.app_icon)
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(32.dp))
        Image(
            bitmap = logo,
            contentDescription = "logo"
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = "JdaApp",
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.size(32.dp))
        UpDateView()
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "纵然世间黑暗      仍有一点星光",
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun AboutBottomPromise() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "开源许可证",
            color = MaterialTheme.colorScheme.inversePrimary,
            modifier = Modifier
                .clickable {
                }
        )
        Text(text = "和")
        Text(
            text = "隐私政策",
            color = MaterialTheme.colorScheme.inversePrimary,
            modifier = Modifier
                .clickable {
                }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpDateView() {
    Card(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(15.dp))
    ) {
        Card(
            onClick = {
            },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "当前版本")
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "1.0.0")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutPagePreview() {
    AboutPage(mainNavController = rememberNavController())
}
