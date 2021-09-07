package org.jarvis.leave.controller;

import org.jarvis.leave.dto.DetailPengajuanCutiDto;
import org.jarvis.leave.model.DetailPengajuanCuti;
import org.jarvis.leave.repository.JenisCutiRepository;
import org.jarvis.leave.repository.PengajuanCutiRepository;
import org.jarvis.leave.service.DetailPengajuanCutiService;
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
    ModelMapper modelMapper;

    @Autowired
    public DetailPengajuanCutiController(DetailPengajuanCutiService detailPengajuanCutiService, PengajuanCutiRepository pengajuanCutiRepository, JenisCutiRepository jenisCutiRepository, ModelMapper modelMapper) {
        this.detailPengajuanCutiService = detailPengajuanCutiService;
        this.pengajuanCutiRepository = pengajuanCutiRepository;
        this.jenisCutiRepository = jenisCutiRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    private List<DetailPengajuanCuti> findAll() {
        return detailPengajuanCutiService.findAll();
    }

    @GetMapping("/{id}")
    private DetailPengajuanCuti findById(@PathVariable Long id) {
        return detailPengajuanCutiService.findById(id);
    }

    private DetailPengajuanCuti map(DetailPengajuanCutiDto detailPengajuanCutiDto) {
        return modelMapper.map(detailPengajuanCutiDto, DetailPengajuanCuti.class);
    }

    @PostMapping()
    private DetailPengajuanCuti save(@RequestBody DetailPengajuanCutiDto detailPengajuanCutiDto) {
        return detailPengajuanCutiService.saveOrUpdate(map(detailPengajuanCutiDto));
    }

    @PutMapping()
    private DetailPengajuanCuti update(@RequestBody DetailPengajuanCutiDto detailPengajuanCutiDto) {
        return detailPengajuanCutiService.saveOrUpdate(map(detailPengajuanCutiDto));
    }

    @DeleteMapping("/{id}")
    private void deleteById(@PathVariable Long id) {
        detailPengajuanCutiService.deleteById(id);
    }
}
