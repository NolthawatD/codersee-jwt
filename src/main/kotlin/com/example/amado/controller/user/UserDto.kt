package com.example.amado.controller.user

import com.example.amado.data.Role
import java.time.LocalDateTime
import java.util.*

data class UserDto(
    val id : Long,
    val email : String,
    val password : String,
    val role : Role,
    val createdAt : LocalDateTime,
)
