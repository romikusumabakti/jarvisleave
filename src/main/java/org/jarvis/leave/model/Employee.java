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

    private String nama;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String nip;

    @ManyToOne
    private Divisi divisi;

    @ManyToOne
    private Role role;

    @Column(unique = true)
    private String username;

    private String password;
}
