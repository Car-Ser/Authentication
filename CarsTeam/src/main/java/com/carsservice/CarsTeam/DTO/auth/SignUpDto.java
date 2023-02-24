package com.carsservice.CarsTeam.DTO.auth;
import lombok.Data;

@Data
public class SignUpDto {
    private String firstName;
    private String dateOfBirth;
    private String lastName;
    private String userName;
    private String country;
    private String email;
    private String password;
    private String roles;
}
