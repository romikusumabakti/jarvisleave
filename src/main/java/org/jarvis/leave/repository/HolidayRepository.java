package org.jarvis.leave.repository;

import org.jarvis.leave.model.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {

    @Override
    @Query("select u from Holiday u where u.isDeleted=false")
    List<Holiday> findAll();
}
