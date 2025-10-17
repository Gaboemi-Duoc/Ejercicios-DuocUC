package com.vivitasol.carcasamvvm.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vivitasol.carcasamvvm.viewmodels.Option3ViewModel
import kotlinx.coroutines.launch

/**
 * CLASE 3: Formularios + Validaciones + Componentes interactivos
 * - TextField con error (nombre, email, edad).
 * - Dropdown (rol).
 * - Checkbox (acepta términos), Switch (suscripción).
 * - Botón de enviar habilitado sólo si es válido.
 * - Snackbar para feedback y reset de formulario.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Option3View(
    vm: Option3ViewModel = viewModel()
) {
    val state = vm.state.collectAsState().value
    val errors = vm.errors.collectAsState().value
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var rolMenuExpanded by remember { mutableStateOf(false) }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Formulario con validación", style = MaterialTheme.typography.titleLarge)

            // NOMBRE
            OutlinedTextField(
                value = state.nombre,
                onValueChange = vm::onNombreChange,
                label = { Text("Nombre completo *") },
                isError = errors.nombre != null,
                supportingText = { if (errors.nombre != null) Text(errors.nombre!!) },
                modifier = Modifier.fillMaxWidth()
            )

            // EMAIL
            OutlinedTextField(
                value = state.email,
                onValueChange = vm::onEmailChange,
                label = { Text("Email *") },
                isError = errors.email != null,
                supportingText = { if (errors.email != null) Text(errors.email!!) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )

            // EDAD
            OutlinedTextField(
                value = state.edad,
                onValueChange = vm::onEdadChange,
                label = { Text("Edad *") },
                isError = errors.edad != null,
                supportingText = { if (errors.edad != null) Text(errors.edad!!) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            // ROL - ExposedDropdown (Material3)
            ExposedDropdownMenuBox(
                expanded = rolMenuExpanded,
                onExpandedChange = { rolMenuExpanded = !rolMenuExpanded }
            ) {
                OutlinedTextField(
                    value = state.rol,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Rol *") },
                    isError = errors.rol != null,
                    supportingText = { if (errors.rol != null) Text(errors.rol!!) },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = rolMenuExpanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = rolMenuExpanded,
                    onDismissRequest = { rolMenuExpanded = false }
                ) {
                    vm.roles.forEach { rol ->
                        DropdownMenuItem(
                            text = { Text(rol) },
                            onClick = {
                                vm.onRolChange(rol)
                                rolMenuExpanded = false
                            }
                        )
                    }
                }
            }

            // ACEPTA TÉRMINOS + SWITCH
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Checkbox(
                        checked = state.aceptaTerminos,
                        onCheckedChange = vm::onTerminosChange
                    )
                    Column {
                        Text("Acepto términos *")
                        if (errors.terminos != null) {
                            Text(errors.terminos!!, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
                        }
                    }
                }
                Row {
                    Text("Suscribirme")
                    Spacer(Modifier.width(8.dp))
                    Switch(checked = state.suscripcion, onCheckedChange = vm::onSuscripcionChange)
                }
            }

            Spacer(Modifier.height(8.dp))

            // BOTONES
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = {
                        val ok = vm.validate()
                        if (ok) {
                            scope.launch {
                                snackbarHostState.showSnackbar("Formulario válido. Enviando…")
                            }
                            // Simular envío y limpiar
                            vm.reset()
                        } else {
                            scope.launch {
                                snackbarHostState.showSnackbar("Revisa los campos resaltados.")
                            }
                        }
                    }
                ) {
                    Text("Enviar")
                }
                OutlinedButton(onClick = { vm.reset() }) {
                    Text("Limpiar")
                }
            }
        }
    }
}

