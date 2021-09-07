package org.jarvis.leave.repository;

import org.jarvis.leave.model.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveTypeRepository extends JpaRepository<LeaveType, Long> {

    @Override
    @Query("select u from LeaveType u where u.isDeleted=false")
    List<LeaveType> findAll();
}