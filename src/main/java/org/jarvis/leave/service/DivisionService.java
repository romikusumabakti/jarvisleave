package org.jarvis.leave.service;

import org.jarvis.leave.model.Division;
import org.jarvis.leave.repository.DivisionRepository;
import org.jarvis.leave.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DivisionService {

    DivisionRepository divisionRepository;
    RoleRepository roleRepository;

    @Autowired
    public DivisionService(DivisionRepository divisionRepository, RoleRepository roleRepository) {
        this.divisionRepository = divisionRepository;
        this.roleRepository = roleRepository;
    }

    public List<Division> findAll() {
        return divisionRepository.findAll();
    }

    public Division findById(Long id) {
        return divisionRepository.findById(id).orElse(null);
    }

    public Division findByName(String name) {
        return divisionRepository.findByName(name);
    }

    public Division saveOrUpdate(Division division) {
        divisionRepository.save(division);
        return findById(division.getId());
    }

    public void deleteById(Long id) {
        Division division = findById(id);
        division.setIsDeleted(true);
        divisionRepository.save(division);
    }

    public void cancelDeleteById(Long id) {
        Division division = findById(id);
        division.setIsDeleted(false);
        divisionRepository.save(division);
    }
}