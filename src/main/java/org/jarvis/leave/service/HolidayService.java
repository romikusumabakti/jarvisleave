package org.jarvis.leave.service;

import org.jarvis.leave.model.Holiday;
import org.jarvis.leave.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidayService {

    HolidayRepository holidayRepository;

    @Autowired
    public HolidayService(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    public List<Holiday> findAll() {
        return holidayRepository.findAll();
    }

    public Holiday findById(Long id) {
        return holidayRepository.findById(id).orElse(null);
    }

    public Holiday saveOrUpdate(Holiday holiday) {
        holidayRepository.save(holiday);
        return findById(holiday.getId());
    }

    public void deleteById(Long id) {
        Holiday holiday = findById(id);
        holiday.setIsDeleted(true);
        holidayRepository.save(holiday);
    }

    public void cancelDeleteById(Long id) {
        Holiday holiday = findById(id);
        holiday.setIsDeleted(false);
        holidayRepository.save(holiday);
    }
}
