package com.carsservice.CarsTeam.DTO.auth;

import lombok.Data;

@Data
public class LoginDTO {
    private String userNameOrEmail;
    private String password;
}
