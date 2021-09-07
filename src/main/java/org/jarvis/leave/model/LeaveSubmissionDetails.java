package org.jarvis.leave.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class LeaveSubmissionDetails extends Base {

    @ManyToOne
    private LeaveSubmission submission;

    @ManyToOne
    private LeaveType type;

    private LocalDate date;
}
