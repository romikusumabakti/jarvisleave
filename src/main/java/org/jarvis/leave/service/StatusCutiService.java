package org.jarvis.leave.service;

import org.jarvis.leave.dto.StatusCutiDto;
import org.jarvis.leave.model.StatusCuti;
import org.jarvis.leave.repository.StatusCutiRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class StatusCutiService {

    StatusCutiRepository statusCutiRepository;
    ModelMapper modelMapper;

    @Autowired
    public StatusCutiService(StatusCutiRepository statusCutiRepository, ModelMapper modelMapper) {
        this.statusCutiRepository = statusCutiRepository;
        this.modelMapper = modelMapper;
    }

    public List<StatusCuti> findAll() {
        return statusCutiRepository.findAll();
    }

    public Optional<StatusCuti> findById(@PathVariable Long id) {
        return statusCutiRepository.findById(id);
    }

    public StatusCuti saveOrUpdate(@RequestBody StatusCutiDto statusCutiDto) {
        StatusCuti statusCuti = modelMapper.map(statusCutiDto, StatusCuti.class);
        statusCutiRepository.save(statusCuti);
        return statusCuti;
    }

    public void deleteById(@PathVariable Long id) {
        StatusCuti statusCuti = statusCutiRepository.getById(id);
        statusCuti.setIsDeleted(true);
        statusCutiRepository.save(statusCuti);
    }

    public void cancelDeleteById(Long id) {
        StatusCuti statusCuti = statusCutiRepository.getById(id);
        statusCuti.setIsDeleted(false);
        statusCutiRepository.save(statusCuti);
    }
}
