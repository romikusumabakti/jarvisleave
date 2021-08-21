INSERT INTO status_cuti(id, status, deskripsi, created_by)
VALUES (1, 'Draft', 'Draft adalah status cuti di mana pengajuan cuti sudah dibuat tapi belum diajukan', 'Dian'),
       (2, 'Open', 'Open adalah status cuti di mana pengajuan cuti sudah diajukan kepada HRD', 'Dian'),
       (3, 'Approved', 'Approved adalah status cuti di mana pengajuan cuti sudah disetujui oleh HRD', 'Dian'),
       (4, 'Rejected', 'Rejected adalah status cuti di mana pengajuan cuti sudah ditolak oleh HRD', 'Dian'),
       (5, 'Cancelled', 'Cancelled adalah status cuti di mana pengajuan cuti yang sebelumnya open namun digagalkan oleh karyawan itu sendiri', 'Dian');