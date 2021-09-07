package org.jarvis.leave.controller;

import org.jarvis.leave.dto.DivisionDto;
import org.jarvis.leave.model.Division;
import org.jarvis.leave.repository.RoleRepository;
import org.jarvis.leave.service.DivisionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/divisions")
public class DivisionController {

    DivisionService divisionService;
    RoleRepository roleRepository;
    ModelMapper modelMapper;

    @Autowired
    public DivisionController(DivisionService divisionService, RoleRepository roleRepository, ModelMapper modelMapper) {
        this.divisionService = divisionService;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    private List<Division> findAll() {
        return divisionService.findAll();
    }

    @GetMapping("/{id}")
    private Division findById(@PathVariable Long id) {
        return divisionService.findById(id);
    }

    private Division map(DivisionDto divisionDto) {
        return modelMapper.map(divisionDto, Division.class);
    }

    @PostMapping()
    private Division save(@RequestBody DivisionDto divisionDto) {
        return divisionService.saveOrUpdate(map(divisionDto));
    }

    @PutMapping()
    private Division update(@RequestBody DivisionDto divisionDto) {
        return divisionService.saveOrUpdate(map(divisionDto));
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