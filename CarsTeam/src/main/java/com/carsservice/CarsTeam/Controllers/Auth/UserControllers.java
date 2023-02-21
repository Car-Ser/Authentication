package com.carsservice.CarsTeam.Controllers.Auth;

import com.carsservice.CarsTeam.Model.Auth.User;
import com.carsservice.CarsTeam.Repositories.Auth.UserRepository;
import com.carsservice.CarsTeam.Services.Auth.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.UUID;

@Controller
public class UserControllers {

    private UserService userService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private BindingResult bindingResult;

    @Autowired
    UserRepository UserRepositories;

    public UserControllers(UserRepository UserRepositories) {
        this.UserRepositories = UserRepositories;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam String email,
                                           @RequestParam String firstName,
                                           @RequestParam String lastName,
                                           @RequestParam String password){
        User existUser = userService.findByEmail(email);
        if (existUser != null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(email + " Is Already Exist");
        } else {
            try {

                User user = new User(email,  bCryptPasswordEncoder.encode(password), firstName, lastName, false, UUID.randomUUID().toString());
                userService.saveUser(user);
                return ResponseEntity.status(201)
                        .body(user.getConfirmationToken().toString());
            } catch (HttpClientErrorException.BadRequest br) {
                return ResponseEntity.status(Integer.parseInt("400")).body(
                        email + " Please Try Again"
                );
            }
        }
    }

}
