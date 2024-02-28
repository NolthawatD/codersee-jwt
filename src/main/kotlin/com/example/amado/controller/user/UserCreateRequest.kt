package com.example.amado.controller.user

import com.example.amado.models.Role
import jakarta.validation.constraints.NotBlank

data class UserCreateRequest(
    @NotBlank(message = "Email can't be empty")
    val email: String,
    val password: String,
    val role: Role,
)
