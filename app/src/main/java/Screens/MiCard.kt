package com.example.estado.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.estado.Persona

@Composable
fun InicioScreen(
    modifier: Modifier = Modifier,
    nombre: String,
    color: String,
    listaPersonas: List<Persona>,
    onNombreChange: (String) -> Unit,
    onColorChange: (String) -> Unit,
    onAgregar: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        TextField(
            value = nombre,
            onValueChange = onNombreChange,
            label = {
                Text("Nombre")
            }
        )

        TextField(
            value = color,
            onValueChange = onColorChange,
            label = {
                Text("Color")
            }
        )

        Button(
            onClick = onAgregar
        ) {
            Text("Agregar")
        }

        LazyColumn {

            items(listaPersonas) { persona ->

                MiCard(
                    nombre = persona.nombre,
                    color = persona.color
                )
            }
        }
    }
}