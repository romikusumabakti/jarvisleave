package org.jarvis.leave.controller;

import org.jarvis.leave.dto.LeaveStatusDto;
import org.jarvis.leave.model.LeaveStatus;
import org.jarvis.leave.service.LeaveStatusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave_statuses")
public class LeaveStatusController {
    
    LeaveStatusService leaveStatusService;
    ModelMapper modelMapper;

    @Autowired
    public LeaveStatusController(LeaveStatusService leaveStatusService, ModelMapper modelMapper) {
        this.leaveStatusService = leaveStatusService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    private List<LeaveStatus> findAll() {
        return leaveStatusService.findAll();
    }

    @GetMapping("/{id}")
    private LeaveStatus findById(@PathVariable Long id) {
        return leaveStatusService.findById(id);
    }

    private LeaveStatus map(LeaveStatusDto leaveStatusDto) {
        return modelMapper.map(leaveStatusDto, LeaveStatus.class);
    }

    @PostMapping()
    private LeaveStatus save(@RequestBody LeaveStatusDto leaveStatusDto) {
        return leaveStatusService.saveOrUpdate(map(leaveStatusDto));
    }

    @PutMapping()
    private LeaveStatus update(@RequestBody LeaveStatusDto leaveStatusDto) {
        return leaveStatusService.saveOrUpdate(map(leaveStatusDto));
    }

    @DeleteMapping("/{id}")
    private void deleteById(@PathVariable Long id) {
        leaveStatusService.deleteById(id);
    }
}
