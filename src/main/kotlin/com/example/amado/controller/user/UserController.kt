package com.example.amado.controller.user

import com.example.amado.controller.task.TaskCreateRequest
import com.example.amado.controller.task.TaskDto
import com.example.amado.data.Role
import com.example.amado.data.User
import com.example.amado.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.util.*

@RestController
@RequestMapping("api/v1/user")
class UserController(
    private val userService: UserService
) {

    @PostMapping("create")
    fun createUser(
        @Valid @RequestBody request: UserCreateRequest
    ): ResponseEntity<UserDto> = ResponseEntity(userService.createUser(request), HttpStatus.OK)

    @GetMapping("all")
    fun getAllUsers(): ResponseEntity<List<UserDto>> =
        ResponseEntity(userService.getAllUsers(), HttpStatus.OK)


//    private fun User.toResponse(): UserResponse =
//        UserResponse(
//            uuid = this.id,
//            email = this.email,
//        )
//
//    @GetMapping("user")
//    fun listAll(): List<UserResponse> =
//        userService.findAll()
//            .map { it.toResponse() }
//
//    @GetMapping("user/{uuid}")
//    fun findByUUID(@PathVariable uuid: UUID): UserResponse =
//        userService.findByUUID(uuid)
//            ?.toResponse()
//            ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find a user.")
//
//
//    @PostMapping("user")
//    fun create(@RequestBody userRequest: UserCreateRequest): UserResponse =
//        userService.createUser(
//            user = userRequest.toModel()
//        )
//            ?.toResponse()
//            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot create a user.")
//
//    @DeleteMapping("user/{uuid}")
//    fun deleteByUUID(@PathVariable uuid: UUID): ResponseEntity<Boolean> {
//        val success = userService.deleteByUUID(uuid)
//
//        return if (success)
//            ResponseEntity.noContent()
//                .build()
//        else
//            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find a user.")
//    }
}