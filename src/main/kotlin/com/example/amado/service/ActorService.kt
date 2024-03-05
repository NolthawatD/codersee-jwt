package com.example.amado.service

import com.example.amado.controller.actor.ActorDto
import com.example.amado.exception.NotFoundExceptionCustom
import com.example.amado.models.Actor
import com.example.amado.models.Movie
import com.example.amado.repository.ActorRepository
import org.springframework.stereotype.Service

@Service
class ActorService(
   private val actorRepository: ActorRepository,
   private val movieService: MovieService
) {
    fun getActor(id: String) = actorRepository.findById(id).orElseThrow {
        NotFoundExceptionCustom("Unable to find actor for $id id")
    }

    fun getAllActors(): List<Actor> = actorRepository.findAll().toList()

    fun updateActor(id: String, actorDto: ActorDto): Actor {
        val actor = getActor(id).copy(actorDto.firstName, actorDto.lastName, actorDto.birthDate)
        actor.id = id
        return actorRepository.save(actor)
    }

    fun createActor(actorDto: ActorDto): Actor {
        return actorRepository.save(Actor(actorDto.firstName, actorDto.lastName, actorDto.birthDate))
    }

    fun deleteActor(id: String) = actorRepository.deleteById(id)

    fun addActorToMovie(actorId: String, movieId: String): Movie{
        val movie: Movie = movieService.getMovie(movieId)
        val actor: Actor = getActor(actorId)
        (movie.actors as ArrayList).add(actor)
        return movieService.updateMovie(movie)
    }
}