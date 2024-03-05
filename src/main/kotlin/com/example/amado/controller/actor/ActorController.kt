package com.example.amado.controller.actor

import com.example.amado.models.Actor
import com.example.amado.models.Movie
import com.example.amado.service.ActorService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/actors")
class ActorController (
    private  val actorService: ActorService
){
    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    private fun createActor(@Valid @RequestBody actor: ActorDto): Actor = actorService.createActor(actor)

    @GetMapping(value = ["/{id}"])
    @ResponseStatus(HttpStatus.OK)
    private fun getActorById(@PathVariable id: String): Actor = actorService.getActor(id)

    @PutMapping(value = ["/{id}"])
    @ResponseStatus(HttpStatus.OK)
    private fun updateActor(@PathVariable id: String, @Valid @RequestBody actor: ActorDto): Actor = actorService.updateActor(id, actor)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private fun getActors(): List<Actor> = actorService.getAllActors()

    @DeleteMapping(value = ["/{id}"])
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private fun deleteActor(id: String) = actorService.deleteActor(id)

    @PatchMapping(value = ["/{actorId}/link/{movieId}"])
    @ResponseStatus(HttpStatus.OK)
    private fun addActorToMovie(@PathVariable actorId: String, @PathVariable movieId: String): Movie {
        return actorService.addActorToMovie(actorId, movieId)
    }

}