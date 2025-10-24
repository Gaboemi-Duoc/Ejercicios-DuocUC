package com.example.mitienda


import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class ProductViewModel : ViewModel() {
    // lista observable de productos
    private val _products = mutableStateListOf<Product>()
    val products: List<Product> get() = _products

    // carrito (lista observable si quieres)
    private val _cart = mutableStateListOf<Product>()
    val cart: List<Product> get() = _cart

    private var nextId = 1

    fun addProduct(name: String, price: Double) {
        val p = Product(nextId++, name, price)
        _products.add(p)
    }

    fun addToCart(product: Product) {
        _cart.add(product)
    }

    fun clearAll() {
        _products.clear()
        _cart.clear()
        nextId = 1
    }
}
