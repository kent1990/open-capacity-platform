# Host: 59.110.164.254  (Version 5.7.22)
# Date: 2018-12-12 22:52:42
# Generator: MySQL-Front 5.4  (Build 4.153) - http://www.mysqlfront.de/

#
# Structure for table "app"
#

DROP TABLE IF EXISTS `app`;
CREATE TABLE `app` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `AppId` varchar(500) NOT NULL DEFAULT 'default' COMMENT 'AppID',
  `Name` varchar(500) NOT NULL DEFAULT 'default' COMMENT '应用名',
  `OrgId` varchar(32) NOT NULL DEFAULT 'default' COMMENT '部门Id',
  `OrgName` varchar(64) NOT NULL DEFAULT 'default' COMMENT '部门名字',
  `OwnerName` varchar(500) NOT NULL DEFAULT 'default' COMMENT 'ownerName',
  `OwnerEmail` varchar(500) NOT NULL DEFAULT 'default' COMMENT 'ownerEmail',
  `IsDeleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
  `DataChange_CreatedBy` varchar(32) NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
  `DataChange_CreatedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `DataChange_LastModifiedBy` varchar(32) DEFAULT '' COMMENT '最后修改人邮箱前缀',
  `DataChange_LastTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`Id`),
  KEY `AppId` (`AppId`(191)),
  KEY `DataChange_LastTime` (`DataChange_LastTime`),
  KEY `IX_Name` (`Name`(191))
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='应用表';

#
# Data for table "app"
#

INSERT INTO `app` VALUES (1,'zuulservice','apollo-gateway','TEST1','样例部门1','apollo','apollo@acme.com',b'0','apollo','2018-12-12 22:33:41','apollo','2018-12-12 22:33:41');

#
# Structure for table "appnamespace"
#

DROP TABLE IF EXISTS `appnamespace`;
CREATE TABLE `appnamespace` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `Name` varchar(32) NOT NULL DEFAULT '' COMMENT 'namespace名字，注意，需要全局唯一',
  `AppId` varchar(32) NOT NULL DEFAULT '' COMMENT 'app id',
  `Format` varchar(32) NOT NULL DEFAULT 'properties' COMMENT 'namespace的format类型',
  `IsPublic` bit(1) NOT NULL DEFAULT b'0' COMMENT 'namespace是否为公共',
  `Comment` varchar(64) NOT NULL DEFAULT '' COMMENT '注释',
  `IsDeleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
  `DataChange_CreatedBy` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人邮箱前缀',
  `DataChange_CreatedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `DataChange_LastModifiedBy` varchar(32) DEFAULT '' COMMENT '最后修改人邮箱前缀',
  `DataChange_LastTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`Id`),
  KEY `IX_AppId` (`AppId`),
  KEY `Name_AppId` (`Name`,`AppId`),
  KEY `DataChange_LastTime` (`DataChange_LastTime`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='应用namespace定义';

#
# Data for table "appnamespace"
#

INSERT INTO `appnamespace` VALUES (1,'application','zuulservice','properties',b'0','default app namespace',b'0','apollo','2018-12-12 22:33:43','apollo','2018-12-12 22:33:43');

#
# Structure for table "audit"
#

DROP TABLE IF EXISTS `audit`;
CREATE TABLE `audit` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `EntityName` varchar(50) NOT NULL DEFAULT 'default' COMMENT '表名',
  `EntityId` int(10) unsigned DEFAULT NULL COMMENT '记录ID',
  `OpName` varchar(50) NOT NULL DEFAULT 'default' COMMENT '操作类型',
  `Comment` varchar(500) DEFAULT NULL COMMENT '备注',
  `IsDeleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
  `DataChange_CreatedBy` varchar(32) NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
  `DataChange_CreatedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `DataChange_LastModifiedBy` varchar(32) DEFAULT '' COMMENT '最后修改人邮箱前缀',
  `DataChange_LastTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`Id`),
  KEY `DataChange_LastTime` (`DataChange_LastTime`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COMMENT='日志审计表';

#
# Data for table "audit"
#

INSERT INTO `audit` VALUES (1,'App',1,'INSERT',NULL,b'0','apollo','2018-12-12 22:33:43',NULL,'2018-12-12 22:33:43'),(2,'AppNamespace',1,'INSERT',NULL,b'0','apollo','2018-12-12 22:33:43',NULL,'2018-12-12 22:33:43'),(3,'Cluster',1,'INSERT',NULL,b'0','apollo','2018-12-12 22:33:43',NULL,'2018-12-12 22:33:43'),(4,'Namespace',1,'INSERT',NULL,b'0','apollo','2018-12-12 22:33:43',NULL,'2018-12-12 22:33:43'),(5,'Item',1,'INSERT',NULL,b'0','apollo','2018-12-12 22:34:17',NULL,'2018-12-12 22:34:17'),(6,'Item',2,'INSERT',NULL,b'0','apollo','2018-12-12 22:34:36',NULL,'2018-12-12 22:34:36'),(7,'Item',3,'INSERT',NULL,b'0','apollo','2018-12-12 22:36:38',NULL,'2018-12-12 22:36:38'),(8,'Release',1,'INSERT',NULL,b'0','apollo','2018-12-12 22:37:17',NULL,'2018-12-12 22:37:17'),(9,'ReleaseHistory',1,'INSERT',NULL,b'0','apollo','2018-12-12 22:37:17',NULL,'2018-12-12 22:37:17'),(10,'Item',4,'INSERT',NULL,b'0','apollo','2018-12-12 22:39:34',NULL,'2018-12-12 22:39:34'),(11,'Release',2,'INSERT',NULL,b'0','apollo','2018-12-12 22:39:39',NULL,'2018-12-12 22:39:39'),(12,'ReleaseHistory',2,'INSERT',NULL,b'0','apollo','2018-12-12 22:39:39',NULL,'2018-12-12 22:39:39'),(13,'Cluster',2,'INSERT',NULL,b'0','apollo','2018-12-12 22:39:49',NULL,'2018-12-12 22:39:49'),(14,'Namespace',2,'INSERT',NULL,b'0','apollo','2018-12-12 22:39:49',NULL,'2018-12-12 22:39:49'),(15,'Namespace',2,'DELETE',NULL,b'0','apollo','2018-12-12 22:39:56',NULL,'2018-12-12 22:39:56'),(16,'Cluster',2,'DELETE',NULL,b'0','apollo','2018-12-12 22:39:57',NULL,'2018-12-12 22:39:57'),(17,'ReleaseHistory',3,'INSERT',NULL,b'0','apollo','2018-12-12 22:39:57',NULL,'2018-12-12 22:39:57'),(18,'Branch',2,'DELETE',NULL,b'0','apollo','2018-12-12 22:39:57',NULL,'2018-12-12 22:39:57'),(19,'Release',3,'INSERT',NULL,b'0','apollo','2018-12-12 22:40:25',NULL,'2018-12-12 22:40:25'),(20,'ReleaseHistory',4,'INSERT',NULL,b'0','apollo','2018-12-12 22:40:25',NULL,'2018-12-12 22:40:25'),(21,'Item',5,'INSERT',NULL,b'0','apollo','2018-12-12 22:51:19',NULL,'2018-12-12 22:51:19'),(22,'ItemSet',NULL,'INSERT',NULL,b'0','apollo','2018-12-12 22:51:19',NULL,'2018-12-12 22:51:19'),(23,'Item',2,'UPDATE',NULL,b'0','apollo','2018-12-12 22:51:20',NULL,'2018-12-12 22:51:20'),(24,'Item',3,'UPDATE',NULL,b'0','apollo','2018-12-12 22:51:20',NULL,'2018-12-12 22:51:20'),(25,'Item',4,'UPDATE',NULL,b'0','apollo','2018-12-12 22:51:21',NULL,'2018-12-12 22:51:21'),(26,'ItemSet',NULL,'UPDATE',NULL,b'0','apollo','2018-12-12 22:51:21',NULL,'2018-12-12 22:51:21'),(27,'Release',4,'INSERT',NULL,b'0','apollo','2018-12-12 22:51:24',NULL,'2018-12-12 22:51:24'),(28,'ReleaseHistory',5,'INSERT',NULL,b'0','apollo','2018-12-12 22:51:24',NULL,'2018-12-12 22:51:24');

#
# Structure for table "cluster"
#

DROP TABLE IF EXISTS `cluster`;
CREATE TABLE `cluster` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `Name` varchar(32) NOT NULL DEFAULT '' COMMENT '集群名字',
  `AppId` varchar(32) NOT NULL DEFAULT '' COMMENT 'App id',
  `ParentClusterId` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '父cluster',
  `IsDeleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
  `DataChange_CreatedBy` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人邮箱前缀',
  `DataChange_CreatedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `DataChange_LastModifiedBy` varchar(32) DEFAULT '' COMMENT '最后修改人邮箱前缀',
  `DataChange_LastTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`Id`),
  KEY `IX_AppId_Name` (`AppId`,`Name`),
  KEY `IX_ParentClusterId` (`ParentClusterId`),
  KEY `DataChange_LastTime` (`DataChange_LastTime`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='集群';

#
# Data for table "cluster"
#

INSERT INTO `cluster` VALUES (1,'default','zuulservice',0,b'0','apollo','2018-12-12 22:33:43','apollo','2018-12-12 22:33:43'),(2,'20181212223948-7231dc6a5ab92209','zuulservice',1,b'1','apollo','2018-12-12 22:39:49','apollo','2018-12-12 22:39:57');

#
# Structure for table "commit"
#

DROP TABLE IF EXISTS `commit`;
CREATE TABLE `commit` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ChangeSets` longtext NOT NULL COMMENT '修改变更集',
  `AppId` varchar(500) NOT NULL DEFAULT 'default' COMMENT 'AppID',
  `ClusterName` varchar(500) NOT NULL DEFAULT 'default' COMMENT 'ClusterName',
  `NamespaceName` varchar(500) NOT NULL DEFAULT 'default' COMMENT 'namespaceName',
  `Comment` varchar(500) DEFAULT NULL COMMENT '备注',
  `IsDeleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
  `DataChange_CreatedBy` varchar(32) NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
  `DataChange_CreatedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `DataChange_LastModifiedBy` varchar(32) DEFAULT '' COMMENT '最后修改人邮箱前缀',
  `DataChange_LastTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`Id`),
  KEY `DataChange_LastTime` (`DataChange_LastTime`),
  KEY `AppId` (`AppId`(191)),
  KEY `ClusterName` (`ClusterName`(191)),
  KEY `NamespaceName` (`NamespaceName`(191))
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='commit 历史表';

