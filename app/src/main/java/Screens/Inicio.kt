package com.example.estado.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Producto(
    val nombre: String,
    val precio: Double
)

@Composable
fun CarritoScreen() {

    val catalogo = remember {
        listOf(
            Producto("Tortillas 1 kg", 24.00),
            Producto("Leche 1 L", 27.50),
            Producto("Huevo 12 pz", 52.00),
            Producto("Frijol 1 kg", 38.00)
        )
    }

    val cantidades = remember {
        mutableStateListOf(0, 0, 0, 0)
    }

    val articulos = cantidades.sum()

    val subtotal = catalogo.indices.sumOf {
        catalogo[it].precio * cantidades[it]
    }

    val descuento = if (subtotal >= 200) {
        subtotal * 0.10
    } else {
        0.0
    }

    val subtotalConDescuento = subtotal - descuento

    val envio = when {
        articulos == 0 -> 0.0
        subtotalConDescuento >= 150 -> 0.0
        else -> 25.0
    }

    val total = subtotalConDescuento + envio

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Text(
            text = "Carrito Don Pepe",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        catalogo.forEachIndexed { index, producto ->

            ProductoItem(
                producto = producto,
                cantidad = cantidades[index],
                onCantidadChange = { nuevaCantidad ->
                    cantidades[index] = nuevaCantidad
                }
            )

            Spacer(modifier = Modifier.height(12.dp))
        }

        ResumenCarrito(
            articulos = articulos,
            subtotal = subtotal,
            descuento = descuento,
            envio = envio,
            total = total
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                cantidades.indices.forEach {
                    cantidades[it] = 0
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Vaciar carrito")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {},
            enabled = articulos > 0,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Confirmar pedido")
        }
    }
}

@Composable
fun ProductoItem(
    producto: Producto,
    cantidad: Int,
    onCantidadChange: (Int) -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = producto.nombre,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "$${"%.2f".format(producto.precio)}"
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Button(
                    onClick = {
                        if (cantidad > 0) {
                            onCantidadChange(cantidad - 1)
                        }
                    },
                    enabled = cantidad > 0
                ) {
                    Text("-")
                }

                Text(
                    text = cantidad.toString(),
                    modifier = Modifier.padding(top = 12.dp)
                )

                Button(
                    onClick = {
                        if (cantidad < 10) {
                            onCantidadChange(cantidad + 1)
                        }
                    },
                    enabled = cantidad < 10
                ) {
                    Text("+")
                }
            }
        }
    }
}

@Composable
fun ResumenCarrito(
    articulos: Int,
    subtotal: Double,
    descuento: Double,
    envio: Double,
    total: Double
) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = "Resumen",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text("Artículos: $articulos")
            Text("Subtotal: $${"%.2f".format(subtotal)}")
            Text("Descuento: $${"%.2f".format(descuento)}")
            Text("Envío: $${"%.2f".format(envio)}")

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Total: $${"%.2f".format(total)}",
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}