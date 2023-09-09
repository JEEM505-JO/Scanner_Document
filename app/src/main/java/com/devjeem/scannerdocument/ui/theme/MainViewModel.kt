package com.devjeem.scannerdocument.ui.theme

import android.graphics.Bitmap
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devjeem.scannerdocument.ui.theme.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _state: MutableStateFlow<UiState> =
        MutableStateFlow(UiState(loading = false, invokeClick = ::clickPhoto, saveDataPhoto = ::saveDataPhoto))
    val state : StateFlow<UiState> = _state.asStateFlow()

    fun classifyFinalVersionTwo(bitmap: Bitmap, rotate: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            //TODO clasificar documento
        }


    private fun clickPhoto() {
        viewModelScope.launch {
            _state.update {
                it.copy(loading = true)
            }
        }
    }

    private fun saveDataPhoto(imageBitmap: ImageBitmap){
        viewModelScope.launch {
            _state.update {
                it.copy(image = imageBitmap)
            }
        }
    }
}