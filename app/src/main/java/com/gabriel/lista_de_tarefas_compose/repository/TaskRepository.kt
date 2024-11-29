package com.gabriel.lista_de_tarefas_compose.repository

import com.gabriel.lista_de_tarefas_compose.datasource.DataSource
import com.gabriel.lista_de_tarefas_compose.model.Task
import kotlinx.coroutines.flow.Flow

class TaskRepository() {
    private val dataSource = DataSource()
    fun saveTask(task: String, description: String, priority: Int){
        dataSource.saveTask(task, description, priority)
    }

    fun getTasks(): Flow<MutableList<Task>>{
        return dataSource.getTasks()
    }
    fun deleteTask(task: String){
        dataSource.deleteTask(task)
    }
}