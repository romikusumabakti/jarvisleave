package org.jarvis.leave.repository;

import org.jarvis.leave.model.HakCuti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HakCutiRepository extends JpaRepository<HakCuti, Integer> {

    @Override
    @Query("select u from HakCuti u where u.isDeleted=false")
    List<HakCuti> findAll();
}
