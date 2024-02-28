package com.example.amado.repository

import com.example.amado.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun findUserById(id: Long): User

    fun findByEmail(email: String): User?

    @Query(value = "SELECT * FROM users", nativeQuery = true)
    fun queryAlUsers(): List<User>

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM User u WHERE u.email = ?1")
    fun doesEmailExist(email: String): Boolean

}

