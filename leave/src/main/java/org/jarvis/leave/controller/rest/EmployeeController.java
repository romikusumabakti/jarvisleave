package org.jarvis.leave.controller.rest;

import org.jarvis.leave.dto.EmployeeDto;
import org.jarvis.leave.model.Employee;
import org.jarvis.leave.repository.RoleRepository;
import org.jarvis.leave.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    EmployeeService employeeService;
    RoleRepository roleRepository;
    ModelMapper modelMapper = new ModelMapper();

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
    private Employee findById(@PathVariable int id) {
        return employeeService.findById(id);
    }

    @PostMapping()
    private Employee save(@RequestBody EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        employee.setRole(roleRepository.findById(employeeDto.getRole()).orElse(null));
        return employeeService.saveOrUpdate(employee);
    }

    @PutMapping()
    private Employee update(@RequestBody EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        employee.setRole(roleRepository.findById(employeeDto.getRole()).orElse(null));
        return employeeService.saveOrUpdate(employee);
    }

    @DeleteMapping("/{id}")
    private void deleteById(@PathVariable int id) {
        employeeService.deleteById(id);
    }
}