package org.jarvis.leave.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Libur extends Base {

    @Column(length = 100)
    private String nama;

    @Column(columnDefinition = "TEXT")
    private String deskripsi;

    private LocalDate tanggal;
}
