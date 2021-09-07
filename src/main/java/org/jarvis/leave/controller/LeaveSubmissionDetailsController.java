package org.jarvis.leave.controller;

import org.jarvis.leave.dto.LeaveSubmissionDetailsDto;
import org.jarvis.leave.model.LeaveSubmissionDetails;
import org.jarvis.leave.repository.LeaveTypeRepository;
import org.jarvis.leave.repository.LeaveSubmissionRepository;
import org.jarvis.leave.service.LeaveSubmissionDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detail_pengajuan_cuti")
public class LeaveSubmissionDetailsController {
    LeaveSubmissionDetailsService leaveSubmissionDetailsService;
    LeaveSubmissionRepository leaveSubmissionRepository;
    LeaveTypeRepository leaveTypeRepository;
    ModelMapper modelMapper;

    @Autowired
    public LeaveSubmissionDetailsController(LeaveSubmissionDetailsService leaveSubmissionDetailsService, LeaveSubmissionRepository leaveSubmissionRepository, LeaveTypeRepository leaveTypeRepository, ModelMapper modelMapper) {
        this.leaveSubmissionDetailsService = leaveSubmissionDetailsService;
        this.leaveSubmissionRepository = leaveSubmissionRepository;
        this.leaveTypeRepository = leaveTypeRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    private List<LeaveSubmissionDetails> findAll() {
        return leaveSubmissionDetailsService.findAll();
    }

    @GetMapping("/{id}")
    private LeaveSubmissionDetails findById(@PathVariable Long id) {
        return leaveSubmissionDetailsService.findById(id);
    }

    private LeaveSubmissionDetails map(LeaveSubmissionDetailsDto leaveSubmissionDetailsDto) {
        return modelMapper.map(leaveSubmissionDetailsDto, LeaveSubmissionDetails.class);
    }

    @PostMapping()
    private LeaveSubmissionDetails save(@RequestBody LeaveSubmissionDetailsDto leaveSubmissionDetailsDto) {
        return leaveSubmissionDetailsService.saveOrUpdate(map(leaveSubmissionDetailsDto));
    }

    @PutMapping()
    private LeaveSubmissionDetails update(@RequestBody LeaveSubmissionDetailsDto leaveSubmissionDetailsDto) {
        return leaveSubmissionDetailsService.saveOrUpdate(map(leaveSubmissionDetailsDto));
    }

    @DeleteMapping("/{id}")
    private void deleteById(@PathVariable Long id) {
        leaveSubmissionDetailsService.deleteById(id);
    }
}
