package org.jarvis.leave.service;

import org.jarvis.leave.model.Role;
import org.jarvis.leave.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Role findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    public Role saveOrUpdate(Role role) {
        roleRepository.save(role);
        return findById(role.getId());
    }

    public void deleteById(Long id) {
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
