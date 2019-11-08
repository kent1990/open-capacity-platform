create database if not exists `iyydb_job` default character set utf8 collate utf8_general_ci;
use `iyydb_job`;

 


set foreign_key_checks=0;



#
# structure for table "XXL_JOB_QRTZ_CALENDARS"
#

create table `xxl_job_qrtz_calendars` (
  `sched_name` varchar(120) not null,
  `calendar_name` varchar(200) not null,
  `calendar` blob not null,
  primary key (`sched_name`,`calendar_name`)
) engine=innodb default charset=utf8;

#
# data for table "XXL_JOB_QRTZ_CALENDARS"
#


#
# structure for table "XXL_JOB_QRTZ_FIRED_TRIGGERS"
#

create table `xxl_job_qrtz_fired_triggers` (
  `sched_name` varchar(120) not null,
  `entry_id` varchar(95) not null,
  `trigger_name` varchar(200) not null,
  `trigger_group` varchar(200) not null,
  `instance_name` varchar(200) not null,
  `fired_time` bigint(13) not null,
  `sched_time` bigint(13) not null,
  `priority` int(11) not null,
  `state` varchar(16) not null,
  `job_name` varchar(200) default null,
  `job_group` varchar(200) default null,
  `is_nonconcurrent` varchar(1) default null,
  `requests_recovery` varchar(1) default null,
  primary key (`sched_name`,`entry_id`)
) engine=innodb default charset=utf8;

#
# data for table "XXL_JOB_QRTZ_FIRED_TRIGGERS"
#

insert into `xxl_job_qrtz_fired_triggers` values ('schedulerFactoryBean','owen15263060811091526306081114','5','1','owen1526306081109',1526306155083,1526306160000,5,'ACQUIRED',null,null,'0','0');

#
# structure for table "XXL_JOB_QRTZ_JOB_DETAILS"
#

create table `xxl_job_qrtz_job_details` (
  `sched_name` varchar(120) not null,
  `job_name` varchar(200) not null,
  `job_group` varchar(200) not null,
  `description` varchar(250) default null,
  `job_class_name` varchar(250) not null,
  `is_durable` varchar(1) not null,
  `is_nonconcurrent` varchar(1) not null,
  `is_update_data` varchar(1) not null,
  `requests_recovery` varchar(1) not null,
  `job_data` blob,
  primary key (`sched_name`,`job_name`,`job_group`)
) engine=innodb default charset=utf8;

#
# data for table "XXL_JOB_QRTZ_JOB_DETAILS"
#

insert into `xxl_job_qrtz_job_details` values ('quartzScheduler','3','1',null,'com.open.capacity.core.jobbean.RemoteHttpJobBean','0','0','0','0',x'ACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800'),('quartzScheduler','4','1',null,'com.open.capacity.core.jobbean.RemoteHttpJobBean','0','0','0','0',x'ACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800'),('schedulerFactoryBean','5','1',null,'com.xxl.job.admin.core.jobbean.RemoteHttpJobBean','0','0','0','0',x'ACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800'),('schedulerFactoryBean','6','1',null,'com.xxl.job.admin.core.jobbean.RemoteHttpJobBean','0','0','0','0',x'ACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800');

#
# structure for table "XXL_JOB_QRTZ_LOCKS"
#

create table `xxl_job_qrtz_locks` (
  `sched_name` varchar(120) not null,
  `lock_name` varchar(40) not null,
  primary key (`sched_name`,`lock_name`)
) engine=innodb default charset=utf8;

#
# data for table "XXL_JOB_QRTZ_LOCKS"
#

insert into `xxl_job_qrtz_locks` values ('quartzScheduler','STATE_ACCESS'),('quartzScheduler','TRIGGER_ACCESS'),('schedulerFactoryBean','STATE_ACCESS'),('schedulerFactoryBean','TRIGGER_ACCESS');

#
# structure for table "XXL_JOB_QRTZ_PAUSED_TRIGGER_GRPS"
#

create table `xxl_job_qrtz_paused_trigger_grps` (
  `sched_name` varchar(120) not null,
  `trigger_group` varchar(200) not null,
  primary key (`sched_name`,`trigger_group`)
) engine=innodb default charset=utf8;

