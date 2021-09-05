package org.jarvis.leave.service;

import org.jarvis.leave.dto.EmployeeDto;
import org.jarvis.leave.model.Employee;
import org.jarvis.leave.repository.EmployeeRepository;
import org.jarvis.leave.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    EmployeeRepository employeeRepository;
    RoleRepository roleRepository;
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, RoleRepository roleRepository) {
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> findById(int id) {
        return employeeRepository.findById(id);
    }

    public Employee saveOrUpdate(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        employee.setRole(roleRepository.getById(employeeDto.getRole()));
        employeeRepository.save(employee);
        employee.setLastModifiedBy(((Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
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