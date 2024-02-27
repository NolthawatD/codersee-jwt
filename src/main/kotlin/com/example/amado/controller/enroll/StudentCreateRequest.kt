package com.example.amado.controller.enroll

import com.example.amado.data.Priority
import jakarta.validation.constraints.NotBlank
import java.time.LocalDateTime

data class StudentCreateRequest(
    val name: String,
)
