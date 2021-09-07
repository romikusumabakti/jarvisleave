package org.jarvis.leave.service;

import org.jarvis.leave.model.StatusCuti;
import org.jarvis.leave.repository.StatusCutiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class StatusCutiService {

    StatusCutiRepository statusCutiRepository;

    @Autowired
    public StatusCutiService(StatusCutiRepository statusCutiRepository) {
        this.statusCutiRepository = statusCutiRepository;
    }

    public List<StatusCuti> findAll() {
        return statusCutiRepository.findAll();
    }

    public StatusCuti findById(@PathVariable Long id) {
        return statusCutiRepository.findById(id).orElse(null);
    }

    public StatusCuti saveOrUpdate(@RequestBody StatusCuti statusCuti) {
        statusCutiRepository.save(statusCuti);
        return findById(statusCuti.getId());
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
