package com.nistra.demy.admins.core.navigation

import androidx.navigation.NavController

/**
 * Navega a una ruta solo si no estás ya en ella.
 *
 * Esta función previene navegaciones duplicadas que causan parpadeos y problemas
 * de UI. Compara tanto la ruta actual como la ruta del grafo padre para manejar
 * correctamente grafos de navegación anidados.
 *
 * Ejemplo de grafo anidado:
 * - Grafo padre: "main/teachers"
 * - Ruta hija: "teachers/register"
 *
 * Si estás en "teachers/register" y navegas a "main/teachers", la función
 * detectará que ya estás en ese grafo y bloqueará la navegación.
 *
 * @param route La ruta destino a navegar
 * @param popUpToRoute Ruta opcional hasta donde hacer pop en el back stack
 * @param inclusive Si true, incluye la ruta popUpToRoute en el pop
 *
 * @author Salim Ramirez
 */
fun NavController.navigateOnce(
    route: String,
    popUpToRoute: String? = null,
    inclusive: Boolean = false
) {
    val currentEntry = currentBackStackEntry
    val currentRoute = currentEntry?.destination?.route
    val currentParentRoute = currentEntry?.destination?.parent?.route

    // Comparar tanto con la ruta actual como con la ruta del grafo padre
    // para evitar navegaciones duplicadas en grafos anidados
    if (currentRoute == route || currentParentRoute == route) {
        return
    }

    this.navigate(route) {
        launchSingleTop = true
        restoreState = true
        if (popUpToRoute != null) {
            popUpTo(popUpToRoute) {
                saveState = true
                this.inclusive = inclusive
            }
        }
    }
}

fun NavController.navigateOnce(destination: RootDestination) {
    navigateOnce(destination.route)
}

fun NavController.popToRoot() {
    this.popBackStack(this.graph.startDestinationId, false)
}

fun NavController.back() {
    popBackStack()
}

fun NavController.replaceCurrent(route: String) {
    popBackStack()
    navigateOnce(route)
}
