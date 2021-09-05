package org.jarvis.leave.controller;

import org.jarvis.leave.dto.LiburDto;
import org.jarvis.leave.model.Libur;
import org.jarvis.leave.service.LiburService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class LiburController {

    LiburService liburService;

    @Autowired
    public LiburController(LiburService liburService) {
        this.liburService = liburService;
    }

    @GetMapping()
    private List<Libur> findAll() {
        return liburService.findAll();
    }

    @GetMapping("/{id}")
    private Optional<Libur> findById(@PathVariable Long id) {
        return liburService.findById(id);
    }

    @PostMapping()
    private Libur save(@RequestBody LiburDto liburDto) {
        return liburService.saveOrUpdate(liburDto);
    }

    @PutMapping()
    private Libur update(@RequestBody LiburDto liburDto) {
        return liburService.saveOrUpdate(liburDto);
    }

    @DeleteMapping("/{id}")
    private void deleteById(@PathVariable Long id) {
        liburService.deleteById(id);
    }
}
