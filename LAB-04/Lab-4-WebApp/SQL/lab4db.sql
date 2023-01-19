/*
MySQL Data Transfer
Source Host: localhost
Source Database: lab4db
Target Host: localhost
Target Database: lab4db
Date: 5/28/2021 5:56:28 AM
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(25) NOT NULL,
  `PASSWORD_HASH` varchar(255) NOT NULL,
  `DATE_CREATED` datetime NOT NULL,
  `USER_TYPE` smallint(5) unsigned zerofill NOT NULL DEFAULT 00001 COMMENT '1 (for customers) or 2 (for administrators)',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USERNAME` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for vehicles
-- ----------------------------
DROP TABLE IF EXISTS `vehicles`;
CREATE TABLE `vehicles` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  `DESCRIPTION` text NOT NULL,
  `PRODUCT_TYPE` smallint(5) unsigned DEFAULT NULL COMMENT '1 (for cars), 2 (for motorbikes) or null (not specified)',
  `IMAGE_PATH` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for reservations
-- ----------------------------
DROP TABLE IF EXISTS `reservations`;
CREATE TABLE `reservations` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `USER_ID` int(10) unsigned NOT NULL,
  `PRODUCT_ID` int(10) unsigned NOT NULL,
  `START_DATE` date NOT NULL,
  `END_DATE` date NOT NULL,
  `COMMENTS` text DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `USER_ID` (`USER_ID`),
  KEY `PRODUCT_ID` (`PRODUCT_ID`),
  CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`ID`),
  CONSTRAINT `reservations_ibfk_2` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `vehicles` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `vehicles` VALUES ('1', 'Audi ', 'Audi R8', '1', 'IMG/AudiR8.jpg');
INSERT INTO `vehicles` VALUES ('2', 'BMW ', 'BMW X6', '1', 'IMG/BMW-X6.jpg');
INSERT INTO `vehicles` VALUES ('3', 'SH', 'SH300', '2', 'IMG/SH300.jpg');
INSERT INTO `vehicles` VALUES ('4', 'Beverly', 'Beverly 300', '2', 'IMG/piaggio-beverly-300.jpg');
