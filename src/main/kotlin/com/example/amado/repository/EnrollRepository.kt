package com.example.amado.repository

import com.example.amado.models.Course
import com.example.amado.models.Student
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Service

interface StudentRepository : JpaRepository<Student, Long> {
    fun findStudentById(id: Long): Student
}

interface CourseRepository : JpaRepository<Course, Long> {
    fun findCourseById(id: Long): Course

    // Custom query method with JPQL
    @Query("SELECT c FROM Course c WHERE c.id = :courseId")
    fun findCourseByIdJPQL(courseId: Long): Course
}

// Example usage
@Service
class EnrollmentService(
    private val studentRepository: StudentRepository,
    private val courseRepository: CourseRepository
) {
    fun enrollStudentInCourse(studentId: Long, courseId: Long) {
        val student = studentRepository.findById(studentId).orElseThrow { NotFoundException() }
        val course = courseRepository.findById(courseId).orElseThrow { NotFoundException() }

        student.courses.add(course)
        course.students.add(student)

        studentRepository.save(student)
        courseRepository.save(course)
    }
}
