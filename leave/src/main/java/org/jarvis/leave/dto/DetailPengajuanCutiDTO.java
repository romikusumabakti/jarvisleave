package org.jarvis.leaveboard.dto;

import java.util.Date;

public class DetailPengajuanCutiDTO {
    Integer detail_pengajuan_cuti_id;
    Integer pengajuan_cuti_id;
    Integer jenis_cuti_id;
    Date tgl_cuti;
    String created_by;
    Date created_date;
    String last_modified_by;
    Date last_modified_date;
    Boolean is_deleted;

    public Integer getDetail_pengajuan_cuti_id() {
        return detail_pengajuan_cuti_id;
    }

    public void setDetail_pengajuan_cuti_id(Integer detail_pengajuan_cuti_id) {
        this.detail_pengajuan_cuti_id = detail_pengajuan_cuti_id;
    }

    public Integer getPengajuan_cuti_id() {
        return pengajuan_cuti_id;
    }

    public void setPengajuan_cuti_id(Integer pengajuan_cuti_id) {
        this.pengajuan_cuti_id = pengajuan_cuti_id;
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

    public Integer getJenis_cuti_id() {
        return jenis_cuti_id;
    }

    public void setJenis_cuti_id(Integer jenis_cuti_id) {
        this.jenis_cuti_id = jenis_cuti_id;
    }

    public Date getTgl_cuti() {
        return tgl_cuti;
    }

    public void setTgl_cuti(Date tgl_cuti) {
        this.tgl_cuti = tgl_cuti;
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
