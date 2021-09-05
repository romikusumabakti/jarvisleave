package org.jarvis.leave.controller;

import org.jarvis.leave.dto.HakCutiDto;
import org.jarvis.leave.model.HakCuti;
import org.jarvis.leave.repository.EmployeeRepository;
import org.jarvis.leave.repository.JenisCutiRepository;
import org.jarvis.leave.service.HakCutiService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/api/hak_cuti")
public class HakCutiController {

    HakCutiService hakCutiService;

    @Autowired
    public HakCutiController(HakCutiService hakCutiService) {
        this.hakCutiService = hakCutiService;
    }

    @GetMapping()
    private List<HakCuti> findAll() {
        return hakCutiService.findAll();
    }

    @GetMapping("/{id}")
    private Optional<HakCuti> findById(@PathVariable Long id) { return hakCutiService.findById(id); }

    @PostMapping()
    private HakCuti save(@RequestBody HakCutiDto hakCutiDto) {
        return hakCutiService.saveOrUpdate(hakCutiDto);
    }

    @PutMapping()
    private HakCuti update(@RequestBody HakCutiDto hakCutiDto) {
        return hakCutiService.saveOrUpdate(hakCutiDto);
    }

    @DeleteMapping("/{id}")
    private void deleteById(@PathVariable Long id) {
        hakCutiService.deleteById(id);
    }
}
