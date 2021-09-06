package org.jarvis.leave.service;

import org.jarvis.leave.model.Employee;
import org.jarvis.leave.repository.EmployeeRepository;
import org.jarvis.leave.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    EmployeeRepository employeeRepository;
    RoleRepository roleRepository;
    ModelMapper modelMapper;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, RoleRepository roleRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee findByNipUsernameOrEmail(String id) {
        return employeeRepository.findByNipUsernameOrEmail(id);
    }

    public Employee saveOrUpdate(Employee employee) {
        employeeRepository.save(employee);
        return findById(employee.getId());
    }

    public void deleteById(Long id) {
        Employee employee = findById(id);
        employee.setIsDeleted(true);
        employeeRepository.save(employee);
    }

    public void cancelDeleteById(Long id) {
        Employee employee = findById(id);
        employee.setIsDeleted(false);
        employeeRepository.save(employee);
    }
}