#
# Data for table "commit"
#

INSERT INTO `commit` VALUES (1,'{\"createItems\":[{\"namespaceId\":1,\"key\":\"server.port\",\"value\":\"9999\",\"lineNum\":1,\"id\":1,\"isDeleted\":false,\"dataChangeCreatedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2018-12-12 22:34:16\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeLastModifiedTime\":\"2018-12-12 22:34:16\"}],\"updateItems\":[],\"deleteItems\":[]}','zuulservice','default','application',NULL,b'0','apollo','2018-12-12 22:34:17','apollo','2018-12-12 22:34:17'),(2,'{\"createItems\":[{\"namespaceId\":1,\"key\":\"zuul.routes.test163.path\",\"value\":\"http://www.163.com\",\"lineNum\":2,\"id\":2,\"isDeleted\":false,\"dataChangeCreatedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2018-12-12 22:34:36\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeLastModifiedTime\":\"2018-12-12 22:34:36\"}],\"updateItems\":[],\"deleteItems\":[]}','zuulservice','default','application',NULL,b'0','apollo','2018-12-12 22:34:36','apollo','2018-12-12 22:34:36'),(3,'{\"createItems\":[{\"namespaceId\":1,\"key\":\"eureka.client.serviceUrl.defaultZone\",\"value\":\"http://192.168.0.78:1111/eureka/\",\"lineNum\":3,\"id\":3,\"isDeleted\":false,\"dataChangeCreatedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2018-12-12 22:36:37\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeLastModifiedTime\":\"2018-12-12 22:36:38\"}],\"updateItems\":[],\"deleteItems\":[]}','zuulservice','default','application',NULL,b'0','apollo','2018-12-12 22:36:38','apollo','2018-12-12 22:36:38'),(4,'{\"createItems\":[{\"namespaceId\":1,\"key\":\"spring.application.name\",\"value\":\"sop-api-gateway\",\"lineNum\":4,\"id\":4,\"isDeleted\":false,\"dataChangeCreatedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2018-12-12 22:39:34\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeLastModifiedTime\":\"2018-12-12 22:39:34\"}],\"updateItems\":[],\"deleteItems\":[]}','zuulservice','default','application',NULL,b'0','apollo','2018-12-12 22:39:35','apollo','2018-12-12 22:39:35'),(5,'{\"createItems\":[{\"namespaceId\":1,\"key\":\"zuul.routes.test163.url\",\"value\":\"http://www.163.com\",\"comment\":\"\",\"lineNum\":2,\"id\":5,\"isDeleted\":false,\"dataChangeCreatedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2018-12-12 22:51:18\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeLastModifiedTime\":\"2018-12-12 22:51:20\"}],\"updateItems\":[{\"oldItem\":{\"namespaceId\":1,\"key\":\"zuul.routes.test163.path\",\"value\":\"http://www.163.com\",\"lineNum\":2,\"id\":2,\"isDeleted\":false,\"dataChangeCreatedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2018-12-12 22:34:36\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeLastModifiedTime\":\"2018-12-12 22:34:36\"},\"newItem\":{\"namespaceId\":1,\"key\":\"zuul.routes.test163.path\",\"value\":\"/test163/\",\"lineNum\":3,\"id\":2,\"isDeleted\":false,\"dataChangeCreatedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2018-12-12 22:34:36\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeLastModifiedTime\":\"2018-12-12 22:51:20\"}}],\"deleteItems\":[]}','zuulservice','default','application',NULL,b'0','apollo','2018-12-12 22:51:21','apollo','2018-12-12 22:51:21');

