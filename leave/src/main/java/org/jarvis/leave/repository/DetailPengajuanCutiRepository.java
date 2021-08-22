package org.jarvis.leave.repository;

import org.jarvis.leave.model.DetailPengajuanCuti;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailPengajuanCutiRepository extends CrudRepository<DetailPengajuanCuti, Integer> {

}
