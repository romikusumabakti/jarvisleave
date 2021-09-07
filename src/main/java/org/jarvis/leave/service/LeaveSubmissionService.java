package org.jarvis.leave.service;

import org.jarvis.leave.model.LeaveSubmission;
import org.jarvis.leave.repository.LeaveSubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

    public LeaveSubmission findById(@PathVariable Long id) {
        return leaveSubmissionRepository.findById(id).orElse(null);
    }

    public LeaveSubmission saveOrUpdate(@RequestBody LeaveSubmission leaveSubmission) {
        leaveSubmissionRepository.save(leaveSubmission);
        return findById(leaveSubmission.getId());
    }

    public void deleteById(@PathVariable Long id) {
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
