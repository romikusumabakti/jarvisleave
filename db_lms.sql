/*
SQLyog Enterprise - MySQL GUI v7.02 
MySQL - 5.5.5-10.4.17-MariaDB : Database - db_lms
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE = ''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS */`db_lms` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `db_lms`;

/*Table structure for table `tbl_detail_pengajuan_cuti` */

DROP TABLE IF EXISTS `tbl_detail_pengajuan_cuti`;

CREATE TABLE `tbl_detail_pengajuan_cuti`
(
    `detail_pengajuan_cuti_id` int(8)    NOT NULL,
    `pengajuan_cuti_id`        int(8)             DEFAULT NULL,
    `jenis_cuti_id`            int(8)             DEFAULT NULL,
    `tgl_cuti`                 date               DEFAULT NULL,
    `created_by`               varchar(50)        DEFAULT NULL,
    `created_date`             timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
    `last_modified_by`         varchar(50)        DEFAULT NULL,
    `last_modified_date`       timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
    `is_deleted`               tinyint(1)         DEFAULT NULL,
    PRIMARY KEY (`detail_pengajuan_cuti_id`),
    KEY `fk_cid` (`pengajuan_cuti_id`),
    KEY `fk_jcd` (`jenis_cuti_id`),
    CONSTRAINT `fk_cid` FOREIGN KEY (`pengajuan_cuti_id`) REFERENCES `tbl_pengajuan_cuti` (`pengajuan_cuti_id`),
    CONSTRAINT `fk_jcd` FOREIGN KEY (`jenis_cuti_id`) REFERENCES `tbl_jenis_cuti` (`jenis_cuti_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

/*Data for the table `tbl_detail_pengajuan_cuti` */

/*Table structure for table `tbl_employee` */

DROP TABLE IF EXISTS `tbl_employee`;

CREATE TABLE `tbl_employee`
(
    `employee_id`        int(8)    NOT NULL,
    `role_id`            int(8)             DEFAULT NULL,
    `nip`                varchar(18)        DEFAULT NULL,
    `nama_lengkap`       varchar(100)       DEFAULT NULL,
    `divisi`             varchar(50)        DEFAULT NULL,
    `email`              varchar(100)       DEFAULT NULL,
    `username`           varchar(50)        DEFAULT NULL,
    `password`           text               DEFAULT NULL,
    `created_by`         varchar(50)        DEFAULT NULL,
    `created_date`       timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
    `last_modified_by`   varchar(50)        DEFAULT NULL,
    `last_modified_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
    `is_deleted`         tinyint(1)         DEFAULT NULL,
    PRIMARY KEY (`employee_id`),
    KEY `fk_rid` (`role_id`),
    CONSTRAINT `fk_rid` FOREIGN KEY (`role_id`) REFERENCES `tbl_role` (`role_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

/*Data for the table `tbl_employee` */

/*Table structure for table `tbl_hak_cuti` */

DROP TABLE IF EXISTS `tbl_hak_cuti`;

CREATE TABLE `tbl_hak_cuti`
(
    `hak_cuti_id`        int(8)    NOT NULL,
    `employee_id`        int(8)             DEFAULT NULL,
    `jenis_cuti_id`      int(8)             DEFAULT NULL,
    `sisa_cuti`          int(2)             DEFAULT NULL,
    `created_by`         varchar(50)        DEFAULT NULL,
    `created_date`       timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
    `last-modified_by`   varchar(50)        DEFAULT NULL,
    `last_modified_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
    `is_deleted`         tinyint(1)         DEFAULT NULL,
    PRIMARY KEY (`hak_cuti_id`),
    KEY `fk_eid` (`employee_id`),
    KEY `fk_jc` (`jenis_cuti_id`),
    CONSTRAINT `fk_eid` FOREIGN KEY (`employee_id`) REFERENCES `tbl_employee` (`employee_id`),
    CONSTRAINT `fk_jc` FOREIGN KEY (`jenis_cuti_id`) REFERENCES `tbl_jenis_cuti` (`jenis_cuti_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

/*Data for the table `tbl_hak_cuti` */

/*Table structure for table `tbl_jenis_cuti` */

DROP TABLE IF EXISTS `tbl_jenis_cuti`;

CREATE TABLE `tbl_jenis_cuti`
(
    `jenis_cuti_id`      int(8)    NOT NULL,
    `jenis_cuti`         varchar(50)        DEFAULT NULL,
    `deskripsi`          text               DEFAULT NULL,
    `created_by`         varchar(50)        DEFAULT NULL,
    `created_date`       timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
    `last_modified_by`   varchar(50)        DEFAULT NULL,
    `last_modified_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
    `is_deleted`         tinyint(1)         DEFAULT NULL,
    PRIMARY KEY (`jenis_cuti_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

/*Data for the table `tbl_jenis_cuti` */

insert into `tbl_jenis_cuti`(`jenis_cuti_id`, `jenis_cuti`, `deskripsi`, `created_by`, `created_date`,
                             `last_modified_by`, `last_modified_date`, `is_deleted`)
values (1, 'tahunan', 'Cuti tahunan adalah cuti yang disediakan oleh perusahaan diluar cuti bersama pemerintah', 'Dian',
        '2021-08-15 19:53:04', NULL, '0000-00-00 00:00:00', NULL),
       (2, 'cuti lintas tahun',
        'Cuti lintas tahun adalah sisa cuti tahunan yang tersisa dari tahun sebelumnya dan hanya bisa dipakai pada 6 bulan pertama saja',
        'Dian', '2021-08-15 19:56:09', NULL, '0000-00-00 00:00:00', NULL);

/*Table structure for table `tbl_libur` */

DROP TABLE IF EXISTS `tbl_libur`;

CREATE TABLE `tbl_libur`
(
    `libur_id`           int(8)    NOT NULL,
    `nama_libur`         varchar(100)       DEFAULT NULL,
    `deskripsi`          text               DEFAULT NULL,
    `tgl_libur`          date               DEFAULT NULL,
    `created_by`         varchar(50)        DEFAULT NULL,
    `created_date`       timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
    `last_modified_by`   varchar(50)        DEFAULT NULL,
    `last_modified_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
    `is_deleted`         tinyint(1)         DEFAULT NULL,
    PRIMARY KEY (`libur_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

/*Data for the table `tbl_libur` */

/*Table structure for table `tbl_pengajuan_cuti` */

DROP TABLE IF EXISTS `tbl_pengajuan_cuti`;

CREATE TABLE `tbl_pengajuan_cuti`
(
    `pengajuan_cuti_id`  int(8)    NOT NULL,
    `employee_id`        int(8)             DEFAULT NULL,
    `status_cuti_id`     int(8)             DEFAULT NULL,
    `pengganti_id`       int(8)             DEFAULT NULL,
    `hrd_id`             int(8)             DEFAULT NULL,
    `alamat`             text               DEFAULT NULL,
    `no_telp`            varchar(15)        DEFAULT NULL,
    `keterangan`         text               DEFAULT NULL,
    `lama_cuti`          int(4)             DEFAULT NULL,
    `created_by`         varchar(50)        DEFAULT NULL,
    `created_date`       timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
    `last_modified_by`   varchar(50)        DEFAULT NULL,
    `last_modified_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
    `is_deleted`         tinyint(1)         DEFAULT NULL,
    PRIMARY KEY (`pengajuan_cuti_id`),
    KEY `fk_emp` (`employee_id`),
    KEY `fk_sct` (`status_cuti_id`),
    CONSTRAINT `fk_emp` FOREIGN KEY (`employee_id`) REFERENCES `tbl_employee` (`employee_id`),
    CONSTRAINT `fk_sct` FOREIGN KEY (`status_cuti_id`) REFERENCES `tbl_status_cuti` (`status_cuti_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

/*Data for the table `tbl_pengajuan_cuti` */

/*Table structure for table `tbl_role` */

DROP TABLE IF EXISTS `tbl_role`;

CREATE TABLE `tbl_role`
(
    `role_id`            int(8)    NOT NULL,
    `nama_role`          varchar(50)        DEFAULT NULL,
    `created_by`         varchar(50)        DEFAULT NULL,
    `created-date`       timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
    `last_modified_by`   varchar(50)        DEFAULT NULL,
    `last_modified_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
    `is_deleted`         tinyint(1)         DEFAULT NULL,
    PRIMARY KEY (`role_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

/*Data for the table `tbl_role` */

insert into `tbl_role`(`role_id`, `nama_role`, `created_by`, `created-date`, `last_modified_by`, `last_modified_date`,
                       `is_deleted`)
values (1, 'hrd', 'Dian', '2021-08-15 14:41:32', NULL, '0000-00-00 00:00:00', NULL),
       (2, 'karyawan', 'Dian', '2021-08-15 14:42:44', NULL, '0000-00-00 00:00:00', NULL);

/*Table structure for table `tbl_status_cuti` */

DROP TABLE IF EXISTS `tbl_status_cuti`;

CREATE TABLE `tbl_status_cuti`
(
    `status_cuti_id`     int(8)    NOT NULL,
    `status_cuti`        varchar(50)        DEFAULT NULL,
    `deskripsi`          text               DEFAULT NULL,
    `created_by`         varchar(50)        DEFAULT NULL,
    `created_date`       timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
    `last_modified_by`   varchar(50)        DEFAULT NULL,
    `last_modified_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
    `is_daleted`         tinyint(1)         DEFAULT NULL,
    PRIMARY KEY (`status_cuti_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

/*Data for the table `tbl_status_cuti` */

insert into `tbl_status_cuti`(`status_cuti_id`, `status_cuti`, `deskripsi`, `created_by`, `created_date`,
                              `last_modified_by`, `last_modified_date`, `is_daleted`)
values (1, 'Draft', 'Draft adalah status cuti dimana pengajuan cuti sudah dibuat tapi belum diajukan', 'Dian',
        '2021-08-15 14:45:43', NULL, '0000-00-00 00:00:00', NULL),
       (2, 'Open', 'Open adalah status cuti dimana pengajuan cuti sudah diajukan kepada hrd', 'Dian',
        '2021-08-15 14:46:09', NULL, '0000-00-00 00:00:00', NULL),
       (3, 'Approved', 'Approved adalah status cuti dimana pengajuan cuti sudah disetujui oleh hrd', 'Dian',
        '2021-08-15 19:49:01', NULL, '0000-00-00 00:00:00', NULL),
       (4, 'Rejected', 'Rejected adalah status cuti dimana pengajuan cuti sudah ditolak oleh hrd', 'Dian',
        '2021-08-15 19:49:53', NULL, '0000-00-00 00:00:00', NULL),
       (5, 'Cancelled',
        'Cancelled adalah status cuti dimana pengajuan cuti yang sebelumnya open namun digagalkan oleh karyawan itu sendiri',
        'Dian', '2021-08-15 19:51:23', NULL, '0000-00-00 00:00:00', NULL);

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
