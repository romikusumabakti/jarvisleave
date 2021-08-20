package org.jarvis.leaveboard.dto;

import java.util.Date;

public class LiburDTO {
    Integer libur_id;
    String nama_libur;
    String deskripsi;
    Date tgl_libur;
    String created_by;
    Date created_date;
    String last_modified_by;
    Date last_modified_date;
    Boolean is_deleted;

    public Integer getLibur_id() {
        return libur_id;
    }

    public Date getTgl_libur() {
        return tgl_libur;
    }

    public void setTgl_libur(Date tgl_libur) {
        this.tgl_libur = tgl_libur;
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

    public void setLibur_id(Integer libur_id) {
        this.libur_id = libur_id;
    }

    public String getNama_libur() {
        return nama_libur;
    }

    public void setNama_libur(String nama_libur) {
        this.nama_libur = nama_libur;
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
