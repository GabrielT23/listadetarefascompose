package com.gabriel.lista_de_tarefas_compose.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.gabriel.lista_de_tarefas_compose.ui.theme.Blue700
import com.gabriel.lista_de_tarefas_compose.ui.theme.White

@Composable
fun ButtonSubmit(
    onClick: () -> Unit,
    modifier: Modifier,
    text: String
){
    Button(
        onClick,
        modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Blue700,
            contentColor = White
        )
    ) {
        Text(text = text, fontWeight = FontWeight.Bold, fontSize = 18.sp)
    }
}