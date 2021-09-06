package org.jarvis.leave.controller;

import org.jarvis.leave.dto.DivisionDto;
import org.jarvis.leave.model.Division;
import org.jarvis.leave.repository.RoleRepository;
import org.jarvis.leave.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/divisions")
public class DivisionController {

    DivisionService divisionService;
    RoleRepository roleRepository;

    @Autowired
    public DivisionController(DivisionService divisionService, RoleRepository roleRepository) {
        this.divisionService = divisionService;
        this.roleRepository = roleRepository;
    }

    @GetMapping()
    private List<Division> findAll() {
        return divisionService.findAll();
    }

    @GetMapping("/{id}")
    private Optional<Division> findById(@PathVariable Long id) {
        return divisionService.findById(id);
    }

    @PostMapping()
    private Division save(@RequestBody DivisionDto divisionDto) {
        return divisionService.saveOrUpdate(divisionDto);
    }

    @PutMapping()
    private Division update(@RequestBody DivisionDto divisionDto) {
        return divisionService.saveOrUpdate(divisionDto);
    }

    @DeleteMapping("/{id}")
    private void deleteById(@PathVariable Long id) {
        divisionService.deleteById(id);
    }

    @PostMapping("/{id}")
    private void cancelDeleteById(@PathVariable Long id) {
        divisionService.cancelDeleteById(id);
    }
}