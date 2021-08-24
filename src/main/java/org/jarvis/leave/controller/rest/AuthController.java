package org.jarvis.leave.controller.rest;

import org.jarvis.leave.dto.LoginDto;
import org.jarvis.leave.service.EmployeeDetailsService;
import org.jarvis.leave.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    AuthenticationManager authenticationManager;
    EmployeeDetailsService employeeDetailsService;
    AuthService authService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, EmployeeDetailsService employeeDetailsService, AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.employeeDetailsService = employeeDetailsService;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
            UserDetails userDetails = employeeDetailsService.loadUserByUsername(loginDto.getUsername());
            String token = authService.createToken(new HashMap<>(), userDetails.getUsername());
            return ResponseEntity.ok(token);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
