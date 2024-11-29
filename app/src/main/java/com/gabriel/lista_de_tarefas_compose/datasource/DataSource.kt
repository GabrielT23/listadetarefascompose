package com.gabriel.lista_de_tarefas_compose.datasource

import android.util.Log
import com.gabriel.lista_de_tarefas_compose.model.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DataSource {

    private val db = FirebaseFirestore.getInstance()
    private val _allTasks = MutableStateFlow<MutableList<Task>>(mutableListOf())
    private val allTasks: StateFlow<MutableList<Task>> = _allTasks

    fun saveTask(task: String, description: String, priority: Int){
        val taskMap = hashMapOf(
            "task" to task,
            "description" to description,
            "priority" to priority
        )
        println("fkfjnfj")
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

    fun getTasks(): Flow<MutableList<Task>>{
        val taskList: MutableList<Task> = mutableListOf()
        db.collection("tasks").get().addOnCompleteListener{ querySnapshot ->
            if(querySnapshot.isSuccessful){
                for (document in querySnapshot.result){
                    val task = document.toObject(Task::class.java)
                    taskList.add(task)
                    _allTasks.value = taskList
                }
            }
        }
        return allTasks
    }
    fun deleteTask(task: String){
        db.collection("tasks").document(task).delete().addOnCompleteListener{

        }.addOnFailureListener{

        }
    }
}