package org.jarvis.leave.repository;

import org.jarvis.leave.model.Libur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LiburRepository extends JpaRepository<Libur, Long> {

    @Override
    @Query("select u from Libur u where u.isDeleted=false")
    List<Libur> findAll();
}
