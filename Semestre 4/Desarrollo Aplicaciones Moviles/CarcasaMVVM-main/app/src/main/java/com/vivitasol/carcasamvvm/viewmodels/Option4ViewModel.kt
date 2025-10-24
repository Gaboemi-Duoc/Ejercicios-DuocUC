package com.vivitasol.carcasamvvm.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class Option4UiState(
    val suscripcion: Boolean = false // preferencia persistida
)

class Option4ViewModel : ViewModel() {
    private val _ui = MutableStateFlow(Option4UiState())
    val ui: StateFlow<Option4UiState> = _ui

    fun setSuscripcion(enabled: Boolean) {
        _ui.value = _ui.value.copy(suscripcion = enabled)
    }
}
