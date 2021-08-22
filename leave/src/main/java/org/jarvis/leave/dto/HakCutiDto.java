package org.jarvis.leave.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class HakCutiDto {
    Integer id;
    Integer employee_id;
    Integer jenis_cuti_id;
    Integer sisa_cuti;
    String created_by;
    Date created_date;
    String last_modified_by;
    Date last_modified_date;
    Boolean is_deleted;
}
