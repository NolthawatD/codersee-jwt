package com.example.amado.models

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

@Entity
@Table(
    name = "users",
    uniqueConstraints = [UniqueConstraint(name = "uk_user_email", columnNames = ["email"])]
)
class User {
    @Id
    @GeneratedValue(generator = "user_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    val id: Long = 0

    @NotBlank
    @Column(name = "email", nullable = false, unique = true)
    var email: String = ""

    @NotBlank
    @Column(name = "password", nullable = false, unique = true)
    var password: String = ""

    @NotNull
    @Enumerated(EnumType.STRING)
    var role: Role = Role.USER

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "deleted_at", nullable = true)
    val deletedAt: LocalDateTime? = null

}