package com.carsservice.CarsTeam.Controllers.Auth;

import com.carsservice.CarsTeam.DTO.auth.LoginDTO;
import com.carsservice.CarsTeam.DTO.auth.SignUpDto;
import com.carsservice.CarsTeam.Model.Auth.User;
import com.carsservice.CarsTeam.Model.Role.RoleEntity;
import com.carsservice.CarsTeam.Repositories.Auth.UserRepository;
import com.carsservice.CarsTeam.Repositories.Role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class UserControllers {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDTO loginDto) {
        if (!userRepository.existsByUserName(loginDto.getUserNameOrEmail())){
            return new ResponseEntity<>("Username does not exist!", HttpStatus.BAD_REQUEST);
        } else if (!userRepository.existsByEmail(loginDto.getUserNameOrEmail())){
            return new ResponseEntity<>("Email does not exist!", HttpStatus.BAD_REQUEST);
        } else {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUserNameOrEmail(), loginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {
        if (userRepository.existsByUserName(signUpDto.getUserName())) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        } else if (userRepository.existsByEmail(signUpDto.getEmail())) {
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        } else {
            User user = new User();
            user.setFirstName(signUpDto.getFirstName());
            user.setLastName(signUpDto.getLastName());
            user.setUserName(signUpDto.getUserName());
            user.setCountry(signUpDto.getCountry());
            user.setDateOfBirth(user.getDateOfBirth());
            user.setEmail(signUpDto.getEmail());
            user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
            RoleEntity roles = roleRepository.findByName(signUpDto.getRoles()).get();
            user.setRoles(Collections.singleton(roles));
            userRepository.save(user);
            return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
        }
    }
}
