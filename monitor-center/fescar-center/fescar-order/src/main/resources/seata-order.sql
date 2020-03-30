/*
Navicat MySQL Data Transfer

Source Server         : 59.110.164.254
Source Server Version : 50722
Source Host           : 59.110.164.254:3306
Source Database       : seata-order

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2020-03-30 16:46:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ocp_order
-- ----------------------------
DROP TABLE IF EXISTS `ocp_order`;
CREATE TABLE `ocp_order` (
  `id` varchar(32) NOT NULL COMMENT '主键id',
  `order_sn` varchar(64) DEFAULT '' COMMENT '订单号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户Id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Records of ocp_order
-- ----------------------------


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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of undo_log
-- ----------------------------
