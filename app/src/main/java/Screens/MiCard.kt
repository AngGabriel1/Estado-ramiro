
package com.example.estado.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp

@Composable
fun MiCard(
    modifier: Modifier = Modifier,
    valor: String,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier
            .aspectRatio(1f)
            .border(
                width = 2.dp,
                color = Color.Blue,
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable {
                onClick()
            },

        contentAlignment = Alignment.Center
    ) {

        Text(
            text = valor,
            fontSize = 40.sp,
            color = if (valor == "X") Color.Blue else Color.Red
        )
    }
}
