package org.jarvis.leave.service;

import org.jarvis.leave.model.Employee;
import org.jarvis.leave.model.EmployeeDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public Employee getUser() {
        return ((EmployeeDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();
    }
}
