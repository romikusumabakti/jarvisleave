package org.jarvis.leave.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class LiburDTO {
    Integer id;
    String nama_libur;
    String deskripsi;
    Date tgl_libur;
    String created_by;
    Date created_date;
    String last_modified_by;
    Date last_modified_date;
    Boolean is_deleted;
}
