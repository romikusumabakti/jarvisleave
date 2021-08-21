package org.jarvis.leave.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class JenisCutiDTO {
    Integer id;
    String jenis_cuti;
    String deskripsi;
    String created_by;
    LocalDate created_date;
    String last_modified_by;
    Date last_modified_date;
    Boolean is_deleted;
}
