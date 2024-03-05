package com.example.amado.repository

import com.example.amado.models.Movie
import org.springframework.data.repository.CrudRepository

interface MovieRepository : CrudRepository<Movie, String> {
    fun findByName(name: String): Movie?

    fun findByGenre(genre: String): Movie?

    fun findByNameAndGenre(name: String, genre: String): Movie?
}