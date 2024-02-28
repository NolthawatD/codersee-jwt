package com.example.amado.service

import com.example.amado.controller.enroll.StudentCreateRequest
import com.example.amado.controller.enroll.StudentDto
import com.example.amado.models.Student
import com.example.amado.exception.NotFoundException
import com.example.amado.repository.StudentRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class StudentService(private val studentRepository: StudentRepository) {

    private fun mappingEntityToDto(student:Student) = StudentDto(
        student.id,
        student.name,
    )

    private fun mappingFromRequestToEntity(student: Student, request: StudentCreateRequest) {
        student.name = request.name
    }

    private fun checkStudentForId(id: Long) {
        if (!studentRepository.existsById(id)) {
            throw NotFoundException("Student with the ID: $id does not exist!")
        }
    }

    fun getStudentById(id: Long): StudentDto {
        checkStudentForId(id)
        val student: Student = studentRepository.findStudentById(id)
        return mappingEntityToDto(student)
    }

    fun getAllStudents(): List<StudentDto> =
        studentRepository.findAll().stream().map(this::mappingEntityToDto).collect(Collectors.toList())

    fun createStudent(request: StudentCreateRequest): StudentDto {
        val student = Student()
        mappingFromRequestToEntity(student, request)
        val savedStudent: Student = studentRepository.save(student)
        return mappingEntityToDto(savedStudent)
    }

}