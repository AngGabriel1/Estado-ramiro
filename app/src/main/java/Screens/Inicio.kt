package com.example.estado.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun InscripcionScreen() {

    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var taller by remember { mutableStateOf("Pintura") }
    var aceptaAviso by remember { mutableStateOf(false) }

    var intentoEnviar by remember { mutableStateOf(false) }
    var inscrito by remember { mutableStateOf(false) }

    val errorNombre =
        if (intentoEnviar && nombre.trim().length < 3)
            "El nombre debe tener al menos 3 caracteres"
        else null

    val errorCorreo =
        if (intentoEnviar && !validarCorreo(correo))
            "Ingresa un correo válido"
        else null

    val errorEdad =
        if (intentoEnviar && !validarEdad(edad))
            "La edad debe estar entre 12 y 99"
        else null

    val errorAviso =
        if (intentoEnviar && !aceptaAviso)
            "Debes aceptar el aviso de privacidad"
        else null

    if (inscrito) {

        ConfirmacionScreen(
            nombre = nombre,
            correo = correo,
            taller = taller,
            onNueva = {
                nombre = ""
                correo = ""
                edad = ""
                taller = "Pintura"
                aceptaAviso = false
                intentoEnviar = false
                inscrito = false
            }
        )

    } else {

        InscripcionForm(
            nombre = nombre,
            onNombreChange = { nombre = it },

            correo = correo,
            onCorreoChange = { correo = it },

            edad = edad,
            onEdadChange = { edad = it },

            taller = taller,
            onTallerChange = { taller = it },

            aceptaAviso = aceptaAviso,
            onAceptaAvisoChange = { aceptaAviso = it },

            errorNombre = errorNombre,
            errorCorreo = errorCorreo,
            errorEdad = errorEdad,
            errorAviso = errorAviso,

            onInscribirme = {
                intentoEnviar = true

                val datosValidos =
                    nombre.trim().length >= 3 &&
                            validarCorreo(correo) &&
                            validarEdad(edad) &&
                            aceptaAviso

                if (datosValidos) {
                    inscrito = true
                }
            }
        )
    }
}

fun validarCorreo(correo: String): Boolean {
    val posicionArroba = correo.indexOf("@")
    val posicionPunto = correo.indexOf(".", posicionArroba + 1)

    return posicionArroba > 0 &&
            posicionPunto > posicionArroba + 1
}

fun validarEdad(edad: String): Boolean {
    val numero = edad.toIntOrNull()

    return numero != null && numero in 12..99
}

@Composable
fun InscripcionForm(
    nombre: String,
    onNombreChange: (String) -> Unit,

    correo: String,
    onCorreoChange: (String) -> Unit,

    edad: String,
    onEdadChange: (String) -> Unit,

    taller: String,
    onTallerChange: (String) -> Unit,

    aceptaAviso: Boolean,
    onAceptaAvisoChange: (Boolean) -> Unit,

    errorNombre: String?,
    errorCorreo: String?,
    errorEdad: String?,
    errorAviso: String?,

    onInscribirme: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        if (quizTerminado) {
            Text(
                text = "¡Quiz terminado!",
                style = MaterialTheme.typography.headlineMedium
            )

        Text(
            text = "Inscripción a talleres"
        )

        Spacer(modifier = Modifier.height(16.dp))

        CampoTexto(
            label = "Nombre completo",
            value = nombre,
            onValueChange = onNombreChange,
            error = errorNombre
        )

        Spacer(modifier = Modifier.height(12.dp))

        CampoTexto(
            label = "Correo",
            value = correo,
            onValueChange = onCorreoChange,
            error = errorCorreo,
            keyboardType = KeyboardType.Email
        )

        Spacer(modifier = Modifier.height(12.dp))

        CampoTexto(
            label = "Edad",
            value = edad,
            onValueChange = {
                if (it.all { caracter -> caracter.isDigit() }) {
                    onEdadChange(it)
                }
            },
            error = errorEdad,
            keyboardType = KeyboardType.Number
        )

        Spacer(modifier = Modifier.height(12.dp))

        TallerDropdown(
            taller = taller,
            onTallerChange = onTallerChange
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text("Vaciar carrito")
        }

            Checkbox(
                checked = aceptaAviso,
                onCheckedChange = onAceptaAvisoChange
            )

            Text(
                text = "Acepto el aviso de privacidad",
                modifier = Modifier.padding(top = 12.dp)
            )
        }

        if (errorAviso != null) {
            Text(
                text = errorAviso
            )
        }
    }
}

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onInscribirme,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Inscribirme")
        }
    }
}

@Composable
fun CampoTexto(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    error: String?,
    keyboardType: KeyboardType = KeyboardType.Text
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(label)
        },
        modifier = Modifier.fillMaxWidth(),
        isError = error != null,
        supportingText = {
            if (error != null) {
                Text(error)
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        singleLine = true
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TallerDropdown(
    taller: String,
    onTallerChange: (String) -> Unit
) {

    var abierto by remember { mutableStateOf(false) }

    val talleres = listOf(
        "Pintura",
        "Fotografía",
        "Guitarra"
    )

    ExposedDropdownMenuBox(
        expanded = abierto,
        onExpandedChange = {
            abierto = !abierto
        }
    ) {

        OutlinedTextField(
            value = taller,
            onValueChange = {},
            readOnly = true,
            label = {
                Text("Taller")
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = abierto
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = abierto,
            onDismissRequest = {
                abierto = false
            }
        ) {

            talleres.forEach { opcion ->

                DropdownMenuItem(
                    text = {
                        Text(opcion)
                    },
                    onClick = {
                        onTallerChange(opcion)
                        abierto = false
                    }
                )
            }
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

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Nombre: $nombre"
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Correo: $correo"
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Taller: $taller"
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onNueva,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Nueva inscripción")
        }
    }
}