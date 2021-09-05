package org.jarvis.leave.controller;

import org.jarvis.leave.dto.DivisionDto;
import org.jarvis.leave.model.Division;
import org.jarvis.leave.repository.RoleRepository;
import org.jarvis.leave.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/division")
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
    private Division getById(@PathVariable int id) {
        return divisionService.getById(id);
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
    private void deleteById(@PathVariable int id) {
        divisionService.deleteById(id);
    }

    @PostMapping("/{id}")
    private void cancelDeleteById(@PathVariable int id) {
        divisionService.cancelDeleteById(id);
    }
}