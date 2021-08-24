package org.jarvis.leave.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class HakCuti extends Base {

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private JenisCuti jenisCuti;

    private Integer sisaCuti;
}
