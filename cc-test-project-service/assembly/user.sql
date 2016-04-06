/*
 Navicat Premium Data Transfer

 Source Server         : Test-master-58.215.179.238
 Source Server Type    : MySQL
 Source Server Version : 50612
 Source Host           : 58.215.179.238
 Source Database       : o2o_demo

 Target Server Type    : MySQL
 Target Server Version : 50612
 File Encoding         : utf-8

 Date: 03/08/2016 13:33:47 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `birth_date` datetime DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `inst` varchar(20) DEFAULT NULL,
  `address` varchar(30) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
