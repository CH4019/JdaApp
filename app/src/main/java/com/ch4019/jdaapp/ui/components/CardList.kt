package com.ch4019.jdaapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ch4019.jdaapp.model.CardListItem

@Composable
fun CardListView(
    mainNavController: NavHostController,
    cardListItems: List<CardListItem>,
) {
    Card(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(20.dp))
    ) {
        CardListComponent(mainNavController, cardListItems)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListComponent(
    mainNavController: NavHostController,
    cardListItems: List<CardListItem>,
) {
    Column {
        cardListItems.forEach {
            Card(
                onClick = {
                    mainNavController.navigate(it.routerId) {
                        // 重新选择同一项目时避免同一目标的多个副本
                        launchSingleTop = true
                        // 重新选择先前选定的项目时恢复状态
                        restoreState = true
                    }
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
                    Icon(
                        imageVector = it.icon,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Text(text = it.title)
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.Outlined.ArrowForwardIos,
                        contentDescription = null
                    )
                }
            }
        }
    }
}
