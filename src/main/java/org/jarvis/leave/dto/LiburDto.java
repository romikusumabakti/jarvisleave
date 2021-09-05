package org.jarvis.leave.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class LiburDto {
    private String nama;
    private String deskripsi;
    private LocalDate tanggal;
}
