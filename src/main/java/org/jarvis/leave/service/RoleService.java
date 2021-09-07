package org.jarvis.leave.service;

import org.jarvis.leave.model.Role;
import org.jarvis.leave.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class RoleService {

    RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findById(@PathVariable Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    public Role saveOrUpdate(@RequestBody Role role) {
        roleRepository.save(role);
        return findById(role.getId());
    }

    public void deleteById(@PathVariable Long id) {
        Role role = findById(id);
        role.setIsDeleted(true);
        roleRepository.save(role);
    }

    public void cancelDeleteById(Long id) {
        Role role = findById(id);
        role.setIsDeleted(false);
        roleRepository.save(role);
    }
}
