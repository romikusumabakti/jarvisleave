package org.jarvis.leave.service;

import org.jarvis.leave.model.Employee;
import org.jarvis.leave.repository.EmployeeRepository;
import org.jarvis.leave.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    EmployeeRepository employeeRepository;
    RoleRepository roleRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, RoleRepository roleRepository) {
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
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