package com.gabriel.lista_de_tarefas_compose.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gabriel.lista_de_tarefas_compose.R
import com.gabriel.lista_de_tarefas_compose.components.ButtonSubmit
import com.gabriel.lista_de_tarefas_compose.components.TextInput
import com.gabriel.lista_de_tarefas_compose.ui.theme.Green100
import com.gabriel.lista_de_tarefas_compose.ui.theme.Green200
import com.gabriel.lista_de_tarefas_compose.ui.theme.Purple40
import com.gabriel.lista_de_tarefas_compose.ui.theme.Red100
import com.gabriel.lista_de_tarefas_compose.ui.theme.White
import com.gabriel.lista_de_tarefas_compose.ui.theme.Yellow100
import com.gabriel.lista_de_tarefas_compose.ui.theme.Yellow200

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaveTasks(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Salvar Tarefa",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Purple40
                )
            )
        },
        containerColor = White,
    ) {
        var taskTitle by remember { mutableStateOf("") }
        var taskDescription by remember { mutableStateOf("") }
        var noPriority by remember { mutableStateOf(false) }
        var lowPriority by remember { mutableStateOf(false) }
        var highPriority by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(0.dp, 80.dp, 0.dp, 0.dp)
        ) {
            TextInput(
                value = taskTitle,
                onValueChange = {
                    taskTitle = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 20.dp, 20.dp, 0.dp), // Certifique-se de usar o "modifier =" explicitamente
                label = "Título da Tarefa",
                maxLines = 1,
                keyboardType = KeyboardType.Text
            )
            TextInput(
                value = taskDescription,
                onValueChange = {
                    taskTitle = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(20.dp, 20.dp, 20.dp, 0.dp), // Certifique-se de usar o "modifier =" explicitamente
                label = "Descrição da Tarefa",
                maxLines = 5,
                keyboardType = KeyboardType.Text
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Nivel de prioridade")
                RadioButton(
                    selected = noPriority,
                    onClick = {
                        noPriority = !noPriority
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Green200,
                        unselectedColor = Green100
                    )
                )
                RadioButton(
                    selected = lowPriority,
                    onClick = {
                        lowPriority = !lowPriority
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Yellow200,
                        unselectedColor = Yellow100
                    )
                )
                RadioButton(
                    selected = highPriority,
                    onClick = {
                        highPriority = !highPriority
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Red100,
                        unselectedColor = Red100
                    )
                )
            }
            ButtonSubmit(
                text = "Salvar",
                modifier = Modifier.fillMaxWidth().height(80.dp).padding(20.dp),
                onClick = {})
        }
    }
}
