CREATE DATABASE IF NOT EXISTS bambinos;
USE bambinos;


DROP TABLE IF EXISTS `bambinos`.`program`;
DROP TABLE IF EXISTS `bambinos`.`teacher`;
DROP TABLE IF EXISTS `bambinos`.`center`;
DROP TABLE IF EXISTS `bambinos`.`center_program`;
DROP TABLE IF EXISTS `bambinos`.`center_admin`;
DROP TABLE IF EXISTS `bambinos`.`parent`;
DROP TABLE IF EXISTS `bambinos`.`child`;
DROP TABLE IF EXISTS `bambinos`.`parent_child`;
DROP TABLE IF EXISTS `bambinos`.`login`;
DROP TABLE IF EXISTS `bambinos`.`contact_info`;
DROP TABLE IF EXISTS `bambinos`.`admin`;
DROP TABLE IF EXISTS `bambinos`.`teacher_center`;
DROP TABLE IF EXISTS `bambinos`.`teacher_program`;


CREATE TABLE `bambinos`.`login` (
   `login_id` VARCHAR(32) NOT NULL,
   `username` VARCHAR(80) NULL,
  `password` VARCHAR(45) NULL,
  `role_type` VARCHAR(45) NOT NULL,
  `login_type` VARCHAR(20) NULL,
  `parent_table` varchar(100) null,
  `parent_id` varchar(32) null,
  `verification_token` VARCHAR(32) NULL,
  `active` VARCHAR(1) NULL,
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`login_id`));

--    INSERT INTO bambinos.role (role_id,role) VALUES ('522f93265cf64549bee83f479cde9277', 'parent');
--    INSERT INTO bambinos.role (role_id,role) VALUES ('ab909f334c3e41129ee8d852c66fc57b', 'admin');
--    INSERT INTO bambinos.role (role_id,role) VALUES ('daec8bea804f4e1d80b6a7685a3aa0d7', 'centermanager');
--    INSERT INTO bambinos.role (role_id,role) VALUES ('cvoinpnq8vwslsf4e1d80vo09qwmvo13', 'centerstaff');
--    INSERT INTO bambinos.role (role_id,role) VALUES ('87c96b02be8742fab69cfd6704c839ac', 'teacher');

CREATE TABLE `bambinos`.`contact_info` (
  `contact_info_id` VARCHAR(32) NOT NULL,
  `parent_table` varchar(50) not null,
  `parent_id` varchar(32) not null,
  `primary_email` VARCHAR(80) NULL,
  `secondary_email` VARCHAR(80) NULL,
  `address_line1` VARCHAR(200) NULL,
  `address_line2` VARCHAR(200) NULL,
  `city` VARCHAR(100) NULL,
  `state` VARCHAR(20) NULL,
  `pincode` VARCHAR(10) NULL,
  `mobile_number` VARCHAR(15) NULL,
  `alt_mobile_number` VARCHAR(15) NULL,
  `land_line` VARCHAR(15) NULL,
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`contact_info_id`));

   CREATE TABLE `bambinos`.`program` (
        `program_id` VARCHAR(32) NOT NULL,
        `program_name` VARCHAR(100) NOT NULL,
       `program_code` VARCHAR(32) NOT NULL,
       `description` VARCHAR(400) NOT NULL,
       `price` decimal (12,6),
       `capacity` int,
       `assessment_period` int,
       `created_by` varchar(32),
       `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
       PRIMARY KEY (`program_id`));

   CREATE TABLE `bambinos`.`center` (
         `center_id` VARCHAR(32) NOT NULL,
         `center_code` VARCHAR(32) NOT NULL,
       `landmark` VARCHAR(32)  NULL,
       `map_location` VARCHAR(100)  NULL,
       `hospital_number` varchar(20),
       `ambulance_number` varchar(20),
       `created_by` varchar(32),
       `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
       PRIMARY KEY (`center_id`));

CREATE TABLE `bambinos`.`center_program` (
     `center_program_id` VARCHAR(32) NOT NULL,
     `center_id` VARCHAR(32) NOT NULL,
    `program_id` VARCHAR(32) NOT NULL,
     `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
     `created_by` varchar(32),
    PRIMARY KEY (`center_program_id`));

CREATE TABLE `bambinos`.`admin` (
         `admin_id` VARCHAR(32) NOT NULL,
       `first_name` VARCHAR(80) NOT NULL,
       `last_name`  VARCHAR(80) NOT NULL,
       `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
       `active` VARCHAR(1) NOT NULL,
       PRIMARY KEY (`admin_id`));

