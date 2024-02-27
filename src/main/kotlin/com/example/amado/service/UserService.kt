package com.example.amado.service

import com.example.amado.controller.user.UserCreateRequest
import com.example.amado.controller.user.UserDto
import com.example.amado.controller.user.UserUpdateRequest
import com.example.amado.data.User
import com.example.amado.exception.BadRequestException
import com.example.amado.exception.NotFoundException
import com.example.amado.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.util.ReflectionUtils
import java.lang.reflect.Field
import java.util.stream.Collectors
import kotlin.reflect.full.memberProperties

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

    private fun checkUserForId(id: Long) {
        if (!userRepository.existsById(id)) {
            throw NotFoundException("User with the ID: $id not found!")
        }
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

    fun getAllUsers(): List<UserDto> =
        userRepository.findAll().stream().map(this::mappingUserEntityToDto).collect(Collectors.toList())

    fun updateUser(id: Long, request: UserUpdateRequest): UserDto {
        checkUserForId(id)

        val existingUser: User = userRepository.findUserById(id)

        val updated = request.copy(password = encoder.encode(request.password))

        for (prop in UserUpdateRequest::class.memberProperties) {
            if (prop.get(updated) != null) {
                val field: Field? = ReflectionUtils.findField(User::class.java, prop.name)
                field?.let {
                    it.isAccessible = true
                    ReflectionUtils.setField(it, existingUser, prop.get(updated))
                }
            }
        }

        val savedUser: User = userRepository.save(existingUser)
        return mappingUserEntityToDto(savedUser)
    }

    fun deleteUser(id: Long): String {
        checkUserForId(id)
        userRepository.deleteById(id)
        return "User with the ID: $id has been deleted."
    }

    fun getCurrentUser(): UserDetails? {
        val authentication = SecurityContextHolder.getContext().authentication
        return if (authentication != null && authentication.isAuthenticated) {
            authentication.principal as UserDetails
        } else {
            null
        }
    }


}