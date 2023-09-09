package com.devjeem.scannerdocument

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devjeem.scannerdocument.ui.theme.MainViewModel
import com.devjeem.scannerdocument.ui.theme.ScannerDocumentTheme
import com.devjeem.scannerdocument.ui.theme.camera.UtilCamera
import com.devjeem.scannerdocument.ui.theme.util.UiState
import com.websitebeaver.documentscanner.DocumentScanner
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var utilCamera: UtilCamera

    private var bitmap: ImageBitmap? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScannerDocumentTheme {
                val viewModel: MainViewModel = hiltViewModel()
//                val state: UiState by viewModel.state.collectAsState()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    /*Greeting(
                        state = state,
                        bitmap = bitmap,
                        documentScanner = document,
                        photoUtilities = utilCamera
                    )*/
                    Greeting(onclick = {
                        startActivity(Intent(this, DocumentScanner::class.java))
                    })
                }
            }
        }

    }

    /* private val document = DocumentScanner(
         this, { croppedImageResults ->
             // display the first cropped image
             val photo = BitmapFactory.decodeFile(croppedImageResults.first())
             bitmap = utilCamera.scaleBitmap(photo).asImageBitmap()
             Toast.makeText(
                 this,
                 "Se pudo capturar la fotografia en itent document",
                 Toast.LENGTH_SHORT
             )
                 .show()

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
     )*/

}


@Composable
fun Greeting(
//    state: UiState,
//    bitmap: ImageBitmap?,
//    documentScanner: DocumentScanner,
//    photoUtilities: UtilCamera,
    onclick: () -> Unit,
) {
//    if (bitmap != null) {
//        state.saveDataPhoto(bitmap)
    /*  val context = LocalContext.current
      val launcherCamera =
          rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
              if (result.resultCode == Activity.RESULT_OK) {
                  documentScanner.handleDocumentScanIntentResult(result)
                  Toast.makeText(
                      context,
                      "Se ha capturado la fotografia en composable",
                      Toast.LENGTH_SHORT
                  )
                      .show()
              } else {
                  Toast.makeText(context, "No se pudo capturar la fotografia", Toast.LENGTH_SHORT)
                      .show()
              }
          }*/

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .size(10.dp)
        )
        Text(text = "Clasificador de imagen", fontFamily = FontFamily.Serif, fontSize = 20.sp)

        Text(text = "Herramientas", fontFamily = FontFamily.Serif, fontSize = 15.sp)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(
                text = "TensorFlow Lite",
                fontFamily = FontFamily.SansSerif,
                fontSize = 15.sp,
                modifier = Modifier.padding(end = 10.dp)
            )
            Text(
                text = "Kotlin",
                fontFamily = FontFamily.SansSerif,
                fontSize = 15.sp,
                modifier = Modifier.padding(end = 10.dp)
            )
            Text(text = "Android Studio", fontFamily = FontFamily.SansSerif, fontSize = 15.sp)
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .size(10.dp)
        )
        Card(
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(10.dp),
            border = BorderStroke(1.dp, Color(255, 136, 44, 255)),
            modifier = Modifier.size(width = 200.dp, height = 200.dp)
        ) {
//            state.image.let { image ->
//                AnimatedVisibility(
//                    visible = image == null,
//                    enter = fadeIn(initialAlpha = 0.4f),
//                    exit = fadeOut(animationSpec = tween(durationMillis = 250))
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
//                        contentDescription = "ImageNull",
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .fillMaxHeight()
//                    )
//                }
//                if (image != null) {
//                    Image(
//                        bitmap = image, contentDescription = "ImageBit",
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .fillMaxHeight()
//                            .rotate(90f),
//                        contentScale = ContentScale.Crop,
//                    )
//                }
//            }
        }
        Row(horizontalArrangement = Arrangement.Center) {
            Button(
                onClick = {
//                    launcherCamera.launch(documentScanner.createDocumentScanIntent())
//                    launcherCamera.launch(documentScanner.createDocumentScanIntent())
                    onclick()
                },
                contentPadding = PaddingValues(all = 5.dp),
                shape = ButtonDefaults.shape,
                modifier = Modifier.size(width = 300.dp, height = 40.dp),
                colors = ButtonDefaults.buttonColors(Color(255, 136, 44, 255))
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_camera), contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(text = "Capturar fotografia")
            }
        }
    }
}






























