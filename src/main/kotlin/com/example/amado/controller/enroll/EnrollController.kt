package com.example.amado.controller.enroll

import com.example.amado.repository.EnrollmentService
import com.example.amado.service.CourseService
import com.example.amado.service.StudentService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/student")
class StudentController(
    @Autowired
    private val enrollmentService: EnrollmentService,
    private val studentService: StudentService,
) {

    @PostMapping("create")
    fun createStudent(
        @Valid @RequestBody request: StudentCreateRequest
    ): ResponseEntity<StudentDto> = ResponseEntity(studentService.createStudent(request), HttpStatus.OK)


    @PostMapping("/{studentId}/enroll/{courseId}")
    fun enrollStudentInCourse(
        @PathVariable studentId: Long, @PathVariable courseId: Long
    ) {
        enrollmentService.enrollStudentInCourse(studentId, courseId)
    }
}

@RestController
@RequestMapping("api/v1/course")
class CourseController(
    @Autowired
    private val courseService: CourseService,
) {

    @PostMapping("create")
    fun createCourse(
        @Valid @RequestBody request: CourseCreateRequest
    ): ResponseEntity<CourseDto> = ResponseEntity(courseService.createCourse(request), HttpStatus.OK)


    @GetMapping("/{courseId}/students")
    fun getStudentsInCourse(
        @PathVariable courseId: Long
    ): ResponseEntity<List<StudentDto>> {
        println("=== courseId : $courseId")
        val students = ResponseEntity(courseService.getStudentsInCourse(courseId), HttpStatus.OK)
        return students
    }
}
