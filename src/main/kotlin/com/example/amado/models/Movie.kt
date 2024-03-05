package com.example.amado.models

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Reference
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed


@RedisHash("Movies")
data class Movie(
    @Indexed val name: String,
    @Indexed val genre: String,
    val year: Int
){
    @get:Id
    var id: String? = null
    @Indexed @get:Reference
    var actors: List<Actor> = listOf()
}
