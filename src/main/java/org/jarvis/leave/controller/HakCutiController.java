package org.jarvis.leave.controller;

import org.jarvis.leave.dto.HakCutiDto;
import org.jarvis.leave.model.HakCuti;
import org.jarvis.leave.service.EmployeeService;
import org.jarvis.leave.service.HakCutiService;
import org.jarvis.leave.service.JenisCutiService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/hak_cuti")
public class HakCutiController {

    HakCutiService hakCutiService;
    EmployeeService employeeService;
    JenisCutiService jenisCutiService;
    ModelMapper modelMapper;

    @Autowired
    public HakCutiController(HakCutiService hakCutiService) {
        this.hakCutiService = hakCutiService;
    }

    @GetMapping()
    private List<HakCuti> findAll() {
        return hakCutiService.findAll();
    }

    @GetMapping("/{id}")
    private HakCuti findById(@PathVariable Long id) { return hakCutiService.findById(id); }

    @PostMapping()
    private HakCuti save(@RequestBody HakCutiDto hakCutiDto) {
        HakCuti hakCuti = modelMapper.map(hakCutiDto, HakCuti.class);
        hakCuti.setEmployee(employeeService.findById(hakCutiDto.getEmployee()));
        hakCuti.setJenisCuti(jenisCutiService.findById(hakCutiDto.getJenisCuti()));
        return hakCutiService.saveOrUpdate(hakCuti);
    }

    @PutMapping()
    private HakCuti update(@RequestBody HakCutiDto hakCutiDto) {
        HakCuti hakCuti = modelMapper.map(hakCutiDto, HakCuti.class);
        hakCuti.setEmployee(employeeService.findById(hakCutiDto.getEmployee()));
        hakCuti.setJenisCuti(jenisCutiService.findById(hakCutiDto.getJenisCuti()));
        return hakCutiService.saveOrUpdate(hakCuti);
    }

    @DeleteMapping("/{id}")
    private void deleteById(@PathVariable Long id) {
        hakCutiService.deleteById(id);
    }
}
