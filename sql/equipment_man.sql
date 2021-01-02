/*
Navicat MySQL Data Transfer

Source Server         : Ry
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : equipment_man

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2021-01-02 19:51:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cate_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '球类');
INSERT INTO `category` VALUES ('2', '田赛类');

-- ----------------------------
-- Table structure for equipment
-- ----------------------------
DROP TABLE IF EXISTS `equipment`;
CREATE TABLE `equipment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `equip_name` varchar(255) DEFAULT NULL,
  `cate_id` int(11) DEFAULT NULL,
  `rent` decimal(10,2) DEFAULT NULL,
  `ini_count` int(11) DEFAULT NULL,
  `rent_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of equipment
-- ----------------------------
INSERT INTO `equipment` VALUES ('1', '篮球', '2', '6.00', '62', '0');
INSERT INTO `equipment` VALUES ('2', '铅球', '2', '6.00', '58', '58');
INSERT INTO `equipment` VALUES ('5', '足球', '2', '6.00', '60', '0');
INSERT INTO `equipment` VALUES ('6', '橄榄球', '2', '6.00', '60', '0');
INSERT INTO `equipment` VALUES ('7', '羽毛球', '2', '6.00', '60', '0');
INSERT INTO `equipment` VALUES ('10', '乒乓球', '2', '6.00', '60', '0');

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `info` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES ('1', '2020-12-28 20:24:21', 'UPDATE: from {\"id\":1,\"username\":\"admin\",\"password\":\"admin2\",\"phone\":\"17858187956\",\"role\":\"admin\"} to {\"id\":1,\"username\":\"admin\",\"password\":\"admin2\",\"phone\":\"17858187956\",\"role\":\"admin\"}');
INSERT INTO `log` VALUES ('2', '2020-12-28 20:35:39', 'UPDATE: from {\"id\":2,\"equipName\":\"铅球\",\"cateId\":2,\"category\":null,\"rent\":6.00,\"iniCount\":58,\"rentCount\":34} to {\"id\":2,\"equipName\":\"铅球\",\"cateId\":2,\"category\":null,\"rent\":6.00,\"iniCount\":58,\"rentCount\":34}');
INSERT INTO `log` VALUES ('3', '2020-12-28 20:35:40', 'INSERT: {\"id\":null,\"userId\":3,\"equipId\":2,\"count\":15,\"timeBegin\":\"2020-12-28 15:30\",\"endTime\":\"2020-12-28 16:30\",\"createTime\":1609158939650,\"user\":null,\"equipment\":null}');
INSERT INTO `log` VALUES ('4', '2021-01-02 13:14:12', 'UPDATE: from {\"id\":2,\"equipName\":\"铅球\",\"cateId\":2,\"category\":null,\"rent\":6.00,\"iniCount\":58,\"rentCount\":49} to {\"id\":2,\"equipName\":\"铅球\",\"cateId\":2,\"category\":null,\"rent\":6.00,\"iniCount\":58,\"rentCount\":49}');
INSERT INTO `log` VALUES ('5', '2021-01-02 13:14:13', 'INSERT: {\"id\":null,\"userId\":3,\"equipId\":2,\"count\":15,\"timeBegin\":\"2020-12-28 15:30\",\"endTime\":\"2020-12-28 16:30\",\"createTime\":1609564452643,\"user\":null,\"equipment\":null}');
INSERT INTO `log` VALUES ('6', '2021-01-02 13:27:16', 'UPDATE: from {\"id\":2,\"equipName\":\"铅球\",\"cateId\":2,\"category\":null,\"rent\":6.00,\"iniCount\":58,\"rentCount\":58} to {\"id\":2,\"equipName\":\"铅球\",\"cateId\":2,\"category\":null,\"rent\":6.00,\"iniCount\":58,\"rentCount\":58}');
INSERT INTO `log` VALUES ('7', '2021-01-02 13:27:16', 'INSERT: {\"id\":null,\"userId\":3,\"equipId\":2,\"count\":9,\"timeBegin\":\"2020-12-28 15:30\",\"endTime\":\"2020-12-28 16:30\",\"createTime\":1609565235968,\"user\":null,\"equipment\":null}');
INSERT INTO `log` VALUES ('8', '2021-01-02 13:29:25', 'UPDATE: from {\"id\":1,\"equipName\":\"篮球\",\"cateId\":1,\"category\":null,\"rent\":5.62,\"iniCount\":90,\"rentCount\":0} to {\"id\":1,\"equipName\":\"篮球\",\"cateId\":1,\"category\":null,\"rent\":5.62,\"iniCount\":90,\"rentCount\":0}');
INSERT INTO `log` VALUES ('9', '2021-01-02 13:29:25', 'DELETE: 7');
INSERT INTO `log` VALUES ('10', '2021-01-02 13:29:25', 'INSERT: {\"id\":null,\"userId\":3,\"equipId\":1,\"count\":7,\"timeReturn\":1609565364916,\"user\":null,\"equipment\":null}');

-- ----------------------------
-- Table structure for rent_record
-- ----------------------------
DROP TABLE IF EXISTS `rent_record`;
CREATE TABLE `rent_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `equip_id` int(11) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `time_begin` varchar(25) DEFAULT NULL,
  `end_time` varchar(25) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rent_record
-- ----------------------------
INSERT INTO `rent_record` VALUES ('8', '3', '2', '9', '2020-12-29 15:30', '2020-12-28 16:30', '2020-12-28 19:34:10');
INSERT INTO `rent_record` VALUES ('9', '3', '2', '15', '2020-12-28 15:30', '2020-12-28 16:30', '2020-12-28 20:35:40');
INSERT INTO `rent_record` VALUES ('10', '3', '2', '15', '2020-12-28 15:30', '2020-12-28 16:30', '2021-01-02 13:14:13');
INSERT INTO `rent_record` VALUES ('11', '3', '2', '9', '2020-12-28 15:30', '2020-12-28 16:30', '2021-01-02 13:27:16');

-- ----------------------------
-- Table structure for return_record
-- ----------------------------
DROP TABLE IF EXISTS `return_record`;
CREATE TABLE `return_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `equip_id` int(11) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `time_return` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of return_record
-- ----------------------------
INSERT INTO `return_record` VALUES ('1', '3', '1', '5', '2020-12-28 18:51:25');
INSERT INTO `return_record` VALUES ('2', '3', '1', '5', '2020-12-28 19:31:20');
INSERT INTO `return_record` VALUES ('3', '4', '2', '5', '2020-12-28 19:32:40');
INSERT INTO `return_record` VALUES ('4', '3', '1', '7', '2021-01-02 13:29:25');

-- ----------------------------
-- Table structure for stock_log
-- ----------------------------
DROP TABLE IF EXISTS `stock_log`;
CREATE TABLE `stock_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `equip_id` int(11) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `cost` decimal(10,0) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stock_log
-- ----------------------------
INSERT INTO `stock_log` VALUES ('1', '1', '-30', '-180', '2021-01-02 19:31:53');
INSERT INTO `stock_log` VALUES ('2', null, '60', '360', '2021-01-02 19:32:56');
INSERT INTO `stock_log` VALUES ('3', '10', '60', '360', '2021-01-02 19:47:54');
INSERT INTO `stock_log` VALUES ('4', '1', '2', '12', '2021-01-02 19:48:24');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin2', '17858187956', 'admin');
INSERT INTO `user` VALUES ('3', 'ry', '123456', '17858187957', 'user');
