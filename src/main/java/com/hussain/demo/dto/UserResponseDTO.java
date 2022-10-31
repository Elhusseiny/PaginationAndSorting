package com.hussain.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {
    private String username ;
    private String email;
    private List<String> roles ;
}
