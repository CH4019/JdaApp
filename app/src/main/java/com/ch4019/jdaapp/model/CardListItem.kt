package com.ch4019.jdaapp.model

import androidx.compose.ui.graphics.vector.ImageVector

data class CardListItem(
    val stringResourceId: String,
    val imageResourceId: ImageVector,
    val routerPageId : String
)
