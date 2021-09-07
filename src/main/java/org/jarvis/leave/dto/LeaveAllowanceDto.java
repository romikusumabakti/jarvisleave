package org.jarvis.leave.dto;

import lombok.Getter;

@Getter
public class LeaveAllowanceDto {
    private Long id;
    private Long employee;
    private Long type;
    private Long allowance;
}
