package com.example.amado.controller.product

import com.example.amado.models.Product
import com.example.amado.repository.ProductRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/product")
class ProductController(
    private  val productRepository: ProductRepository
) {

    @GetMapping("frontend")
    fun frontend(): ResponseEntity<List<Product>> {
        return ResponseEntity.ok(this.productRepository.findAll())
    }
}