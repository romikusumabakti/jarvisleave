package org.jarvis.leave.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class LeaveAllowance extends Base {

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private LeaveType type;

    private Integer allowance;
}
