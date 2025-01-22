package com.gabriel.lista_de_tarefas_compose.repository

import com.gabriel.lista_de_tarefas_compose.datasource.DataSource
import com.gabriel.lista_de_tarefas_compose.model.Task
import kotlinx.coroutines.flow.Flow

class TaskRepository {
    private val dataSource = DataSource()

    fun saveTask(task: String, description: String, priority: Int) {
        dataSource.saveTask(task, description, priority)
    }

    fun getTasks(): Flow<MutableList<Task>> {
        return dataSource.getTasks()
    }

    fun deleteTask(task: String) {
        dataSource.deleteTask(task)
    }

    fun getTaskById(
        taskId: String,
        onSuccess: (Task?) -> Unit,
        onError: (Exception) -> Unit
    ) {
        dataSource.getTaskById(taskId, onSuccess, onError)
    }

    fun updateTask(taskId: String, title: String, description: String, priority: Int) {
        dataSource.updateTask(taskId, title, description, priority)
    }
}
