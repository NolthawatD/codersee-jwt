package com.example.amado.controller.product

class PaginationResponse(
    val data: List<Any>,
    val total: Int,
    val page: Int,
    val lastPage: Int
) {
}