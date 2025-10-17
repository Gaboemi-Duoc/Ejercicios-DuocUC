package com.vivitasol.carcasamvvm.views

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import kotlinx.coroutines.launch
import com.vivitasol.carcasamvvm.navigation.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuShellView() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val innerNavController = rememberNavController()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    text = "Menú",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(16.dp)
                )
                NavigationDrawerItem(
                    label = { Text("2.1.3Componentes") },
                    selected = currentInnerRoute(innerNavController) == Route.Option1.route,
                    onClick = {
                        innerNavController.navigate(Route.Option1.route) {
                            popUpTo(Route.Option1.route) { inclusive = false }
                            launchSingleTop = true
                        }
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(
                    label = { Text("2.2.4Navegacion") },
                    selected = currentInnerRoute(innerNavController) == Route.Option2.route,
                    onClick = {
                        innerNavController.navigate(Route.Option2.route) {
                            popUpTo(Route.Option1.route) { inclusive = false }
                            launchSingleTop = true
                        }
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(
                    label = { Text("2.3.3form") },
                    selected = currentInnerRoute(innerNavController) == Route.Option3.route,
                    onClick = {
                        innerNavController.navigate(Route.Option3.route) {
                            popUpTo(Route.Option1.route) { inclusive = false }
                            launchSingleTop = true
                        }
                        scope.launch { drawerState.close() }
                    }
                )

                NavigationDrawerItem(
                    label = { Text("2.4.2Persistencia y Animaciones") },
                    selected = currentInnerRoute(innerNavController) == Route.Option4.route,
                    onClick = {
                        innerNavController.navigate(Route.Option4.route) {
                            popUpTo(Route.Option1.route) { inclusive = false }
                            launchSingleTop = true
                        }
                        scope.launch { drawerState.close() }
                    }
                )

                NavigationDrawerItem(
                    label = { Text("2.4.4FuncionNativa(Camara)") },
                    selected = currentInnerRoute(innerNavController) == Route.Option5.route,
                    onClick = {
                        innerNavController.navigate(Route.Option5.route) {
                            popUpTo(Route.Option1.route) { inclusive = false }
                            launchSingleTop = true
                        }
                        scope.launch { drawerState.close() }
                    }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Carcasa Ejemplos Semestre") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerState.isClosed) drawerState.open() else drawerState.close()
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menú")
                        }
                    }
                )
            }
        ) { innerPadding ->
            // NavHost interno para las opciones del menú
            NavHost(
                navController = innerNavController,
                startDestination = Route.Option1.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Route.Option1.route) { Option1View() }
                composable(Route.Option2.route) { Option2View(navController = innerNavController) } // <--recibe nav
                composable(Route.Option3.route) { Option3View() }
                //pantalla de detalle para la clase 2(con nav)
                composable(Route.Option2Detail.route) { backStack ->
                    val id = backStack.arguments?.getString("id") ?: "sin-id"
                    Option2DetailView(
                        id = id,
                        onBack = { innerNavController.navigateUp() }
                    )
                }
                composable(Route.Option4.route) { Option4View() }
                composable(Route.Option5.route) { Option5CameraView() }
            }
        }
    }
}

@Composable
private fun currentInnerRoute(navController: NavHostController): String? {
    val entry by navController.currentBackStackEntryAsState()
    return entry?.destination?.route
}
