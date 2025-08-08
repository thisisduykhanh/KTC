package com.example.demo.dto;


import lombok.Builder;


@Builder
public record StudentDTO(
    Long id,
    String name,
    String email,
    String address,
    String phone
) {
}
