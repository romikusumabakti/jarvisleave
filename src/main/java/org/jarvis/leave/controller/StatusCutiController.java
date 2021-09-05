package org.jarvis.leave.controller;

import org.jarvis.leave.dto.StatusCutiDto;
import org.jarvis.leave.model.StatusCuti;
import org.jarvis.leave.service.StatusCutiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/status_cuti")
public class StatusCutiController {
    
    StatusCutiService statusCutiService;

    @Autowired
    public StatusCutiController(StatusCutiService statusCutiService) {
        this.statusCutiService = statusCutiService;

    }

    @GetMapping()
    private List<StatusCuti> findAll() {
        return statusCutiService.findAll();
    }

    @GetMapping("/{id}")
    private Optional<StatusCuti> findById(@PathVariable Long id) {
        return statusCutiService.findById(id);
    }

    @PostMapping()
    private StatusCuti save(@RequestBody StatusCutiDto statusCutiDto) {
        return statusCutiService.saveOrUpdate(statusCutiDto);
    }

    @PutMapping()
    private StatusCuti update(@RequestBody StatusCutiDto statusCutiDto) {
        return statusCutiService.saveOrUpdate(statusCutiDto);
    }

    @DeleteMapping("/{id}")
    private void deleteById(@PathVariable Long id) {
        statusCutiService.deleteById(id);
    }
}
