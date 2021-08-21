package org.jarvis.leave.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class DetailPengajuanCuti extends Base {

    @ManyToOne
    @JoinColumn(name = "pengajuan_cuti")
    private PengajuanCuti pengajuanCuti;

    @ManyToOne
    @JoinColumn (name = "jenis_cuti")
    private JenisCuti jenisCuti;

    private LocalDate tanggal;
}
