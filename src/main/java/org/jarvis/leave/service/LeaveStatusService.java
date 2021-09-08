package org.jarvis.leave.service;

import org.jarvis.leave.model.LeaveStatus;
import org.jarvis.leave.repository.LeaveStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveStatusService {

    LeaveStatusRepository leaveStatusRepository;

    @Autowired
    public LeaveStatusService(LeaveStatusRepository leaveStatusRepository) {
        this.leaveStatusRepository = leaveStatusRepository;
    }

    public List<LeaveStatus> findAll() {
        return leaveStatusRepository.findAll();
    }

    public LeaveStatus findById(Long id) {
        return leaveStatusRepository.findById(id).orElse(null);
    }

    public LeaveStatus saveOrUpdate(LeaveStatus leaveStatus) {
        leaveStatusRepository.save(leaveStatus);
        return findById(leaveStatus.getId());
    }

    public void deleteById(Long id) {
        LeaveStatus leaveStatus = leaveStatusRepository.getById(id);
        leaveStatus.setIsDeleted(true);
        leaveStatusRepository.save(leaveStatus);
    }

    public void cancelDeleteById(Long id) {
        LeaveStatus leaveStatus = leaveStatusRepository.getById(id);
        leaveStatus.setIsDeleted(false);
        leaveStatusRepository.save(leaveStatus);
    }
}
