package org.jarvis.leaveboard.dto;

import java.util.Date;

public class StatusCutiDTO {
    Integer status_cuti_id;
    String status_cuti;
    String deskripsi;
    String created_by;
    Date created_date;
    String last_modified_by;
    Date last_modified_date;
    Boolean is_deleted;

    public Integer getStatus_cuti_id() {
        return status_cuti_id;
    }

    public void setStatus_cuti_id(Integer status_cuti_id) {
        this.status_cuti_id = status_cuti_id;
    }

    public String getStatus_cuti() {
        return status_cuti;
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

    public void setStatus_cuti(String status_cuti) {
        this.status_cuti = status_cuti;
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
