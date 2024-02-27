package com.example.amado.controller.article

import java.util.UUID

data class ArticleDto(
    val id : UUID,
    val title : String,
    val content : String,
)