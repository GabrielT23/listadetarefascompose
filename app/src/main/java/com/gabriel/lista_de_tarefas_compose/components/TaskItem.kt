package com.gabriel.lista_de_tarefas_compose.components

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.gabriel.lista_de_tarefas_compose.R
import com.gabriel.lista_de_tarefas_compose.model.Task
import com.gabriel.lista_de_tarefas_compose.repository.TaskRepository
import com.gabriel.lista_de_tarefas_compose.ui.theme.Green200
import com.gabriel.lista_de_tarefas_compose.ui.theme.Red100
import com.gabriel.lista_de_tarefas_compose.ui.theme.ShapeCardPriority
import com.gabriel.lista_de_tarefas_compose.ui.theme.White
import com.gabriel.lista_de_tarefas_compose.ui.theme.Yellow200
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun TaskItem(
    position: Int,
    taskList: MutableList<Task>,
    context: Context,
    navController: NavController
) {

    val taskTitle = taskList[position].task
    val taskDescription = taskList[position].description
    val taskpriority = taskList[position].priority
    val scope = rememberCoroutineScope()
    val taskRepository = TaskRepository()

    fun dialogDelete(){
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Deletar Tarefa")
            .setMessage("Deseja Exluir a tarefa?")
            .setPositiveButton("Sim"){_, _ ->
                taskRepository.deleteTask(taskTitle.toString())
                scope.launch(Dispatchers.Main){
                    taskList.removeAt(position)
                }
                navController.navigate("listTasks")
                Toast.makeText(context, "Sucesso ao Deletar a tarefa", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Não"){_, _ ->

            }.show()
    }

    val priorityLevel: String = when(taskpriority){
        0 -> "Baixa"
        1 -> "Média"
        2 -> "Alta"
        else -> {
            "Baixa"
        }
    }

    val priorityCollor: Color = when(taskpriority){
        0 -> Green200
        1 -> Yellow200
        2 -> Red100
        else -> {
            Green200
        }
    }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = White
        ),
        modifier = Modifier.fillMaxWidth().padding(10.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier.padding(20.dp)
        ) {
            val(txtTitle, txtDescription, cardPriority, txtPriority, btDelete, btEdit) = createRefs()

            Text(
                taskTitle.toString(),
                modifier = Modifier.constrainAs(txtTitle){
                    top.linkTo(parent.top, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
            }
            )
            Text(
                taskDescription.toString(),
                modifier = Modifier.constrainAs(txtDescription){
                    top.linkTo(txtTitle.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )
            Text(
                "Nivel de Prioridade: $priorityLevel",
                modifier = Modifier.constrainAs(txtPriority){
                    top.linkTo(txtDescription.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                }
            )
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = priorityCollor

                ),
                modifier = Modifier.size(30.dp).constrainAs(cardPriority){
                    top.linkTo(txtDescription.bottom, margin = 10.dp)
                    start.linkTo(txtPriority.end, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                },
                shape = ShapeCardPriority.large
            )
            {}
            IconButton(
                onClick = {
                    dialogDelete()
                },
                modifier = Modifier.constrainAs(btDelete){
                    top.linkTo(txtDescription.bottom, margin = 10.dp)
                    start.linkTo(cardPriority.end, margin = 30.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                    end.linkTo(parent.end, margin = 10.dp)
                },
            ) {
                Image(imageVector = ImageVector.vectorResource(id = R.drawable.baseline_delete_24), contentDescription = null)
            }
            IconButton(onClick = { navController.navigate("editTask/${taskTitle}") },
                modifier = Modifier.constrainAs(btEdit){
                    top.linkTo(txtDescription.bottom, margin = 10.dp)
                    start.linkTo(btDelete.end, margin = 30.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                    end.linkTo(parent.end, margin = 10.dp)
                },) {
                Icon(Icons.Default.Edit, contentDescription = "Editar Tarefa")
            }
        }
    }
}
