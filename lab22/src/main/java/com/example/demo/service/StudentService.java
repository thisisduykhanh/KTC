package com.example.demo.service;

import com.example.demo.dto.StudentDTO;

import java.util.List;

public interface StudentService {

    StudentDTO createStudent(StudentDTO StudentDTO);
    StudentDTO getStudentById(Long id);
    List<StudentDTO> getAllStudents();
    StudentDTO updateStudent(Long id, StudentDTO StudentDTO);
    StudentDTO deleteStudent(Long id);
}
