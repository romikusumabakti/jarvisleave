package org.jarvis.leaveboard.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "tbl_libur")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Libur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "libur_id", nullable = false, unique = true)
    private Integer libur_id;

    @Column(name = "nama_libur")
    private String nama_libur;

    @Column(name = "deskripsi")
    private String deskripsi;

    @Column(name = "tgl_libur")
    private Date tgl_libur;

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
