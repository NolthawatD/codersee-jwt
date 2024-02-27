package com.example.amado.data.model

import com.example.amado.data.Priority
import java.time.LocalDateTime

data class TaskDto(
    val id: Long,
    val description: String,
    val isReminderSet: Boolean,
    val isTaskOpen: Boolean,
    val priority: Priority,
    val createdAt: LocalDateTime,
)