#
# Structure for table "grayreleaserule"
#

DROP TABLE IF EXISTS `grayreleaserule`;
CREATE TABLE `grayreleaserule` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `AppId` varchar(32) NOT NULL DEFAULT 'default' COMMENT 'AppID',
  `ClusterName` varchar(32) NOT NULL DEFAULT 'default' COMMENT 'Cluster Name',
  `NamespaceName` varchar(32) NOT NULL DEFAULT 'default' COMMENT 'Namespace Name',
  `BranchName` varchar(32) NOT NULL DEFAULT 'default' COMMENT 'branch name',
  `Rules` varchar(16000) DEFAULT '[]' COMMENT '灰度规则',
  `ReleaseId` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '灰度对应的release',
  `BranchStatus` tinyint(2) DEFAULT '1' COMMENT '灰度分支状态: 0:删除分支,1:正在使用的规则 2：全量发布',
  `IsDeleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
  `DataChange_CreatedBy` varchar(32) NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
  `DataChange_CreatedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `DataChange_LastModifiedBy` varchar(32) DEFAULT '' COMMENT '最后修改人邮箱前缀',
  `DataChange_LastTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`Id`),
  KEY `DataChange_LastTime` (`DataChange_LastTime`),
  KEY `IX_Namespace` (`AppId`,`ClusterName`,`NamespaceName`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='灰度规则表';

#
# Data for table "grayreleaserule"
#

INSERT INTO `grayreleaserule` VALUES (1,'zuulservice','default','application','20181212223948-7231dc6a5ab92209','[]',0,0,b'0','apollo','2018-12-12 22:39:56','apollo','2018-12-12 22:39:56');

#
# Structure for table "instance"
#

DROP TABLE IF EXISTS `instance`;
CREATE TABLE `instance` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增Id',
  `AppId` varchar(32) NOT NULL DEFAULT 'default' COMMENT 'AppID',
  `ClusterName` varchar(32) NOT NULL DEFAULT 'default' COMMENT 'ClusterName',
  `DataCenter` varchar(64) NOT NULL DEFAULT 'default' COMMENT 'Data Center Name',
  `Ip` varchar(32) NOT NULL DEFAULT '' COMMENT 'instance ip',
  `DataChange_CreatedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `DataChange_LastTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `IX_UNIQUE_KEY` (`AppId`,`ClusterName`,`Ip`,`DataCenter`),
  KEY `IX_IP` (`Ip`),
  KEY `IX_DataChange_LastTime` (`DataChange_LastTime`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='使用配置的应用实例';

#
# Data for table "instance"
#

INSERT INTO `instance` VALUES (1,'zuulservice','default','','192.168.0.78','2018-12-12 22:38:02','2018-12-12 22:38:02');

#
# Structure for table "instanceconfig"
#

DROP TABLE IF EXISTS `instanceconfig`;
CREATE TABLE `instanceconfig` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增Id',
  `InstanceId` int(11) unsigned DEFAULT NULL COMMENT 'Instance Id',
  `ConfigAppId` varchar(32) NOT NULL DEFAULT 'default' COMMENT 'Config App Id',
  `ConfigClusterName` varchar(32) NOT NULL DEFAULT 'default' COMMENT 'Config Cluster Name',
  `ConfigNamespaceName` varchar(32) NOT NULL DEFAULT 'default' COMMENT 'Config Namespace Name',
  `ReleaseKey` varchar(64) NOT NULL DEFAULT '' COMMENT '发布的Key',
  `ReleaseDeliveryTime` timestamp NULL DEFAULT NULL COMMENT '配置获取时间',
  `DataChange_CreatedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `DataChange_LastTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `IX_UNIQUE_KEY` (`InstanceId`,`ConfigAppId`,`ConfigNamespaceName`),
  KEY `IX_ReleaseKey` (`ReleaseKey`),
  KEY `IX_DataChange_LastTime` (`DataChange_LastTime`),
  KEY `IX_Valid_Namespace` (`ConfigAppId`,`ConfigClusterName`,`ConfigNamespaceName`,`DataChange_LastTime`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='应用实例的配置信息';

#
# Data for table "instanceconfig"
#

INSERT INTO `instanceconfig` VALUES (1,1,'zuulservice','default','application','20181212225123-7231dc6a5ab9220b','2018-12-12 22:51:25','2018-12-12 22:38:02','2018-12-12 22:51:26');

#
# Structure for table "item"
#

DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增Id',
  `NamespaceId` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '集群NamespaceId',
  `Key` varchar(128) NOT NULL DEFAULT 'default' COMMENT '配置项Key',
  `Value` longtext NOT NULL COMMENT '配置项值',
  `Comment` varchar(1024) DEFAULT '' COMMENT '注释',
  `LineNum` int(10) unsigned DEFAULT '0' COMMENT '行号',
  `IsDeleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
  `DataChange_CreatedBy` varchar(32) NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
  `DataChange_CreatedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `DataChange_LastModifiedBy` varchar(32) DEFAULT '' COMMENT '最后修改人邮箱前缀',
  `DataChange_LastTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`Id`),
  KEY `IX_GroupId` (`NamespaceId`),
  KEY `DataChange_LastTime` (`DataChange_LastTime`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='配置项目';

#
# Data for table "item"
#

INSERT INTO `item` VALUES (1,1,'server.port','9999',NULL,1,b'0','apollo','2018-12-12 22:34:17','apollo','2018-12-12 22:34:17'),(2,1,'zuul.routes.test163.path','/test163/',NULL,3,b'0','apollo','2018-12-12 22:34:36','apollo','2018-12-12 22:51:21'),(3,1,'eureka.client.serviceUrl.defaultZone','http://192.168.0.78:1111/eureka/',NULL,4,b'0','apollo','2018-12-12 22:36:38','apollo','2018-12-12 22:51:21'),(4,1,'spring.application.name','sop-api-gateway',NULL,5,b'0','apollo','2018-12-12 22:39:34','apollo','2018-12-12 22:51:21'),(5,1,'zuul.routes.test163.url','http://www.163.com','',2,b'0','apollo','2018-12-12 22:51:19','apollo','2018-12-12 22:51:19');

#
# Structure for table "namespace"
#

DROP TABLE IF EXISTS `namespace`;
CREATE TABLE `namespace` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `AppId` varchar(500) NOT NULL DEFAULT 'default' COMMENT 'AppID',
  `ClusterName` varchar(500) NOT NULL DEFAULT 'default' COMMENT 'Cluster Name',
  `NamespaceName` varchar(500) NOT NULL DEFAULT 'default' COMMENT 'Namespace Name',
  `IsDeleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
  `DataChange_CreatedBy` varchar(32) NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
  `DataChange_CreatedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `DataChange_LastModifiedBy` varchar(32) DEFAULT '' COMMENT '最后修改人邮箱前缀',
  `DataChange_LastTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`Id`),
  KEY `AppId_ClusterName_NamespaceName` (`AppId`(191),`ClusterName`(191),`NamespaceName`(191)),
  KEY `DataChange_LastTime` (`DataChange_LastTime`),
  KEY `IX_NamespaceName` (`NamespaceName`(191))
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='命名空间';

#
# Data for table "namespace"
#

INSERT INTO `namespace` VALUES (1,'zuulservice','default','application',b'0','apollo','2018-12-12 22:33:43','apollo','2018-12-12 22:33:43'),(2,'zuulservice','20181212223948-7231dc6a5ab92209','application',b'1','apollo','2018-12-12 22:39:49','apollo','2018-12-12 22:39:57');

#
# Structure for table "namespacelock"
#

DROP TABLE IF EXISTS `namespacelock`;
CREATE TABLE `namespacelock` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `NamespaceId` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '集群NamespaceId',
  `DataChange_CreatedBy` varchar(32) NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
  `DataChange_CreatedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `DataChange_LastModifiedBy` varchar(32) DEFAULT 'default' COMMENT '最后修改人邮箱前缀',
  `DataChange_LastTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `IsDeleted` bit(1) DEFAULT b'0' COMMENT '软删除',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `IX_NamespaceId` (`NamespaceId`),
  KEY `DataChange_LastTime` (`DataChange_LastTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='namespace的编辑锁';

#
# Data for table "namespacelock"
#


#
# Structure for table "release"
#

DROP TABLE IF EXISTS `release`;
CREATE TABLE `release` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `ReleaseKey` varchar(64) NOT NULL DEFAULT '' COMMENT '发布的Key',
  `Name` varchar(64) NOT NULL DEFAULT 'default' COMMENT '发布名字',
  `Comment` varchar(256) DEFAULT NULL COMMENT '发布说明',
  `AppId` varchar(500) NOT NULL DEFAULT 'default' COMMENT 'AppID',
  `ClusterName` varchar(500) NOT NULL DEFAULT 'default' COMMENT 'ClusterName',
  `NamespaceName` varchar(500) NOT NULL DEFAULT 'default' COMMENT 'namespaceName',
  `Configurations` longtext NOT NULL COMMENT '发布配置',
  `IsAbandoned` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否废弃',
  `IsDeleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
  `DataChange_CreatedBy` varchar(32) NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
  `DataChange_CreatedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `DataChange_LastModifiedBy` varchar(32) DEFAULT '' COMMENT '最后修改人邮箱前缀',
  `DataChange_LastTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`Id`),
  KEY `AppId_ClusterName_GroupName` (`AppId`(191),`ClusterName`(191),`NamespaceName`(191)),
  KEY `DataChange_LastTime` (`DataChange_LastTime`),
  KEY `IX_ReleaseKey` (`ReleaseKey`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='发布';

#
# Data for table "release"
#

INSERT INTO `release` VALUES (1,'20181212223716-7231dc6a5ab92207','20181212223713-release','','zuulservice','default','application','{\"server.port\":\"9999\",\"zuul.routes.test163.path\":\"http://www.163.com\",\"eureka.client.serviceUrl.defaultZone\":\"http://192.168.0.78:1111/eureka/\"}',b'0',b'0','apollo','2018-12-12 22:37:17','apollo','2018-12-12 22:37:17'),(2,'20181212223938-7231dc6a5ab92208','20181212223936-release','','zuulservice','default','application','{\"spring.application.name\":\"sop-api-gateway\",\"server.port\":\"9999\",\"zuul.routes.test163.path\":\"http://www.163.com\",\"eureka.client.serviceUrl.defaultZone\":\"http://192.168.0.78:1111/eureka/\"}',b'0',b'0','apollo','2018-12-12 22:39:39','apollo','2018-12-12 22:39:39'),(3,'20181212224025-7231dc6a5ab9220a','20181212224022-release','','zuulservice','default','application','{\"spring.application.name\":\"sop-api-gateway\",\"server.port\":\"9999\",\"zuul.routes.test163.path\":\"http://www.163.com\",\"eureka.client.serviceUrl.defaultZone\":\"http://192.168.0.78:1111/eureka/\"}',b'0',b'0','apollo','2018-12-12 22:40:25','apollo','2018-12-12 22:40:25'),(4,'20181212225123-7231dc6a5ab9220b','20181212225120-release','','zuulservice','default','application','{\"zuul.routes.test163.url\":\"http://www.163.com\",\"spring.application.name\":\"sop-api-gateway\",\"server.port\":\"9999\",\"zuul.routes.test163.path\":\"/test163/\",\"eureka.client.serviceUrl.defaultZone\":\"http://192.168.0.78:1111/eureka/\"}',b'0',b'0','apollo','2018-12-12 22:51:23','apollo','2018-12-12 22:51:23');

#
# Structure for table "releasehistory"
#

DROP TABLE IF EXISTS `releasehistory`;
CREATE TABLE `releasehistory` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增Id',
  `AppId` varchar(32) NOT NULL DEFAULT 'default' COMMENT 'AppID',
  `ClusterName` varchar(32) NOT NULL DEFAULT 'default' COMMENT 'ClusterName',
  `NamespaceName` varchar(32) NOT NULL DEFAULT 'default' COMMENT 'namespaceName',
  `BranchName` varchar(32) NOT NULL DEFAULT 'default' COMMENT '发布分支名',
  `ReleaseId` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '关联的Release Id',
  `PreviousReleaseId` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '前一次发布的ReleaseId',
  `Operation` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '发布类型，0: 普通发布，1: 回滚，2: 灰度发布，3: 灰度规则更新，4: 灰度合并回主分支发布，5: 主分支发布灰度自动发布，6: 主分支回滚灰度自动发布，7: 放弃灰度',
  `OperationContext` longtext NOT NULL COMMENT '发布上下文信息',
  `IsDeleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
  `DataChange_CreatedBy` varchar(32) NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
  `DataChange_CreatedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `DataChange_LastModifiedBy` varchar(32) DEFAULT '' COMMENT '最后修改人邮箱前缀',
  `DataChange_LastTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`Id`),
  KEY `IX_Namespace` (`AppId`,`ClusterName`,`NamespaceName`,`BranchName`),
  KEY `IX_ReleaseId` (`ReleaseId`),
  KEY `IX_DataChange_LastTime` (`DataChange_LastTime`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='发布历史';

#
# Data for table "releasehistory"
#

INSERT INTO `releasehistory` VALUES (1,'zuulservice','default','application','default',1,0,0,'{\"isEmergencyPublish\":false}',b'0','apollo','2018-12-12 22:37:17','apollo','2018-12-12 22:37:17'),(2,'zuulservice','default','application','default',2,1,0,'{\"isEmergencyPublish\":false}',b'0','apollo','2018-12-12 22:39:39','apollo','2018-12-12 22:39:39'),(3,'zuulservice','default','application','20181212223948-7231dc6a5ab92209',0,0,7,'{}',b'0','apollo','2018-12-12 22:39:57','apollo','2018-12-12 22:39:57'),(4,'zuulservice','default','application','default',3,2,0,'{\"isEmergencyPublish\":false}',b'0','apollo','2018-12-12 22:40:25','apollo','2018-12-12 22:40:25'),(5,'zuulservice','default','application','default',4,3,0,'{\"isEmergencyPublish\":false}',b'0','apollo','2018-12-12 22:51:24','apollo','2018-12-12 22:51:24');

#
# Structure for table "releasemessage"
#

DROP TABLE IF EXISTS `releasemessage`;
CREATE TABLE `releasemessage` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `Message` varchar(1024) NOT NULL DEFAULT '' COMMENT '发布的消息内容',
  `DataChange_LastTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`Id`),
  KEY `DataChange_LastTime` (`DataChange_LastTime`),
  KEY `IX_Message` (`Message`(191))
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='发布消息';

#
# Data for table "releasemessage"
#

INSERT INTO `releasemessage` VALUES (3,'zuulservice+20181212223948-7231dc6a5ab92209+application','2018-12-12 22:39:56'),(6,'zuulservice+default+application','2018-12-12 22:51:24');

#
# Structure for table "serverconfig"
#

DROP TABLE IF EXISTS `serverconfig`;
CREATE TABLE `serverconfig` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增Id',
  `Key` varchar(64) NOT NULL DEFAULT 'default' COMMENT '配置项Key',
  `Cluster` varchar(32) NOT NULL DEFAULT 'default' COMMENT '配置对应的集群，default为不针对特定的集群',
  `Value` varchar(2048) NOT NULL DEFAULT 'default' COMMENT '配置项值',
  `Comment` varchar(1024) DEFAULT '' COMMENT '注释',
  `IsDeleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
  `DataChange_CreatedBy` varchar(32) NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
  `DataChange_CreatedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `DataChange_LastModifiedBy` varchar(32) DEFAULT '' COMMENT '最后修改人邮箱前缀',
  `DataChange_LastTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`Id`),
  KEY `IX_Key` (`Key`),
  KEY `DataChange_LastTime` (`DataChange_LastTime`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='配置服务自身配置';

#
# Data for table "serverconfig"
#

INSERT INTO `serverconfig` VALUES (1,'eureka.service.url','default','http://127.0.0.1:1111/eureka/','Eureka服务Url，多个service以英文逗号分隔',b'0','default','2018-12-11 14:05:44','','2018-12-11 14:14:21'),(2,'namespace.lock.switch','default','false','一次发布只能有一个人修改开关',b'0','default','2018-12-11 14:05:44','','2018-12-11 14:05:44'),(3,'item.key.length.limit','default','128','item key 最大长度限制',b'0','default','2018-12-11 14:05:44','','2018-12-11 14:05:44'),(4,'item.value.length.limit','default','20000','item value最大长度限制',b'0','default','2018-12-11 14:05:44','','2018-12-11 14:05:44'),(5,'config-service.cache.enabled','default','false','ConfigService是否开启缓存，开启后能提高性能，但是会增大内存消耗！',b'0','default','2018-12-11 14:05:44','','2018-12-11 14:05:44');
