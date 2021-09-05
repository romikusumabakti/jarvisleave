package org.jarvis.leave.controller;

import org.jarvis.leave.dto.EmployeeDto;
import org.jarvis.leave.model.Employee;
import org.jarvis.leave.repository.RoleRepository;
import org.jarvis.leave.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    EmployeeService employeeService;
    RoleRepository roleRepository;

    @Autowired
    public EmployeeController(EmployeeService employeeService, RoleRepository roleRepository) {
        this.employeeService = employeeService;
        this.roleRepository = roleRepository;
    }

    @GetMapping()
    private List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    private Optional<Employee> getById(@PathVariable int id) {
        return employeeService.findById(id);
    }

    @PostMapping()
    private Employee save(@RequestBody EmployeeDto employeeDto) {
        return employeeService.saveOrUpdate(employeeDto);
    }

    @PutMapping()
    private Employee update(@RequestBody EmployeeDto employeeDto) {
        return employeeService.saveOrUpdate(employeeDto);
    }

    @DeleteMapping("/{id}")
    private void deleteById(@PathVariable int id) {
        employeeService.deleteById(id);
    }

    @PostMapping("/{id}")
    private void cancelDeleteById(@PathVariable int id) {
        employeeService.cancelDeleteById(id);
    }
}