package com.devjeem.scannerdocument.ui.theme.util

import androidx.compose.ui.graphics.ImageBitmap

data class UiState(
    val success: Boolean = false,
    val loading: Boolean = false,
    val invokeClick: () -> Unit = {},
    var image: ImageBitmap? = null,
    val saveDataPhoto: (ImageBitmap) -> Unit = {},
)