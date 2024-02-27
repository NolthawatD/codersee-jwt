package com.example.amado

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AmadoApplication

fun main(args: Array<String>) {
	runApplication<AmadoApplication>(*args)
	val serverPort = System.getenv("SERVER_PORT")
	println("Running on http://locahost:${serverPort}")
}
