package com.example.estado.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Resena(
    val id: Int,
    val comentario: String,
    val calificacion: Int
)

@Composable
fun ResenasScreen() {

    // Estados principales de la pantalla
    var comentario by remember { mutableStateOf("") }
    var calificacion by remember { mutableStateOf(0) }
    var mensajeError by remember { mutableStateOf("") }

    val resenas = remember {
        mutableStateListOf<Resena>()
    }

    val promedio = if (resenas.isNotEmpty()) {
        resenas.map { it.calificacion }.average()
    } else {
        0.0
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {

        Text(
            text = "Sistema de reseñas",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text("Comparte tu opinión y califica tu experiencia.")

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Escribe tu reseña",
            style = MaterialTheme.typography.titleMedium
        )

        OutlinedTextField(
            value = comentario,
            onValueChange = {
                comentario = it
                mensajeError = ""
            },
            label = { Text("Tu comentario") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Selecciona tu calificación",
            style = MaterialTheme.typography.titleMedium
        )

        SelectorEstrellas(
            calificacion = calificacion,
            onCalificacionChange = {
                calificacion = it
                mensajeError = ""
            }
        )

        if (mensajeError.isNotEmpty()) {
            Text(
                text = mensajeError,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                if (comentario.isBlank()) {
                    mensajeError = "Escribe un comentario."
                } else if (calificacion == 0) {
                    mensajeError = "Selecciona una calificación."
                } else {
                    resenas.add(
                        Resena(
                            id = (resenas.maxOfOrNull { it.id } ?: 0) + 1,
                            comentario = comentario,
                            calificacion = calificacion
                        )
                    )

                    comentario = ""
                    calificacion = 0
                    mensajeError = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Publicar reseña")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Resumen de reseñas",
            style = MaterialTheme.typography.titleLarge
        )

        Text("Total de reseñas: ${resenas.size}")

        Text(
            text = "Calificación promedio: ${
                if (resenas.isEmpty()) "Sin calificaciones"
                else String.format("%.1f / 5", promedio)
            }"
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Opiniones de los usuarios",
            style = MaterialTheme.typography.titleMedium
        )

        if (resenas.isEmpty()) {
            Text("Todavía no hay reseñas.")
        } else {
            resenas.forEach { resena ->
                ResenaItem(
                    resena = resena,
                    onEliminar = {
                        resenas.remove(resena)
                    }
                )
            }
        }
    }
}

@Composable
fun SelectorEstrellas(
    calificacion: Int,
    onCalificacionChange: (Int) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (numero in 1..5) {
            TextButton(
                onClick = {
                    onCalificacionChange(numero)
                }
            ) {
                Text(
                    text = if (numero <= calificacion) "★" else "☆",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun ResenaItem(
    resena: Resena,
    onEliminar: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Calificación: ${"★".repeat(resena.calificacion)}",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(text = resena.comentario)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = onEliminar) {
                    Text("Eliminar")
                }
            }
        }
    }
}