CREATE TABLE `bambinos`.`center_admin` (
        `center_admin_id` VARCHAR(32) NOT NULL,
        `admin_id` VARCHAR(32) NOT NULL,
       `center_id` VARCHAR(32) NOT NULL,
       `role_type`  VARCHAR(80) NOT NULL,
       `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
       `active` VARCHAR(1) NOT NULL,
        `created_by` varchar(32),
       PRIMARY KEY (`center_admin_id`));

CREATE TABLE `bambinos`.`teacher` (
         `teacher_id` VARCHAR(32) NOT NULL,
         `first_name` VARCHAR(80) NOT NULL,
         `last_name` VARCHAR(80) NOT NULL,
       `employee_id` int  NULL,
       `sex` VARCHAR(1)  NULL,
       `dob` DATE null,
       `employment_type` varchar(20),
       `qualification` varchar(20),
       `medical_qualification` varchar(20),
       `linked_in_profile` varchar(200),
       `cost_per_hour` decimal (12,6),
       `created_by` varchar(32),
       `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
       PRIMARY KEY (`teacher_id`));

 CREATE TABLE `bambinos`.`teacher_program` (
      `teacher_program_id` VARCHAR(32) NOT NULL,
      `teacher_id` VARCHAR(32) NOT NULL,
     `program_id` VARCHAR(32) NOT NULL,
      `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
      `created_by` varchar(32),
     PRIMARY KEY (`teacher_program_id`));

  CREATE TABLE `bambinos`.`teacher_center` (
       `teacher_center_id` VARCHAR(32) NOT NULL,
       `center_id` VARCHAR(32) NOT NULL,
      `teacher_id` VARCHAR(32) NOT NULL,
       `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
       `created_by` varchar(32),
      PRIMARY KEY (`teacher_center_id`));

-- CREATE TABLE `bambinos`.`parent` (
--          `parent_id` VARCHAR(32) NOT NULL,
--        `first_name` VARCHAR(80) NOT NULL,
--        `last_name`  VARCHAR(80) NOT NULL,
--        `parent_type` varchar(10) not null,
--        `occupation` varchar(100) null,
--        `company_name` varchar(200) null,
--        `company_phone` varchar(20) null,
--        `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
--        `created_by` varchar(32),
--        PRIMARY KEY (`parent_id`));

   CREATE TABLE `bambinos`.`child` (
            `child_id` VARCHAR(32) NOT NULL,
          `first_name` VARCHAR(80) NOT NULL,
          `last_name`  VARCHAR(80) NOT NULL,
          `sex` varchar(1) not null,
          `dob` date not null,
          `address_line1` VARCHAR(200) NULL,
            `address_line2` VARCHAR(200) NULL,
            `city` VARCHAR(100) NULL,
            `state` VARCHAR(20) NULL,
            `pincode` VARCHAR(10) NULL,
            `mobile_number` VARCHAR(15) NULL,
            `father_name` VARCHAR(200) null,
            `mother_name` varchar(200) null,
            `father_mobile` varchar(20) null,
            `mother_mobile` varchar(20) null,
            `father_occupation` varchar(100) null,
            `father_company_name` varchar(200) null,
            `father_company_phone` varchar(20) null,
            `mother_occupation` varchar(100) null,
            `mother_company_name` varchar(200) null,
            `mother_company_phone` varchar(20) null,
          `blood_group` varchar(10) null,
          `allergy` varchar(1),
          `allergy_description` varchar(100),
          `dietary_need` varchar(100),
          `food_needed` varchar(1),
          `allow_social_media` varchar(1),
          `cctv_access` varchar(1),
          `joining_date` date ,
          `emergency_contact_name` varchar(100),
          `emergency_contact_number` varchar(20),
          `emergency_contact_alt_number` varchar(20),
          `emergency_contact_relation` varchar(20),
          `payment_freq` int,
          `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
          `created_by` varchar(32),
          PRIMARY KEY (`child_id`));

--  CREATE TABLE `bambinos`.`parent_child` (
--           `parent_child_id` VARCHAR(32) NOT NULL,
--            `parent_id` VARCHAR(32) NOT NULL,
--          `child_id` VARCHAR(32) NOT NULL,
--          `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
--          `created_by` varchar(32),
--          PRIMARY KEY (`parent_child_id`));


