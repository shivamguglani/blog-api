package com.backend.spring.boot.payloads;


import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

@NoArgsConstructor
@Getter
@Setter

public class UserDto {

    private int id;

    @NotEmpty
    @Size(min = 4,message = "name should be greater than 4 characters")
    private String name;

    @NotEmpty @Email (message = "Email address is not Valid")

    @Column(unique = true)
    private  String email;
    @NotEmpty
    @Size (min = 8,max = 15 ,message = "password must be of greater than 8 and less than 15 characters")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",message = "Password must be of Minimum eight characters, at least one letter , one number and one special character")
    private  String password;
    @NotEmpty
    @Size (min = 5,message = "address must be greater than 5 characters")
    private  String address;


}
