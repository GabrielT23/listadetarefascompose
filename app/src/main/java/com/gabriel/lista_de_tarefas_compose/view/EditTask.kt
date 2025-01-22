package com.gabriel.lista_de_tarefas_compose.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gabriel.lista_de_tarefas_compose.components.TextInput
import com.gabriel.lista_de_tarefas_compose.repository.TaskRepository
import com.gabriel.lista_de_tarefas_compose.ui.theme.Blue700
import com.gabriel.lista_de_tarefas_compose.ui.theme.Green100
import com.gabriel.lista_de_tarefas_compose.ui.theme.Green200
import com.gabriel.lista_de_tarefas_compose.ui.theme.Purple40
import com.gabriel.lista_de_tarefas_compose.ui.theme.Red100
import com.gabriel.lista_de_tarefas_compose.ui.theme.White
import com.gabriel.lista_de_tarefas_compose.ui.theme.Yellow100
import com.gabriel.lista_de_tarefas_compose.ui.theme.Yellow200
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTask(navController: NavController, taskId: String?) {
    val context = LocalContext.current
    val taskRepository = TaskRepository()
    val scope = rememberCoroutineScope()

    var taskTitle by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf(0) }

    // Recuperar dados da tarefa
    LaunchedEffect(taskId) {
        taskId?.let {
            taskRepository.getTaskById(
                taskId = it,
                onSuccess = { task ->
                    task?.let {
                        taskTitle = task.task ?: ""
                        taskDescription = task.description ?: ""
                        priority = task.priority ?: 0
                    }
                },
                onError = { exception ->
                    Toast.makeText(context, "Erro ao carregar tarefa: ${exception.message}", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar Tarefa", color = White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Purple40)
            )
        },
        containerColor = White,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(0.dp, 100.dp, 0.dp, 0.dp)
        ) {
            TextInput(
                value = taskTitle,
                onValueChange = { taskTitle = it },
                label = "Título da Tarefa",
                keyboardType = KeyboardType.Text,
                modifier = Modifier.fillMaxWidth(), // Adicionando o modifier
                maxLines = 1 // Definindo o limite de linhas
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextInput(
                value = taskDescription,
                onValueChange = { taskDescription = it },
                label = "Descrição da Tarefa",
                keyboardType = KeyboardType.Text,
                modifier = Modifier.fillMaxWidth(), // Adicionando o modifier
                maxLines = 1 // Definindo o limite de linhas
            )
            Spacer(modifier = Modifier.height(16.dp))
            PrioritySelector(priority) { newPriority -> priority = newPriority }
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = {
                if (taskTitle.isNotEmpty() && taskDescription.isNotEmpty()) {
                    scope.launch(Dispatchers.IO) {
                        taskRepository.updateTask(taskId!!, taskTitle, taskDescription, priority)
                        scope.launch(Dispatchers.Main) {
                            Toast.makeText(context, "Tarefa atualizada com sucesso!", Toast.LENGTH_SHORT).show()
                            navController.navigate("listTasks")
                        }
                    }
                } else {
                    Toast.makeText(context, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                }
            },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue700,
                    contentColor = White
                )
                ) {
                Text("Salvar Alterações")
            }
        }
    }
}

@Composable
fun PrioritySelector(priority: Int, onPrioritySelected: (Int) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        RadioButton(
            selected = priority == 2,
            onClick = { onPrioritySelected(0) },
            colors = RadioButtonDefaults.colors(
                selectedColor = Green200,
                unselectedColor = Green100
            )
        )
        RadioButton(
            selected = priority == 1,
            onClick = { onPrioritySelected(1) },
            colors = RadioButtonDefaults.colors(
                selectedColor = Yellow200,
                unselectedColor = Yellow100
            )
        )
        RadioButton(
            selected = priority == 0,
            onClick = { onPrioritySelected(2) },
            colors = RadioButtonDefaults.colors(
                selectedColor = Red100,
                unselectedColor = Red100
            )
        )
    }
}

