package com.example.amado.controller.task

import com.example.amado.models.Priority

data class TaskUpdateRequest(
    val id: Long?,
    val description: String?,
    val isReminderSet: Boolean?,
    val isTaskOpen: Boolean?,
    val priority: Priority?
)
