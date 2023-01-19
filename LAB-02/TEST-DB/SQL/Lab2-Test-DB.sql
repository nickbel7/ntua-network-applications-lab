/*
MySQL Data Transfer
Source Host: localhost
Source Database: lab1db
Target Host: localhost
Target Database: lab1db
Date: 3/22/2021 9:12:52 PM
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for table1
-- ----------------------------
DROP TABLE IF EXISTS `table1`;
CREATE TABLE `table1` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `VAL1` varchar(200) NOT NULL,
  `VAL2` varchar(200) NOT NULL,
  `VAL3` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `table1` VALUES ('1', 'Row-1-Value-1', 'Row-1-Value-2', '11');
INSERT INTO `table1` VALUES ('2', 'Row-2-Value-1', 'Row-2-Value-2', '22');
INSERT INTO `table1` VALUES ('3', 'Row-3-Value-1', 'Row-3-Value-3', '33');
