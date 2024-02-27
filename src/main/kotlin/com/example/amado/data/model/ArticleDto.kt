package com.example.amado.data.model

import java.util.UUID

data class ArticleDto(
    val id : UUID,
    val title : String,
    val content : String,
)