package org.jarvis.leave.service;

import org.jarvis.leave.model.PengajuanCuti;
import org.jarvis.leave.repository.PengajuanCutiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PengajuanCutiService {

    PengajuanCutiRepository pengajuanCutiRepository;

    @Autowired
    public PengajuanCutiService(PengajuanCutiRepository pengajuanCutiRepository) {
        this.pengajuanCutiRepository = pengajuanCutiRepository;
    }

    public List<PengajuanCuti> findAll() {
        return pengajuanCutiRepository.findAll();
    }

    public PengajuanCuti findById(@PathVariable Long id) {
        return pengajuanCutiRepository.findById(id).orElse(null);
    }

    public PengajuanCuti saveOrUpdate(@RequestBody PengajuanCuti pengajuanCuti) {
        pengajuanCutiRepository.save(pengajuanCuti);
        return findById(pengajuanCuti.getId());
    }

    public void deleteById(@PathVariable Long id) {
        PengajuanCuti pengajuanCuti = findById(id);
        pengajuanCuti.setIsDeleted(true);
        pengajuanCutiRepository.save(pengajuanCuti);
    }

    public void cancelDeleteById(Long id) {
        PengajuanCuti pengajuanCuti = findById(id);
        pengajuanCuti.setIsDeleted(false);
        pengajuanCutiRepository.save(pengajuanCuti);
    }
}
