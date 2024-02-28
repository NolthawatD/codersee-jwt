package com.example.amado.controller.task

import com.example.amado.models.Priority
import java.time.LocalDateTime

data class TaskDto(
    val id: Long,
    val description: String,
    val isReminderSet: Boolean,
    val isTaskOpen: Boolean,
    val priority: Priority,
    val createdAt: LocalDateTime,
)
