package org.jarvis.leave.controller;

import org.jarvis.leave.dto.JenisCutiDto;
import org.jarvis.leave.model.JenisCuti;
import org.jarvis.leave.service.JenisCutiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jenis_cuti")
public class JenisCutiController {

    JenisCutiService jenisCutiService;

    @Autowired
    public JenisCutiController(JenisCutiService jenisCutiService) {
        this.jenisCutiService = jenisCutiService;
    }

    @GetMapping()
    private List<JenisCuti> findAll() {
        return jenisCutiService.findAll();
    }

    @GetMapping("/{id}")
    private Optional<JenisCuti> findById(@PathVariable Long id) {
        return jenisCutiService.findById(id);
    }

    @PostMapping()
    private JenisCuti save(@RequestBody JenisCutiDto jenisCutiDto) {
        return jenisCutiService.saveOrUpdate(jenisCutiDto);
    }

    @PutMapping()
    private JenisCuti update(@RequestBody JenisCutiDto jenisCutiDto) {
        return jenisCutiService.saveOrUpdate(jenisCutiDto);
    }

    @DeleteMapping("/{id}")
    private void deleteById(@PathVariable Long id) {
        jenisCutiService.deleteById(id);
    }
}