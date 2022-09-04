package com.example.demo.dto;

import com.example.demo.validator.EmailValidator;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotBlank(message = "User name should not be null")
    private String name;

    @EmailValidator
    private String email;

    @NotBlank(message="password should not be null")
    @Size(min=5,message = "password should be minimum {min} characters")
    private String password;

    @NotBlank(message="password should not be null")
    @Size(min=5,message = "password should be minimum {min} characters")
    private String confirmPassword;

}
