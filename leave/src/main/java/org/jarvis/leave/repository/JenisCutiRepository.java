package org.jarvis.leave.repository;

import org.jarvis.leave.model.JenisCuti;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JenisCutiRepository extends CrudRepository<JenisCuti, Integer> {

    @Override
    @Query("select u from JenisCuti u where u.isDeleted=false")
    Iterable<JenisCuti> findAll();
}