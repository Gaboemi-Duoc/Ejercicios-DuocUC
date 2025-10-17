package com.vivitasol.carcasamvvm.views


import android.Manifest
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * CLASE 5: Integración con recursos nativos - Cámara
 *
 * Flujo:
 * 1) Solicitar permiso de cámara (runtime).
 * 2) Crear un archivo temporal y su Uri con FileProvider (seguro).
 * 3) Lanzar ActivityResultContracts.TakePicture con ese Uri.
 * 4) Si la foto se tomó, mostrarla en pantalla con Coil.
 *
 * Notas:
 * - Guardamos la imagen en getExternalFilesDir(Pictures) (privado de la app).
 * - No se requiere READ/WRITE_EXTERNAL_STORAGE.
 */
@Composable
fun Option5CameraView() {
    val context = LocalContext.current

    // Estado: permiso, Uri actual del archivo para capturar, y Uri de la última foto tomada
    var hasCameraPermission by rememberSaveable { mutableStateOf(false) }
    var pendingImageUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    var lastPhotoUri by rememberSaveable { mutableStateOf<Uri?>(null) }


    // 1) Launcher para TakePicture
    val takePictureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            lastPhotoUri = pendingImageUri
        } else {
            lastPhotoUri = null
        }
    }

    // 1) Launcher para pedir permiso de cámara
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        hasCameraPermission = granted
        // Si recién se concedió, disparamos captura
        if (granted) {
            launchCamera(context) { tempUri ->
                pendingImageUri = tempUri
                takePictureLauncher.launch(tempUri)
            }
        }
    }

    // Compuesta principal
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Clase 5: Cámara",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            "Toca el botón para abrir la cámara, tomar una foto y mostrarla.",
            style = MaterialTheme.typography.bodyMedium
        )

        Button(
            onClick = {
                if (hasCameraPermission) {
                    // Ya tenemos permiso → creamos Uri y lanzamos cámara
                    launchCamera(context) { tempUri ->
                        pendingImageUri = tempUri
                        takePictureLauncher.launch(tempUri)
                    }
                } else {
                    // Pedir permiso primero
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                }
            }
        ) {
            Text("Tomar Foto")
        }

        // Vista previa de la foto tomada
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 180.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            if (lastPhotoUri != null) {
                AsyncImage(
                    model = lastPhotoUri,
                    contentDescription = "Foto tomada",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Aún no hay foto")
                }
            }
        }

        // Info didáctica
        Text(
            "Conceptos:\n" +
                    "• RequestPermission → CAMERA\n" +
                    "• FileProvider → Uri seguro para la cámara\n" +
                    "• TakePicture(Uri) → guarda imagen completa\n" +
                    "• Coil (AsyncImage) → muestra el Uri",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

/* Utilidad: crear archivo temporal y devolver su Uri con FileProvider */
private fun createImageUri(context: Context): Uri {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    val image = File.createTempFile(
        "IMG_${timeStamp}_",
        ".jpg",
        storageDir
    )
    return FileProvider.getUriForFile(
        context,
        "${context.packageName}.provider",
        image
    )
}

/* Abstrae la creación de Uri y ejecuta un callback con el Uri listo para TakePicture */
private fun launchCamera(context: Context, onUriReady: (Uri) -> Unit) {
    val uri = createImageUri(context)
    onUriReady(uri)
}
