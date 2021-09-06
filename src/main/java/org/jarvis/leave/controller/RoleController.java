package org.jarvis.leave.controller;

import org.jarvis.leave.dto.RoleDto;
import org.jarvis.leave.model.Role;
import org.jarvis.leave.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;

    }

    @GetMapping()
    private List<Role> findAll() {
        return roleService.findAll();
    }

    @GetMapping("/{id}")
    private Role findById(@PathVariable Long id) {
        return roleService.findById(id);
    }

    @PostMapping()
    private Role save(@RequestBody RoleDto roleDto) {
        return roleService.saveOrUpdate(roleDto);
    }

    @PutMapping()
    private Role update(@RequestBody RoleDto roleDto) {
        return roleService.saveOrUpdate(roleDto);
    }

    @DeleteMapping("/{id}")
    private void deleteById(@PathVariable Long id) {
        roleService.deleteById(id);
    }
}
