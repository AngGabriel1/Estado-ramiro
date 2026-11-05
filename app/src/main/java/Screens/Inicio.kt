
package com.example.estado.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions

@Composable
fun InicioScreen(
    modifier: Modifier = Modifier,

    numero1: String,
    numero2: String,
    resultado: String,

    onNumero1Change: (String) -> Unit,
    onNumero2Change: (String) -> Unit,

    onCalcular: (String) -> Unit,
    onLimpiar: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),

        verticalArrangement = Arrangement.Top
    ) {

        Text(
            text = "Calculadora"
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Primer número
        TextField(
            value = numero1,
            onValueChange = onNumero1Change,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Primer número")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Segundo número
        TextField(
            value = numero2,
            onValueChange = onNumero2Change,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Segundo número")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Primera fila de operaciones
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Button(
                onClick = {
                    onCalcular("+")
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("+")
            }

            Button(
                onClick = {
                    onCalcular("-")
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("-")
            }

            Button(
                onClick = {
                    onCalcular("*")
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("×")
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Segunda fila de operaciones
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Button(
                onClick = {
                    onCalcular("/")
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("÷")
            }

            Button(
                onClick = {
                    onCalcular("%")
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("%")
            }

            Button(
                onClick = {
                    onCalcular("^")
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("xʸ")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Mostrar resultado
        MiCard(
            resultado = resultado
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Limpiar calculadora
        OutlinedButton(
            onClick = onLimpiar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Limpiar")
        }

    }
}
