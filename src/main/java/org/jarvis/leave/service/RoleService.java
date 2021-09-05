package org.jarvis.leave.service;

import org.jarvis.leave.dto.RoleDto;
import org.jarvis.leave.model.Role;
import org.jarvis.leave.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    RoleRepository roleRepository;
    ModelMapper modelMapper;

    @Autowired
    public RoleService(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Optional<Role> findById(@PathVariable Long id) {
        return roleRepository.findById(id);
    }

    public Role saveOrUpdate(@RequestBody RoleDto roleDto) {
        Role role = modelMapper.map(roleDto, Role.class);
        roleRepository.save(role);
        return role;
    }

    public void deleteById(@PathVariable Long id) {
        Role role = roleRepository.getById(id);
        role.setIsDeleted(true);
        roleRepository.save(role);
    }

    public void cancelDeleteById(Long id) {
        Role role = roleRepository.getById(id);
        role.setIsDeleted(false);
        roleRepository.save(role);
    }
}
