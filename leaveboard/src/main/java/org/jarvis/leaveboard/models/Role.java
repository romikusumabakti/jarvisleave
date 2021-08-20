package org.jarvis.leaveboard.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "tbl_role")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false, unique = true)
    private Integer role_id;

    @Column(name = "nama_role")
    private String nama_role;

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
