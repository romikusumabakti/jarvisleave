package org.jarvis.leave.controller;

import org.jarvis.leave.dto.PengajuanCutiDto;
import org.jarvis.leave.model.PengajuanCuti;
import org.jarvis.leave.repository.EmployeeRepository;
import org.jarvis.leave.service.PengajuanCutiService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pengajuan_cuti")
public class PengajuanCutiController {

    PengajuanCutiService pengajuanCutiService;
    EmployeeRepository employeeRepository;
    ModelMapper modelMapper;

    @Autowired
    public PengajuanCutiController(PengajuanCutiService pengajuanCutiService, EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.pengajuanCutiService = pengajuanCutiService;
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    private List<PengajuanCuti> findAll() {
        return pengajuanCutiService.findAll();
    }

    @GetMapping("/{id}")
    private PengajuanCuti findById(@PathVariable Long id) {
        return pengajuanCutiService.findById(id);
    }

    private PengajuanCuti map(PengajuanCutiDto pengajuanCutiDto) {
        return modelMapper.map(pengajuanCutiDto, PengajuanCuti.class);
    }

    @PostMapping()
    private PengajuanCuti save(@RequestBody PengajuanCutiDto pengajuanCutiDto) {
        return pengajuanCutiService.saveOrUpdate(map(pengajuanCutiDto));
    }

    @PutMapping()
    private PengajuanCuti update(@RequestBody PengajuanCutiDto pengajuanCutiDto) {
        return pengajuanCutiService.saveOrUpdate(map(pengajuanCutiDto));
    }

    @DeleteMapping("/{id}")
    private void deleteById(@PathVariable Long id) {
        pengajuanCutiService.deleteById(id);
    }
}
