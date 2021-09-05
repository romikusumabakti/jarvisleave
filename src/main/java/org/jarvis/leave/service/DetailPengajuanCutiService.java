package org.jarvis.leave.service;

import org.jarvis.leave.model.DetailPengajuanCuti;
import org.jarvis.leave.repository.DetailPengajuanCutiRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class DetailPengajuanCutiService {

    DetailPengajuanCutiRepository detailPengajuanCutiRepository;
    ModelMapper modelMapper;

    @Autowired
    public DetailPengajuanCutiService(DetailPengajuanCutiRepository detailPengajuanCutiRepository, ModelMapper modelMapper) {
        this.detailPengajuanCutiRepository = detailPengajuanCutiRepository;
        this.modelMapper = modelMapper;
    }

    public List<DetailPengajuanCuti> findAll() {
        return detailPengajuanCutiRepository.findAll();
    }

    public Optional<DetailPengajuanCuti> findById(@PathVariable Long id) {
        return detailPengajuanCutiRepository.findById(id);
    }

    public DetailPengajuanCuti saveOrUpdate(@RequestBody DetailPengajuanCuti detailPengajuanCuti) {
        detailPengajuanCutiRepository.save(detailPengajuanCuti);
        return detailPengajuanCuti;
    }

    public void deleteById(@PathVariable Long id) {
        DetailPengajuanCuti detailPengajuanCuti = detailPengajuanCutiRepository.getById(id);
        detailPengajuanCuti.setIsDeleted(true);
        detailPengajuanCutiRepository.save(detailPengajuanCuti);
    }

    public void cancelDeleteById(Long id) {
        DetailPengajuanCuti detailPengajuanCuti = detailPengajuanCutiRepository.getById(id);
        detailPengajuanCuti.setIsDeleted(false);
        detailPengajuanCutiRepository.save(detailPengajuanCuti);
    }
}
