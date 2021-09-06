package org.jarvis.leave.controller;

import org.jarvis.leave.dto.EmployeeDto;
import org.jarvis.leave.model.Employee;
import org.jarvis.leave.service.DivisionService;
import org.jarvis.leave.service.EmployeeService;
import org.jarvis.leave.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

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
    private Employee findById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    private ResponseEntity<?> saveOrUpdate(EmployeeDto employeeDto) {

        Employee employee = modelMapper.map(employeeDto, Employee.class);
        employee.setRole(roleService.findById(employeeDto.getRole()).orElse(null));
        employee.setDivision(divisionService.findById(employeeDto.getDivision()).orElse(null));

        if (employeeDto.getId() == null) {

            HashMap<String, String> errors = new HashMap<>();

            if (employeeService.findByNipUsernameOrEmail(employeeDto.getNip()) != null) {
                errors.put("nip", "NIP sudah dipakai.");
            }

            if (employeeService.findByNipUsernameOrEmail(employeeDto.getUsername()) != null) {
                errors.put("username", "Nama pengguna sudah dipakai.");
            }

            if (employeeService.findByNipUsernameOrEmail(employeeDto.getEmail()) != null) {
                errors.put("email", "Email sudah dipakai.");
            }

            if (!errors.isEmpty()) {
                return ResponseEntity.badRequest().body(errors);
            }
        } else {

            if (employeeDto.getPassword() != null) {
                employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
            } else {
                employee.setPassword(employeeService.findById(employeeDto.getId()).getPassword());
            }
        }

        return ResponseEntity.ok(employeeService.saveOrUpdate(employee));
    }

    @PostMapping()
    private ResponseEntity<?> save(@RequestBody EmployeeDto employeeDto) {
        return saveOrUpdate(employeeDto);
    }

    @PutMapping()
    private ResponseEntity<?> update(@RequestBody EmployeeDto employeeDto) {
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