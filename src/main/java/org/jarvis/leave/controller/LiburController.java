package org.jarvis.leave.controller;

import org.jarvis.leave.dto.JenisCutiDto;
import org.jarvis.leave.dto.LiburDto;
import org.jarvis.leave.model.JenisCuti;
import org.jarvis.leave.model.Libur;
import org.jarvis.leave.service.LiburService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class LiburController {
    LiburService liburService;
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public LiburController(LiburService liburService) {
        this.liburService = liburService;
    }

    @GetMapping()
    private List<Libur> findAll() {
        return liburService.findAll();
    }

    @GetMapping("/{id}")
    private Libur findById(@PathVariable int id) {
        return liburService.findById(id);
    }

    @PostMapping()
    private Libur save(@RequestBody LiburDto liburDto) {
        Libur libur = modelMapper.map(liburDto, Libur.class);
        return liburService.saveOrUpdate(libur);
    }

    @PutMapping()
    private Libur update(@RequestBody LiburDto liburDto) {
        Libur libur = modelMapper.map(liburDto, Libur.class);
        return liburService.saveOrUpdate(libur);
    }

    @DeleteMapping("/{id}")
    private void deleteById(@PathVariable int id) {
        liburService.deleteById(id);
    }
}
