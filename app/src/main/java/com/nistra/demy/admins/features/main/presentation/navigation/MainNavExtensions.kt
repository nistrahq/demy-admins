package com.nistra.demy.admins.features.main.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

fun NavHostController.currentTitle(): String {
    val parentRoute = currentParentRoute()
    return when (parentRoute) {
        MainDestination.Dashboard.route -> "Dashboard"
        MainDestination.Teachers.route -> "Teachers"
        MainDestination.Students.route -> "Students"
        else -> "Demy Admins"
    }
}

/**
 * Versión Composable que observa reactivamente los cambios de navegación.
 *
 * Esta es la versión que debe usarse en @Composable functions para asegurar
 * que se recompone correctamente cuando cambia la ruta.
 *
 * Usa el mismo sistema de mapeo centralizado que `currentParentRoute()`, por lo
 * que agregar un nuevo módulo en `moduleToParentRouteMap` funcionará automáticamente
 * en ambas funciones.
 *
 * @see currentParentRoute
 * @see moduleToParentRouteMap
 */
@Composable
fun NavHostController.currentParentRouteAsState(): String {
    val navBackStackEntry by currentBackStackEntryAsState()
    val childRoute = navBackStackEntry?.destination?.route
    val parentRoute = navBackStackEntry?.destination?.parent?.route

    // Si hay una ruta padre disponible en el grafo, usarla directamente
    if (parentRoute != null) {
        return parentRoute
    }

    // Si no hay ruta padre, extraer el módulo desde la ruta hija
    if (childRoute != null) {
        val moduleName = childRoute.split("/").firstOrNull()

        // Buscar en el mapa centralizado
        val mappedParentRoute = moduleName?.let { moduleToParentRouteMap[it] }

        if (mappedParentRoute != null) {
            return mappedParentRoute
        }
    }

    return childRoute ?: ""
}

/**
 * Mapa centralizado de módulos a sus rutas padre.
 *
 * Este mapa define la relación entre el nombre del módulo (primer segmento de la ruta)
 * y su ruta de grafo padre completa.
 *
 * **IMPORTANTE AL AGREGAR NUEVOS MÓDULOS:**
 * Cuando agregues un nuevo módulo, solo añade una entrada aquí:
 * ```
 * "courses" to MainDestination.Courses.route
 * ```
 *
 * Esto funcionará automáticamente para todos los destinos del módulo:
 * - courses/list -> main/courses
 * - courses/create -> main/courses
 * - courses/edit/{id} -> main/courses
 *
 * @author Salim Ramirez
 */
private val moduleToParentRouteMap = mapOf(
    "dashboard" to MainDestination.Dashboard.route,
    "teachers" to MainDestination.Teachers.route,
    "students" to MainDestination.Students.route
    // Agregar nuevos módulos aquí
)

/**
 * Obtiene la ruta del grafo padre actual.
 *
 * Esta función es esencial para la selección correcta del Drawer en una
 * arquitectura de navegación con grafos anidados.
 *
 * Ejemplo de estructura de navegación:
 * ```
 * main/teachers (grafo padre)
 *   ├─ teachers/register (destino hijo)
 *   ├─ teachers/list (destino hijo)
 *   ├─ teachers/edit/{id} (destino hijo con parámetros)
 *   └─ teachers/details/{id} (destino hijo con parámetros)
 * ```
 *
 * Cuando estás en cualquiera de estos destinos, esta función devuelve "main/teachers"
 * para que el Drawer pueda marcar correctamente la opción "Teachers" como seleccionada.
 *
 * **ESCALABILIDAD:**
 * Esta función está diseñada para funcionar con múltiples destinos dentro de un módulo.
 * Solo necesitas agregar el módulo una vez en `moduleToParentRouteMap` y funcionará
 * para todos sus destinos presentes y futuros.
 *
 * @return La ruta del grafo padre, o la ruta actual si no está en un grafo anidado
 * @author Salim Ramirez
 */
fun NavHostController.currentParentRoute(): String {
    val entry = currentBackStackEntry ?: return ""
    val childRoute = entry.destination.route
    val parentRoute = entry.destination.parent?.route

    // Si hay una ruta padre disponible en el grafo, usarla directamente
    if (parentRoute != null) {
        return parentRoute
    }

    // Si no hay ruta padre, extraer el módulo desde la ruta hija
    // Formato esperado: "{modulo}/{destino}[/{params}]"
    if (childRoute != null) {
        val moduleName = childRoute.split("/").firstOrNull()

        // Buscar en el mapa centralizado
        val mappedParentRoute = moduleName?.let { moduleToParentRouteMap[it] }

        if (mappedParentRoute != null) {
            return mappedParentRoute
        }
    }

    return childRoute ?: ""
}

