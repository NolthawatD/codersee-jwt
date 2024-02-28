package com.example.amado.controller.user

import com.example.amado.models.Role
import java.time.LocalDateTime

data class UserDto(
    val id : Long,
    val email : String,
    val password : String,
    val role : Role,
    val createdAt : LocalDateTime,
)
