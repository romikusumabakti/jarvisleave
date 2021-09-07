package org.jarvis.leave.controller;

import org.jarvis.leave.dto.LeaveSubmissionDto;
import org.jarvis.leave.model.LeaveSubmission;
import org.jarvis.leave.repository.EmployeeRepository;
import org.jarvis.leave.service.LeaveSubmissionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pengajuan_cuti")
public class LeaveSubmissionController {

    LeaveSubmissionService leaveSubmissionService;
    EmployeeRepository employeeRepository;
    ModelMapper modelMapper;

    @Autowired
    public LeaveSubmissionController(LeaveSubmissionService leaveSubmissionService, EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.leaveSubmissionService = leaveSubmissionService;
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    private List<LeaveSubmission> findAll() {
        return leaveSubmissionService.findAll();
    }

    @GetMapping("/{id}")
    private LeaveSubmission findById(@PathVariable Long id) {
        return leaveSubmissionService.findById(id);
    }

    private LeaveSubmission map(LeaveSubmissionDto leaveSubmissionDto) {
        return modelMapper.map(leaveSubmissionDto, LeaveSubmission.class);
    }

    @PostMapping()
    private LeaveSubmission save(@RequestBody LeaveSubmissionDto leaveSubmissionDto) {
        return leaveSubmissionService.saveOrUpdate(map(leaveSubmissionDto));
    }

    @PutMapping()
    private LeaveSubmission update(@RequestBody LeaveSubmissionDto leaveSubmissionDto) {
        return leaveSubmissionService.saveOrUpdate(map(leaveSubmissionDto));
    }

    @DeleteMapping("/{id}")
    private void deleteById(@PathVariable Long id) {
        leaveSubmissionService.deleteById(id);
    }
}
