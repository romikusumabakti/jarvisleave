package org.jarvis.leave.service;

import org.jarvis.leave.model.Employee;
import org.jarvis.leave.model.LeaveAllowance;
import org.jarvis.leave.model.LeaveSubmission;
import org.jarvis.leave.repository.LeaveSubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveSubmissionService {

    LeaveSubmissionRepository leaveSubmissionRepository;
    LeaveStatusService leaveStatusService;
    LeaveAllowanceService leaveAllowanceService;
    LeaveTypeService leaveTypeService;
    UserService userService;

    @Autowired
    public LeaveSubmissionService(LeaveSubmissionRepository leaveSubmissionRepository, LeaveStatusService leaveStatusService, LeaveAllowanceService leaveAllowanceService, LeaveTypeService leaveTypeService, UserService userService) {
        this.leaveSubmissionRepository = leaveSubmissionRepository;
        this.leaveStatusService = leaveStatusService;
        this.leaveAllowanceService = leaveAllowanceService;
        this.leaveTypeService = leaveTypeService;
        this.userService = userService;
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

    public void approve(Long id) {
        LeaveSubmission leaveSubmission = findById(id);
        leaveSubmission.setStatus(leaveStatusService.findById(3L));
        leaveSubmission.setHrd(userService.getUser());
        LeaveAllowance annualLeaveAllowance = leaveAllowanceService.findByEmployeeAndType(leaveSubmission.getEmployee(), leaveTypeService.findById(1L));
        LeaveAllowance crossYearLeaveAllowance = leaveAllowanceService.findByEmployeeAndType(leaveSubmission.getEmployee(), leaveTypeService.findById(2L));
        if (crossYearLeaveAllowance.getAllowance() > 0) {
            crossYearLeaveAllowance.setAllowance(crossYearLeaveAllowance.getAllowance() - 1);
        } else {
            annualLeaveAllowance.setAllowance(annualLeaveAllowance.getAllowance() - 1);
        }
        leaveSubmissionRepository.save(leaveSubmission);
    }

    public void reject(Long id) {
        LeaveSubmission leaveSubmission = findById(id);
        leaveSubmission.setStatus(leaveStatusService.findById(4L));
        leaveSubmission.setHrd(userService.getUser());
        leaveSubmissionRepository.save(leaveSubmission);
    }
}
