package org.jarvis.leave.repository;

import org.jarvis.leave.model.PengajuanCuti;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PengajuanCutiRepository extends CrudRepository<PengajuanCuti, Integer> {

    @Override
    @Query("select u from PengajuanCuti u where u.isDeleted=false")
    Iterable<PengajuanCuti> findAll();
}
