package com.example.amado.repository

import com.example.amado.models.Actor
import org.springframework.data.repository.CrudRepository

interface ActorRepository: CrudRepository<Actor, String>