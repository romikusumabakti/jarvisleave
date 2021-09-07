package org.jarvis.leave.service;

import org.jarvis.leave.model.LeaveType;
import org.jarvis.leave.repository.LeaveTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class LeaveTypeService {

    LeaveTypeRepository leaveTypeRepository;

    @Autowired
    public LeaveTypeService(LeaveTypeRepository leaveTypeRepository) {
        this.leaveTypeRepository = leaveTypeRepository;
    }

    public List<LeaveType> findAll() {
        return leaveTypeRepository.findAll();
    }

    public LeaveType findById(@PathVariable Long id) {
        return leaveTypeRepository.findById(id).orElse(null);
    }

    public LeaveType saveOrUpdate(@RequestBody LeaveType leaveType) {
        leaveTypeRepository.save(leaveType);
        return findById(leaveType.getId());
    }

    public void deleteById(@PathVariable Long id) {
        LeaveType leaveType = findById(id);
        leaveType.setIsDeleted(true);
        leaveTypeRepository.save(leaveType);
    }

    public void cancelDeleteById(Long id) {
        LeaveType leaveType = findById(id);
        leaveType.setIsDeleted(false);
        leaveTypeRepository.save(leaveType);
    }
}