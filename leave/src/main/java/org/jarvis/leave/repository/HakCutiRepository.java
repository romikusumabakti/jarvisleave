package org.jarvis.leave.repository;

import org.jarvis.leave.model.HakCuti;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HakCutiRepository extends CrudRepository<HakCuti, Integer> {
    @Override
    @Query("select u from HakCuti u where u.isDeleted=false")
    Iterable<HakCuti> findAll();
}
