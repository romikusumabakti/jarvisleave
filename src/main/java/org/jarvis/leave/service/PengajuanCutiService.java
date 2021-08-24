package org.jarvis.leave.service;

import org.jarvis.leave.model.PengajuanCuti;
import org.jarvis.leave.repository.PengajuanCutiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class PengajuanCutiService {

    PengajuanCutiRepository pengajuanCutiRepository;

    @Autowired
    public PengajuanCutiService(PengajuanCutiRepository pengajuanCutiRepository) {
        this.pengajuanCutiRepository = pengajuanCutiRepository;
    }

    public List<PengajuanCuti> findAll() {
        List<PengajuanCuti> pengajuanCutis = new ArrayList<>();
        pengajuanCutiRepository.findAll().forEach(pengajuanCutis::add);
        return pengajuanCutis;
    }

    public PengajuanCuti findById(@PathVariable int id) {
        return pengajuanCutiRepository.findById(id).orElse(null);
    }

    public PengajuanCuti saveOrUpdate(@RequestBody PengajuanCuti pengajuanCuti) {
        pengajuanCutiRepository.save(pengajuanCuti);
        return pengajuanCuti;
    }

    public void deleteById(@PathVariable int id) {
        PengajuanCuti pengajuanCuti = findById(id);
        pengajuanCuti.setIsDeleted(true);
        pengajuanCutiRepository.save(pengajuanCuti);
    }

}
