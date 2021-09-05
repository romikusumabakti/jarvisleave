package org.jarvis.leave.controller;

import org.jarvis.leave.dto.PengajuanCutiDto;
import org.jarvis.leave.dto.RoleDto;
import org.jarvis.leave.model.PengajuanCuti;
import org.jarvis.leave.model.Role;
import org.jarvis.leave.repository.EmployeeRepository;
import org.jarvis.leave.service.PengajuanCutiService;
import org.jarvis.leave.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    RoleService roleService;
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;

    }

    @GetMapping()
    private List<Role> findAll() {
        return roleService.findAll();
    }

    @GetMapping("/{id}")
    private Role findById(@PathVariable int id) {
        return roleService.findById(id);
    }

    @PostMapping()
    private Role save(@RequestBody RoleDto roleDto) {
        Role role = modelMapper.map(roleDto, Role.class);
        return roleService.saveOrUpdate(role);
    }

    @PutMapping()
    private Role update(@RequestBody RoleDto roleDto) {
        Role role = modelMapper.map(roleDto, Role.class);
        return roleService.saveOrUpdate(role);
    }

    @DeleteMapping("/{id}")
    private void deleteById(@PathVariable int id) {
        roleService.deleteById(id);
    }
}
