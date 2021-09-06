package org.jarvis.leave.service;

import org.jarvis.leave.dto.JenisCutiDto;
import org.jarvis.leave.model.JenisCuti;
import org.jarvis.leave.repository.JenisCutiRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class JenisCutiService {

    JenisCutiRepository jenisCutiRepository;
    ModelMapper modelMapper;

    @Autowired
    public JenisCutiService(JenisCutiRepository jenisCutiRepository) {
        this.jenisCutiRepository = jenisCutiRepository;
    }

    public List<JenisCuti> findAll() {
        return jenisCutiRepository.findAll();
    }

    public JenisCuti findById(@PathVariable Long id) {
        return jenisCutiRepository.findById(id).orElse(null);
    }

    public JenisCuti saveOrUpdate(@RequestBody JenisCuti jenisCuti) {
        jenisCutiRepository.save(jenisCuti);
        return findById(jenisCuti.getId());
    }

    public void deleteById(@PathVariable Long id) {
        JenisCuti jenisCuti = findById(id);
        jenisCuti.setIsDeleted(true);
        jenisCutiRepository.save(jenisCuti);
    }

    public void cancelDeleteById(Long id) {
        JenisCuti jenisCuti = findById(id);
        jenisCuti.setIsDeleted(false);
        jenisCutiRepository.save(jenisCuti);
    }
}