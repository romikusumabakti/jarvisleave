package org.jarvis.leave.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class JenisCuti extends Base {

    @Column(length = 50)
    private String jenis_cuti;

    @Column(columnDefinition = "TEXT")
    private String deskripsi;
}
