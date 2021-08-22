package org.jarvis.leave.dto;

import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
public class EmployeeDto {
    private int role;
    private String nip;
    private String namaLengkap;
    private String divisi;
    private String email;
    private String username;
    private String password;

    public String getPassword() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
