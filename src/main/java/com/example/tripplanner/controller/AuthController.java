package com.example.tripplanner.controller;

import com.example.tripplanner.dto.JwtResponseDTO;
import com.example.tripplanner.dto.UserLoginDTO;
import com.example.tripplanner.dto.UserRegistrationDTO;
import com.example.tripplanner.exception.UserAlreadyExistsException;
import com.example.tripplanner.security.jwt.JwtService;
import com.example.tripplanner.security.service.CustomUserDetailsService;
import com.example.tripplanner.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, CustomUserDetailsService customUserDetailsService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<JwtResponseDTO> registerUser(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        try {
            userService.registerUser(userRegistrationDTO);
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(userRegistrationDTO.getEmail());
            String jwtToken = jwtService.generateToken(userDetails);

            return ResponseEntity.ok(new JwtResponseDTO(jwtToken));
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginDTO.getEmail(), userLoginDTO.getPassword()));

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwtToken = jwtService.generateToken(userDetails);

            return ResponseEntity.ok(new JwtResponseDTO(jwtToken));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
