package org.jarvis.leave.service;

import org.jarvis.leave.model.Employee;
import org.jarvis.leave.model.LeaveSubmission;
import org.jarvis.leave.repository.LeaveSubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveSubmissionService {

    LeaveSubmissionRepository leaveSubmissionRepository;

    @Autowired
    public LeaveSubmissionService(LeaveSubmissionRepository leaveSubmissionRepository) {
        this.leaveSubmissionRepository = leaveSubmissionRepository;
    }

    public List<LeaveSubmission> findAll() {
        return leaveSubmissionRepository.findAll();
    }

    public LeaveSubmission findById(Long id) {
        return leaveSubmissionRepository.findById(id).orElse(null);
    }

    public List<LeaveSubmission> findAllByEmployee(Employee employee) {
        return leaveSubmissionRepository.findAllByEmployee(employee);
    }

    public LeaveSubmission saveOrUpdate(LeaveSubmission leaveSubmission) {
        leaveSubmissionRepository.save(leaveSubmission);
        return findById(leaveSubmission.getId());
    }

    public void deleteById(Long id) {
        LeaveSubmission leaveSubmission = findById(id);
        leaveSubmission.setIsDeleted(true);
        leaveSubmissionRepository.save(leaveSubmission);
    }

    public void cancelDeleteById(Long id) {
        LeaveSubmission leaveSubmission = findById(id);
        leaveSubmission.setIsDeleted(false);
        leaveSubmissionRepository.save(leaveSubmission);
    }
}
