package org.jarvis.leaveboard.dto;

import java.util.Date;

public class JenisCutiDTO {
    Integer jenis_cuti_id;
    String jenis_cuti;
    String deskripsi;
    String created_by;
    Date created_date;
    String last_modified_by;
    Date last_modified_date;
    Boolean is_deleted;

    public Integer getJenis_cuti_id() {
        return jenis_cuti_id;
    }

    public void setJenis_cuti_id(Integer jenis_cuti_id) {
        this.jenis_cuti_id = jenis_cuti_id;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Date getLast_modified_date() {
        return last_modified_date;
    }

    public void setLast_modified_date(Date last_modified_date) {
        this.last_modified_date = last_modified_date;
    }

    public String getJenis_cuti() {
        return jenis_cuti;
    }

    public void setJenis_cuti(String jenis_cuti) {
        this.jenis_cuti = jenis_cuti;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getLast_modified_by() {
        return last_modified_by;
    }

    public void setLast_modified_by(String last_modified_by) {
        this.last_modified_by = last_modified_by;
    }

    public Boolean getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
    }
}
