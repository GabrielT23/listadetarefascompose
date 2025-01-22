package com.gabriel.lista_de_tarefas_compose.datasource

import android.util.Log
import com.gabriel.lista_de_tarefas_compose.model.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DataSource {

    private val db = FirebaseFirestore.getInstance()
    private val _allTasks = MutableStateFlow<MutableList<Task>>(mutableListOf())
    private val allTasks: StateFlow<MutableList<Task>> = _allTasks

    fun saveTask(task: String, description: String, priority: Int) {
        val taskMap = hashMapOf(
            "task" to task,
            "description" to description,
            "priority" to priority
        )
        db.collection("tasks").document(task).set(taskMap)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Firestore", "Tarefa salva com sucesso!")
                } else {
                    Log.e("Firestore", "Erro ao salvar tarefa", task.exception)
                }
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Erro ao salvar tarefa", exception)
            }
    }

    fun getTasks(): Flow<MutableList<Task>> {
        val taskList: MutableList<Task> = mutableListOf()
        db.collection("tasks").get().addOnCompleteListener { querySnapshot ->
            if (querySnapshot.isSuccessful) {
                for (document in querySnapshot.result) {
                    val task = document.toObject<Task>()
                    taskList.add(task)
                }
                _allTasks.value = taskList
            } else {
                Log.e("Firestore", "Erro ao buscar tarefas", querySnapshot.exception)
            }
        }
        return allTasks
    }

    fun deleteTask(taskId: String) {
        db.collection("tasks").document(taskId).delete()
            .addOnCompleteListener {
                Log.d("Firestore", "Tarefa deletada com sucesso!")
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Erro ao deletar tarefa", exception)
            }
    }

    fun getTaskById(taskId: String, onSuccess: (Task?) -> Unit, onError: (Exception) -> Unit) {
        db.collection("tasks").document(taskId).get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val task = documentSnapshot.toObject<Task>()
                    onSuccess(task)
                } else {
                    onSuccess(null)
                }
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Erro ao buscar tarefa", exception)
                onError(exception)
            }
    }

    fun updateTask(taskId: String, title: String, description: String, priority: Int) {
        val updatedTask = hashMapOf(
            "task" to title,
            "description" to description,
            "priority" to priority
        )

        db.collection("tasks").document(taskId).update(updatedTask as Map<String, Any>)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Firestore", "Tarefa atualizada com sucesso!")
                } else {
                    Log.e("Firestore", "Erro ao atualizar tarefa", task.exception)
                }
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Erro ao atualizar tarefa", exception)
            }
    }
}
