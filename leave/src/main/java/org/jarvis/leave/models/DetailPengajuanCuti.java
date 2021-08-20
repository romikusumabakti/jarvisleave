package org.jarvis.leaveboard.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_detail_pengajuan_cuti")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class DetailPengajuanCuti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_pengajuan_cuti_id", nullable = false, unique = true)
    private Integer detail_pengajuan_cuti_id;

    @ManyToOne
    @JoinColumn (name = "pengajuan_cuti_id")
    private PengajuanCuti pengajuanCuti;

    @ManyToOne
    @JoinColumn (name = "jenis_cuti_id")
    private JenisCuti jenisCuti;

    @Column(name = "tgl_cuti")
    private Date tgl_cuti;

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
