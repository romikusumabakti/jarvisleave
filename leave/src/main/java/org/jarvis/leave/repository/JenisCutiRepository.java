package org.jarvis.leave.repository;

import org.jarvis.leave.model.JenisCuti;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JenisCutiRepository extends CrudRepository<JenisCuti, Integer> {}