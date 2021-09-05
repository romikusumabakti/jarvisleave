package org.jarvis.leave.service;

import org.jarvis.leave.model.Employee;
import org.jarvis.leave.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

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

    public Optional<Employee> findById(int id) {
        return employeeRepository.findById(id);
    }

    public Employee saveOrUpdate(@RequestBody Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    public void deleteById(int id) {
        Employee employee = employeeRepository.getById(id);
        employee.setIsDeleted(true);
        employeeRepository.save(employee);
    }

    public void cancelDeleteById(int id) {
        Employee employee = employeeRepository.getById(id);
        employee.setIsDeleted(false);
        employeeRepository.save(employee);
    }
}