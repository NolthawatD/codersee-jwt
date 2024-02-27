package com.example.amado.service

import com.example.amado.controller.enroll.CourseCreateRequest
import com.example.amado.controller.enroll.CourseDto
import com.example.amado.controller.enroll.StudentDto
import com.example.amado.data.Course
import com.example.amado.data.Student
import com.example.amado.exception.NotFoundException
import com.example.amado.repository.CourseRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class CourseService(private val courseRepository: CourseRepository) {

    private fun mappingEntityToDto(course: Course) = CourseDto(
        course.id,
        course.name,
    )

    private fun mappingUserEntityToDto(student:Student) = StudentDto(
        student.id,
        student.name,
    )

    private fun mappingFromRequestToEntity(course: Course, request: CourseCreateRequest) {
        course.name = request.name
    }

    private fun checkCourseForId(id: Long) {
        if (!courseRepository.existsById(id)) {
            throw NotFoundException("Course with the ID: $id does not exist!")
        }
    }

    fun findById(id: Long): List<CourseDto> =
        courseRepository.findById(id).stream().map(this::mappingEntityToDto).collect(Collectors.toList())

    fun getStudentsInCourse(courseId: Long): List<StudentDto> {
        println("=== courseId : $courseId")
        val course = courseRepository.findCourseByIdJPQL(courseId)
        return course.students.stream().map(this::mappingUserEntityToDto).collect(Collectors.toList())
    }

    fun getCourseById(id: Long): CourseDto {
        checkCourseForId(id)
        val course: Course = courseRepository.findCourseById(id)
        return mappingEntityToDto(course)
    }

    fun getAllCourses(): List<CourseDto> =
        courseRepository.findAll().stream().map(this::mappingEntityToDto).collect(Collectors.toList())

    fun createCourse(request: CourseCreateRequest): CourseDto {
        val course = Course()
        mappingFromRequestToEntity(course, request)
        val savedCourse: Course = courseRepository.save(course)
        return mappingEntityToDto(savedCourse)
    }

}