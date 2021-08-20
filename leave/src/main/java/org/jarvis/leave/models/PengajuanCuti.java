package org.jarvis.leaveboard.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "tbl_pengajuan_cuti")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class PengajuanCuti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pengajuan_cuti_id", nullable = false, unique = true)
    private Integer pengajuan_cuti_id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employeeId;

    @ManyToOne
    @JoinColumn (name = "status_cuti_id")
    private StatusCuti statusCuti;

    @Column(name = "pengganti_id")
    private Integer pengganti_id;

    @Column(name = "hrd_id")
    private Integer hrd_id;

    @Column(name = "alamat")
    private String alamat;

    @Column(name = "no_telp")
    private String no_telp;

    @Column(name = "keterangan")
    private String keterangan;

    @Column(name = "lama_cuti")
    private Integer lama_cuti;

    @Column(name = "created_by")
    private String created_by;

    @Column(name = "created_date")
    private Date created_date;

    @Column(name = "last_modified_by")
    private String last_modified_by;

    @Column(name = "last_modified_date")
    private Date last_modified_date;

    @Column(name = "is_deleted")
    private String is_deleted;
}
