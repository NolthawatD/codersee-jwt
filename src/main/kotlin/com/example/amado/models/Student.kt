package com.example.amado.models

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(
    name = "students",
)
class Student {
    @Id
    @GeneratedValue(generator = "student_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
    val id: Long = 0

    @Column(name = "name", nullable = false, unique = true)
    var name: String = ""

    @JsonIgnore
    @ManyToMany(mappedBy = "students")
    val courses: MutableSet<Course> = mutableSetOf()
}

@Entity
@Table(
    name = "courses",
)
class Course {
    @Id
    @GeneratedValue(generator = "course_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "course_sequence", sequenceName = "course_sequence", allocationSize = 1)
    val id: Long = 0

    @Column(name = "name", nullable = false, unique = true)
    var name: String = ""

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "student_course",
        joinColumns = [JoinColumn(name = "course_id")],
        inverseJoinColumns = [JoinColumn(name = "student_id")]
    )
    val students: MutableSet<Student> = mutableSetOf()
}

