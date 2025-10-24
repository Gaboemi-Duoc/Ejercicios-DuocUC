package com.vivitasol.carcasamvvm.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Option2ViewModel : ViewModel() {
    // Simulamos una lista simple para navegar a detalle
    private val _items = MutableStateFlow((1..10).map { "Item $it" })
    val items: StateFlow<List<String>> = _items
}
