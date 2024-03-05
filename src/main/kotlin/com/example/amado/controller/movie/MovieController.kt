package com.example.amado.controller.movie

import com.example.amado.models.Movie
import com.example.amado.service.MovieService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/movies")
class MovieController(
    private val movieService: MovieService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    private fun createMovie(@Valid @RequestBody movie: MovieDto): Movie = movieService.createMovie(movie)

    @GetMapping(value = ["/{id}"])
    @ResponseStatus(HttpStatus.OK)
    private fun getMovieById(@PathVariable id: String): Movie = movieService.getMovie(id)

    @GetMapping(value = ["name/{name}"])
    @ResponseStatus(HttpStatus.OK)
    private fun getMovieByName(@PathVariable name: String): Movie? = movieService.getByName(name)

    @GetMapping(value = ["genre/{genre}"])
    @ResponseStatus(HttpStatus.OK)
    private fun getMovieByGenre(@PathVariable genre: String): Movie? {
        println("=== genre : $genre")
        return movieService.getByGenre(genre)
    }

    @GetMapping(value = ["name/{name}/genre/{genre}"])
    @ResponseStatus(HttpStatus.OK)
    private fun getMovieByNameAndGenre(@PathVariable name: String, @PathVariable genre: String): Movie? {
        println("=== name : $name")
        println("=== genre : $genre")
        return  movieService.getByNameAndGenre(name, genre)
    }


    @PutMapping(value = ["/{id}"])
    @ResponseStatus(HttpStatus.OK)
    private fun updateMovie(@PathVariable id: String, @Valid @RequestBody movie: MovieDto): Movie =
        movieService.updateMovie(id, movie)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private fun getMovies(): List<Movie> = movieService.getAllMovies()

    @DeleteMapping(value = ["/{id}"])
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private fun deleteMovie(id: String) = movieService.deleteMovie(id)
}