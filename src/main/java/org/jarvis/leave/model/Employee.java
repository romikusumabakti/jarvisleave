package org.jarvis.leave.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class Employee extends Base {

    @Column(unique = true)
    private String nip;

    private String name;

    @ManyToOne
    private Role role;

    @ManyToOne
    private Division division;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;

    @JsonIgnore
    private String password;
}
