package org.jarvis.leave.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PengajuanCutiDTO {
    Integer id;
    Integer employee_id;
    Integer status_cuti_id;
    Integer penganti_id;
    Integer hrd_id;
    String alamat;
    String no_telp;
    String keterangan;
    Integer lama_cuti;
    String created_by;
    Date created_date;
    String last_modified_by;
    Date last_modified_date;
    Boolean is_deleted;
}
