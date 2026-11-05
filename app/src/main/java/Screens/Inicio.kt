package com.example.estado.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FilterChip
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Tarea(
    val id: Int,
    val nombre: String,
    val completada: Boolean = false
)

enum class FiltroTareas {
    TODAS,
    PENDIENTES,
    COMPLETADAS
}

@Composable
fun TareasScreen() {

    var nombreTarea by remember {
        mutableStateOf("")
    }

    var tareas by remember {
        mutableStateOf(listOf<Tarea>())
    }

    var filtro by remember {
        mutableStateOf(FiltroTareas.TODAS)
    }

    var siguienteId by remember {
        mutableIntStateOf(1)
    }

    var tareaEliminar by remember {
        mutableStateOf<Tarea?>(null)
    }

    val tareasFiltradas = when (filtro) {
        FiltroTareas.TODAS -> tareas
        FiltroTareas.PENDIENTES -> tareas.filter { !it.completada }
        FiltroTareas.COMPLETADAS -> tareas.filter { it.completada }
    }

    val pendientes = tareas.count { !it.completada }
    val completadas = tareas.count { it.completada }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {

        Text("Gestor de tareas")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nombreTarea,
            onValueChange = {
                nombreTarea = it
            },
            label = {
                Text("Nueva tarea")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (nombreTarea.isNotBlank()) {
                    tareas = tareas + Tarea(
                        id = siguienteId,
                        nombre = nombreTarea.trim()
                    )

                    siguienteId++
                    nombreTarea = ""
                }
            },
            enabled = nombreTarea.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar tarea")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Total: ${tareas.size}")
        Text("Pendientes: $pendientes")
        Text("Completadas: $completadas")

        Spacer(modifier = Modifier.height(16.dp))

        Text("Filtrar tareas")

        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            FilterChip(
                selected = filtro == FiltroTareas.TODAS,
                onClick = {
                    filtro = FiltroTareas.TODAS
                },
                label = {
                    Text("Todas")
                }
            )

            FilterChip(
                selected = filtro == FiltroTareas.PENDIENTES,
                onClick = {
                    filtro = FiltroTareas.PENDIENTES
                },
                label = {
                    Text("Pendientes")
                }
            )

            FilterChip(
                selected = filtro == FiltroTareas.COMPLETADAS,
                onClick = {
                    filtro = FiltroTareas.COMPLETADAS
                },
                label = {
                    Text("Completadas")
                }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn {
            items(
                items = tareasFiltradas,
                key = { it.id }
            ) { tarea ->

                TareaItem(
                    tarea = tarea,
                    onCambiarEstado = {
                        tareas = tareas.map {
                            if (it.id == tarea.id) {
                                it.copy(
                                    completada = !it.completada
                                )
                            } else {
                                it
                            }
                        }
                    },
                    onEliminar = {
                        tareaEliminar = tarea
                    }
                )
            }
        }
    }

    if (tareaEliminar != null) {

        AlertDialog(
            onDismissRequest = {
                tareaEliminar = null
            },
            title = {
                Text("Eliminar tarea")
            },
            text = {
                Text(
                    "¿Estás seguro de que deseas eliminar '${tareaEliminar?.nombre}'?"
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        tareas = tareas.filter {
                            it.id != tareaEliminar?.id
                        }

                        tareaEliminar = null
                    }
                ) {
                    Text("Eliminar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        tareaEliminar = null
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Composable
fun TareaItem(
    tarea: Tarea,
    onCambiarEstado: () -> Unit,
    onEliminar: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Checkbox(
            checked = tarea.completada,
            onCheckedChange = {
                onCambiarEstado()
            }
        )

        Text(
            text = tarea.nombre,
            modifier = Modifier.weight(1f)
        )

        TextButton(
            onClick = onEliminar
        ) {
            Text("Eliminar")
        }
    }
}