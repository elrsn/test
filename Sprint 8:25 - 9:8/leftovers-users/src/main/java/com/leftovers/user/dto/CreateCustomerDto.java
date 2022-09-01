package com.leftovers.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCustomerDto {
    @NotNull
    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @NotNull
    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @NotNull
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email must be valid")
    private String email;

    @NotNull
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 10, max = 128, message = "Length must be between 10 and 128 characters")
    private String password;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^\\d{10,15}$", message = "Phone number must be between 10 and 15 numbers")
    private String phoneNo;

    @NotNull
    @NotBlank(message = "Address line is mandatory")
    private String addressLine;

    private String unitNumber;

    @NotNull
    @NotBlank(message = "City is mandatory")
    private String city;

    @NotNull
    @NotBlank(message = "State is mandatory")
    @Size(min = 2, max = 2, message = "State must consist of only 2 characters")
    private String state;

    @NotNull
    @NotBlank(message = "Zipcode is mandatory")
    @Pattern(regexp = "^\\d{5}(?:[-\\s]\\d{4})?$")
    private String zipcode;
}
