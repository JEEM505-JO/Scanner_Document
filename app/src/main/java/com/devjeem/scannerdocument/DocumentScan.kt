package com.devjeem.scannerdocument

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.devjeem.scannerdocument.ui.theme.util.DocumentScanViewModel
import com.websitebeaver.documentscanner.DocumentScanner
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DocumentScan : ComponentActivity() {
    private val viewModel: DocumentScanViewModel by viewModels()

    private val document = DocumentScanner(
        this, { croppedImageResults ->
            // display the first cropped image
            val photo = BitmapFactory.decodeFile(croppedImageResults.first())
            viewModel.saveDataPhoto(photo)
            Toast.makeText(
                this,
                "Se pudo capturar la fotografia en itent document",
                Toast.LENGTH_SHORT
            )
                .show()
            finish()
        },
        {
            // an error happened
                errorMessage ->
            Log.v("documentscannerlogs", errorMessage)
            Toast.makeText(this, "No se pudo capturar la fotografia", Toast.LENGTH_SHORT)
                .show()
        },
        {
            // user canceled document scan
            Log.v("documentscannerlogs", "User canceled document scan")
            Toast.makeText(this, "Escaneo cancelado", Toast.LENGTH_SHORT)
                .show()
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        document.startScan()
    }
}