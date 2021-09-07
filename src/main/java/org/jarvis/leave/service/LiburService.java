package org.jarvis.leave.service;

import org.jarvis.leave.model.Libur;
import org.jarvis.leave.repository.LiburRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class LiburService {

    LiburRepository liburRepository;

    @Autowired
    public LiburService(LiburRepository liburRepository) {
        this.liburRepository = liburRepository;
    }

    public List<Libur> findAll() {
        return liburRepository.findAll();
    }

    public Libur findById(@PathVariable Long id) {
        return liburRepository.findById(id).orElse(null);
    }

    public Libur saveOrUpdate(@RequestBody Libur libur) {
        liburRepository.save(libur);
        return findById(libur.getId());
    }

    public void deleteById(@PathVariable Long id) {
        Libur libur = findById(id);
        libur.setIsDeleted(true);
        liburRepository.save(libur);
    }

    public void cancelDeleteById(Long id) {
        Libur libur = findById(id);
        libur.setIsDeleted(false);
        liburRepository.save(libur);
    }
}
