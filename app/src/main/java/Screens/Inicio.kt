package com.example.estado.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class Interlineado(val factor: Float) {
    COMPACTO(1.0f),
    NORMAL(1.3f),
    AMPLIO(1.6f)
}

@Composable
fun LecturaScreen() {

    var tamano by remember { mutableStateOf(18f) }

    var interlineado by remember {
        mutableStateOf(Interlineado.NORMAL)
    }

    var modoOscuro by remember {
        mutableStateOf(false)
    }

    var usaSerif by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        if (quizTerminado) {
            Text(
                text = "¡Quiz terminado!",
                style = MaterialTheme.typography.headlineMedium
            )

        Text(
            text = "Ajustes de lectura",
            fontSize = 26.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        ControlesLectura(
            tamano = tamano,
            onTamanoChange = { tamano = it },

            interlineado = interlineado,
            onInterlineadoChange = { interlineado = it },

            modoOscuro = modoOscuro,
            onModoOscuroChange = { modoOscuro = it },

            usaSerif = usaSerif,
            onUsaSerifChange = { usaSerif = it },

            onRestablecer = {
                tamano = 18f
                interlineado = Interlineado.NORMAL
                modoOscuro = false
                usaSerif = false
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        TallerDropdown(
            taller = taller,
            onTallerChange = onTallerChange
        )

        Spacer(modifier = Modifier.height(12.dp))

        VistaPrevia(
            texto = "Este es un texto de ejemplo para comprobar cómo se verá la lectura.",
            tamano = tamano,
            interlineado = interlineado,
            modoOscuro = modoOscuro,
            usaSerif = usaSerif
        )
    }
}

@Composable
fun ControlesLectura(
    tamano: Float,
    onTamanoChange: (Float) -> Unit,

    interlineado: Interlineado,
    onInterlineadoChange: (Interlineado) -> Unit,

    modoOscuro: Boolean,
    onModoOscuroChange: (Boolean) -> Unit,

    usaSerif: Boolean,
    onUsaSerifChange: (Boolean) -> Unit,

    onRestablecer: () -> Unit
) {

    Column {

        Text(
            text = "Tamaño de letra: ${tamano.toInt()} sp"
        )

        Slider(
            value = tamano,
            onValueChange = onTamanoChange,
            valueRange = 14f..32f
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Interlineado"
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Vaciar carrito")
        }

            Row {
                RadioButton(
                    selected = interlineado == Interlineado.COMPACTO,
                    onClick = {
                        onInterlineadoChange(Interlineado.COMPACTO)
                    }
                )

                Text("Compacto")
            }

            Row {
                RadioButton(
                    selected = interlineado == Interlineado.NORMAL,
                    onClick = {
                        onInterlineadoChange(Interlineado.NORMAL)
                    }
                )

                Text("Normal")
            }

            Row {
                RadioButton(
                    selected = interlineado == Interlineado.AMPLIO,
                    onClick = {
                        onInterlineadoChange(Interlineado.AMPLIO)
                    }
                )

                Text("Amplio")
            }
        }
    }
}

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Inscribirme")
        }
    }
}

            Text("Modo oscuro")

            Switch(
                checked = modoOscuro,
                onCheckedChange = onModoOscuroChange
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text("Fuente")

            FilterChip(
                selected = usaSerif,
                onClick = {
                    onUsaSerifChange(!usaSerif)
                },
                label = {
                    Text(
                        if (usaSerif) "Serif" else "Sans"
                    )
                }
            )
        }
    }
}

@Composable
fun ConfirmacionScreen(
    nombre: String,
    correo: String,
    taller: String,
    onNueva: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {

        Text(
            text = "¡Inscripción realizada!"
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onRestablecer,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Restablecer")
        }
    }
}

@Composable
fun VistaPrevia(
    texto: String,
    tamano: Float,
    interlineado: Interlineado,
    modoOscuro: Boolean,
    usaSerif: Boolean
) {

    val fondo = if (modoOscuro) {
        Color.DarkGray
    } else {
        Color.White
    }

    val colorTexto = if (modoOscuro) {
        Color.White
    } else {
        Color.Black
    }

    val fuente = if (usaSerif) {
        FontFamily.Serif
    } else {
        FontFamily.SansSerif
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(fondo)
            .padding(16.dp)
    ) {

        Text(
            text = "Vista previa",
            color = colorTexto,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = texto,
            color = colorTexto,
            style = TextStyle(
                fontSize = tamano.sp,
                lineHeight = (tamano * interlineado.factor).sp,
                fontFamily = fuente
            )
        )
    }
}