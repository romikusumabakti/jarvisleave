package org.jarvis.leave.repository;

import org.jarvis.leave.model.Employee;
import org.jarvis.leave.model.LeaveAllowance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveAllowanceRepository extends JpaRepository<LeaveAllowance, Long> {

    @Override
    @Query("select u from LeaveAllowance u where u.isDeleted=false")
    List<LeaveAllowance> findAll();

    List<LeaveAllowance> findAllByEmployee(Employee employee);
}
