package org.jarvis.leave.controller;

import org.jarvis.leave.dto.DetailPengajuanCutiDto;
import org.jarvis.leave.dto.EmployeeDto;
import org.jarvis.leave.model.DetailPengajuanCuti;
import org.jarvis.leave.model.Employee;
import org.jarvis.leave.repository.JenisCutiRepository;
import org.jarvis.leave.repository.PengajuanCutiRepository;
import org.jarvis.leave.repository.RoleRepository;
import org.jarvis.leave.service.DetailPengajuanCutiService;
import org.jarvis.leave.service.EmployeeService;
import org.jarvis.leave.service.JenisCutiService;
import org.jarvis.leave.service.PengajuanCutiService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detail_pengajuan_cuti")
public class DetailPengajuanCutiController {
    DetailPengajuanCutiService detailPengajuanCutiService;
    PengajuanCutiRepository pengajuanCutiRepository;
    JenisCutiRepository jenisCutiRepository;
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public DetailPengajuanCutiController(DetailPengajuanCutiService detailPengajuanCutiService, PengajuanCutiRepository pengajuanCutiRepository, JenisCutiRepository jenisCutiRepository) {
        this.detailPengajuanCutiService = detailPengajuanCutiService;
        this.pengajuanCutiRepository = pengajuanCutiRepository;
        this.jenisCutiRepository = jenisCutiRepository;
    }

    @GetMapping()
    private List<DetailPengajuanCuti> findAll() {
        return detailPengajuanCutiService.findAll();
    }

    @GetMapping("/{id}")
    private DetailPengajuanCuti findById(@PathVariable int id) {
        return detailPengajuanCutiService.getById(id);
    }

    @PostMapping()
    private DetailPengajuanCuti save(@RequestBody DetailPengajuanCutiDto detailPengajuanCutiDto) {
        DetailPengajuanCuti detailPengajuanCuti = modelMapper.map(detailPengajuanCutiDto, DetailPengajuanCuti.class);
        detailPengajuanCuti.setPengajuanCuti(pengajuanCutiRepository.findById(detailPengajuanCutiDto.getPengajuan_cuti_id()).orElse(null));
        detailPengajuanCuti.setJenisCuti(jenisCutiRepository.findById(detailPengajuanCutiDto.getJenis_cuti_id()).orElse(null));
        return detailPengajuanCutiService.saveOrUpdate(detailPengajuanCuti);
    }

    @PutMapping()
    private DetailPengajuanCuti update(@RequestBody DetailPengajuanCutiDto detailPengajuanCutiDto) {
        DetailPengajuanCuti detailPengajuanCuti = modelMapper.map(detailPengajuanCutiDto, DetailPengajuanCuti.class);
        detailPengajuanCuti.setPengajuanCuti(pengajuanCutiRepository.getById(detailPengajuanCutiDto.getPengajuan_cuti_id()));
        detailPengajuanCuti.setJenisCuti(jenisCutiRepository.getById(detailPengajuanCutiDto.getJenis_cuti_id()));
        return detailPengajuanCutiService.saveOrUpdate(detailPengajuanCuti);
    }

    @DeleteMapping("/{id}")
    private void deleteById(@PathVariable int id) {
        detailPengajuanCutiService.deleteById(id);
    }
}
