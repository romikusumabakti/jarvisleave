package org.jarvis.leave.service;

import org.jarvis.leave.dto.DivisionDto;
import org.jarvis.leave.model.Division;
import org.jarvis.leave.repository.DivisionRepository;
import org.jarvis.leave.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DivisionService {

    DivisionRepository divisionRepository;
    RoleRepository roleRepository;
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public DivisionService(DivisionRepository divisionRepository, RoleRepository roleRepository) {
        this.divisionRepository = divisionRepository;
        this.roleRepository = roleRepository;
    }

    public List<Division> findAll() {
        return divisionRepository.findAll();
    }

    public Division getById(int id) {
        return divisionRepository.getById(id);
    }

    public Division saveOrUpdate(DivisionDto divisionDto) {
        Division division = modelMapper.map(divisionDto, Division.class);
        divisionRepository.save(division);
        return division;
    }

    public void deleteById(int id) {
        Division division = getById(id);
        division.setIsDeleted(true);
        divisionRepository.save(division);
    }

    public void cancelDeleteById(int id) {
        Division division = getById(id);
        division.setIsDeleted(false);
        divisionRepository.save(division);
    }
}