package org.jarvis.leave.repository;

import org.jarvis.leave.model.LeaveSubmissionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveSubmissionDetailsRepository extends JpaRepository<LeaveSubmissionDetails, Long> {

    @Override
    @Query("select u from LeaveSubmissionDetails u where u.isDeleted=false")
    List<LeaveSubmissionDetails> findAll();
}
