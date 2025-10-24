package com.vivitasol.carcasamvvm.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class FormState(
    val nombre: String = "",
    val email: String = "",
    val edad: String = "",
    val rol: String = "",
    val aceptaTerminos: Boolean = false,
    val suscripcion: Boolean = false
)

data class FormErrors(
    val nombre: String? = null,
    val email: String? = null,
    val edad: String? = null,
    val rol: String? = null,
    val terminos: String? = null
)

class Option3ViewModel : ViewModel() {
    private val _state = MutableStateFlow(FormState())
    val state: StateFlow<FormState> = _state

    private val _errors = MutableStateFlow(FormErrors())
    val errors: StateFlow<FormErrors> = _errors

    private val rolesValidos = listOf("Estudiante", "Docente", "Invitad@")
    val roles: List<String> = rolesValidos

    fun onNombreChange(v: String) { _state.value = _state.value.copy(nombre = v) }
    fun onEmailChange(v: String) { _state.value = _state.value.copy(email = v) }
    fun onEdadChange(v: String) { _state.value = _state.value.copy(edad = v) }
    fun onRolChange(v: String) { _state.value = _state.value.copy(rol = v) }
    fun onTerminosChange(v: Boolean) { _state.value = _state.value.copy(aceptaTerminos = v) }
    fun onSuscripcionChange(v: Boolean) { _state.value = _state.value.copy(suscripcion = v) }

    fun validate(): Boolean {
        val s = _state.value
        var ok = true

        val nombreErr = if (s.nombre.isBlank()) "Obligatorio" else null
        val emailErr = when {
            s.email.isBlank() -> "Obligatorio"
            !android.util.Patterns.EMAIL_ADDRESS.matcher(s.email).matches() -> "Email inválido"
            else -> null
        }
        val edadErr = when {
            s.edad.isBlank() -> "Obligatorio"
            s.edad.toIntOrNull() == null -> "Debe ser número"
            s.edad.toInt() !in 1..120 -> "Rango 1-120"
            else -> null
        }
        val rolErr = if (s.rol.isBlank() || s.rol !in rolesValidos) "Selecciona un rol" else null
        val termErr = if (!s.aceptaTerminos) "Debes aceptar términos" else null

        _errors.value = FormErrors(nombreErr, emailErr, edadErr, rolErr, termErr)
        ok = listOf(nombreErr, emailErr, edadErr, rolErr, termErr).all { it == null }
        return ok
    }

    fun reset() {
        _state.value = FormState()
        _errors.value = FormErrors()
    }
}
