package com.example.amado.controller.auth

data class AuthenticationResponse (
    val accessToken: String,
    val refreshToken: String,
)
