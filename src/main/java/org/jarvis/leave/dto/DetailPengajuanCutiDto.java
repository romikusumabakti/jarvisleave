package org.jarvis.leave.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class DetailPengajuanCutiDto {
    private Long pengajuanCuti;
    private Long jenisCuti;
    private LocalDate tanggal;
}
