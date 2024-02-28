package com.example.amado.repository

import com.example.amado.models.Product
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ProductRepository : JpaRepository<Product, Int> {

    @Query("select p from Product p where p.title like %?1% or p.description like %?1%")
    fun search(s: String, pageable: Pageable): List<Product>

    @Query("select COUNT(p) from Product p where p.title like %?1% or p.description like %?1%", countQuery = "*")
    fun countSearch(s: String): Int

}