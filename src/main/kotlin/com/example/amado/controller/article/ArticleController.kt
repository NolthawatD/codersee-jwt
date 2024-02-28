package com.example.amado.controller.article

import com.example.amado.models.Article
import com.example.amado.service.ArticleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1")
class ArticleController(
    private val articleService: ArticleService
) {

    private fun Article.toResponse(): ArticleDto =
        ArticleDto(
            id = this.id,
            title = this.title,
            content = this.content,
        )

    @GetMapping("all-articles")
    fun listAll(): List<ArticleDto> = articleService.findAll().map { it.toResponse() }

}