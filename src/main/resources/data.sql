INSERT IGNORE INTO role(id, nama, created_by)
VALUES (1, 'HRD', 'Dian'),
       (2, 'Karyawan', 'Dian');

INSERT IGNORE INTO status_cuti(id, status, deskripsi, created_by)
VALUES (1, 'Draft', 'Draft adalah status cuti di mana pengajuan cuti sudah dibuat tapi belum diajukan', 'Dian'),
       (2, 'Open', 'Open adalah status cuti di mana pengajuan cuti sudah diajukan kepada HRD', 'Dian'),
       (3, 'Approved', 'Approved adalah status cuti di mana pengajuan cuti sudah disetujui oleh HRD', 'Dian'),
       (4, 'Rejected', 'Rejected adalah status cuti di mana pengajuan cuti sudah ditolak oleh HRD', 'Dian'),
       (5, 'Cancelled',
        'Cancelled adalah status cuti di mana pengajuan cuti yang sebelumnya open namun digagalkan oleh karyawan itu sendiri',
        'Dian');

INSERT IGNORE INTO jenis_cuti(id, jenis, deskripsi, created_by)
VALUES (1, 'Tahunan', 'Cuti tahunan adalah cuti yang disediakan oleh perusahaan di luar cuti bersama pemerintah',
        'Dian'),
       (2, 'Cuti lintas tahun',
        'Cuti lintas tahun adalah sisa cuti tahunan yang tersisa dari tahun sebelumnya dan hanya bisa dipakai pada 6 bulan pertama saja',
        'Dian');

INSERT IGNORE INTO employee(id, role_id, nip, nama_lengkap, divisi, email, username, password, created_by)
VALUES (1, 2, '02041911003', 'Annisa Nur Wachidah', 'PPMB', 'annisanurw@gmail.com', 'annisa',
        '$2a$10$izN/5EDMPOXQ0MZfCI3rRuZWx6V2tN5xE/9gp.jCziREX261xDmme', 'Romi'),
       (2, 2, '02041911007', 'Candra Komara', 'PPMB', 'candrakomara@gmail.com', 'candra',
        '$2a$10$izN/5EDMPOXQ0MZfCI3rRuZWx6V2tN5xE/9gp.jCziREX261xDmme', 'Romi'),
       (3, 2, '01021911021', "Dian Nurul Khazaainatu Qurrota A'yun", 'PPMB', 'diannurulqa@gmail.com', 'dian',
        '$2a$10$izN/5EDMPOXQ0MZfCI3rRuZWx6V2tN5xE/9gp.jCziREX261xDmme', 'Romi'),
       (4, 1, '02031911067', 'Romi Kusuma Bakti', 'Kerohanian', 'romikusumab@gmail.com', 'romi',
        '$2a$10$izN/5EDMPOXQ0MZfCI3rRuZWx6V2tN5xE/9gp.jCziREX261xDmme', 'Romi'),
       (5, 2, '01021911037', 'Tiara Agustin', 'Pendidikan', 'tiaraagustin@gmail.com', 'tiara',
        '$2a$10$izN/5EDMPOXQ0MZfCI3rRuZWx6V2tN5xE/9gp.jCziREX261xDmme', 'Romi');