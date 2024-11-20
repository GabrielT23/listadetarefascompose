package com.gabriel.lista_de_tarefas_compose.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.gabriel.lista_de_tarefas_compose.ui.theme.Black
import com.gabriel.lista_de_tarefas_compose.ui.theme.Blue700
import com.gabriel.lista_de_tarefas_compose.ui.theme.ShapeEditText
import com.gabriel.lista_de_tarefas_compose.ui.theme.White

@Composable
fun TextInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    label: String,
    maxLines: Int,
    keyboardType: KeyboardType
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = {
            Text(text = label)
        },
        maxLines = maxLines,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Blue700,
            focusedLabelColor = Blue700,
            unfocusedTextColor = Black,
            unfocusedContainerColor = White,
            cursorColor = Blue700
        ),
        shape = ShapeEditText.small,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
    )
}