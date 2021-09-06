package org.jarvis.leave.service;

import org.jarvis.leave.model.DetailPengajuanCuti;
import org.jarvis.leave.repository.DetailPengajuanCutiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class DetailPengajuanCutiService {

    DetailPengajuanCutiRepository detailPengajuanCutiRepository;

    @Autowired
    public DetailPengajuanCutiService(DetailPengajuanCutiRepository detailPengajuanCutiRepository) {
        this.detailPengajuanCutiRepository = detailPengajuanCutiRepository;
    }

    public List<DetailPengajuanCuti> findAll() {
        return detailPengajuanCutiRepository.findAll();
    }

    public DetailPengajuanCuti findById(@PathVariable Long id) {
        return detailPengajuanCutiRepository.findById(id).orElse(null);
    }

    public DetailPengajuanCuti saveOrUpdate(@RequestBody DetailPengajuanCuti detailPengajuanCuti) {
        detailPengajuanCutiRepository.save(detailPengajuanCuti);
        return findById(detailPengajuanCuti.getId());
    }

    public void deleteById(@PathVariable Long id) {
        DetailPengajuanCuti detailPengajuanCuti = findById(id);
        detailPengajuanCuti.setIsDeleted(true);
        detailPengajuanCutiRepository.save(detailPengajuanCuti);
    }

    public void cancelDeleteById(Long id) {
        DetailPengajuanCuti detailPengajuanCuti = findById(id);
        detailPengajuanCuti.setIsDeleted(false);
        detailPengajuanCutiRepository.save(detailPengajuanCuti);
    }
}
