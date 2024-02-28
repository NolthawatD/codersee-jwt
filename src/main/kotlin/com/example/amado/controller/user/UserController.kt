package com.example.amado.controller.user

import com.example.amado.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/user")
class UserController(
    private val userService: UserService
) {

    @PostMapping("create")
    fun createUser(
        @Valid @RequestBody request: UserCreateRequest
    ): ResponseEntity<UserDto> = ResponseEntity(userService.createUser(request), HttpStatus.OK)

    @PatchMapping("update/{id}")
    fun updateUser(
        @PathVariable id: Long,
        @Valid @RequestBody request: UserUpdateRequest
    ): ResponseEntity<UserDto> = ResponseEntity(userService.updateUser(id, request), HttpStatus.OK)

    @DeleteMapping("delete/{id}")
    fun deleteTask(@PathVariable id: Long): ResponseEntity<String> =
        ResponseEntity(userService.deleteUser(id), HttpStatus.OK)

    @GetMapping("all")
    fun getAllUsers(): ResponseEntity<List<UserDto>> =
        ResponseEntity(userService.getAllUsers(), HttpStatus.OK)

    @GetMapping("me")
    fun getCurrentUser(): ResponseEntity<UserDetails> =
        ResponseEntity(userService.getCurrentUser(), HttpStatus.OK)


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