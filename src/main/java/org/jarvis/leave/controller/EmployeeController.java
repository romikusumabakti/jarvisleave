package org.jarvis.leave.controller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jarvis.leave.dto.EmployeeDto;
import org.jarvis.leave.model.Employee;
import org.jarvis.leave.service.DivisionService;
import org.jarvis.leave.service.EmployeeService;
import org.jarvis.leave.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    EmployeeService employeeService;
    RoleService roleService;
    DivisionService divisionService;
    ModelMapper modelMapper;
    PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeController(EmployeeService employeeService, RoleService roleService, DivisionService divisionService, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.employeeService = employeeService;
        this.roleService = roleService;
        this.divisionService = divisionService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping()
    private List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    private Employee findById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    private Employee map(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        employee.setRole(roleService.findById(employeeDto.getRole()));
        employee.setDivision(divisionService.findById(employeeDto.getDivision()));
        return employee;
    }

    @PostMapping()
    private ResponseEntity<?> save(@RequestBody EmployeeDto employeeDto) {

        Map<String, String> errors = new HashMap<>();

        if (employeeService.findByNipUsernameOrEmail(employeeDto.getNip()) != null) {
            errors.put("nip", "NIP sudah dipakai.");
        }

        if (employeeService.findByNipUsernameOrEmail(employeeDto.getUsername()) != null) {
            errors.put("username", "Nama pengguna sudah dipakai.");
        }

        if (employeeService.findByNipUsernameOrEmail(employeeDto.getEmail()) != null) {
            errors.put("email", "Email sudah dipakai.");
        }

        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(errors);
        } else {
            Employee employee = map(employeeDto);
            return ResponseEntity.ok(employee);
        }
    }

    @PutMapping()
    private Employee update(@RequestBody EmployeeDto employeeDto) {

        Employee employee = map(employeeDto);

        if (employeeDto.getPassword() != null) {
            employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
        } else {
            employee.setPassword(employeeService.findById(employeeDto.getId()).getPassword());
        }

        return employeeService.saveOrUpdate(employee);
    }

    @DeleteMapping("/{id}")
    private void deleteById(@PathVariable Long id) {
        employeeService.deleteById(id);
    }

    @PostMapping("/{id}")
    private void cancelDeleteById(@PathVariable Long id) {
        employeeService.cancelDeleteById(id);
    }

    @GetMapping("/excel")
    private void exportToExcel(HttpServletResponse response) throws IOException {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Karyawan");
        Row header = sheet.createRow(0);

        String[] fields = {"NIP", "Nama", "Role", "Divisi", "Email", "Nama pengguna"};

        for (int i = 0; i < fields.length; i++) {
            Cell headerCell = header.createCell(i);
            headerCell.setCellValue(fields[i]);
            sheet.setColumnWidth(i, 5000);
        }

        List<Employee> employees = employeeService.findAll();

        int rowCount = 1;
        for (Employee employee : employees) {
            Row row = sheet.createRow(rowCount++);
            int cellCount = 0;
            row.createCell(cellCount++).setCellValue(employee.getNip());
            row.createCell(cellCount++).setCellValue(employee.getName());
            row.createCell(cellCount++).setCellValue(employee.getRole().getName());
            row.createCell(cellCount++).setCellValue(employee.getDivision().getName());
            row.createCell(cellCount++).setCellValue(employee.getEmail());
            row.createCell(cellCount).setCellValue(employee.getUsername());
        }

        response.setContentType("application/octet-stream");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH.mm.ss");
        String currentDateTime = dateTimeFormatter.format(LocalDateTime.now());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Karyawan " + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }
}