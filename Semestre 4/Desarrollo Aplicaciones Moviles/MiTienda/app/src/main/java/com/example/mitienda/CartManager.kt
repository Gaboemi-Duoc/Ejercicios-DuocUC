package com.example.mitienda


object CartManager {
    private val items = mutableListOf<Product>()
    fun add(product: Product) = items.add(product)
    fun getAll(): List<Product> = items.toList()
}