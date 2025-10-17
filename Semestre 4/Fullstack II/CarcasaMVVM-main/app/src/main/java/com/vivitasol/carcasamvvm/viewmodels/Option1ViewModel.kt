package com.vivitasol.carcasamvvm.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Option1ViewModel : ViewModel() {
    private val _texto = MutableStateFlow("Pantalla Opción 1 (en blanco)")
    val texto: StateFlow<String> = _texto
}
