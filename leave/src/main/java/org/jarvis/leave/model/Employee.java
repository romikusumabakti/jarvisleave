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
public class Employee extends Base {

    @ManyToOne
    @JoinColumn(name = "role")
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
