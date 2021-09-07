package org.jarvis.leave.controller;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
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
import org.springframework.web.multipart.MultipartFile;

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
        if (employeeDto.getPassword() != null) {
            employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
        }
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
            return ResponseEntity.ok(employeeService.saveOrUpdate(employee));
        }
    }

    @PutMapping()
    private Employee update(@RequestBody EmployeeDto employeeDto) {

        Employee employee = map(employeeDto);

        if (employeeDto.getPassword() == null) {
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
            row.createCell(0).setCellValue(employee.getNip());
            row.createCell(1).setCellValue(employee.getName());
            row.createCell(2).setCellValue(employee.getRole().getName());
            row.createCell(3).setCellValue(employee.getDivision().getName());
            row.createCell(4).setCellValue(employee.getEmail());
            row.createCell(5).setCellValue(employee.getUsername());
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

    @PostMapping("/excel")
    public List<Employee> importFromExcel(@RequestParam("file") MultipartFile excelFile) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook(excelFile.getInputStream());
        XSSFSheet sheet = workbook.getSheetAt(0);
        DataFormatter formatter = new DataFormatter();

        for(int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            XSSFRow row = sheet.getRow(i);
            Employee employee = new Employee();
            employee.setNip(formatter.formatCellValue(row.getCell(0)));
            employee.setName(row.getCell(1).getStringCellValue());
            employee.setRole(roleService.findByName(row.getCell(2).getStringCellValue()));
            employee.setDivision(divisionService.findByName(row.getCell(3).getStringCellValue()));
            employee.setEmail(row.getCell(4).getStringCellValue());
            employee.setUsername(row.getCell(5).getStringCellValue());
            employee.setPassword(passwordEncoder.encode(formatter.formatCellValue(row.getCell(6))));
            employeeService.saveOrUpdate(employee);
        }

        return employeeService.findAll();
    }
}