package org.jarvis.leave.controller;

import org.jarvis.leave.dto.StatusCutiDto;
import org.jarvis.leave.model.StatusCuti;
import org.jarvis.leave.service.StatusCutiService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/status_cuti")
public class StatusCutiController {
    
    StatusCutiService statusCutiService;
    ModelMapper modelMapper;

    @Autowired
    public StatusCutiController(StatusCutiService statusCutiService, ModelMapper modelMapper) {
        this.statusCutiService = statusCutiService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    private List<StatusCuti> findAll() {
        return statusCutiService.findAll();
    }

    @GetMapping("/{id}")
    private StatusCuti findById(@PathVariable Long id) {
        return statusCutiService.findById(id);
    }

    private StatusCuti map(StatusCutiDto statusCutiDto) {
        return modelMapper.map(statusCutiDto, StatusCuti.class);
    }

    @PostMapping()
    private StatusCuti save(@RequestBody StatusCutiDto statusCutiDto) {
        return statusCutiService.saveOrUpdate(map(statusCutiDto));
    }

    @PutMapping()
    private StatusCuti update(@RequestBody StatusCutiDto statusCutiDto) {
        return statusCutiService.saveOrUpdate(map(statusCutiDto));
    }

    @DeleteMapping("/{id}")
    private void deleteById(@PathVariable Long id) {
        statusCutiService.deleteById(id);
    }
}
