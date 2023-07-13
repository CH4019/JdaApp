package com.ch4019.jdaapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ch4019.jdaapp.model.NavigationItem

@Composable
fun CardListView(
    navHostController: NavHostController,
    navigationItems: List<NavigationItem>,
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp)
    ) {
        CardListComponent(navHostController, navigationItems)
    }
}

@Composable
private fun CardListComponent(
    mainNavController: NavHostController,
    navigationItems: List<NavigationItem>,
) {
    navigationItems.forEach {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    mainNavController.navigate(it.routerId) {
                        popUpTo(it.routerId) {
                            inclusive = true
                        }
                    }
                }
                .padding(16.dp),
        ) {
            Icon(
                imageVector = it.icon,
                contentDescription = null
            )
            Text(it.title, Modifier.padding(start = 16.dp))
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Outlined.ArrowForwardIos,
                contentDescription = null
            )
        }
    }
}
