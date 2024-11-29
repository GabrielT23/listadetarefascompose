package com.gabriel.lista_de_tarefas_compose.model

data class Task (
    val task: String? = null,
    val description: String? = null,
    val priority: Int? = null
)