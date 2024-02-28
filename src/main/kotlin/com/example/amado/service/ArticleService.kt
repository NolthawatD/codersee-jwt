package com.example.amado.service

import com.example.amado.models.Article
import com.example.amado.repository.ArticleRepository
import org.springframework.stereotype.Service

@Service
class ArticleService(private val articleRepository: ArticleRepository) {
    fun findAll(): List<Article> = articleRepository.findAll()
}