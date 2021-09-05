package org.jarvis.leave.controller;

import org.jarvis.leave.dto.EmployeeDto;
import org.jarvis.leave.model.Employee;
import org.jarvis.leave.service.DivisionService;
import org.jarvis.leave.service.EmployeeService;
import org.jarvis.leave.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    EmployeeService employeeService;
    RoleService roleService;
    DivisionService divisionService;
    ModelMapper modelMapper;
    PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeController(EmployeeService employeeService, RoleService roleService, DivisionService divisionService, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.employeeService = employeeService;
        this.roleService = roleService;
        this.divisionService = divisionService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping()
    private List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    private Optional<Employee> findById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    private Employee saveOrUpdate(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        employee.setRole(roleService.findById(employeeDto.getRole()).orElse(null));
        employee.setDivision(divisionService.findById(employeeDto.getDivision()).orElse(null));
        if (employeeDto.getPassword() != null) {
            employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
        } else {
            employee.setPassword(Objects.requireNonNull(employeeService.findById(employeeDto.getId()).orElse(null)).getPassword());
        }
        return employeeService.saveOrUpdate(employee);
    }

    @PostMapping()
    private Employee save(@RequestBody EmployeeDto employeeDto) {
        return saveOrUpdate(employeeDto);
    }

    @PutMapping()
    private Employee update(@RequestBody EmployeeDto employeeDto) {
        return saveOrUpdate(employeeDto);
    }

    @DeleteMapping("/{id}")
    private void deleteById(@PathVariable Long id) {
        employeeService.deleteById(id);
    }

    @PostMapping("/{id}")
    private void cancelDeleteById(@PathVariable Long id) {
        employeeService.cancelDeleteById(id);
    }
}