#
# data for table "XXL_JOB_QRTZ_PAUSED_TRIGGER_GRPS"
#


#
# structure for table "XXL_JOB_QRTZ_SCHEDULER_STATE"
#

create table `xxl_job_qrtz_scheduler_state` (
  `sched_name` varchar(120) not null,
  `instance_name` varchar(200) not null,
  `last_checkin_time` bigint(13) not null,
  `checkin_interval` bigint(13) not null,
  primary key (`sched_name`,`instance_name`)
) engine=innodb default charset=utf8;

#
# data for table "XXL_JOB_QRTZ_SCHEDULER_STATE"
#

insert into `xxl_job_qrtz_scheduler_state` values ('quartzScheduler','USERCHI-E29PEDA1525876570951',1525876886761,5000),('schedulerFactoryBean','owen1526306081109',1526306155345,5000);

#
# structure for table "XXL_JOB_QRTZ_TRIGGER_GROUP"
#

create table `xxl_job_qrtz_trigger_group` (
  `id` int(11) not null auto_increment,
  `app_name` varchar(64) not null comment '执行器AppName',
  `title` varchar(12) not null comment '执行器名称',
  `order` tinyint(4) not null default '0' comment '排序',
  `address_type` tinyint(4) not null default '0' comment '执行器地址类型：0=自动注册、1=手动录入',
  `address_list` varchar(512) default null comment '执行器地址列表，多地址逗号分隔',
  primary key (`id`)
) engine=innodb auto_increment=2 default charset=utf8;

#
# data for table "XXL_JOB_QRTZ_TRIGGER_GROUP"
#

insert into `xxl_job_qrtz_trigger_group` values (1,'open-xxl-job-demo','示例执行器',1,0,null);

#
# structure for table "XXL_JOB_QRTZ_TRIGGER_INFO"
#

create table `xxl_job_qrtz_trigger_info` (
  `id` int(11) not null auto_increment,
  `job_group` int(11) not null comment '执行器主键ID',
  `job_cron` varchar(128) not null comment '任务执行CRON',
  `job_desc` varchar(255) not null,
  `add_time` datetime default null,
  `update_time` datetime default null,
  `author` varchar(64) default null comment '作者',
  `alarm_email` varchar(255) default null comment '报警邮件',
  `executor_route_strategy` varchar(50) default null comment '执行器路由策略',
  `executor_handler` varchar(255) default null comment '执行器任务handler',
  `executor_param` varchar(512) default null comment '执行器任务参数',
  `executor_block_strategy` varchar(50) default null comment '阻塞处理策略',
  `executor_fail_strategy` varchar(50) default null comment '失败处理策略',
  `glue_type` varchar(50) not null comment 'GLUE类型',
  `glue_source` text comment 'GLUE源代码',
  `glue_remark` varchar(128) default null comment 'GLUE备注',
  `glue_updatetime` datetime default null comment 'GLUE更新时间',
  `child_jobid` varchar(255) default null comment '子任务ID，多个逗号分隔',
  primary key (`id`)
) engine=innodb auto_increment=5 default charset=utf8;

#
# data for table "XXL_JOB_QRTZ_TRIGGER_INFO"
#

insert into `xxl_job_qrtz_trigger_info` values (5,1,'*/5 * * * * ?','有参任务','2018-05-12 06:30:38','2018-05-12 06:30:38','merlin','624191343@qq.com','FIRST','demoJobHandler','demoJobHandler','SERIAL_EXECUTION','FAIL_ALARM','BEAN','','GLUE代码初始化','2018-05-12 06:30:38',null),(6,1,'*/5 * * * * ?','无参任务','2018-05-12 06:32:16','2018-05-12 06:32:16','merlin','624191343@qq.com','FIRST','demoJobHandler','','SERIAL_EXECUTION','FAIL_ALARM','BEAN','','GLUE代码初始化','2018-05-12 06:32:16',null);

#
# structure for table "XXL_JOB_QRTZ_TRIGGER_LOG"
#

