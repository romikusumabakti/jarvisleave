package org.jarvis.leave.repository;

import org.jarvis.leave.model.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveStatusRepository extends JpaRepository<LeaveStatus, Long> {

    @Override
    @Query("select u from LeaveStatus u where u.isDeleted=false")
    List<LeaveStatus> findAll();
}
