package org.jarvis.leave.service;

import org.jarvis.leave.model.HakCuti;
import org.jarvis.leave.repository.EmployeeRepository;
import org.jarvis.leave.repository.HakCutiRepository;
import org.jarvis.leave.repository.JenisCutiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class HakCutiService {

    HakCutiRepository hakCutiRepository;
    EmployeeRepository employeeRepository;
    JenisCutiRepository jenisCutiRepository;

    @Autowired
    public HakCutiService(HakCutiRepository hakCutiRepository, EmployeeRepository employeeRepository, JenisCutiRepository jenisCutiRepository) {
        this.hakCutiRepository = hakCutiRepository;
        this.employeeRepository = employeeRepository;
        this.jenisCutiRepository = jenisCutiRepository;
    }

    public List<HakCuti> findAll() {
        return hakCutiRepository.findAll();
    }

    public HakCuti findById(@PathVariable Long id) {
        return hakCutiRepository.findById(id).orElse(null);
    }

    public HakCuti saveOrUpdate(@RequestBody HakCuti hakCuti) {
        hakCutiRepository.save(hakCuti);
        return findById(hakCuti.getId());
    }

    public void deleteById(@PathVariable Long id) {
        HakCuti hakCuti = findById(id);
        hakCuti.setIsDeleted(true);
        hakCutiRepository.save(hakCuti);
    }

    public void cancelDeleteById(Long id) {
        HakCuti hakCuti = findById(id);
        hakCuti.setIsDeleted(false);
        hakCutiRepository.save(hakCuti);
    }
}
