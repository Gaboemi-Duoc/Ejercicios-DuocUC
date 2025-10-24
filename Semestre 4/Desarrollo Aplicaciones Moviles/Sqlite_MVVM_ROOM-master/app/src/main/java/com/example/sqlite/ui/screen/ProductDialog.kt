package com.example.sqlite.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.sqlite.ui.ProductViewModel

@Composable
fun ProductDialog(
    vm: ProductViewModel,
    onDismiss: () -> Unit,
    onSaved: () -> Unit
) {
    val form by vm.form.collectAsState()

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (form.id == null) "Nuevo producto" else "Editar producto") },
        text = {
            Column(Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = form.nombre,
                    onValueChange = vm::onNombreChange,
                    label = { Text("Nombre") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = form.tipo,
                    onValueChange = vm::onTipoChange,
                    label = { Text("Tipo") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = form.precio,
                    onValueChange = vm::onPrecioChange,
                    label = { Text("Precio") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = form.stock,
                    onValueChange = vm::onStockChange,
                    label = { Text("Stock") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(onClick = { vm.guardar(onSaved) }) {
                Text(if (form.id == null) "Guardar" else "Actualizar")
            }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancelar") } }
    )
}
