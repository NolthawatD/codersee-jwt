package com.example.amado.controller.movie

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.PastOrPresent

data class MovieDto(
    @get:NotBlank(message = "Movie name cannot be empty") val name: String?,
    @get:NotBlank(message = "Movie genre cannot be empty") val genre: String?,
    @get:Min(message = "Movie year should be 1900 or after", value = 1900) @PastOrPresent val year: Int
)
