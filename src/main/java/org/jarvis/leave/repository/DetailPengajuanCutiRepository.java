package org.jarvis.leave.repository;

import org.jarvis.leave.model.DetailPengajuanCuti;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailPengajuanCutiRepository extends CrudRepository<DetailPengajuanCuti, Integer> {

    @Override
    @Query("select u from DetailPengajuanCuti u where u.isDeleted=false")
    Iterable<DetailPengajuanCuti> findAll();
}