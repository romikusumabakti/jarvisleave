package org.jarvis.leave.controller;

import org.jarvis.leave.dto.LeaveAllowanceDto;
import org.jarvis.leave.model.LeaveAllowance;
import org.jarvis.leave.service.EmployeeService;
import org.jarvis.leave.service.LeaveAllowanceService;
import org.jarvis.leave.service.LeaveTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/leave_allowances")
public class LeaveAllowanceController {

    LeaveAllowanceService leaveAllowanceService;
    EmployeeService employeeService;
    LeaveTypeService leaveTypeService;
    ModelMapper modelMapper;

    @Autowired
    public LeaveAllowanceController(LeaveAllowanceService leaveAllowanceService) {
        this.leaveAllowanceService = leaveAllowanceService;
    }

    @GetMapping()
    private List<LeaveAllowance> findAll() {
        return leaveAllowanceService.findAll();
    }

    @GetMapping("/{id}")
    private LeaveAllowance findById(@PathVariable Long id) { return leaveAllowanceService.findById(id); }

    private LeaveAllowance map(LeaveAllowanceDto leaveAllowanceDto) {
        LeaveAllowance leaveAllowance = modelMapper.map(leaveAllowanceDto, LeaveAllowance.class);
        leaveAllowance.setEmployee(employeeService.findById(leaveAllowanceDto.getEmployee()));
        leaveAllowance.setType(leaveTypeService.findById(leaveAllowanceDto.getType()));
        return leaveAllowance;
    }

    @PostMapping()
    private LeaveAllowance save(@RequestBody LeaveAllowanceDto leaveAllowanceDto) {
        return leaveAllowanceService.saveOrUpdate(map(leaveAllowanceDto));
    }

    @PutMapping()
    private LeaveAllowance update(@RequestBody LeaveAllowanceDto leaveAllowanceDto) {
        return leaveAllowanceService.saveOrUpdate(map(leaveAllowanceDto));
    }

    @DeleteMapping("/{id}")
    private void deleteById(@PathVariable Long id) {
        leaveAllowanceService.deleteById(id);
    }
}
