package com.example.mitienda

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TiendaApp(viewModel: ProductViewModel) {
    var currentScreen by remember { mutableStateOf(Screen.Admin) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = if (currentScreen == Screen.Admin)
                            "Tienda  - Admin"
                        else
                            "Tienda  - Cliente"
                    )
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentScreen == Screen.Admin,
                    onClick = { currentScreen = Screen.Admin },
                    icon = { Icon(Icons.Default.AdminPanelSettings, contentDescription = "Admin") },
                    label = { Text("Admin") }
                )
                NavigationBarItem(
                    selected = currentScreen == Screen.Client,
                    onClick = { currentScreen = Screen.Client },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Cliente") },
                    label = { Text("Cliente") }
                )
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (currentScreen) {
                Screen.Admin -> AdminScreen(viewModel)
                Screen.Client -> ClientScreen(viewModel)
            }
        }
    }
}

enum class Screen { Admin, Client }

@Composable
fun AdminScreen(viewModel: ProductViewModel) {
    var name by remember { mutableStateOf("") }
    var priceText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre del producto") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = priceText,
            onValueChange = { priceText = it },
            label = { Text("Precio") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                val price = priceText.toDoubleOrNull() ?: 0.0
                if (name.isNotBlank()) {
                    viewModel.addProduct(name.trim(), price)
                    name = ""
                    priceText = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar producto")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Productos ingresados",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        ProductList(products = viewModel.products, onAddToCart = { })
    }
}

@Composable
fun ClientScreen(viewModel: ProductViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Catálogo", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        ProductList(products = viewModel.products, onAddToCart = { product ->
            viewModel.addToCart(product)
        })

        Spacer(modifier = Modifier.height(16.dp))

        Text("Carrito (${viewModel.cart.size})", style = MaterialTheme.typography.titleMedium)

        if (viewModel.cart.isNotEmpty()) {
            LazyColumn {
                items(viewModel.cart) { item ->
                    Text("- ${item.name}  $${item.price}")
                }
            }
        }
    }
}

@Composable
fun ProductList(products: List<Product>, onAddToCart: (Product) -> Unit) {
    if (products.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("No hay productos aún")
        }
    } else {
        LazyColumn {
            items(products) { product ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(product.name, style = MaterialTheme.typography.titleMedium)
                            Text("$${product.price}", style = MaterialTheme.typography.bodyMedium)
                        }
                        Button(onClick = { onAddToCart(product) }) {
                            Text("Agregar")
                        }
                    }
                }
            }
        }
    }
}
