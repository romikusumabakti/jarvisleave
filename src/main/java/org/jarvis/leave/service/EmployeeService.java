package org.jarvis.leave.service;

import org.jarvis.leave.model.Employee;
import org.jarvis.leave.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

    public Employee findById(@PathVariable int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee saveOrUpdate(@RequestBody Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    public void deleteById(@PathVariable int id) {
        Employee employee = findById(id);
        employee.setIsDeleted(true);
        employeeRepository.save(employee);
    }
}