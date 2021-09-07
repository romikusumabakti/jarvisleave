package org.jarvis.leave.dto;

import lombok.Getter;

@Getter
public class LeaveSubmissionDto {
    private Long id;
    private Long employee;
    private Long replacement;
    private Integer duration;
    private String description;
    private String phone;
    private String address;
    private Long status;
    private Long approver;
}
