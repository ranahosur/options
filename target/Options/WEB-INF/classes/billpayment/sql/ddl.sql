CREATE DATABASE IF NOT EXISTS options;
USE options;


DROP TABLE IF EXISTS `options`.`role`;
DROP TABLE IF EXISTS `options`.`user_role`;
DROP TABLE IF EXISTS `options`.`team`;
DROP TABLE IF EXISTS `options`.`team_member`;
DROP TABLE IF EXISTS `options`.`admin_privilege`;
DROP TABLE IF EXISTS `options`.`transaction`;
DROP TABLE IF EXISTS `options`.`option_detail`;
DROP TABLE IF EXISTS `options`.`participant`;
DROP TABLE IF EXISTS `options`.`user`;
DROP TABLE IF EXISTS `options`.`participant_transaction`;


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
  `verification_token` VARCHAR(32) NULL,
  `active` VARCHAR(1) NULL,
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
      `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
       `active` VARCHAR(1) NOT NULL,
       PRIMARY KEY (`team_id`));

   CREATE TABLE `options`.`team_member` (
         `team_member_id` VARCHAR(32) NOT NULL,
         `team_id` VARCHAR(32) NOT NULL,
       `user_id` VARCHAR(32) NOT NULL,
       `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
       `active` VARCHAR(1) NOT NULL,
       PRIMARY KEY (`team_member_id`));

CREATE TABLE `options`.`admin_privilege` (
         `admin_privilege_id` VARCHAR(32) NOT NULL,
       `user_id` VARCHAR(32) NOT NULL,
       `max_team_count` INTEGER NOT NULL,
       `max_team_member_count` INTEGER NOT NULL,
       `trial_period_end_date` DATE NOT NULL,
       `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
       `active` VARCHAR(1) NOT NULL,
       PRIMARY KEY (`admin_privilege_id`));

CREATE TABLE `options`.`option_detail` (
`option_detail_id` varchar(32) not null,
`name` varchar(200) not null,
`symbol` varchar(40) not null,
`stock_price` decimal(12,6) not null,
`strike_price` decimal(12,6) not null,
`expiry_date` Date not null,
`call_bid_price` decimal (12,6),
`call_ask_price` decimal (12,6),
`call_volume` int,
`call_open_int` int,
`call_open_int_change` int,
`call_ask_qty` int,
`call_bid_qty` int,
`call_last_traded_price` decimal (12,6),
`call_implied_volatility` decimal (12,6),
`call_net_change` decimal (12,6),
`put_bid_price` decimal (12,6),
`put_ask_price` decimal (12,6),
`put_volume` int,
`put_open_int` int,
`put_open_int_change` int,
`put_ask_qty` int,
`put_bid_qty` int,
`put_last_traded_price` decimal (12,6),
`put_implied_volatility` decimal (12,6),
`put_net_change` decimal (12,6),
`create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY (`option_Detail_id`)
);

CREATE TABLE `options`.`participant` (
`participant_id` varchar(32) not null,
`user_id` varchar(32) not null,
`total_funds`  decimal (12,6) null,
`create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY (`participant_id`)
);

CREATE TABLE `options`.`participant_transaction` (
`participant_transaction_id` varchar(32) not null,
`participant_id` varchar(32) not null,
`option_detail_id` varchar(32) not null,
`option_type` varchar(32) not null,
`funds_invested`  decimal (12,6) null,
`entry_price` decimal (12,6) null,
`exit_price` decimal (12,6) null,
`lot_count` int not null,
`status` varchar(32) not null,
`result` varchar(32),
`exercise_date` date,
`linked_transaction_id` varchar(32),
`create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY (`participant_transaction_id`)
);


