/*
Navicat MySQL Data Transfer

Source Server         : 59.110.164.254
Source Server Version : 50722
Source Host           : 59.110.164.254:3306
Source Database       : seata-item

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2020-03-30 16:46:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ocp_item
-- ----------------------------
DROP TABLE IF EXISTS `ocp_item`;
CREATE TABLE `ocp_item` (
  `id` varchar(32) NOT NULL COMMENT '主键id',
  `product_id` varchar(32) DEFAULT NULL COMMENT '商品id',
  `total` int(11) DEFAULT NULL COMMENT '总库存',
  `used` int(11) DEFAULT NULL COMMENT '已用库存',
  `residue` int(11) DEFAULT NULL COMMENT '剩余库存',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品库存表';

-- ----------------------------
-- Records of ocp_item
-- ----------------------------
INSERT INTO `ocp_item` VALUES ('00FC42AD4FDC400C8F95103FE0B12345', '112', '1000', '0', '1000');

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of undo_log
-- ----------------------------
