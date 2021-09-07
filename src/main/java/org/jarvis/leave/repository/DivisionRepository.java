package org.jarvis.leave.repository;

import org.jarvis.leave.model.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DivisionRepository extends JpaRepository<Division, Long> {

    @Override
    @Query("select u from Division u where u.isDeleted=false")
    List<Division> findAll();

    Division findByName(String name);
}