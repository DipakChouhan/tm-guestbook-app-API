CREATE TABLE `guestbook-app`.`GUEST_BOOK_ENTRY`(
  `GUEST_BOOK_ENTRY_ID` INT NOT NULL AUTO_INCREMENT,
  `GUEST_BOOK_ENTRY_TEXT` VARCHAR(4000) NULL,
  `GUEST_BOOK_ENTRY_IMAGE` MEDIUMBLOB NULL,
  `GUEST_BOOK_ENTRY_STATUS` INT NULL,
  `GUEST_BOOK_ENTRY_CAPTURED_BY` INT NULL,
  `CREATED_BY`VARCHAR(20) NOT NULL DEFAULT '17.0.0.1',
  `CREATED_ON` DATETIME NOT NULL DEFAULT NOW(),
  `MODIFIED_BY`VARCHAR(20) NOT NULL DEFAULT '127.0.0.1',
  `MODIFIED_ON` DATETIME NOT NULL DEFAULT NOW(),
  PRIMARY KEY (`GUEST_BOOK_ENTRY_ID`)
);

CREATE TABLE `guestbook-app`.`user`(
  `USER_ID` INT NOT NULL AUTO_INCREMENT,
  `USER_EMAIL` VARCHAR(40) NULL,
  `USER_PASSWORD` VARCHAR(2000) NULL,
  `USER_NAME` VARCHAR(45) NULL,
  `NAME` VARCHAR(45) NULL,
  `ROLE` VARCHAR(10) NOT NULL DEFAULT 'USER',
  PRIMARY KEY (`USER_ID`)
);

update `guestbook-app`.`user` user SET user.ROLE = 'ADMIN WHERE USER_EMAIL = '<email>';



--CREATE TABLE `guestbook-app`.`master_status`(
--  `MASTER_STATUS_ID` INT NOT NULL,
--  `MASTER_STATUS_CODE` VARCHAR(10) NOT NULL,
--  `MASTER_STATUS_DESCRIPTION` VARCHAR(10) NOT NULL,
--  `CREATED_BY`VARCHAR(20) NOT NULL DEFAULT '17.0.0.1',
--  `CREATED_ON` DATETIME NOT NULL DEFAULT NOW(),
--  `MODIFIED_BY`VARCHAR(20) NOT NULL DEFAULT '127.0.0.1',
--  `MODIFIED_ON` DATETIME NOT NULL DEFAULT NOW(),
--  PRIMARY KEY (`MASTER_STATUS_ID`)
--);
--
--drop
--
--insert into `guestbook-app`.`master_status`(MASTER_STATUS_ID, MASTER_STATUS_CODE, MASTER_STATUS_DESCRIPTION) values (1, 'PENDING', 'Pending');
--insert into `guestbook-app`.`master_status`(MASTER_STATUS_ID, MASTER_STATUS_CODE, MASTER_STATUS_DESCRIPTION) values (2, 'APPROVED', 'Approved');
--COMMIT;