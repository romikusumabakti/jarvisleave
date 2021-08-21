package org.jarvis.leave.repository;

import org.jarvis.leave.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    @Override
    @Query("select e from Employee e where e.isDeleted=false")
    Iterable<Employee> findAll();
}