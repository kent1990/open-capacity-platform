# 导出 oauth-center 的数据库结构
CREATE DATABASE IF NOT EXISTS `oauth-center` DEFAULT CHARACTER SET = utf8mb4;
Use `oauth-center`;
#
# Structure for table "oauth_client_details"
#

DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `client_id` varchar(48) NOT NULL COMMENT '应用标识',
  `resource_ids` varchar(256) DEFAULT NULL COMMENT '资源限定串(逗号分割)',
  `client_secret` varchar(256) DEFAULT NULL COMMENT '应用密钥(bcyt) 加密',
  `client_secret_str` varchar(256) DEFAULT NULL COMMENT '应用密钥(明文)',
  `scope` varchar(256) DEFAULT NULL COMMENT '范围',
  `authorized_grant_types` varchar(256) DEFAULT NULL COMMENT '5种oauth授权方式(authorization_code,password,refresh_token,client_credentials)',
  `web_server_redirect_uri` varchar(256) DEFAULT NULL COMMENT '回调地址 ',
  `authorities` varchar(256) DEFAULT NULL COMMENT '权限',
  `access_token_validity` int(11) DEFAULT NULL COMMENT 'access_token有效期',
  `refresh_token_validity` int(11) DEFAULT NULL COMMENT 'refresh_token有效期',
  `additional_information` varchar(4096) DEFAULT '{}' COMMENT '{}',
  `autoapprove` varchar(256) DEFAULT NULL COMMENT '是否自动授权 是-true',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

#
# Data for table "oauth_client_details"
#

INSERT INTO `oauth_client_details` VALUES (1,'app',NULL,'$2a$10$i3F515wEDiB4Gvj9ym9Prui0dasRttEUQ9ink4Wpgb4zEDCAlV8zO','app','app','password,refresh_token',NULL,NULL,180000,NULL,'{}','true'),(2,'mobile','mobile,test','$2a$10$ULxRssv/4NWOc388lZFbyus3IFfsbcpG/BZOq4TRxDhsx5HHIR7Jm','mobile','all','password,refresh_token',NULL,NULL,180000,NULL,'{}','true'),(4,'webApp',NULL,'$2a$10$06msMGYRH8nrm4iVnKFNKOoddB8wOwymVhbUzw/d3ZixD7Nq8ot72','webApp','app','authorization_code,password,refresh_token,client_credentials',NULL,NULL,180000,NULL,'{}','true'),(5,'beck','','$2a$10$56LGyH.2wOFNNp3ScUkspOMdyRnenYhnWEnfI0itIFfsXsd5ZhKh.','beck','all','authorization_code,password,refresh_token,client_credentials','http://www.baidu.com','',180000,NULL,'{}','true'),(6,'owen',NULL,'$2a$10$a1ZEXiZQr604LN.wVxet.etPm6RvDs.HIaXP48J2HKRaEnZORTVwe','owen','app','authorization_code,password,refresh_token,client_credentials','http://127.0.0.1:9997/dashboard/login',NULL,180000,NULL,'{}','true');

#
# Structure for table "sys_client_service"
#

DROP TABLE IF EXISTS `sys_client_service`;
CREATE TABLE `sys_client_service` (
  `clientId` int(11) NOT NULL COMMENT '应用标识',
  `serviceId` int(11) NOT NULL COMMENT '服务权限标识',
  PRIMARY KEY (`clientId`,`serviceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "sys_client_service"
#


#
# Structure for table "sys_service"
#

DROP TABLE IF EXISTS `sys_service`;
CREATE TABLE `sys_service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) NOT NULL,
  `name` varchar(64) NOT NULL,
  `url` varchar(1024) DEFAULT NULL,
  `path` varchar(1024) DEFAULT NULL,
  `css` varchar(32) DEFAULT NULL,
  `sort` int(11) NOT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  `isMenu` int(11) DEFAULT NULL COMMENT '是否服务 1 是 2 不是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4;

#
# Data for table "sys_service"
#


INSERT INTO `sys_service` VALUES (50,-1,'认证中心',NULL,'/api-auth/',NULL,1,'2019-04-09 11:30:26','2019-04-09 11:30:26',1),(51,50,'应用保存',NULL,'/clients/saveOrUpdate',NULL,1,'2019-04-09 11:33:13','2019-04-09 11:33:23',1),(52,50,'应用查询',NULL,'/clients',NULL,2,'2019-04-09 11:33:50','2019-04-09 11:36:33',1),(53,50,'应用树',NULL,'/clients/all',NULL,3,'2019-04-09 11:34:29','2019-04-09 11:34:29',1),(54,50,'token查询',NULL,'/oauth/token/list',NULL,4,'2019-04-09 11:38:57','2019-04-09 11:38:57',1),(55,50,'前台密码模式',NULL,'/oauth/user/token',NULL,5,'2019-04-09 11:39:46','2019-04-09 11:39:46',1),(56,50,'客户端模式',NULL,'/oauth/client/token',NULL,6,'2019-04-09 11:40:35','2019-04-09 11:40:35',1),(57,50,'刷新token模式',NULL,'/oauth/refresh/token',NULL,7,'2019-04-09 11:41:11','2019-04-09 11:41:11',1),(58,50,'移除token模式',NULL,'/oauth/remove/token',NULL,8,'2019-04-09 11:41:43','2019-04-09 11:41:43',1),(59,50,'获取token有效期',NULL,'/oauth/get/token',NULL,9,'2019-04-09 11:42:21','2019-04-09 11:42:21',1),(60,50,'用户信息',NULL,'/oauth/userinfo',NULL,10,'2019-04-09 11:43:00','2019-04-09 11:45:00',1),(61,50,'服务管理',NULL,'/services/saveOrUpdate',NULL,11,'2019-04-09 11:44:22','2019-04-09 11:44:22',1),(62,50,'授权码模式',NULL,'/oauth/authorize',NULL,12,'2019-04-09 11:48:29','2019-04-09 11:49:30',1),(63,50,'后端密码模式',NULL,'/oauth/token',NULL,13,'2019-04-09 11:51:08','2019-04-09 11:51:08',1),(64,-1,'用户中心',NULL,'/api-user/',NULL,2,'2019-04-09 11:52:59','2019-04-09 11:52:59',1),(65,64,'菜单赋权',NULL,'/menus/granted',NULL,1,'2019-04-09 11:54:47','2019-04-09 11:54:47',1),(66,64,'菜单列表',NULL,'/menus/findAlls',NULL,2,'2019-04-09 11:55:27','2019-04-09 11:55:27',1),(67,64,'菜单查询',NULL,'/menus/findOnes',NULL,3,'2019-04-09 11:56:27','2019-04-09 11:56:27',1),(68,64,'菜单保存',NULL,'/menus/saveOrUpdate',NULL,4,'2019-04-09 11:57:12','2019-04-09 11:57:12',1),(69,64,'当前用户菜单列表',NULL,'/menus/current',NULL,5,'2019-04-09 11:57:39','2019-04-09 11:57:49',1),(70,64,'权限赋权',NULL,'/permissions/granted',NULL,6,'2019-04-09 11:58:40','2019-04-09 11:58:40',1),(71,64,'权限列表',NULL,'/permissions',NULL,7,'2019-04-09 11:59:10','2019-04-09 11:59:10',1),(72,64,'权限保存',NULL,'/permissions/saveOrUpdate',NULL,8,'2019-04-09 11:59:41','2019-04-09 11:59:41',1),(73,64,'角色列表',NULL,'/roles',NULL,9,'2019-04-09 12:01:17','2019-04-09 12:01:24',1);
