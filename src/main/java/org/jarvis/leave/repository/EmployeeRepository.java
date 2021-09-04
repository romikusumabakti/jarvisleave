package org.jarvis.leave.repository;

import org.jarvis.leave.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Override
    @Query("select u from Employee u where u.isDeleted=false")
    List<Employee> findAll();

    @Query("select u from Employee u where u.nip = ?1 OR u.username = ?1 OR u.email = ?1")
    Employee getByNipUsernameOrEmail(String NipOrUsername);
}