package org.jarvis.leave.controller;

import org.jarvis.leave.dto.JenisCutiDto;
import org.jarvis.leave.model.JenisCuti;
import org.jarvis.leave.service.JenisCutiService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jenis_cuti")
public class JenisCutiController {

    JenisCutiService jenisCutiService;
    ModelMapper modelMapper;

    @Autowired
    public JenisCutiController(JenisCutiService jenisCutiService, ModelMapper modelMapper) {
        this.jenisCutiService = jenisCutiService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    private List<JenisCuti> findAll() {
        return jenisCutiService.findAll();
    }

    @GetMapping("/{id}")
    private JenisCuti findById(@PathVariable Long id) {
        return jenisCutiService.findById(id);
    }

    private JenisCuti map(JenisCutiDto jenisCutiDto) {
        return modelMapper.map(jenisCutiDto, JenisCuti.class);
    }

    @PostMapping()
    private JenisCuti save(@RequestBody JenisCutiDto jenisCutiDto) {
        return jenisCutiService.saveOrUpdate(map(jenisCutiDto));
    }

    @PutMapping()
    private JenisCuti update(@RequestBody JenisCutiDto jenisCutiDto) {
        return jenisCutiService.saveOrUpdate(map(jenisCutiDto));
    }

    @DeleteMapping("/{id}")
    private void deleteById(@PathVariable Long id) {
        jenisCutiService.deleteById(id);
    }
}