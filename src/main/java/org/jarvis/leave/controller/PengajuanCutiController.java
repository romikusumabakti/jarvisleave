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
    private PengajuanCuti findById(@PathVariable int id) {
        return pengajuanCutiService.findById(id);
    }

    @PostMapping()
    private PengajuanCuti save(@RequestBody PengajuanCutiDto pengajuanCutiDto) {
        PengajuanCuti pengajuanCuti = modelMapper.map(pengajuanCutiDto, PengajuanCuti.class);
        pengajuanCuti.setEmployee(employeeRepository.findById(pengajuanCutiDto.getEmployee_id()).orElse(null));
        return pengajuanCutiService.saveOrUpdate(pengajuanCuti);
    }

    @PutMapping()
    private PengajuanCuti update(@RequestBody PengajuanCutiDto pengajuanCutiDto) {
        PengajuanCuti pengajuanCuti = modelMapper.map(pengajuanCutiDto, PengajuanCuti.class);
        pengajuanCuti.setEmployee(employeeRepository.findById(pengajuanCutiDto.getEmployee_id()).orElse(null));
        return pengajuanCutiService.saveOrUpdate(pengajuanCuti);
    }

    @DeleteMapping("/{id}")
    private void deleteById(@PathVariable int id) {
        pengajuanCutiService.deleteById(id);
    }
}
