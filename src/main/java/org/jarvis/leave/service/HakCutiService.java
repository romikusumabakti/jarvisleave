package org.jarvis.leave.service;

import org.jarvis.leave.model.DetailPengajuanCuti;
import org.jarvis.leave.model.HakCuti;
import org.jarvis.leave.repository.HakCutiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class HakCutiService {
    HakCutiRepository hakCutiRepository;

    @Autowired
    public HakCutiService(HakCutiRepository hakCutiRepository) {
        this.hakCutiRepository = hakCutiRepository;
    }

    public List<HakCuti> findAll() {
        List<HakCuti> hakCutis = new ArrayList<>();
        hakCutiRepository.findAll().forEach(hakCutis::add);
        return hakCutis;
    }

    public HakCuti getById(@PathVariable int id) {
        return hakCutiRepository.getById(id);
    }

    public HakCuti getById(@PathVariable int id) {
        return hakCutiRepository.getById(id);
    }

    public HakCuti saveOrUpdate(@RequestBody HakCuti hakCuti) {
        hakCutiRepository.save(hakCuti);
        return hakCuti;
    }

    public void deleteById(@PathVariable int id) {
        HakCuti hakCuti = getById(id);
        hakCuti.setIsDeleted(true);
        hakCutiRepository.save(hakCuti);
    }
}
