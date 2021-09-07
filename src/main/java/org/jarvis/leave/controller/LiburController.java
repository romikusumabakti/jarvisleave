package org.jarvis.leave.controller;

import org.jarvis.leave.dto.LiburDto;
import org.jarvis.leave.dto.RoleDto;
import org.jarvis.leave.model.Libur;
import org.jarvis.leave.model.Role;
import org.jarvis.leave.service.LiburService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class LiburController {

    LiburService liburService;
    ModelMapper modelMapper;

    @Autowired
    public LiburController(LiburService liburService, ModelMapper modelMapper) {
        this.liburService = liburService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    private List<Libur> findAll() {
        return liburService.findAll();
    }

    @GetMapping("/{id}")
    private Libur findById(@PathVariable Long id) {
        return liburService.findById(id);
    }

    private Libur map(LiburDto liburDto) {
        return modelMapper.map(liburDto, Libur.class);
    }

    @PostMapping()
    private Libur save(@RequestBody LiburDto liburDto) {
        return liburService.saveOrUpdate(map(liburDto));
    }

    @PutMapping()
    private Libur update(@RequestBody LiburDto liburDto) {
        return liburService.saveOrUpdate(map(liburDto));
    }

    @DeleteMapping("/{id}")
    private void deleteById(@PathVariable Long id) {
        liburService.deleteById(id);
    }
}
