package org.jarvis.leave.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EmployeeDTO {
    Integer id;
    Integer role_id;
    String nip;
    String nama_lengkap;
    String divisi;
    String email;
    String username;
    String password;
    String created_by;
    Date created_date;
    String last_modified_by;
    Date last_modified_date;
    Boolean is_deleted;
}
