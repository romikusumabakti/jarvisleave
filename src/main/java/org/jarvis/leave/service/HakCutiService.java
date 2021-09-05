package org.jarvis.leave.service;

import org.jarvis.leave.dto.HakCutiDto;
import org.jarvis.leave.model.HakCuti;
import org.jarvis.leave.repository.EmployeeRepository;
import org.jarvis.leave.repository.HakCutiRepository;
import org.jarvis.leave.repository.JenisCutiRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class HakCutiService {

    HakCutiRepository hakCutiRepository;
    EmployeeRepository employeeRepository;
    JenisCutiRepository jenisCutiRepository;
    ModelMapper modelMapper;

    @Autowired
    public HakCutiService(HakCutiRepository hakCutiRepository, EmployeeRepository employeeRepository, JenisCutiRepository jenisCutiRepository, ModelMapper modelMapper) {
        this.hakCutiRepository = hakCutiRepository;
        this.employeeRepository = employeeRepository;
        this.jenisCutiRepository = jenisCutiRepository;
        this.modelMapper = modelMapper;
    }

    public List<HakCuti> findAll() {
        return hakCutiRepository.findAll();
    }

    public Optional<HakCuti> findById(@PathVariable Long id) {
        return hakCutiRepository.findById(id);
    }

    public HakCuti saveOrUpdate(@RequestBody HakCutiDto hakCutiDto) {
        HakCuti hakCuti = modelMapper.map(hakCutiDto, HakCuti.class);
        hakCuti.setEmployee(employeeRepository.findById(hakCutiDto.getEmployee()).orElse(null));
        hakCuti.setJenisCuti(jenisCutiRepository.findById(hakCutiDto.getJenisCuti()).orElse(null));
        hakCutiRepository.save(hakCuti);
        return hakCuti;
    }

    public void deleteById(@PathVariable Long id) {
        HakCuti hakCuti = hakCutiRepository.getById(id);
        hakCuti.setIsDeleted(true);
        hakCutiRepository.save(hakCuti);
    }

    public void cancelDeleteById(Long id) {
        HakCuti hakCuti = hakCutiRepository.getById(id);
        hakCuti.setIsDeleted(false);
        hakCutiRepository.save(hakCuti);
    }
}
