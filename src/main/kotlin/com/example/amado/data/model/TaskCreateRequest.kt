package com.example.amado.data.model

import com.example.amado.data.Priority
import jakarta.validation.constraints.NotBlank
import java.time.LocalDateTime

data class TaskCreateRequest(
    @NotBlank(message = "Task description can't be empty")
    val id: Long,

    val description: String,

    val isReminderSet: Boolean,

    val isTaskOpen: Boolean,

    @NotBlank(message = "Task created_at can't be empty")
    val createdAt: LocalDateTime,

    val priority: Priority
)
