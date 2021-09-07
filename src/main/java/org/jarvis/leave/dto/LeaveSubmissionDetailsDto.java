package org.jarvis.leave.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class LeaveSubmissionDetailsDto {
    private Long id;
    private Long submission;
    private Long type;
    private LocalDate date;
}
