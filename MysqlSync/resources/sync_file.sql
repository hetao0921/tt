/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.235
Source Server Version : 50619
Source Host           : 192.168.1.235:3306
Source Database       : nvmp

Target Server Type    : MYSQL
Target Server Version : 50619
File Encoding         : 65001

Date: 2014-10-28 09:13:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sync_file`
-- ----------------------------
DROP TABLE IF EXISTS `sync_file`;
CREATE TABLE `sync_file` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `modifiedtime` datetime DEFAULT NULL,
  `filepath` varchar(200) DEFAULT NULL,
  `filename` varchar(200) DEFAULT NULL,
  `file` longblob,
  `mainflag` bigint(255) DEFAULT NULL,
  `backupflag` bigint(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of sync_file
-- ----------------------------
