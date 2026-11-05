package com.example.estado.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Pregunta(
    val texto: String,
    val opciones: List<String>,
    val respuestaCorrecta: Int
)

private val preguntas = listOf(
    Pregunta(
        "¿Cuál es la capital de México?",
        listOf("Guadalajara", "Ciudad de México", "Monterrey"),
        1
    ),
    Pregunta(
        "¿Cuántos planetas hay en el sistema solar?",
        listOf("7", "8", "9"),
        1
    ),
    Pregunta(
        "¿Cuál es el resultado de 5 × 6?",
        listOf("25", "30", "35"),
        1
    ),
    Pregunta(
        "¿Qué lenguaje se utiliza principalmente en Android?",
        listOf("Kotlin", "PHP", "Swift"),
        0
    ),
    Pregunta(
        "¿Cuál es el océano más grande?",
        listOf("Atlántico", "Índico", "Pacífico"),
        2
    )
)

@Composable
fun QuizScreen() {
    var indicePregunta by remember { mutableStateOf(0) }
    var respuestaSeleccionada by remember { mutableStateOf<Int?>(null) }
    var puntaje by remember { mutableStateOf(0) }
    var quizTerminado by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        if (quizTerminado) {
            Text(
                text = "¡Quiz terminado!",
                style = MaterialTheme.typography.headlineMedium
            )

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

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (respuestaSeleccionada ==
                        preguntaActual.respuestaCorrecta
                    ) {
                        puntaje++
                    }

                    if (indicePregunta < preguntas.lastIndex) {
                        indicePregunta++
                        respuestaSeleccionada = null
                    } else {
                        quizTerminado = true
                    }
                },
                enabled = respuestaSeleccionada != null,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    if (indicePregunta == preguntas.lastIndex) {
                        "Terminar quiz"
                    } else {
                        "Siguiente pregunta"
                    }
                )
            }
        }
    }
}

@Composable
fun OpcionRespuesta(
    texto: String,
    seleccionada: Boolean,
    habilitada: Boolean,
    onSeleccionar: () -> Unit
) {
    if (seleccionada) {
        Button(
            onClick = onSeleccionar,
            enabled = habilitada,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(texto)
        }
    } else {
        OutlinedButton(
            onClick = onSeleccionar,
            enabled = habilitada,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(texto)
        }
    }
}