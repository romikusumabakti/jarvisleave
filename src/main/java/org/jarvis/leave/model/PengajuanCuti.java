package org.jarvis.leave.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class PengajuanCuti extends Base {

    @ManyToOne
    private Employee employee;

    @ManyToOne
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
