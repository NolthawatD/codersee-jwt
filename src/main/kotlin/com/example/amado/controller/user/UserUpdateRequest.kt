package com.example.amado.controller.user

import com.example.amado.models.Role

data class UserUpdateRequest(
    val id: Long?,
    val email: String?,
    val password: String?,
    val role: Role?,
)
