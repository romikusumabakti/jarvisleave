package org.jarvis.leave.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class HakCuti extends Base {

    @ManyToOne
    @JoinColumn(name = "employee")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "jenis_cuti")
    private JenisCuti jenisCuti;

    private Integer sisaCuti;
}