create table `xxl_job_qrtz_trigger_log` (
  `id` int(11) not null auto_increment,
  `job_group` int(11) not null comment '执行器主键ID',
  `job_id` int(11) not null comment '任务，主键ID',
  `glue_type` varchar(50) default null comment 'GLUE类型',
  `executor_address` varchar(255) default null comment '执行器地址，本次执行的地址',
  `executor_handler` varchar(255) default null comment '执行器任务handler',
  `executor_param` varchar(512) default null comment '执行器任务参数',
  `trigger_time` datetime default null comment '调度-时间',
  `trigger_code` int(11) not null comment '调度-结果',
  `trigger_msg` varchar(2048) default null comment '调度-日志',
  `handle_time` datetime default null comment '执行-时间',
  `handle_code` int(11) not null comment '执行-状态',
  `handle_msg` varchar(2048) default null comment '执行-日志',
  primary key (`id`)
) engine=innodb auto_increment=69 default charset=utf8;

#
# data for table "XXL_JOB_QRTZ_TRIGGER_LOG"
#

insert into `xxl_job_qrtz_trigger_log` values (1,1,5,'BEAN','192.168.45.1:9999','demoJobHandler','demoJobHandler','2018-05-14 21:51:25',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:51:36',200,''),(2,1,5,'BEAN','192.168.45.1:9999','demoJobHandler','demoJobHandler','2018-05-14 21:51:30',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:51:46',200,''),(3,1,5,'BEAN','192.168.45.1:9999','demoJobHandler','demoJobHandler','2018-05-14 21:51:35',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:51:56',200,''),(4,1,5,'BEAN','192.168.45.1:9999','demoJobHandler','demoJobHandler','2018-05-14 21:51:40',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:52:06',200,''),(5,1,5,'BEAN','192.168.45.1:9999','demoJobHandler','demoJobHandler','2018-05-14 21:51:45',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:52:16',200,''),(6,1,5,'BEAN','192.168.45.1:9999','demoJobHandler','demoJobHandler','2018-05-14 21:51:50',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:52:26',200,''),(7,1,5,'BEAN','192.168.45.1:9999','demoJobHandler','demoJobHandler','2018-05-14 21:51:55',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:52:36',200,''),(8,1,5,'BEAN','192.168.45.1:9999','demoJobHandler','demoJobHandler','2018-05-14 21:52:00',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:52:46',200,''),(9,1,5,'BEAN','192.168.45.1:9999','demoJobHandler','demoJobHandler','2018-05-14 21:52:05',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:52:56',200,''),(10,1,5,'BEAN','192.168.45.1:9999','demoJobHandler','demoJobHandler','2018-05-14 21:52:10',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:53:06',200,''),(11,1,5,'BEAN','192.168.45.1:9999','demoJobHandler','demoJobHandler','2018-05-14 21:52:15',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:53:16',200,''),(12,1,5,'BEAN','192.168.45.1:9999','demoJobHandler','demoJobHandler','2018-05-14 21:52:20',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:53:26',200,''),(13,1,5,'BEAN','192.168.45.1:9999','demoJobHandler','demoJobHandler','2018-05-14 21:52:25',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:53:36',200,''),(14,1,5,'BEAN','192.168.45.1:9999','demoJobHandler','demoJobHandler','2018-05-14 21:52:30',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:53:46',200,''),(15,1,5,'BEAN','192.168.45.1:9999','demoJobHandler','demoJobHandler','2018-05-14 21:52:35',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:53:56',200,''),(16,1,5,'BEAN','192.168.45.1:9999','demoJobHandler','demoJobHandler','2018-05-14 21:52:40',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:54:06',500,'Web容器销毁终止 [业务运行中，被强制终止]'),(17,1,5,'BEAN','192.168.45.1:9999','demoJobHandler','demoJobHandler','2018-05-14 21:52:45',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:54:06',500,'Web容器销毁终止 [任务尚未执行，在调度队列中被终止]'),(18,1,5,'BEAN','192.168.45.1:9999','demoJobHandler','demoJobHandler','2018-05-14 21:52:50',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:54:06',500,'Web容器销毁终止 [任务尚未执行，在调度队列中被终止]'),(19,1,5,'BEAN','192.168.45.1:9999','demoJobHandler','demoJobHandler','2018-05-14 21:52:55',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:54:06',500,'Web容器销毁终止 [任务尚未执行，在调度队列中被终止]'),(20,1,5,'BEAN','192.168.45.1:9999','demoJobHandler','demoJobHandler','2018-05-14 21:53:00',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:54:06',500,'Web容器销毁终止 [任务尚未执行，在调度队列中被终止]'),(21,1,6,'BEAN','192.168.45.1:9999','demoJobHandler','','2018-05-14 21:53:01',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:53:11',200,''),(22,1,5,'BEAN','192.168.45.1:9999','demoJobHandler','demoJobHandler','2018-05-14 21:53:05',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:54:06',500,'Web容器销毁终止 [任务尚未执行，在调度队列中被终止]'),(23,1,6,'BEAN','192.168.45.1:9999','demoJobHandler','','2018-05-14 21:53:05',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:53:21',200,''),(24,1,5,'BEAN','192.168.45.1:9999','demoJobHandler','demoJobHandler','2018-05-14 21:53:10',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:54:06',500,'Web容器销毁终止 [任务尚未执行，在调度队列中被终止]'),(25,1,6,'BEAN','192.168.45.1:9999','demoJobHandler','','2018-05-14 21:53:10',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:53:31',200,''),(26,1,5,'BEAN','192.168.45.1:9999','demoJobHandler','demoJobHandler','2018-05-14 21:53:15',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:54:06',500,'Web容器销毁终止 [任务尚未执行，在调度队列中被终止]'),(27,1,6,'BEAN','192.168.45.1:9999','demoJobHandler','','2018-05-14 21:53:15',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:53:41',200,''),(28,1,5,'BEAN','192.168.45.1:9999','demoJobHandler','demoJobHandler','2018-05-14 21:53:20',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:54:06',500,'Web容器销毁终止 [任务尚未执行，在调度队列中被终止]'),(29,1,6,'BEAN','192.168.45.1:9999','demoJobHandler','','2018-05-14 21:53:20',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:53:51',200,''),(30,1,5,'BEAN','192.168.45.1:9999','demoJobHandler','demoJobHandler','2018-05-14 21:53:25',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:54:06',500,'Web容器销毁终止 [任务尚未执行，在调度队列中被终止]'),(31,1,6,'BEAN','192.168.45.1:9999','demoJobHandler','','2018-05-14 21:53:25',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:54:01',200,''),(32,1,5,'BEAN','192.168.45.1:9999','demoJobHandler','demoJobHandler','2018-05-14 21:53:30',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:54:06',500,'Web容器销毁终止 [任务尚未执行，在调度队列中被终止]'),(33,1,6,'BEAN','192.168.45.1:9999','demoJobHandler','','2018-05-14 21:53:30',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:54:06',500,'Web容器销毁终止 [业务运行中，被强制终止]'),(34,1,6,'BEAN','192.168.45.1:9999','demoJobHandler','','2018-05-14 21:53:35',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:54:06',500,'Web容器销毁终止 [任务尚未执行，在调度队列中被终止]'),(35,1,6,'BEAN','192.168.45.1:9999','demoJobHandler','','2018-05-14 21:53:40',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:54:06',500,'Web容器销毁终止 [任务尚未执行，在调度队列中被终止]'),(36,1,6,'BEAN','192.168.45.1:9999','demoJobHandler','','2018-05-14 21:53:45',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:54:06',500,'Web容器销毁终止 [任务尚未执行，在调度队列中被终止]'),(37,1,6,'BEAN','192.168.45.1:9999','demoJobHandler','','2018-05-14 21:53:50',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:54:06',500,'Web容器销毁终止 [任务尚未执行，在调度队列中被终止]'),(38,1,6,'BEAN','192.168.45.1:9999','demoJobHandler','','2018-05-14 21:53:55',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:54:06',500,'Web容器销毁终止 [任务尚未执行，在调度队列中被终止]'),(39,1,6,'BEAN','192.168.45.1:9999','demoJobHandler','','2018-05-14 21:54:00',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:54:06',500,'Web容器销毁终止 [任务尚未执行，在调度队列中被终止]'),(40,1,6,'BEAN','192.168.45.1:9999','demoJobHandler','','2018-05-14 21:54:05',200,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：200<br>msg：null','2018-05-14 21:54:06',500,'Web容器销毁终止 [任务尚未执行，在调度队列中被终止]'),(41,1,6,'BEAN','192.168.45.1:9999','demoJobHandler','','2018-05-14 21:54:10',500,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.45.1:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.45.1:9999<br>code：500<br>msg：java.lang.RuntimeException: Network request error: Connect to 192.168.45.1:9999 [/192.168.45.1] failed: Connection refused: connect',null,0,null),(42,1,6,'BEAN',null,'demoJobHandler','','2018-05-14 21:54:46',500,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br>----------------------<br>调度失败：执行器地址为空',null,0,null),(43,1,6,'BEAN',null,'demoJobHandler','','2018-05-14 21:54:46',500,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br>----------------------<br>调度失败：执行器地址为空',null,0,null),(44,1,6,'BEAN',null,'demoJobHandler','','2018-05-14 21:54:46',500,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br>----------------------<br>调度失败：执行器地址为空',null,0,null),(45,1,6,'BEAN',null,'demoJobHandler','','2018-05-14 21:54:46',500,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br>----------------------<br>调度失败：执行器地址为空',null,0,null),(46,1,6,'BEAN',null,'demoJobHandler','','2018-05-14 21:54:46',500,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br>----------------------<br>调度失败：执行器地址为空',null,0,null),(47,1,6,'BEAN',null,'demoJobHandler','','2018-05-14 21:54:46',500,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br>----------------------<br>调度失败：执行器地址为空',null,0,null),(48,1,6,'BEAN',null,'demoJobHandler','','2018-05-14 21:54:46',500,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br>----------------------<br>调度失败：执行器地址为空',null,0,null),(49,1,6,'BEAN',null,'demoJobHandler','','2018-05-14 21:54:50',500,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br>----------------------<br>调度失败：执行器地址为空',null,0,null),(50,1,6,'BEAN',null,'demoJobHandler','','2018-05-14 21:54:55',500,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br>----------------------<br>调度失败：执行器地址为空',null,0,null),(51,1,6,'BEAN',null,'demoJobHandler','','2018-05-14 21:55:00',500,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br>----------------------<br>调度失败：执行器地址为空',null,0,null),(52,1,6,'BEAN',null,'demoJobHandler','','2018-05-14 21:55:05',500,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br>----------------------<br>调度失败：执行器地址为空',null,0,null),(53,1,5,'BEAN',null,'demoJobHandler','demoJobHandler','2018-05-14 21:55:07',500,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br>----------------------<br>调度失败：执行器地址为空',null,0,null),(54,1,6,'BEAN',null,'demoJobHandler','','2018-05-14 21:55:10',500,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br>----------------------<br>调度失败：执行器地址为空',null,0,null),(55,1,6,'BEAN',null,'demoJobHandler','','2018-05-14 21:55:15',500,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br>----------------------<br>调度失败：执行器地址为空',null,0,null),(56,1,6,'BEAN',null,'demoJobHandler','','2018-05-14 21:55:20',500,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br>----------------------<br>调度失败：执行器地址为空',null,0,null),(57,1,6,'BEAN',null,'demoJobHandler','','2018-05-14 21:55:25',500,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br>----------------------<br>调度失败：执行器地址为空',null,0,null),(58,1,6,'BEAN',null,'demoJobHandler','','2018-05-14 21:55:30',500,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br>----------------------<br>调度失败：执行器地址为空',null,0,null),(59,1,5,'BEAN',null,'demoJobHandler','demoJobHandler','2018-05-14 21:55:35',500,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br>----------------------<br>调度失败：执行器地址为空',null,0,null),(60,1,6,'BEAN',null,'demoJobHandler','','2018-05-14 21:55:35',500,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br>----------------------<br>调度失败：执行器地址为空',null,0,null),(61,1,5,'BEAN',null,'demoJobHandler','demoJobHandler','2018-05-14 21:55:40',500,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br>----------------------<br>调度失败：执行器地址为空',null,0,null),(62,1,6,'BEAN',null,'demoJobHandler','','2018-05-14 21:55:40',500,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br>----------------------<br>调度失败：执行器地址为空',null,0,null),(63,1,5,'BEAN',null,'demoJobHandler','demoJobHandler','2018-05-14 21:55:45',500,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br>----------------------<br>调度失败：执行器地址为空',null,0,null),(64,1,6,'BEAN',null,'demoJobHandler','','2018-05-14 21:55:45',500,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br>----------------------<br>调度失败：执行器地址为空',null,0,null),(65,1,5,'BEAN',null,'demoJobHandler','demoJobHandler','2018-05-14 21:55:50',500,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br>----------------------<br>调度失败：执行器地址为空',null,0,null),(66,1,6,'BEAN',null,'demoJobHandler','','2018-05-14 21:55:50',500,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br>----------------------<br>调度失败：执行器地址为空',null,0,null),(67,1,5,'BEAN',null,'demoJobHandler','demoJobHandler','2018-05-14 21:55:55',500,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br>----------------------<br>调度失败：执行器地址为空',null,0,null),(68,1,6,'BEAN',null,'demoJobHandler','','2018-05-14 21:55:55',500,'调度机器：192.168.45.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br>----------------------<br>调度失败：执行器地址为空',null,0,null);

#
# structure for table "XXL_JOB_QRTZ_TRIGGER_LOGGLUE"
#

create table `xxl_job_qrtz_trigger_logglue` (
  `id` int(11) not null auto_increment,
  `job_id` int(11) not null comment '任务，主键ID',
  `glue_type` varchar(50) default null comment 'GLUE类型',
  `glue_source` text comment 'GLUE源代码',
  `glue_remark` varchar(128) not null comment 'GLUE备注',
  `add_time` timestamp null default null,
  `update_time` timestamp null default null on update current_timestamp,
  primary key (`id`)
) engine=innodb default charset=utf8;

#
# data for table "XXL_JOB_QRTZ_TRIGGER_LOGGLUE"
#


#
# structure for table "XXL_JOB_QRTZ_TRIGGER_REGISTRY"
#

create table `xxl_job_qrtz_trigger_registry` (
  `id` int(11) not null auto_increment,
  `registry_group` varchar(255) not null,
  `registry_key` varchar(255) not null,
  `registry_value` varchar(255) not null,
  `update_time` timestamp not null default current_timestamp,
  primary key (`id`)
) engine=innodb auto_increment=3 default charset=utf8;

#
# data for table "XXL_JOB_QRTZ_TRIGGER_REGISTRY"
#

insert into `xxl_job_qrtz_trigger_registry` values (2,'EXECUTOR','open-xxl-job-demo','192.168.45.1:9999','2018-05-12 06:35:07');

#
# structure for table "XXL_JOB_QRTZ_TRIGGERS"
#

create table `xxl_job_qrtz_triggers` (
  `sched_name` varchar(120) not null,
  `trigger_name` varchar(200) not null,
  `trigger_group` varchar(200) not null,
  `job_name` varchar(200) not null,
  `job_group` varchar(200) not null,
  `description` varchar(250) default null,
  `next_fire_time` bigint(13) default null,
  `prev_fire_time` bigint(13) default null,
  `priority` int(11) default null,
  `trigger_state` varchar(16) not null,
  `trigger_type` varchar(8) not null,
  `start_time` bigint(13) not null,
  `end_time` bigint(13) default null,
  `calendar_name` varchar(200) default null,
  `misfire_instr` smallint(2) default null,
  `job_data` blob,
  primary key (`sched_name`,`trigger_name`,`trigger_group`),
  key `sched_name` (`sched_name`,`job_name`,`job_group`),
  constraint `xxl_job_qrtz_triggers_ibfk_1` foreign key (`sched_name`, `job_name`, `job_group`) references `xxl_job_qrtz_job_details` (`sched_name`, `job_name`, `job_group`)
) engine=innodb default charset=utf8;

#
# data for table "XXL_JOB_QRTZ_TRIGGERS"
#

insert into `xxl_job_qrtz_triggers` values ('quartzScheduler','3','1','3','1',null,1525885261000,-1,5,'PAUSED','CRON',1525876730000,0,null,2,x''),('quartzScheduler','4','1','4','1',null,1525885261000,-1,5,'PAUSED','CRON',1525876778000,0,null,2,x''),('schedulerFactoryBean','5','1','5','1',null,1526306160000,1526306155000,5,'ACQUIRED','CRON',1526305882000,0,null,2,x''),('schedulerFactoryBean','6','1','6','1',null,1526306160000,1526306155000,5,'WAITING','CRON',1526305980000,0,null,2,x'');

#
# structure for table "XXL_JOB_QRTZ_SIMPROP_TRIGGERS"
#

create table `xxl_job_qrtz_simprop_triggers` (
  `sched_name` varchar(120) not null,
  `trigger_name` varchar(200) not null,
  `trigger_group` varchar(200) not null,
  `str_prop_1` varchar(512) default null,
  `str_prop_2` varchar(512) default null,
  `str_prop_3` varchar(512) default null,
  `int_prop_1` int(11) default null,
  `int_prop_2` int(11) default null,
  `long_prop_1` bigint(20) default null,
  `long_prop_2` bigint(20) default null,
  `dec_prop_1` decimal(13,4) default null,
  `dec_prop_2` decimal(13,4) default null,
  `bool_prop_1` varchar(1) default null,
  `bool_prop_2` varchar(1) default null,
  primary key (`sched_name`,`trigger_name`,`trigger_group`),
  constraint `xxl_job_qrtz_simprop_triggers_ibfk_1` foreign key (`sched_name`, `trigger_name`, `trigger_group`) references `xxl_job_qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) engine=innodb default charset=utf8;

#
# data for table "XXL_JOB_QRTZ_SIMPROP_TRIGGERS"
#


#
# structure for table "XXL_JOB_QRTZ_SIMPLE_TRIGGERS"
#

create table `xxl_job_qrtz_simple_triggers` (
  `sched_name` varchar(120) not null,
  `trigger_name` varchar(200) not null,
  `trigger_group` varchar(200) not null,
  `repeat_count` bigint(7) not null,
  `repeat_interval` bigint(12) not null,
  `times_triggered` bigint(10) not null,
  primary key (`sched_name`,`trigger_name`,`trigger_group`),
  constraint `xxl_job_qrtz_simple_triggers_ibfk_1` foreign key (`sched_name`, `trigger_name`, `trigger_group`) references `xxl_job_qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) engine=innodb default charset=utf8;

#
# data for table "XXL_JOB_QRTZ_SIMPLE_TRIGGERS"
#


#
# structure for table "XXL_JOB_QRTZ_CRON_TRIGGERS"
#

create table `xxl_job_qrtz_cron_triggers` (
  `sched_name` varchar(120) not null,
  `trigger_name` varchar(200) not null,
  `trigger_group` varchar(200) not null,
  `cron_expression` varchar(200) not null,
  `time_zone_id` varchar(80) default null,
  primary key (`sched_name`,`trigger_name`,`trigger_group`),
  constraint `xxl_job_qrtz_cron_triggers_ibfk_1` foreign key (`sched_name`, `trigger_name`, `trigger_group`) references `xxl_job_qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) engine=innodb default charset=utf8;

#
# data for table "XXL_JOB_QRTZ_CRON_TRIGGERS"
#

insert into `xxl_job_qrtz_cron_triggers` values ('quartzScheduler','3','1','1 1 1 * * ? *','Asia/Shanghai'),('quartzScheduler','4','1','1 1 1 * * ? *','Asia/Shanghai'),('schedulerFactoryBean','5','1','*/5 * * * * ?','GMT+08:00'),('schedulerFactoryBean','6','1','*/5 * * * * ?','GMT+08:00');

#
# structure for table "XXL_JOB_QRTZ_BLOB_TRIGGERS"
#

create table `xxl_job_qrtz_blob_triggers` (
  `sched_name` varchar(120) not null,
  `trigger_name` varchar(200) not null,
  `trigger_group` varchar(200) not null,
  `blob_data` blob,
  primary key (`sched_name`,`trigger_name`,`trigger_group`),
  constraint `xxl_job_qrtz_blob_triggers_ibfk_1` foreign key (`sched_name`, `trigger_name`, `trigger_group`) references `xxl_job_qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) engine=innodb default charset=utf8;

#
# data for table "XXL_JOB_QRTZ_BLOB_TRIGGERS"
#
