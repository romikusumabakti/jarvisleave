package org.jarvis.leave.controller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jarvis.leave.dto.LeaveSubmissionDto;
import org.jarvis.leave.model.LeaveSubmission;
import org.jarvis.leave.repository.EmployeeRepository;
import org.jarvis.leave.service.LeaveSubmissionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/leave_submissions")
public class LeaveSubmissionController {

    LeaveSubmissionService leaveSubmissionService;
    EmployeeRepository employeeRepository;
    ModelMapper modelMapper;

    @Autowired
    public LeaveSubmissionController(LeaveSubmissionService leaveSubmissionService, EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.leaveSubmissionService = leaveSubmissionService;
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    private List<LeaveSubmission> findAll() {
        return leaveSubmissionService.findAll();
    }

    @GetMapping("/{id}")
    private LeaveSubmission findById(@PathVariable Long id) {
        return leaveSubmissionService.findById(id);
    }

    private LeaveSubmission map(LeaveSubmissionDto leaveSubmissionDto) {
        return modelMapper.map(leaveSubmissionDto, LeaveSubmission.class);
    }

    @PostMapping()
    private LeaveSubmission save(@RequestBody LeaveSubmissionDto leaveSubmissionDto) {
        return leaveSubmissionService.saveOrUpdate(map(leaveSubmissionDto));
    }

    @PutMapping()
    private LeaveSubmission update(@RequestBody LeaveSubmissionDto leaveSubmissionDto) {
        return leaveSubmissionService.saveOrUpdate(map(leaveSubmissionDto));
    }

    @DeleteMapping("/{id}")
    private void deleteById(@PathVariable Long id) {
        leaveSubmissionService.deleteById(id);
    }

    @GetMapping("/excel")
    private void exportToExcel(HttpServletResponse response) throws IOException {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Karyawan");
        Row header = sheet.createRow(0);

        String[] fields = {"Karyawan", "Pengganti", "Durasi", "Deskripsi", "Telepon", "Alamat", "Status", "HRD"};

        for (int i = 0; i < fields.length; i++) {
            Cell headerCell = header.createCell(i);
            headerCell.setCellValue(fields[i]);
            sheet.setColumnWidth(i, 5000);
        }

        List<LeaveSubmission> leaveSubmissions = leaveSubmissionService.findAll();

        int rowCount = 1;
        for (LeaveSubmission leaveSubmission : leaveSubmissions) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(leaveSubmission.getEmployee().getName());
            row.createCell(1).setCellValue(leaveSubmission.getReplacement().getName());
            row.createCell(2).setCellValue(leaveSubmission.getDuration());
            row.createCell(3).setCellValue(leaveSubmission.getDescription());
            row.createCell(4).setCellValue(leaveSubmission.getPhone());
            row.createCell(5).setCellValue(leaveSubmission.getAddress());
            row.createCell(6).setCellValue(leaveSubmission.getStatus().getName());
            if (leaveSubmission.getHrd() != null) {
                row.createCell(7).setCellValue(leaveSubmission.getHrd().getName());
            }
        }

        response.setContentType("application/octet-stream");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH.mm.ss");
        String currentDateTime = dateTimeFormatter.format(LocalDateTime.now());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Pengajuan cuti " + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }

    @PostMapping("/{id}/approve")
    private void approve(@PathVariable Long id) {
        leaveSubmissionService.approve(id);
    }

    @PostMapping("/{id}/reject")
    private void reject(@PathVariable Long id) {
        leaveSubmissionService.reject(id);
    }
}
