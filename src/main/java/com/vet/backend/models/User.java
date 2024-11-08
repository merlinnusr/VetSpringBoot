package com.vet.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Email
    private String email;
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    @Size(min = 10, max = 10, message = "Phone number must be exactly 10 digits")
    @Column(length = 10)  // This sets the column length in the database
    private String phone;
    @JsonIgnore
    private String password;
    @OneToOne(cascade = CascadeType.ALL,
                fetch = FetchType.EAGER)
    private Role role;
}
