package org.jarvis.leave.controller;

import org.jarvis.leave.dto.LeaveTypeDto;
import org.jarvis.leave.model.LeaveType;
import org.jarvis.leave.service.LeaveTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave_types")
public class LeaveTypeController {

    LeaveTypeService leaveTypeService;
    ModelMapper modelMapper;

    @Autowired
    public LeaveTypeController(LeaveTypeService leaveTypeService, ModelMapper modelMapper) {
        this.leaveTypeService = leaveTypeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    private List<LeaveType> findAll() {
        return leaveTypeService.findAll();
    }

    @GetMapping("/{id}")
    private LeaveType findById(@PathVariable Long id) {
        return leaveTypeService.findById(id);
    }

    private LeaveType map(LeaveTypeDto leaveTypeDto) {
        return modelMapper.map(leaveTypeDto, LeaveType.class);
    }

    @PostMapping()
    private LeaveType save(@RequestBody LeaveTypeDto leaveTypeDto) {
        return leaveTypeService.saveOrUpdate(map(leaveTypeDto));
    }

    @PutMapping()
    private LeaveType update(@RequestBody LeaveTypeDto leaveTypeDto) {
        return leaveTypeService.saveOrUpdate(map(leaveTypeDto));
    }

    @DeleteMapping("/{id}")
    private void deleteById(@PathVariable Long id) {
        leaveTypeService.deleteById(id);
    }
}