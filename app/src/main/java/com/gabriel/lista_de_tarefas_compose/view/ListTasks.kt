package com.gabriel.lista_de_tarefas_compose.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gabriel.lista_de_tarefas_compose.R
import com.gabriel.lista_de_tarefas_compose.ui.theme.Black
import com.gabriel.lista_de_tarefas_compose.ui.theme.Purple40
import com.gabriel.lista_de_tarefas_compose.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListTasks(
    navController: NavController
) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(
                    "Lista de Tarefas",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = White) },
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
                    contentDescription = "Icone Salvar Tarefa")
            }
        }
    ){  }
}