package com.devjeem.scannerdocument.ui.theme.camera

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.os.Environment
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject

class UtilCamera @Inject constructor(
    private val context: Context,
) {
    lateinit var file: File
    val authority = "com.devjeem.scannerdocument" + ".fileprovider"
    private var namePhoto = ""

    fun createPhotoFile(): File {
        val dir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        file = File.createTempFile("IMG_", ".jpg", dir)
        return file
    }

    fun getNamePhoto(): String {
        return namePhoto
    }

    fun savePhotoOfFile(bitmap: Bitmap){
        val file = createPhotoFile()
        try {
            val fos = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            fos.flush()
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun getByteArrayOfPhoto(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream)
        return stream.toByteArray()
    }

    fun scaleBitmap(bm: Bitmap): Bitmap {
        var bitmap = bm
        val maxWidth = 1920
        val maxHeight = 1080
        var width = bitmap.width
        var height = bitmap.height

        if (width > maxWidth && height > maxHeight) {
            if (width > height) {
                // landscape
                val ratio = width / maxWidth
                width = maxWidth
                height /= ratio
            } else if (height > width) {
                // portrait
                val ratio = height / maxHeight
                height = maxHeight
                width /= ratio
            } else {
                // square
                height = maxHeight
                width = maxWidth
            }
        }
        bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true)

        return rotateBitmap(bitmap)
    }
    private fun rotateBitmap(source: Bitmap, ): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(90f)
        return Bitmap.createBitmap(source, 0, 0, source.width, source.height, matrix, true)
    }
}
