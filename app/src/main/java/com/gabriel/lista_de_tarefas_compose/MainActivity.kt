package com.gabriel.lista_de_tarefas_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gabriel.lista_de_tarefas_compose.ui.theme.ListadetarefascomposeTheme
import com.gabriel.lista_de_tarefas_compose.view.ListTasks
import com.gabriel.lista_de_tarefas_compose.view.SaveTasks

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListadetarefascomposeTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "listTasks") {
                    composable(
                        route = "listTasks"
                    ){
                        ListTasks(navController)
                    }

                    composable(
                        route = "saveTasks"
                    ){
                        SaveTasks(navController)
                    }
                }
            }
        }
    }
}

