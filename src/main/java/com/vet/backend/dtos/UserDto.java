package com.vet.backend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vet.backend.models.Role;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String email;
    private String phone;
    private String password;
    private Role role;
}
