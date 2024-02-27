package com.example.amado.data.model

import com.example.amado.data.Priority

data class TaskUpdateRequest(
    val id: Long?,
    val description: String?,
    val isReminderSet: Boolean?,
    val isTaskOpen: Boolean?,
    val priority: Priority?
)
