package org.jarvis.leave.controller;

import org.jarvis.leave.dto.LoginDto;
import org.jarvis.leave.model.Employee;
import org.jarvis.leave.repository.EmployeeRepository;
import org.jarvis.leave.service.AuthService;
import org.jarvis.leave.service.EmployeeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    AuthenticationManager authenticationManager;
    EmployeeDetailsService employeeDetailsService;
    AuthService authService;
    EmployeeRepository employeeRepository;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, EmployeeDetailsService employeeDetailsService, AuthService authService, EmployeeRepository employeeRepository) {
        this.authenticationManager = authenticationManager;
        this.employeeDetailsService = employeeDetailsService;
        this.authService = authService;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/check")
    private ResponseEntity<?> check(@RequestParam String id) {
        Employee employee = employeeRepository.get(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("NIP, nama pengguna, atau email tidak valid.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
            UserDetails userDetails = employeeDetailsService.loadUserByUsername(loginDto.getUsername());
            String token = authService.createToken(userDetails.getUsername());
            return ResponseEntity.ok(token);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
