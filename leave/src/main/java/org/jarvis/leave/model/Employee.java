package org.jarvis.leave.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class Employee extends Base {

    @ManyToOne
    private Role role;

    @Column(length = 18)
    private String nip;

    @Column(length = 100)
    private String namaLengkap;

    @Column(length = 50)
    private String divisi;

    @Column(length = 100)
    private String email;

    @Column(length = 50)
    private String username;

    @Column(columnDefinition = "TEXT")
    private String password;
}
