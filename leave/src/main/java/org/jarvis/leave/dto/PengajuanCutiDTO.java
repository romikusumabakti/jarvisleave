package org.jarvis.leaveboard.dto;

import java.util.Date;

public class PengajuanCutiDTO {
    Integer pengajuan_cuti_id;
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

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public Integer getStatus_cuti_id() {
        return status_cuti_id;
    }

    public void setStatus_cuti_id(Integer status_cuti_id) {
        this.status_cuti_id = status_cuti_id;
    }

    public Integer getPenganti_id() {
        return penganti_id;
    }

    public void setPenganti_id(Integer penganti_id) {
        this.penganti_id = penganti_id;
    }

    public Integer getHrd_id() {
        return hrd_id;
    }

    public void setHrd_id(Integer hrd_id) {
        this.hrd_id = hrd_id;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Integer getLama_cuti() {
        return lama_cuti;
    }

    public void setLama_cuti(Integer lama_cuti) {
        this.lama_cuti = lama_cuti;
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
