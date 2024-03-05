package com.example.amado.service

import com.example.amado.controller.movie.MovieDto
import com.example.amado.exception.NotFoundExceptionCustom
import com.example.amado.models.Movie
import com.example.amado.repository.MovieRepository
import org.springframework.stereotype.Service

@Service
class MovieService(
    private val movieRepository: MovieRepository
) {
    fun getMovie(id: String): Movie = movieRepository.findById(id).orElseThrow {
        NotFoundExceptionCustom("Unable to find movie for $id id")
    }

    fun getAllMovies(): List<Movie> = movieRepository.findAll().toList()

    fun updateMovie(id: String, movieDto: MovieDto): Movie {
        val movie: Movie =
            movieRepository.findById(id).orElseThrow { NotFoundExceptionCustom("Unable to find movie for $id id") }
        val updatedMovie =
            movie.copy(name = movieDto.name.orEmpty(), genre = movieDto.genre.orEmpty(), year = movieDto.year)
        updatedMovie.id = movie.id
        return movieRepository.save(updatedMovie)
    }

    fun updateMovie(movie: Movie): Movie = movieRepository.save(movie)

    fun createMovie(movieDto: MovieDto): Movie {
        return movieRepository.save(
            Movie(
                name = movieDto.name.orEmpty(),
                genre = movieDto.genre.orEmpty(),
                year = movieDto.year
            )
        )
    }

    fun deleteMovie(id: String) = movieRepository.delete(getMovie(id))

    fun getByName(name: String) = movieRepository.findByName(name)

    fun getByGenre(genre: String) = movieRepository.findByGenre(genre)

    fun getByNameAndGenre(name: String, genre: String) = movieRepository.findByNameAndGenre(name, genre)

}