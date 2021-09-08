package org.jarvis.leave.controller;

import org.jarvis.leave.dto.LeaveSubmissionDto;
import org.jarvis.leave.model.LeaveAllowance;
import org.jarvis.leave.model.LeaveSubmission;
import org.jarvis.leave.service.LeaveAllowanceService;
import org.jarvis.leave.service.LeaveSubmissionService;
import org.jarvis.leave.service.LeaveTypeService;
import org.jarvis.leave.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/user")
public class UserController {

    LeaveAllowanceService leaveAllowanceService;
    LeaveSubmissionService leaveSubmissionService;
    LeaveTypeService leaveTypeService;
    UserService userService;
    ModelMapper modelMapper;

    @Autowired
    public UserController(LeaveAllowanceService leaveAllowanceService, LeaveSubmissionService leaveSubmissionService, LeaveTypeService leaveTypeService, UserService userService, ModelMapper modelMapper) {
        this.leaveAllowanceService = leaveAllowanceService;
        this.leaveSubmissionService = leaveSubmissionService;
        this.leaveTypeService = leaveTypeService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/leave_allowances")
    private List<LeaveAllowance> getLeaveAllowances() {
        return leaveAllowanceService.findAllByEmployee(userService.getUser());
    }

    @GetMapping("/leave_submissions")
    private List<LeaveSubmission> getLeaveSubmission() {
        return leaveSubmissionService.findAllByEmployee(userService.getUser());
    }

    private LeaveSubmission map(LeaveSubmissionDto leaveSubmissionDto) {
        if (Objects.equals(leaveSubmissionDto.getEmployee(), userService.getUser().getId())) {
            return modelMapper.map(leaveSubmissionDto, LeaveSubmission.class);
        } else {
            return null;
        }
    }

    @PostMapping("/leave_submissions")
    private LeaveSubmission submit(@RequestBody LeaveSubmissionDto leaveSubmissionDto) {
        if (leaveAllowanceService.findByEmployeeAndType(userService.getUser(), leaveTypeService.findById(1L)).getAllowance() > 0) {
            return leaveSubmissionService.saveOrUpdate(map(leaveSubmissionDto));
        } else {
            return null;
        }
    }

    @PutMapping("/leave_submissions")
    private LeaveSubmission update(@RequestBody LeaveSubmissionDto leaveSubmissionDto) {
        if (leaveSubmissionDto.getId() != null) {
            return leaveSubmissionService.saveOrUpdate(map(leaveSubmissionDto));
        } else {
            return null;
        }
    }
}
