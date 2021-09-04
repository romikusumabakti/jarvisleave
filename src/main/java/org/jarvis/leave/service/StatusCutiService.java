package org.jarvis.leave.service;

import org.jarvis.leave.model.StatusCuti;
import org.jarvis.leave.repository.StatusCutiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatusCutiService {

    StatusCutiRepository statusCutiRepository;

    @Autowired
    public StatusCutiService(StatusCutiRepository statusCutiRepository) {
        this.statusCutiRepository = statusCutiRepository;
    }

    public List<StatusCuti> findAll() {
        List<StatusCuti> statusCutis = new ArrayList<>();
        statusCutiRepository.findAll().forEach(statusCutis::add);
        return statusCutis;
    }

    public StatusCutiRepository getById(@PathVariable int id) {
        return (StatusCutiRepository) statusCutiRepository.getById(id);
    }

    public StatusCuti saveOrUpdate(@RequestBody StatusCuti statusCuti) {
        statusCutiRepository.save(statusCuti);
        return statusCuti;
    }

    public void deleteById(@PathVariable int id) {
        StatusCuti statusCuti = (StatusCuti) getById(id);
        statusCuti.setIsDeleted(true);
        statusCutiRepository.save(statusCuti);
    }
}
