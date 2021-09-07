package org.jarvis.leave.repository;

import org.jarvis.leave.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Override
    @Query("select u from Role u where u.isDeleted=false")
    List<Role> findAll();

    Role findByName(String name);
}