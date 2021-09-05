package org.jarvis.leave.service;

import org.jarvis.leave.model.DetailPengajuanCuti;
import org.jarvis.leave.model.JenisCuti;
import org.jarvis.leave.repository.JenisCutiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class JenisCutiService {

    JenisCutiRepository jenisCutiRepository;

    @Autowired
    public JenisCutiService(JenisCutiRepository jenisCutiRepository) {
        this.jenisCutiRepository = jenisCutiRepository;
    }

    public List<JenisCuti> findAll() {
        List<JenisCuti> jenisCutis = new ArrayList<>();
        jenisCutiRepository.findAll().forEach(jenisCutis::add);
        return jenisCutis;
    }

    public JenisCuti getById(@PathVariable int id) {
        return jenisCutiRepository.getById(id);
    }

    public JenisCuti getById(@PathVariable int id) {
        return jenisCutiRepository.getById(id);
    }

    public JenisCuti saveOrUpdate(@RequestBody JenisCuti jenisCuti) {
        jenisCutiRepository.save(jenisCuti);
        return jenisCuti;
    }

    public void deleteById(@PathVariable int id) {
        JenisCuti jenisCuti = getById(id);
        jenisCuti.setIsDeleted(true);
        jenisCutiRepository.save(jenisCuti);
    }
}