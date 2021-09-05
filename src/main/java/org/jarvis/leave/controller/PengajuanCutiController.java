package org.jarvis.leave.controller;

import org.jarvis.leave.dto.PengajuanCutiDto;
import org.jarvis.leave.model.PengajuanCuti;
import org.jarvis.leave.repository.EmployeeRepository;
import org.jarvis.leave.service.PengajuanCutiService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pengajuan_cuti")
public class PengajuanCutiController {

    PengajuanCutiService pengajuanCutiService;
    EmployeeRepository employeeRepository;
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public PengajuanCutiController(PengajuanCutiService pengajuanCutiService, EmployeeRepository employeeRepository) {
        this.pengajuanCutiService = pengajuanCutiService;
        this.employeeRepository = employeeRepository;

    }

    @GetMapping()
    private List<PengajuanCuti> findAll() {
        return pengajuanCutiService.findAll();
    }

    @GetMapping("/{id}")
    private Optional<PengajuanCuti> findById(@PathVariable Long id) {
        return pengajuanCutiService.findById(id);
    }

    @PostMapping()
    private PengajuanCuti save(@RequestBody PengajuanCutiDto pengajuanCutiDto) {
        return pengajuanCutiService.saveOrUpdate(pengajuanCutiDto);
    }

    @PutMapping()
    private PengajuanCuti update(@RequestBody PengajuanCutiDto pengajuanCutiDto) {
        return pengajuanCutiService.saveOrUpdate(pengajuanCutiDto);
    }

    @DeleteMapping("/{id}")
    private void deleteById(@PathVariable Long id) {
        pengajuanCutiService.deleteById(id);
    }
}
