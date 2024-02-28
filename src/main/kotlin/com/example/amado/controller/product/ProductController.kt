package com.example.amado.controller.product

import com.example.amado.models.Product
import com.example.amado.repository.ProductRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/product")
class ProductController(
    private val productRepository: ProductRepository
) {

    @GetMapping("frontend")
    fun frontend(): ResponseEntity<List<Product>> {
        return ResponseEntity.ok(this.productRepository.findAll())
    }

    @GetMapping("backend")
    fun backend(
        @RequestParam("s", defaultValue = "") s: String,
        @RequestParam("sort", defaultValue = "") sort: String,
        @RequestParam("page", defaultValue = "1") page: Int,
    ): ResponseEntity<Any> {
        var direction = Sort.unsorted()

        if (sort == "asc") {
            direction = Sort.by(Sort.Direction.ASC, "price")
        } else if (sort == "desc") {
            direction = Sort.by(Sort.Direction.DESC, "price")
        }

        val perPage = 9
        val total = this.productRepository.countSearch(s)

        return ResponseEntity.ok(
            PaginationResponse(
                data = this.productRepository.search(s, PageRequest.of(page - 1, perPage, direction)),
                total,
                page,
                lastPage = (total / perPage) + 1
            )
        )
    }
}