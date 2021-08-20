package org.jarvis.leaveboard.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "tbl_employee")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false, unique = true)
    private Integer employee_id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role roleId;

    @Column(name = "nip")
    private String nip;

    @Column(name = "nama_lengkap")
    private String nama_lengkap;

    @Column(name = "divisi")
    private String divisi;

    @Column(name = "email")
    private String email;

    @Column(name = "user_name")
    private String user_name;

    @Column(name = "password")
    private String password;

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
