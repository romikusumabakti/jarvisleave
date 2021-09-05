package org.jarvis.leave.repository;

import org.jarvis.leave.model.PengajuanCuti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PengajuanCutiRepository extends JpaRepository<PengajuanCuti, Long> {

    @Override
    @Query("select u from PengajuanCuti u where u.isDeleted=false")
    List<PengajuanCuti> findAll();
}
