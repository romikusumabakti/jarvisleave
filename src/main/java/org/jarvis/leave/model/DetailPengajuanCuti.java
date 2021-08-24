package org.jarvis.leave.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class DetailPengajuanCuti extends Base {

    @ManyToOne
    private PengajuanCuti pengajuanCuti;

    @ManyToOne
    private JenisCuti jenisCuti;

    private LocalDate tanggal;
}
