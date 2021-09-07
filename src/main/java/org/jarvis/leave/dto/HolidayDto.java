package org.jarvis.leave.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class HolidayDto {
    private String name;
    private String description;
    private LocalDate date;
}
