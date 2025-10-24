package com.example.sqlite.ui


data class ProductFormState(
    val id: Long? = null,
    val nombre: String = "",
    val precio: String = "",
    val stock: String = "",
    val tipo: String = "",
    val error: String? = null
)