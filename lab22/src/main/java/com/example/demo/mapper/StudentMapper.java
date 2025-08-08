package com.example.demo.mapper;

import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDTO toDto(Student student);
    Student toEntity(StudentDTO studentDTO);

    List<StudentDTO> toDtoList(List<Student> customers);

    void updateEntityFromDto(StudentDTO customerDTO, @MappingTarget Student customer);
}
