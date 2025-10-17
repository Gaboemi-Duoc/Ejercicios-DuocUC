package com.vivitasol.carcasamvvm.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WelcomeViewModel : ViewModel() {
    private val _titulo = MutableStateFlow("Bienvenid@s")
    val titulo: StateFlow<String> = _titulo
}
