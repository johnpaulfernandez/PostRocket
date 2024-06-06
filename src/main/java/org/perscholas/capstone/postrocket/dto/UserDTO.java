package org.perscholas.capstone.postrocket.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Used to validate the user registration form.
// This Data Transfer Object (DTO) is annotated using Hibernate-Validation annotations
// which validate trivial fields on empty and our own custom @FieldMatch annotations
// which validates if the password is equal to the 'confirm password' and the email address field
// is equal to the 'confirm email' address field.
@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    @Column(unique = true)
    @NotEmpty
    @Email
    private String email;

    @NotEmpty(message = "Required")
    private String password;

    @NotEmpty(message = "Required")
    private String firstName;

    @NotEmpty(message = "Required")
    private String lastName;

    @NotEmpty(message = "Required")
    private String birthMonth;

    @NotEmpty(message = "Required")
    @Pattern(regexp = "^[0-9]{4}$", message = "Only four consecutive numbers")
    private String birthYear;

    public UserDTO(String email, String password, String firstName, String lastName, String birthMonth, String birthYear) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthMonth = birthMonth;
        this.birthYear = birthYear;
    }
}