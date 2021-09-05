package org.jarvis.leave.service;

import org.jarvis.leave.dto.PengajuanCutiDto;
import org.jarvis.leave.model.PengajuanCuti;
import org.jarvis.leave.repository.PengajuanCutiRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class PengajuanCutiService {

    PengajuanCutiRepository pengajuanCutiRepository;
    ModelMapper modelMapper;

    @Autowired
    public PengajuanCutiService(PengajuanCutiRepository pengajuanCutiRepository, ModelMapper modelMapper) {
        this.pengajuanCutiRepository = pengajuanCutiRepository;
        this.modelMapper = modelMapper;
    }

    public List<PengajuanCuti> findAll() {
        return pengajuanCutiRepository.findAll();
    }

    public Optional<PengajuanCuti> findById(@PathVariable Long id) {
        return pengajuanCutiRepository.findById(id);
    }

    public PengajuanCuti saveOrUpdate(@RequestBody PengajuanCutiDto pengajuanCutiDto) {
        PengajuanCuti pengajuanCuti = modelMapper.map(pengajuanCutiDto, PengajuanCuti.class);
        pengajuanCutiRepository.save(pengajuanCuti);
        return pengajuanCuti;
    }

    public void deleteById(@PathVariable Long id) {
        PengajuanCuti pengajuanCuti = pengajuanCutiRepository.getById(id);
        pengajuanCuti.setIsDeleted(true);
        pengajuanCutiRepository.save(pengajuanCuti);
    }

    public void cancelDeleteById(Long id) {
        PengajuanCuti pengajuanCuti = pengajuanCutiRepository.getById(id);
        pengajuanCuti.setIsDeleted(false);
        pengajuanCutiRepository.save(pengajuanCuti);
    }
}
