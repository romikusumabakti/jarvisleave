package org.jarvis.leave.controller;

import org.jarvis.leave.model.Employee;
import org.jarvis.leave.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/")
    private List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findAll().forEach(employees::add);
        return employees;
    }

    @GetMapping("/{id}")
    private Employee getEmployee(@PathVariable("id") int id) {
        return employeeRepository.findById(id).get();
    }

    @DeleteMapping("/{id}")
    private void deleteEmployee(@PathVariable("id") int id) {
        employeeRepository.deleteById(id);
    }

    @PostMapping("/")
    private Employee saveEmployee(@RequestBody Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    @PutMapping("/")
    private Employee update(@RequestBody Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }
}