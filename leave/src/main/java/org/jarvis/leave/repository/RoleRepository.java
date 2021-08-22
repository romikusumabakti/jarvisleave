package org.jarvis.leave.repository;

import org.jarvis.leave.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

    @Override
    @Query("select u from Role u where u.isDeleted=false")
    Iterable<Role> findAll();
}