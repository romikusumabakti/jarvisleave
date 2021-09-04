package org.jarvis.leave.service;

import org.jarvis.leave.model.Employee;
import org.jarvis.leave.model.EmployeeDetails;
import org.jarvis.leave.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDetailsService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    public EmployeeDetailsService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Employee employee = employeeRepository.getByNipUsernameOrEmail(username);
        if (employee == null) {
            throw new UsernameNotFoundException(username);
        }
        return new EmployeeDetails(employee);
    }
}