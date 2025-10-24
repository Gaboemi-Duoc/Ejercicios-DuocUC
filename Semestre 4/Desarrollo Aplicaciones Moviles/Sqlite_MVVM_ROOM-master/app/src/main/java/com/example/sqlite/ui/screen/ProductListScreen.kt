package com.example.sqlite.ui.screen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sqlite.data.Producto
import com.example.sqlite.ui.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(vm: ProductViewModel) {
    val productos by vm.productos.collectAsStateWithLifecycle()
    val formState by vm.form.collectAsStateWithLifecycle()

    var showDialog by remember { mutableStateOf(false) }
    var toDelete by remember { mutableStateOf<Producto?>(null) }

    if (formState.error != null) {
        AlertDialog(
            onDismissRequest = { vm.limpiarError() },
            confirmButton = { TextButton(onClick = { vm.limpiarError() }) { Text("OK") } },
            title = { Text("Error") },
            text = { Text(formState.error ?: "") }
        )
    }

    if (showDialog) {
        ProductDialog(
            vm = vm,
            onDismiss = { showDialog = false },
            onSaved = { showDialog = false }
        )
    }

    if (toDelete != null) {
        AlertDialog(
            onDismissRequest = { toDelete = null },
            confirmButton = {
                TextButton(onClick = {
                    toDelete?.let(vm::eliminar)
                    toDelete = null
                }) { Text("Eliminar") }
            },
            dismissButton = { TextButton(onClick = { toDelete = null }) { Text("Cancelar") } },
            title = { Text("Confirmar eliminación") },
            text = { Text("¿Seguro que deseas eliminar '${toDelete?.nombre}'?") }
        )
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Productos (Room + MVVM)") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                vm.editar(null)
                showDialog = true
            }) { Text("+") }
        }
    ) { padding ->
        if (productos.isEmpty()) {
            Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text("No hay productos. Presiona + para agregar.")
            }
        } else {
            LazyColumn(Modifier.fillMaxSize().padding(padding)) {
                items(productos, key = { it.id }) { p ->
                    ProductItem(
                        product = p,
                        onEdit = {
                            vm.editar(p)
                            showDialog = true
                        },
                        onDelete = { toDelete = p }
                    )
                }
            }
        }
    }
}

@Composable
private fun ProductItem(
    product: Producto,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Card(Modifier.fillMaxWidth().padding(8.dp)) {
        Row(
            Modifier.fillMaxWidth().padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.clickable { onEdit() }) {
                Text(product.nombre, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text(product.tipo, style = MaterialTheme.typography.labelSmall)
                Text("Precio: $${"%.0f".format(product.precio)} | Stock: ${product.stock}")
                Text("Id: ${product.id}", style = MaterialTheme.typography.bodySmall)
            }
            Row {
                TextButton(onClick = onEdit) { Text("Editar") }
                TextButton(onClick = onDelete) { Text("Eliminar") }
            }
        }
    }
}
