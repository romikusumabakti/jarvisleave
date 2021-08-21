INSERT INTO role(id, nama, created_by)
VALUES (1, 'HRD', 'Dian'),
       (2, 'Karyawan', 'Dian');

INSERT INTO status_cuti(id, status, deskripsi, created_by)
VALUES (1, 'Draft', 'Draft adalah status cuti di mana pengajuan cuti sudah dibuat tapi belum diajukan', 'Dian'),
       (2, 'Open', 'Open adalah status cuti di mana pengajuan cuti sudah diajukan kepada HRD', 'Dian'),
       (3, 'Approved', 'Approved adalah status cuti di mana pengajuan cuti sudah disetujui oleh HRD', 'Dian'),
       (4, 'Rejected', 'Rejected adalah status cuti di mana pengajuan cuti sudah ditolak oleh HRD', 'Dian'),
       (5, 'Cancelled', 'Cancelled adalah status cuti di mana pengajuan cuti yang sebelumnya open namun digagalkan oleh karyawan itu sendiri', 'Dian');

INSERT INTO jenis_cuti(id, jenis, deskripsi, created_by)
VALUES (1, 'Tahunan', 'Cuti tahunan adalah cuti yang disediakan oleh perusahaan di luar cuti bersama pemerintah', 'Dian'),
       (2, 'Cuti lintas tahun', 'Cuti lintas tahun adalah sisa cuti tahunan yang tersisa dari tahun sebelumnya dan hanya bisa dipakai pada 6 bulan pertama saja', 'Dian');