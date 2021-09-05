package org.jarvis.leave.service;

import org.jarvis.leave.dto.LiburDto;
import org.jarvis.leave.model.Libur;
import org.jarvis.leave.repository.LiburRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class LiburService {

    LiburRepository liburRepository;
    ModelMapper modelMapper;

    @Autowired
    public LiburService(LiburRepository liburRepository, ModelMapper modelMapper) {
        this.liburRepository = liburRepository;
        this.modelMapper = modelMapper;
    }

    public List<Libur> findAll() {
        return liburRepository.findAll();
    }

    public Optional<Libur> findById(@PathVariable Long id) {
        return liburRepository.findById(id);
    }

    public Libur saveOrUpdate(@RequestBody LiburDto liburDto) {
        Libur libur = modelMapper.map(liburDto, Libur.class);
        liburRepository.save(libur);
        return libur;
    }

    public void deleteById(@PathVariable Long id) {
        Libur libur = liburRepository.getById(id);
        libur.setIsDeleted(true);
        liburRepository.save(libur);
    }

    public void cancelDeleteById(Long id) {
        Libur libur = liburRepository.getById(id);
        libur.setIsDeleted(false);
        liburRepository.save(libur);
    }
}
