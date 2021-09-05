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
import java.util.Optional;

@Service
public class JenisCutiService {

    JenisCutiRepository jenisCutiRepository;
    ModelMapper modelMapper;

    @Autowired
    public JenisCutiService(JenisCutiRepository jenisCutiRepository, ModelMapper modelMapper) {
        this.jenisCutiRepository = jenisCutiRepository;
        this.modelMapper = modelMapper;
    }

    public List<JenisCuti> findAll() {
        return jenisCutiRepository.findAll();
    }

    public Optional<JenisCuti> findById(@PathVariable Long id) {
        return jenisCutiRepository.findById(id);
    }

    public JenisCuti saveOrUpdate(@RequestBody JenisCutiDto jenisCutiDto) {
        JenisCuti jenisCuti = modelMapper.map(jenisCutiDto, JenisCuti.class);
        jenisCutiRepository.save(jenisCuti);
        return jenisCuti;
    }

    public void deleteById(@PathVariable Long id) {
        JenisCuti jenisCuti = jenisCutiRepository.getById(id);
        jenisCuti.setIsDeleted(true);
        jenisCutiRepository.save(jenisCuti);
    }

    public void cancelDeleteById(Long id) {
        JenisCuti jenisCuti = jenisCutiRepository.getById(id);
        jenisCuti.setIsDeleted(false);
        jenisCutiRepository.save(jenisCuti);
    }
}