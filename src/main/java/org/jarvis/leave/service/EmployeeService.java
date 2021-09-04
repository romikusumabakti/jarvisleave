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

    public Employee getById(int id) {
        return employeeRepository.getById(id);
    }

    public Employee saveOrUpdate(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        employee.setRole(roleRepository.getById(employeeDto.getRole()));
        employeeRepository.save(employee);
        employee.setLastModifiedBy(((Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        return employee;
    }

    public void deleteById(int id) {
        Employee employee = getById(id);
        employee.setIsDeleted(true);
        employeeRepository.save(employee);
    }

    public void cancelDeleteById(int id) {
        Employee employee = getById(id);
        employee.setIsDeleted(false);
        employeeRepository.save(employee);
    }
}