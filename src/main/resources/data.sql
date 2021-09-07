INSERT
IGNORE INTO role(id, name)
VALUES (1, 'HRD'),
       (2, 'Karyawan');

INSERT
IGNORE INTO division(id, name)
VALUES (1, 'PPMB'),
       (2, 'Pendidikan'),
       (3, 'Kerohanian'),
       (4, 'Keasramaan'),
       (5, 'Kesejahteraan'),
       (6, 'Kesehatan');

INSERT
IGNORE INTO leave_type(id, name, description)
VALUES (1, 'Tahunan', 'Cuti yang disediakan oleh perusahaan di luar cuti bersama pemerintah.'),
       (2, 'Lintas tahun', 'Sisa cuti tahunan yang tersisa dari tahun sebelumnya dan hanya bisa dipakai pada 6 bulan pertama saja.');

INSERT
IGNORE INTO leave_status(id, name, description)
VALUES (1, 'Draf', 'Pengajuan cuti sudah dibuat tapi belum diajukan.'),
       (2, 'Tertunda', 'Pengajuan cuti sudah diajukan kepada HRD.'),
       (3, 'Disetujui', 'Pengajuan cuti sudah disetujui oleh HRD.'),
       (4, 'Ditolak', 'Pengajuan cuti sudah ditolak oleh HRD.'),
       (5, 'Dibatalkan', 'Pengajuan cuti dibatalkan oleh karyawan itu sendiri.');

INSERT
IGNORE INTO holiday(id, name, description, date)
VALUES (1, 'Maulid Nabi Muhammad SAW', 'Peringatan Maulid Nabi Muhammad SAW.', '2021-10-20'),
       (2, "Isra' Mi'raj Nabi Muhammad SAW", "Peringatan Isra' Mi'raj Nabi Muhammad SAW.", '2022-03-28');

INSERT
IGNORE INTO employee(id, role_id, nip, name, division_id, email, username, password)
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

INSERT
IGNORE INTO leave_allowance(id, employee_id, type_id, allowance)
VALUES (1, 1, 1, 12),
       (3, 2, 1, 12),
       (5, 3, 1, 12),
       (7, 4, 1, 12),
       (9, 5, 1, 12),
       (11, 6, 1, 12),
       (13, 7, 1, 12),
       (15, 8, 1, 12),
       (17, 9, 1, 12),
       (19, 10, 1, 12),
       (21, 11, 1, 12),
       (23, 12, 1, 12),
       (25, 13, 1, 12),
       (27, 14, 1, 12),
       (29, 15, 1, 12),
       (31, 16, 1, 12),
       (33, 17, 1, 12);

INSERT
IGNORE INTO leave_allowance(id, employee_id, type_id, allowance)
VALUES (2, 1, 2, 0),
       (4, 2, 2, 0),
       (6, 3, 2, 0),
       (8, 4, 2, 0),
       (10, 5, 2, 0),
       (12, 6, 2, 0),
       (14, 7, 2, 0),
       (16, 8, 2, 0),
       (18, 9, 2, 0),
       (20, 10, 2, 0),
       (22, 11, 2, 0),
       (24, 12, 2, 0),
       (26, 13, 2, 0),
       (28, 14, 2, 0),
       (30, 15, 2, 0),
       (32, 16, 2, 0),
       (34, 17, 2, 0);

INSERT
IGNORE INTO leave_submission(id, employee_id, replacement_id, duration, description, phone, address, status_id)
VALUES (1, FLOOR(6 + (RAND() * 6)), FLOOR(12 + (RAND() * 6)), 1, 'Mudik', '081234567890', 'Bandung', 2),
       (2, FLOOR(6 + (RAND() * 6)), FLOOR(12 + (RAND() * 6)), 2, 'Lelah', '081234567890', 'Bandung', 2),
       (3, FLOOR(6 + (RAND() * 6)), FLOOR(12 + (RAND() * 6)), 3, 'Refreshing', '081234567890', 'Bandung', 2);

INSERT
IGNORE INTO leave_submission_details(id, submission_id, type_id, date)
VALUES (1, 1, 1, '2021-10-01'),
       (2, 2, 1, '2021-11-01'),
       (3, 3, 1, '2021-12-01');