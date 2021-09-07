package org.jarvis.leave.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class LeaveSubmission extends Base {

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Employee replacement;

    private Integer duration;

    private String description;

    private String phone;

    private String address;

    @ManyToOne
    private LeaveStatus status;

    @ManyToOne
    private Employee approver;
}
