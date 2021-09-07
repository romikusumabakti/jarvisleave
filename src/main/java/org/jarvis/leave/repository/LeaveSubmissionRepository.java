package org.jarvis.leave.repository;

import org.jarvis.leave.model.LeaveSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveSubmissionRepository extends JpaRepository<LeaveSubmission, Long> {

    @Override
    @Query("select u from LeaveSubmission u where u.isDeleted=false")
    List<LeaveSubmission> findAll();
}
