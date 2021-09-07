INSERT IGNORE INTO role(id, name)
VALUES (1, 'HRD'),
       (2, 'Karyawan');

INSERT IGNORE INTO division(id, name)
VALUES (1, 'PPMB'),
       (2, 'Pendidikan'),
       (3, 'Kerohanian'),
       (4, 'Keasramaan'),
       (5, 'Kesejahteraan'),
       (6, 'Kesehatan');

INSERT IGNORE INTO leave_status(id, name, description)
VALUES (1, 'Draft', 'Draft adalah status cuti di mana pengajuan cuti sudah dibuat tapi belum diajukan'),
       (2, 'Open', 'Open adalah status cuti di mana pengajuan cuti sudah diajukan kepada HRD'),
       (3, 'Approved', 'Approved adalah status cuti di mana pengajuan cuti sudah disetujui oleh HRD'),
       (4, 'Rejected', 'Rejected adalah status cuti di mana pengajuan cuti sudah ditolak oleh HRD'),
       (5, 'Cancelled', 'Cancelled adalah status cuti di mana pengajuan cuti yang sebelumnya open namun digagalkan oleh karyawan itu sendiri');

INSERT IGNORE INTO leave_type(id, name, description)
VALUES (1, 'Tahunan', 'Cuti tahunan adalah cuti yang disediakan oleh perusahaan di luar cuti bersama pemerintah'),
       (2, 'Cuti lintas tahun', 'Cuti lintas tahun adalah sisa cuti tahunan yang tersisa dari tahun sebelumnya dan hanya bisa dipakai pada 6 bulan pertama saja');

INSERT IGNORE INTO employee(id, role_id, nip, name, division_id, email, username, password)
VALUES (1, 1, '02041911003', 'Annisa Nur Wachidah', 1, 'annisanurw@gmail.com', 'annisa',
        '$2a$10$izN/5EDMPOXQ0MZfCI3rRuZWx6V2tN5xE/9gp.jCziREX261xDmme'),
       (2, 1, '02041911007', 'Candra Komara', 2, 'candrakomara@gmail.com', 'candra',
        '$2a$10$izN/5EDMPOXQ0MZfCI3rRuZWx6V2tN5xE/9gp.jCziREX261xDmme'),
       (3, 1, '01021911021', "Dian Nurul Khazaainatu Qurrota A'yun", 1, 'diannurulqa@gmail.com', 'dian',
        '$2a$10$izN/5EDMPOXQ0MZfCI3rRuZWx6V2tN5xE/9gp.jCziREX261xDmme'),
       (4, 1, '02031911067', 'Romi Kusuma Bakti', 3, 'romikusumab@gmail.com', 'romi',
        '$2a$10$izN/5EDMPOXQ0MZfCI3rRuZWx6V2tN5xE/9gp.jCziREX261xDmme'),
       (5, 1, '01021911037', 'Tiara Agustin', 2, 'tiaraagustin@gmail.com', 'tiara',
        '$2a$10$izN/5EDMPOXQ0MZfCI3rRuZWx6V2tN5xE/9gp.jCziREX261xDmme'),
       (6, 2, '02041911018', 'Iqbal Sihabudin', 1, 'iqbalsihab@gmail.com', 'iqbal',
        '$2a$10$izN/5EDMPOXQ0MZfCI3rRuZWx6V2tN5xE/9gp.jCziREX261xDmme'),
       (7, 2, '02041911027', 'Rahma Danil', 2, 'rahmadanil@gmail.com', 'danil',
        '$2a$10$izN/5EDMPOXQ0MZfCI3rRuZWx6V2tN5xE/9gp.jCziREX261xDmme'),
       (8, 2, '02041911002', 'Anjar Maulana', 2, 'anjarmaulana@gmail.com', 'anjar',
        '$2a$10$izN/5EDMPOXQ0MZfCI3rRuZWx6V2tN5xE/9gp.jCziREX261xDmme'),
       (9, 2, '02041911016', 'Hera Elvira', 2, 'heraelvira@gmail.com', 'hera',
        '$2a$10$izN/5EDMPOXQ0MZfCI3rRuZWx6V2tN5xE/9gp.jCziREX261xDmme'),
       (10, 2, '02041911024', 'Muhammad Rizal', 4, 'muhammadrizal@gmail.com', 'rizal',
        '$2a$10$izN/5EDMPOXQ0MZfCI3rRuZWx6V2tN5xE/9gp.jCziREX261xDmme'),
       (11, 2, '02041911022', "Mar'atus Sholikha", 5, 'maratussholikha@gmail.com', 'maratus',
        '$2a$10$izN/5EDMPOXQ0MZfCI3rRuZWx6V2tN5xE/9gp.jCziREX261xDmme'),
       (12, 2, '0204181015', 'Dio Prasetiyo', 6, 'dioprasetiyo@gmail.com', 'dio',
        '$2a$10$izN/5EDMPOXQ0MZfCI3rRuZWx6V2tN5xE/9gp.jCziREX261xDmme'),
       (13, 2, '02041911025', 'Nikita Putri Septemby', 2, 'nikitaputris@gmail.com', 'niki',
        '$2a$10$izN/5EDMPOXQ0MZfCI3rRuZWx6V2tN5xE/9gp.jCziREX261xDmme'),
       (14, 2, '0203181027', 'Ade Radia Rahmat', 3, 'aderadiarahmat@gmail.com', 'ade',
        '$2a$10$izN/5EDMPOXQ0MZfCI3rRuZWx6V2tN5xE/9gp.jCziREX261xDmme'),
       (15, 2, '02041911026', 'Putri Melenia', 1, 'putrimelenia@gmail.com', 'putri',
        '$2a$10$izN/5EDMPOXQ0MZfCI3rRuZWx6V2tN5xE/9gp.jCziREX261xDmme'),
       (16, 2, '02041911012', 'Fatin Firdiansyah', 5, 'fatinfirdiansyah@gmail.com', 'firdi',
        '$2a$10$izN/5EDMPOXQ0MZfCI3rRuZWx6V2tN5xE/9gp.jCziREX261xDmme'),
       (17, 2, '01021911024', 'Elvina Fitriani', 1, 'elvinafitriani@gmail.com', 'elvina',
        '$2a$10$izN/5EDMPOXQ0MZfCI3rRuZWx6V2tN5xE/9gp.jCziREX261xDmme');