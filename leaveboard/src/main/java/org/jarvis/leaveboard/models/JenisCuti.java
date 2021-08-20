package org.jarvis.leaveboard.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "tbl_jenis_cuti")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class JenisCuti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jenis_cuti_id", nullable = false, unique = true)
    private Integer jenis_cuti_id;

    @Column(name = "jenis_cuti")
    private String jenis_cuti;

    @Column(name = "deskripsi")
    private String deskripsi;

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
