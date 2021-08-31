package org.jarvis.leave.repository;

import org.jarvis.leave.model.Libur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiburRepository extends CrudRepository<Libur, Integer> {
    @Override
    @Query("select u from Libur u where u.isDeleted=false")
    Iterable<Libur> findAll();

}
