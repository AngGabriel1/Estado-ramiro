package com.example.estado.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        if (quizTerminado) {
            Text(
                text = "¡Quiz terminado!",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Tu puntuación fue: $puntaje de ${preguntas.size}",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    indicePregunta = 0
                    respuestaSeleccionada = null
                    puntaje = 0
                    quizTerminado = false
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Volver a intentar")
            }
        } else {
            val preguntaActual = preguntas[indicePregunta]

            Text(
                text = "Quiz de cultura general",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Pregunta ${indicePregunta + 1} de ${preguntas.size}",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = preguntaActual.texto,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            preguntaActual.opciones.forEachIndexed { indice, opcion ->
                OpcionRespuesta(
                    texto = opcion,
                    seleccionada = respuestaSeleccionada == indice,
                    habilitada = respuestaSeleccionada == null,
                    onSeleccionar = {
                        respuestaSeleccionada = indice
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))
            }

            if (respuestaSeleccionada != null) {
                val esCorrecta =
                    respuestaSeleccionada == preguntaActual.respuestaCorrecta

                Text(
                    text = if (esCorrecta) {
                        "¡Respuesta correcta!"
                    } else {
                        "Respuesta incorrecta"
                    },
                    color = if (esCorrecta) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.error
                    },
                    style = MaterialTheme.typography.titleMedium
                )
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