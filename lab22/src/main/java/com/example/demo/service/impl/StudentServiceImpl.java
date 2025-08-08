package com.example.demo.service.impl;

import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.Student;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;


    @Override
    @Transactional
    public StudentDTO createStudent(StudentDTO studentDTO) {
        if (studentRepository.findByEmail(studentDTO.email()) != null) {
            throw new IllegalArgumentException("Student already exists with email: " + studentDTO.email());
        }

        Student newStudent = studentMapper.toEntity(studentDTO);
        studentRepository.save(newStudent);
        return studentMapper.toDto(newStudent);
    }

    @Override
    @Transactional(readOnly = true)
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));
        return studentMapper.toDto(student);
    }


    @Override
    @Transactional(readOnly = true)
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
            .map(studentMapper::toDto)
            .toList();
    }

    @Override
    @Transactional
    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        Student updatedStudent = studentRepository.findById(id)
            .map(existing -> {
                studentMapper.updateEntityFromDto(studentDTO, existing);
                return studentRepository.save(existing);
            })
            .orElseThrow(() -> new NoSuchElementException("Student not found with id: " + id));

        return studentMapper.toDto(updatedStudent);

    }

    @Override
    @Transactional
    public StudentDTO deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Student not found with id: " + id));

        studentRepository.deleteById(id);
        return studentMapper.toDto(student);
    }
}
