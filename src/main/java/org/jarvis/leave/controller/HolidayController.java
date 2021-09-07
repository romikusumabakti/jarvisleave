package org.jarvis.leave.controller;

import org.jarvis.leave.dto.HolidayDto;
import org.jarvis.leave.model.Holiday;
import org.jarvis.leave.service.HolidayService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/holidays")
public class HolidayController {

    HolidayService holidayService;
    ModelMapper modelMapper;

    @Autowired
    public HolidayController(HolidayService holidayService, ModelMapper modelMapper) {
        this.holidayService = holidayService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    private List<Holiday> findAll() {
        return holidayService.findAll();
    }

    @GetMapping("/{id}")
    private Holiday findById(@PathVariable Long id) {
        return holidayService.findById(id);
    }

    private Holiday map(HolidayDto holidayDto) {
        return modelMapper.map(holidayDto, Holiday.class);
    }

    @PostMapping()
    private Holiday save(@RequestBody HolidayDto holidayDto) {
        return holidayService.saveOrUpdate(map(holidayDto));
    }

    @PutMapping()
    private Holiday update(@RequestBody HolidayDto holidayDto) {
        return holidayService.saveOrUpdate(map(holidayDto));
    }

    @DeleteMapping("/{id}")
    private void deleteById(@PathVariable Long id) {
        holidayService.deleteById(id);
    }
}
