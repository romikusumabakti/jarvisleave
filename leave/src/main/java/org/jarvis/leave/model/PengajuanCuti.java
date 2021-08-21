package org.jarvis.leave.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class PengajuanCuti extends Base {

    @ManyToOne
    @JoinColumn(name = "employee")
    private Employee employee;

    @ManyToOne
    @JoinColumn (name = "status_cuti")
    private StatusCuti statusCuti;

    private Integer penggantiId;

    private Integer hrdId;

    @Column(columnDefinition = "TEXT")
    private String alamat;

    @Column(length = 15)
    private String noTelepon;

    @Column(columnDefinition = "TEXT")
    private String keterangan;

    private Integer lamaCuti;
}
