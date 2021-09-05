package org.jarvis.leave.repository;

import org.jarvis.leave.model.JenisCuti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JenisCutiRepository extends JpaRepository<JenisCuti, Integer> {

    @Override
    @Query("select u from JenisCuti u where u.isDeleted=false")
    List<JenisCuti> findAll();
}