package org.jarvis.leave.controller;

import org.jarvis.leave.dto.RoleDto;
import org.jarvis.leave.dto.StatusCutiDto;
import org.jarvis.leave.model.Role;
import org.jarvis.leave.model.StatusCuti;
import org.jarvis.leave.service.RoleService;
import org.jarvis.leave.service.StatusCutiService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/status_cuti")
public class StatusCutiController {
    StatusCutiService statusCutiService;
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public StatusCutiController(StatusCutiService statusCutiService) {
        this.statusCutiService = statusCutiService;

    }

    @GetMapping()
    private List<StatusCuti> findAll() {
        return statusCutiService.findAll();
    }

    @GetMapping("/{id}")
    private StatusCuti findById(@PathVariable int id) {
        return statusCutiService.findById(id);
    }

    @PostMapping()
    private StatusCuti save(@RequestBody StatusCutiDto statusCutiDto) {
        StatusCuti statusCuti = modelMapper.map(statusCutiDto, StatusCuti.class);
        return statusCutiService.saveOrUpdate(statusCuti);
    }

    @PutMapping()
    private StatusCuti update(@RequestBody StatusCutiDto statusCutiDto) {
        StatusCuti statusCuti = modelMapper.map(statusCutiDto, StatusCuti.class);
        return statusCutiService.saveOrUpdate(statusCuti);
    }

    @DeleteMapping("/{id}")
    private void deleteById(@PathVariable int id) {
        statusCutiService.deleteById(id);
    }
}
