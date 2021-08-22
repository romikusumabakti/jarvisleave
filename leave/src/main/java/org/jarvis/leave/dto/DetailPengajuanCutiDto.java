package org.jarvis.leave.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
public class DetailPengajuanCutiDto {
    Integer id;
    Integer pengajuan_cuti_id;
    Integer jenis_cuti_id;
    LocalDate tgl_cuti;
    String created_by;
    Instant created_date;
    String last_modified_by;
    Instant last_modified_date;
    Boolean is_deleted;
}
