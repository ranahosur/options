CREATE DATABASE IF NOT EXISTS options;
USE options;

DROP TABLE IF EXISTS `options`.`user`;
DROP TABLE IF EXISTS `options`.`role`;
DROP TABLE IF EXISTS `options`.`user_role`;
DROP TABLE IF EXISTS `options`.`team`;
DROP TABLE IF EXISTS `options`.`team_member`;
DROP TABLE IF EXISTS `options`.`admin_privilege`;
CREATE TABLE `options`.`role` (
   `role_id` VARCHAR(32) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`role_id`));
CREATE TABLE `options`.`user` (
   `user_id` VARCHAR(32) NOT NULL,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NULL,
  `email` VARCHAR(80) NULL,
  `address_line1` VARCHAR(200) NULL,
  `address_line2` VARCHAR(200) NULL,
  `city` VARCHAR(100) NULL,
  `state` VARCHAR(20) NULL,
  `zip` VARCHAR(10) NULL,
  `country_code` VARCHAR(3) NULL,
  `country_name` VARCHAR(30) NULL,
  `phone_number` VARCHAR(15) NULL,
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`));
  CREATE TABLE `options`.`user_role` (
     `user_role_id` VARCHAR(32) NOT NULL,
     `user_id` VARCHAR(32) NOT NULL,
    `role_id` VARCHAR(32) NOT NULL,
    PRIMARY KEY (`user_role_id`));
    INSERT INTO options.role (role_id,role) VALUES ('522f93265cf64549bee83f479cde9277', 'participant');
    INSERT INTO options.role (role_id,role) VALUES ('ab909f334c3e41129ee8d852c66fc57b', 'admin');
    INSERT INTO options.role (role_id,role) VALUES ('daec8bea804f4e1d80b6a7685a3aa0d7', 'superadmin');
    INSERT INTO options.role (role_id,role) VALUES ('87c96b02be8742fab69cfd6704c839ac', 'team');

   CREATE TABLE `options`.`team` (
        `team_id` VARCHAR(32) NOT NULL,
        `team_name` VARCHAR(100) NOT NULL,
       `admin_id` VARCHAR(32) NOT NULL,
       `user_id` VARCHAR(32) NOT NULL,
       `create_date` datetime NOT NULL,
       `active` VARCHAR(1) NOT NULL,
       PRIMARY KEY (`team_id`));

   CREATE TABLE `options`.`team_member` (
         `team_member_id` VARCHAR(32) NOT NULL,
         `team_id` VARCHAR(32) NOT NULL,
       `user_id` VARCHAR(32) NOT NULL,
       `create_date` datetime NOT NULL,
       `active` VARCHAR(1) NOT NULL,
       PRIMARY KEY (`team_member_id`));

CREATE TABLE `options`.`admin_privilege` (
         `admin_privilege_id` VARCHAR(32) NOT NULL,
       `user_id` VARCHAR(32) NOT NULL,
       `max_team_count` INTEGER NOT NULL,
       `max_team_member_count` INTEGER NOT NULL,
       `trial_period_end_date` DATE NOT NULL,
       `create_date` datetime NOT NULL,
       `active` VARCHAR(1) NOT NULL,
       PRIMARY KEY (`admin_privilege_id`));