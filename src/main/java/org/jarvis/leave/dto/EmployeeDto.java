package org.jarvis.leave.dto;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
public class EmployeeDto {
    private int role;
    private String nip;
    private String name;
    private String division;
    private String email;
    private String username;
    private String password;

    @Autowired
    PasswordEncoder passwordEncoder;
    public String getPassword() {
        return passwordEncoder.encode(password);
    }
}
