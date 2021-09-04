package org.jarvis.leave.service;

import org.jarvis.leave.model.Employee;
import org.jarvis.leave.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee getById(int id) {
        return employeeRepository.getById(id);
    }

    public Employee saveOrUpdate(Employee employee) {
        employeeRepository.save(employee);
        employee.setLastModifiedBy(((Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        return employee;
    }

    public void deleteById(int id) {
        Employee employee = getById(id);
        employee.setIsDeleted(true);
        employeeRepository.save(employee);
    }
}