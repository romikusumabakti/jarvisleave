package org.jarvis.leave.service;

import org.jarvis.leave.model.LeaveSubmissionDetails;
import org.jarvis.leave.repository.LeaveSubmissionDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class LeaveSubmissionDetailsService {

    LeaveSubmissionDetailsRepository leaveSubmissionDetailsRepository;

    @Autowired
    public LeaveSubmissionDetailsService(LeaveSubmissionDetailsRepository leaveSubmissionDetailsRepository) {
        this.leaveSubmissionDetailsRepository = leaveSubmissionDetailsRepository;
    }

    public List<LeaveSubmissionDetails> findAll() {
        return leaveSubmissionDetailsRepository.findAll();
    }

    public LeaveSubmissionDetails findById(@PathVariable Long id) {
        return leaveSubmissionDetailsRepository.findById(id).orElse(null);
    }

    public LeaveSubmissionDetails saveOrUpdate(@RequestBody LeaveSubmissionDetails leaveSubmissionDetails) {
        leaveSubmissionDetailsRepository.save(leaveSubmissionDetails);
        return findById(leaveSubmissionDetails.getId());
    }

    public void deleteById(@PathVariable Long id) {
        LeaveSubmissionDetails leaveSubmissionDetails = findById(id);
        leaveSubmissionDetails.setIsDeleted(true);
        leaveSubmissionDetailsRepository.save(leaveSubmissionDetails);
    }

    public void cancelDeleteById(Long id) {
        LeaveSubmissionDetails leaveSubmissionDetails = findById(id);
        leaveSubmissionDetails.setIsDeleted(false);
        leaveSubmissionDetailsRepository.save(leaveSubmissionDetails);
    }
}
