package org.jarvis.leave.controller.rest;

import org.jarvis.leave.dto.JenisCutiDto;
import org.jarvis.leave.model.JenisCuti;
import org.jarvis.leave.repository.RoleRepository;
import org.jarvis.leave.service.JenisCutiService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jenis_cuti")
public class JenisCutiController {

    JenisCutiService jenisCutiService;
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public JenisCutiController(JenisCutiService jenisCutiService) {
        this.jenisCutiService = jenisCutiService;
    }

    @GetMapping()
    private List<JenisCuti> findAll() {
        return jenisCutiService.findAll();
    }

    @GetMapping("/{id}")
    private JenisCuti findById(@PathVariable int id) {
        return jenisCutiService.findById(id);
    }

    @PostMapping()
    private JenisCuti save(@RequestBody JenisCutiDto jenisCutiDto) {
        JenisCuti jenisCuti = modelMapper.map(jenisCutiDto, JenisCuti.class);
        return jenisCutiService.saveOrUpdate(jenisCuti);
    }

    @PutMapping()
    private JenisCuti update(@RequestBody JenisCutiDto jenisCutiDto) {
        JenisCuti jenisCuti = modelMapper.map(jenisCutiDto, JenisCuti.class);
        return jenisCutiService.saveOrUpdate(jenisCuti);
    }

    @DeleteMapping("/{id}")
    private void deleteById(@PathVariable int id) {
        jenisCutiService.deleteById(id);
    }
}