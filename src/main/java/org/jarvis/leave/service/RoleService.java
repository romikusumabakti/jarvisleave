package org.jarvis.leave.service;

import org.jarvis.leave.model.Role;
import org.jarvis.leave.model.StatusCuti;
import org.jarvis.leave.repository.RoleRepository;
import org.jarvis.leave.repository.StatusCutiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAll() {
        List<Role> roles = new ArrayList<>();
        roleRepository.findAll().forEach(roles::add);
        return roles;
    }

    public RoleRepository findById(@PathVariable int id) {
        return (RoleRepository) roleRepository.findById(id).orElse(null);
    }

    public Role saveOrUpdate(@RequestBody Role role) {
        roleRepository.save(role);
        return role;
    }

    public void deleteById(@PathVariable int id) {
        Role role = (Role) getById(id);
        role.setIsDeleted(true);
        roleRepository.save(role);
    }
}
