package com.gabriel.lista_de_tarefas_compose.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gabriel.lista_de_tarefas_compose.R
import com.gabriel.lista_de_tarefas_compose.components.TaskItem
import com.gabriel.lista_de_tarefas_compose.model.Task
import com.gabriel.lista_de_tarefas_compose.ui.theme.Black
import com.gabriel.lista_de_tarefas_compose.ui.theme.Purple40
import com.gabriel.lista_de_tarefas_compose.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListTasks(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Lista de Tarefas",
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
        containerColor = Black,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("saveTasks")
            }, containerColor = Purple40) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
                    contentDescription = "Icone Salvar Tarefa"
                )
            }
        }
    ) {
        val tasksList: MutableList<Task> = mutableListOf(
            Task(
                task = "Jogar futebol",
                description = "Partida com os amigos no campo",
                priority = 0
            ),
            Task(
                task = "Andar de bicicleta",
                description = "Passeio matinal no parque",
                priority = 1
            ),
            Task(
                task = "Almoçar",
                description = "Comida caseira deliciosa",
                priority = 2
            )
        )

        LazyColumn (
            modifier = Modifier.padding(0.dp, 80.dp, 0.dp, 0.dp)
        ){
            itemsIndexed(tasksList) { index, _ ->
                TaskItem(index, tasksList)
            }
        }
    }
}