package com.example.estado.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen() {

    // Estados principales
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var mostrarContrasena by remember { mutableStateOf(false) }
    var recordarSesion by remember { mutableStateOf(false) }

    // Estados de validación y mensaje
    var errorCorreo by remember { mutableStateOf("") }
    var errorContrasena by remember { mutableStateOf("") }
    var mostrarDialogo by remember { mutableStateOf(false) }

    fun iniciarSesion() {
        errorCorreo = ""
        errorContrasena = ""

        if (correo.isBlank()) {
            errorCorreo = "Ingresa tu correo electrónico"
        } else if (!correo.contains("@") || !correo.contains(".")) {
            errorCorreo = "Ingresa un correo válido"
        }

        if (contrasena.isBlank()) {
            errorContrasena = "Ingresa tu contraseña"
        }

        if (errorCorreo.isEmpty() && errorContrasena.isEmpty()) {
            mostrarDialogo = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Clínica Vida",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Bienvenido. Inicia sesión para continuar."
        )

        Spacer(modifier = Modifier.height(28.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = {
                correo = it
                errorCorreo = ""
            },
            label = { Text("Correo electrónico") },
            placeholder = { Text("ejemplo@correo.com") },
            singleLine = true,
            isError = errorCorreo.isNotEmpty(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            modifier = Modifier.fillMaxWidth()
        )

        if (errorCorreo.isNotEmpty()) {
            Text(
                text = errorCorreo,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = contrasena,
            onValueChange = {
                contrasena = it
                errorContrasena = ""
            },
            label = { Text("Contraseña") },
            singleLine = true,
            isError = errorContrasena.isNotEmpty(),
            visualTransformation = if (mostrarContrasena) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                TextButton(
                    onClick = {
                        mostrarContrasena = !mostrarContrasena
                    }
                ) {
                    Text(
                        if (mostrarContrasena) "Ocultar"
                        else "Mostrar"
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        if (errorContrasena.isNotEmpty()) {
            Text(
                text = errorContrasena,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        androidx.compose.foundation.layout.Row(
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Checkbox(
                checked = recordarSesion,
                onCheckedChange = {
                    recordarSesion = it
                }
            )

            Text("Recordar mi sesión")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { iniciarSesion() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar sesión")
        }
    }

    if (mostrarDialogo) {
        AlertDialog(
            onDismissRequest = {
                mostrarDialogo = false
            },
            title = {
                Text("¡Bienvenido a Clínica Vida!")
            },
            text = {
                Text(
                    "Sesión iniciada correctamente para $correo."
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        mostrarDialogo = false
                    }
                ) {
                    Text("Aceptar")
                }
            }
        )
    }
}