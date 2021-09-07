package org.jarvis.leave.service;

import org.jarvis.leave.model.LeaveAllowance;
import org.jarvis.leave.repository.EmployeeRepository;
import org.jarvis.leave.repository.LeaveAllowanceRepository;
import org.jarvis.leave.repository.LeaveTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class LeaveAllowanceService {

    LeaveAllowanceRepository leaveAllowanceRepository;
    EmployeeRepository employeeRepository;
    LeaveTypeRepository leaveTypeRepository;

    @Autowired
    public LeaveAllowanceService(LeaveAllowanceRepository leaveAllowanceRepository, EmployeeRepository employeeRepository, LeaveTypeRepository leaveTypeRepository) {
        this.leaveAllowanceRepository = leaveAllowanceRepository;
        this.employeeRepository = employeeRepository;
        this.leaveTypeRepository = leaveTypeRepository;
    }

    public List<LeaveAllowance> findAll() {
        return leaveAllowanceRepository.findAll();
    }

    public LeaveAllowance findById(@PathVariable Long id) {
        return leaveAllowanceRepository.findById(id).orElse(null);
    }

    public LeaveAllowance saveOrUpdate(@RequestBody LeaveAllowance leaveAllowance) {
        leaveAllowanceRepository.save(leaveAllowance);
        return findById(leaveAllowance.getId());
    }

    public void deleteById(@PathVariable Long id) {
        LeaveAllowance leaveAllowance = findById(id);
        leaveAllowance.setIsDeleted(true);
        leaveAllowanceRepository.save(leaveAllowance);
    }

    public void cancelDeleteById(Long id) {
        LeaveAllowance leaveAllowance = findById(id);
        leaveAllowance.setIsDeleted(false);
        leaveAllowanceRepository.save(leaveAllowance);
    }
}