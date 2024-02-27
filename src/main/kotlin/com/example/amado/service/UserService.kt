package com.example.amado.service

import com.example.amado.controller.user.UserCreateRequest
import com.example.amado.controller.user.UserDto
import com.example.amado.data.User
import com.example.amado.exception.BadRequestException
import com.example.amado.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(
    private val userRepository: UserRepository,
    private val encoder: PasswordEncoder
) {

    private fun mappingUserEntityToDto(user: User) = UserDto(
        user.id,
        user.email,
        user.password,
        user.role,
        user.createdAt,
    )

    private fun mappingUserFromRequestToEntity(user: User, request: UserCreateRequest) {
        user.email = request.email
        user.password = request.password
        user.role = request.role
    }

    fun createUser(request: UserCreateRequest): UserDto {
        println("=== check does email exist")
        if (userRepository.doesEmailExist(request.email)) {
            println("=== found email exist")
            throw BadRequestException("There is already a user with the email: ${request.email}")
        }

        val user = User()

        val updated = request.copy(password = encoder.encode(request.password))
        mappingUserFromRequestToEntity(user, updated)

        val saveUser: User = userRepository.save(user)
        return mappingUserEntityToDto(saveUser)

    }
}