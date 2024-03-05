package com.example.amado.controller.actor

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Past
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

data class ActorDto(
    @get:NotBlank(message = "First name cannot be empty") val firstName: String,
    @get:NotBlank(message = "Last name cannot be empty") val lastName: String,
    @field:DateTimeFormat(pattern = "yyyy-MM-dd")
    @field:JsonDeserialize(using = LocalDateDeserializer::class)
    @field:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @get:Past(message = "Provide date in yyyy-MM-dd format in past time") val birthDate: LocalDate
)
