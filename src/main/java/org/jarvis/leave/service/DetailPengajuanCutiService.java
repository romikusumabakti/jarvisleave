package org.jarvis.leave.service;

import org.jarvis.leave.model.DetailPengajuanCuti;
import org.jarvis.leave.model.Employee;
import org.jarvis.leave.model.HakCuti;
import org.jarvis.leave.repository.DetailPengajuanCutiRepository;
import org.jarvis.leave.repository.HakCutiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetailPengajuanCutiService {
    DetailPengajuanCutiRepository detailPengajuanCutiRepository;

    @Autowired
    public DetailPengajuanCutiService(DetailPengajuanCutiRepository detailPengajuanCutiRepository) {
        this.detailPengajuanCutiRepository = detailPengajuanCutiRepository;
    }

    public List<DetailPengajuanCuti> findAll() {
        List<DetailPengajuanCuti> detailPengajuanCutis = new ArrayList<>();
        detailPengajuanCutiRepository.findAll().forEach(detailPengajuanCutis::add);
        return detailPengajuanCutis;
    }

    public DetailPengajuanCuti findById(@PathVariable int id) {
        return detailPengajuanCutiRepository.findById(id).orElse(null);
    }

    public DetailPengajuanCuti getById(@PathVariable int id) {
        return detailPengajuanCutiRepository.getById(id);
    }

    public DetailPengajuanCuti saveOrUpdate(@RequestBody DetailPengajuanCuti detailPengajuanCuti) {
        detailPengajuanCutiRepository.save(detailPengajuanCuti);
        return detailPengajuanCuti;
    }

    public void deleteById(@PathVariable int id) {
        DetailPengajuanCuti detailPengajuanCuti = getById(id);
        detailPengajuanCuti.setIsDeleted(true);
        detailPengajuanCutiRepository.save(detailPengajuanCuti);
    }
}
