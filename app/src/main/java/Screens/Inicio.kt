package com.example.estado.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import kotlin.math.ceil

@Composable
fun CuentaScreen() {

    var montoTexto by remember { mutableStateOf("") }
    var propinaPct by remember { mutableStateOf(10) }
    var usaOtro by remember { mutableStateOf(false) }
    var personas by remember { mutableStateOf(1) }
    var redondear by remember { mutableStateOf(false) }

    val monto = montoTexto.toDoubleOrNull() ?: 0.0

    val propina = monto * (propinaPct / 100.0)

    val total = monto + propina

    val porPersonaSinRedondear = total / personas

    val porPersona = if (redondear) {
        ceil(porPersonaSinRedondear)
    } else {
        porPersonaSinRedondear
    }

    val sobrante = if (redondear) {
        (porPersona * personas) - total
    } else {
        null
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {

        Text(
            text = "Cuenta dividida"
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = montoTexto,
            onValueChange = {
                montoTexto = it
            },
            label = {
                Text("Monto de la cuenta")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        if (
            montoTexto.isNotEmpty() &&
            montoTexto.toDoubleOrNull() == null
        ) {
            Text(
                text = "Monto no válido. Se tomará como $0.00"
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        SelectorPropina(
            porcentaje = propinaPct,
            usaOtro = usaOtro,
            onPorcentajeChange = {
                propinaPct = it
            },
            onOtroChange = {
                usaOtro = it
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        StepperPersonas(
            personas = personas,
            onChange = {
                personas = it
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text("Redondear hacia arriba por persona")

            Switch(
                checked = redondear,
                onCheckedChange = {
                    redondear = it
                }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        ResultadoCuenta(
            propina = propina,
            total = total,
            porPersona = porPersona,
            sobrante = sobrante
        )
    }
}

@Composable
fun SelectorPropina(
    porcentaje: Int,
    usaOtro: Boolean,
    onPorcentajeChange: (Int) -> Unit,
    onOtroChange: (Boolean) -> Unit
) {

    Column {

        Text("Propina")

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            FilterChip(
                selected = !usaOtro && porcentaje == 10,
                onClick = {
                    onOtroChange(false)
                    onPorcentajeChange(10)
                },
                label = {
                    Text("10%")
                }
            )

            FilterChip(
                selected = !usaOtro && porcentaje == 15,
                onClick = {
                    onOtroChange(false)
                    onPorcentajeChange(15)
                },
                label = {
                    Text("15%")
                }
            )

            FilterChip(
                selected = !usaOtro && porcentaje == 20,
                onClick = {
                    onOtroChange(false)
                    onPorcentajeChange(20)
                },
                label = {
                    Text("20%")
                }
            )

            FilterChip(
                selected = usaOtro,
                onClick = {
                    onOtroChange(true)
                },
                label = {
                    Text("Otro")
                }
            )
        }

        if (usaOtro) {

            Spacer(modifier = Modifier.height(12.dp))

            Text("Propina: $porcentaje%")

            Slider(
                value = porcentaje.toFloat(),
                onValueChange = {
                    onPorcentajeChange(it.toInt())
                },
                valueRange = 0f..30f,
                steps = 29
            )
        }
    }
}

@Composable
fun StepperPersonas(
    personas: Int,
    onChange: (Int) -> Unit
) {

    Column {

        Text("Personas")

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Button(
                onClick = {
                    if (personas > 1) {
                        onChange(personas - 1)
                    }
                },
                enabled = personas > 1
            ) {
                Text("-")
            }

            Text(
                text = personas.toString(),
                modifier = Modifier.padding(top = 12.dp)
            )

            Button(
                onClick = {
                    if (personas < 20) {
                        onChange(personas + 1)
                    }
                },
                enabled = personas < 20
            ) {
                Text("+")
            }
        }
    }
}

@Composable
fun ResultadoCuenta(
    propina: Double,
    total: Double,
    porPersona: Double,
    sobrante: Double?
) {

    Column {

        Text(
            text = "Resultados"
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Propina total: $${"%.2f".format(propina)}"
        )

        Text(
            text = "Total con propina: $${"%.2f".format(total)}"
        )

        Text(
            text = "Por persona: $${"%.2f".format(porPersona)}"
        )

        if (sobrante != null) {

            Text(
                text = "Sobrante por redondeo: $${"%.2f".format(sobrante)}"
            )
        }
    }
}