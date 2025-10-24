package com.example.sqlite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sqlite.data.Producto
import com.example.sqlite.data.ProductRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProductViewModel(private val repo: ProductRepository): ViewModel() {

    // Lista observable desde la UI
    val productos: StateFlow<List<Producto>> =
        repo.productos.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    private val _form = MutableStateFlow(ProductFormState())
    val form: StateFlow<ProductFormState> = _form.asStateFlow()

    fun editar(product: Producto?) {
        _form.value = if (product == null) {
            ProductFormState()
        } else {
            ProductFormState(
                id = product.id,
                nombre = product.nombre,
                precio = product.precio.toString(),
                stock = product.stock.toString(),
                tipo = product.tipo
            )
        }
    }

    fun onNombreChange(v: String) { _form.update { it.copy(nombre = v) } }
    fun onTipoChange(v: String) { _form.update { it.copy(tipo = v) } }
    fun onPrecioChange(v: String) { _form.update { it.copy(precio = v) } }
    fun onStockChange(v: String) { _form.update { it.copy(stock = v) } }
    fun limpiarError() { _form.update { it.copy(error = null) } }

    fun guardar(oAlFinal: () -> Unit = {}) = viewModelScope.launch {
        try {
            val f = _form.value
            val precio = f.precio.replace(",", ".").toDoubleOrNull() ?: throw IllegalArgumentException("Precio inválido")
            val stock = f.stock.toIntOrNull() ?: throw IllegalArgumentException("Stock inválido")

            if (f.id == null) {
                repo.agregar(f.nombre, precio, stock, f.tipo)
            } else {
                repo.actualizar(f.id, f.nombre, precio, stock, f.tipo)
            }
            editar(null)
            oAlFinal()
        } catch (e: Exception) {
            _form.update { it.copy(error = e.message ?: "Error desconocido") }
        }
    }

    fun eliminar(product: Producto) = viewModelScope.launch {
        repo.eliminar(product)
    }
}
