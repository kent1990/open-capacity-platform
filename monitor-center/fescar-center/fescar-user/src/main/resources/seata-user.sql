/*
Navicat MySQL Data Transfer

Source Server         : 59.110.164.254
Source Server Version : 50722
Source Host           : 59.110.164.254:3306
Source Database       : seata-user

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2020-03-30 16:46:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ocp_tql
-- ----------------------------
DROP TABLE IF EXISTS `ocp_tql`;
CREATE TABLE `ocp_tql` (
  `id` varchar(32) NOT NULL COMMENT '主键id',
  `user_id` varchar(32) DEFAULT NULL,
  `money` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户账户';

-- ----------------------------
-- Records of ocp_tql
-- ----------------------------
INSERT INTO `ocp_tql` VALUES ('00FC42AD4FDC400C8F95103FE0BE8839', '111', '10');

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) NOT NULL,
  `context` varchar(128) NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of undo_log
-- ----------------------------
