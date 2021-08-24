package org.jarvis.leave.controller.rest;

import org.jarvis.leave.dto.HakCutiDto;
import org.jarvis.leave.model.HakCuti;
import org.jarvis.leave.repository.EmployeeRepository;
import org.jarvis.leave.repository.JenisCutiRepository;
import org.jarvis.leave.service.HakCutiService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/hak_cuti")
public class HakCutiController {

    HakCutiService hakCutiService;
    EmployeeRepository employeeRepository;
    JenisCutiRepository jenisCutiRepository;
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public HakCutiController(HakCutiService hakCutiService) {
        this.hakCutiService = hakCutiService;
    }

    @GetMapping()
    private List<HakCuti> findAll() {
        return hakCutiService.findAll();
    }

    @GetMapping("/{id}")
    private HakCuti findById(@PathVariable int id) { return hakCutiService.findById(id); }

    @PostMapping()
    private HakCuti save(@RequestBody HakCutiDto hakCutiDto) {
        HakCuti hakCuti = modelMapper.map(hakCutiDto, HakCuti.class);
        hakCuti.setEmployee(employeeRepository.findById(hakCutiDto.getEmployee()).orElse(null));
        hakCuti.setJenisCuti(jenisCutiRepository.findById(hakCutiDto.getJenisCuti()).orElse(null));
        return hakCutiService.saveOrUpdate(hakCuti);
    }

    @PutMapping()
    private HakCuti update(@RequestBody HakCutiDto hakCutiDto) {
        HakCuti hakCuti = modelMapper.map(hakCutiDto, HakCuti.class);
        hakCuti.setEmployee(employeeRepository.findById(hakCutiDto.getEmployee()).orElse(null));
        hakCuti.setJenisCuti(jenisCutiRepository.findById(hakCutiDto.getJenisCuti()).orElse(null));
        return hakCutiService.saveOrUpdate(hakCuti);
    }

    @DeleteMapping("/{id}")
    private void deleteById(@PathVariable int id) {
        hakCutiService.deleteById(id);
    }
}
