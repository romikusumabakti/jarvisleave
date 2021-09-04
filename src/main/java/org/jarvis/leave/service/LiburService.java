package org.jarvis.leave.service;

import org.jarvis.leave.model.DetailPengajuanCuti;
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
        return (List<Libur>) liburRepository.findAll();
    }

    public Libur findById(@PathVariable int id) {
        return liburRepository.findById(id).orElse(null);
    }

    public Libur getById(@PathVariable int id) {
        return liburRepository.getById(id);
    }

    public Libur saveOrUpdate(@RequestBody Libur libur) {
        liburRepository.save(libur);
        return libur;
    }

    public void deleteById(@PathVariable int id) {
        Libur libur = getById(id);
        libur.setIsDeleted(true);
        liburRepository.save(libur);
    }

}
