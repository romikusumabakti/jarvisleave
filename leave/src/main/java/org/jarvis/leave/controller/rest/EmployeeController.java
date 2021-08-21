package org.jarvis.leave.controller.rest;

import org.jarvis.leave.model.Employee;
import org.jarvis.leave.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping()
    private List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findAll().forEach(employees::add);
        return employees;
    }

    @GetMapping("/{id}")
    private Employee get(@PathVariable int id) {
        return employeeRepository.findById(id).get();
    }

    @PostMapping("/")
    private Employee save(@RequestBody Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    @DeleteMapping("/{id}")
    private void delete(@PathVariable int id) {
        Employee employee = get(id);
        employee.setIsDeleted(true);
        employeeRepository.save(employee);
    }

    @PutMapping("/")
    private Employee update(@RequestBody Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }
}