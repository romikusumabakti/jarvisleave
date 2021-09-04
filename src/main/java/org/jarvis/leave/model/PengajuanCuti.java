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

    @ManyToOne
    private Employee pengganti;

    @ManyToOne
    private Employee approver;

    private String alamat;

    private String noTelepon;

    private String keterangan;

    private Integer lamaCuti;
}
