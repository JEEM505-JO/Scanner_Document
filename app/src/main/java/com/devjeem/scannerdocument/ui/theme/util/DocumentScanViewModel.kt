package com.devjeem.scannerdocument.ui.theme.util

import android.graphics.Bitmap
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devjeem.scannerdocument.ui.theme.camera.UtilCamera
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DocumentScanViewModel @Inject constructor(val camera: UtilCamera) : ViewModel() {
    private val _state: MutableStateFlow<UiState> =
        MutableStateFlow(UiState(loading = false))
    val state: StateFlow<UiState> = _state.asStateFlow()


    fun saveDataPhoto(bitmap: Bitmap) {
        viewModelScope.launch {
            camera.savePhotoOfFile(bitmap)
            _state.update {
                it.copy(image = bitmap.asImageBitmap())
            }
        }
    }

}