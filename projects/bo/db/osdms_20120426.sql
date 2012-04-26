/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.1.28-rc-community : Database - myrose
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`myrose` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `myrose`;

/*Table structure for table `t_system_authority` */

DROP TABLE IF EXISTS `t_system_authority`;

CREATE TABLE `t_system_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

/*Data for the table `t_system_authority` */

LOCK TABLES `t_system_authority` WRITE;

insert  into `t_system_authority`(`id`,`name`) values (1,'系统管理');
insert  into `t_system_authority`(`id`,`name`) values (2,'用户管理');
insert  into `t_system_authority`(`id`,`name`) values (3,'用户浏览');
insert  into `t_system_authority`(`id`,`name`) values (4,'用户修改');
insert  into `t_system_authority`(`id`,`name`) values (5,'角色管理');
insert  into `t_system_authority`(`id`,`name`) values (6,'角色浏览');
insert  into `t_system_authority`(`id`,`name`) values (7,'角色修改');
insert  into `t_system_authority`(`id`,`name`) values (8,'配置管理');
insert  into `t_system_authority`(`id`,`name`) values (9,'配置浏览');
insert  into `t_system_authority`(`id`,`name`) values (10,'配置修改');
insert  into `t_system_authority`(`id`,`name`) values (11,'日志管理');
insert  into `t_system_authority`(`id`,`name`) values (12,'日志浏览');
insert  into `t_system_authority`(`id`,`name`) values (13,'日志修改');
insert  into `t_system_authority`(`id`,`name`) values (14,'模板管理');
insert  into `t_system_authority`(`id`,`name`) values (15,'模板浏览');
insert  into `t_system_authority`(`id`,`name`) values (16,'模板修改');
insert  into `t_system_authority`(`id`,`name`) values (17,'任务管理');
insert  into `t_system_authority`(`id`,`name`) values (18,'任务浏览');
insert  into `t_system_authority`(`id`,`name`) values (19,'任务修改');
insert  into `t_system_authority`(`id`,`name`) values (20,'作业规程编制');
insert  into `t_system_authority`(`id`,`name`) values (21,'作业规程审批');
insert  into `t_system_authority`(`id`,`name`) values (22,'作业规程查询');
insert  into `t_system_authority`(`id`,`name`) values (23,'作业规程快讯');
insert  into `t_system_authority`(`id`,`name`) values (24,'在线帮助');
insert  into `t_system_authority`(`id`,`name`) values (25,'首页浏览');

UNLOCK TABLES;

/*Table structure for table `t_system_company` */

DROP TABLE IF EXISTS `t_system_company`;

CREATE TABLE `t_system_company` (
  `folder` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone_number` varchar(50) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_modify_by` varchar(50) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ak_key_2` (`company_name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `t_system_company` */

LOCK TABLES `t_system_company` WRITE;

insert  into `t_system_company`(`folder`,`id`,`company_name`,`email`,`phone_number`,`status`,`version`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values ('default',1,'默认','darkmi@126.com','13811215910','NORMAL',0,'admin','2012-04-25 16:42:29',NULL,NULL);
insert  into `t_system_company`(`folder`,`id`,`company_name`,`email`,`phone_number`,`status`,`version`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values ('beijingkuangyejituan',2,'北京矿业集团','22@126.com','13811215910','NORMAL',0,'admin','2012-04-25 11:29:45',NULL,NULL);
insert  into `t_system_company`(`folder`,`id`,`company_name`,`email`,`phone_number`,`status`,`version`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values ('hebeikuangyejituan',3,'河北矿业集团','11@126.com','13811215910',NULL,0,'admin','2012-04-26 19:23:56',NULL,NULL);

UNLOCK TABLES;

/*Table structure for table `t_system_config` */

DROP TABLE IF EXISTS `t_system_config`;

CREATE TABLE `t_system_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `system` varchar(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_modify_by` varchar(50) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ak_key_2` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Data for the table `t_system_config` */

LOCK TABLES `t_system_config` WRITE;

insert  into `t_system_config`(`id`,`name`,`value`,`system`,`description`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (1,'cs.dispatch.interval','200','CS','配置流多长时间启动','admin','2011-03-14 17:12:53','admin','2011-03-24 09:56:54');
insert  into `t_system_config`(`id`,`name`,`value`,`system`,`description`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (4,'cs.nvs.address','192.168.14.208','CS','nvs的地址','admin','2011-03-14 17:17:56','admin','2011-05-09 14:14:35');
insert  into `t_system_config`(`id`,`name`,`value`,`system`,`description`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (10,'ens.lease','3','ENS','租期','admin','2011-03-14 17:23:37','admin','2011-03-14 17:23:42');
insert  into `t_system_config`(`id`,`name`,`value`,`system`,`description`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (15,'sm.video.server.ip','192.168.14.204','SM','视频服务器的ip','admin','2011-03-14 17:25:49','user','2011-10-26 14:54:28');
insert  into `t_system_config`(`id`,`name`,`value`,`system`,`description`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (16,'sm.video.server.port','1554','SM','视频服务器的端口','admin','2011-03-14 17:26:06','user','2011-10-26 14:54:40');
insert  into `t_system_config`(`id`,`name`,`value`,`system`,`description`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (18,'batch.rtm.program.keep.day','7','RTM','节目保存多长时间','admin','2011-03-14 17:27:17','user','2011-06-24 09:23:28');
insert  into `t_system_config`(`id`,`name`,`value`,`system`,`description`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (19,'batch.rtm.program.history.keep.day','30','RTM','节目历史保存多长时间','admin','2011-03-14 17:27:33','admin','2011-03-24 16:47:35');
insert  into `t_system_config`(`id`,`name`,`value`,`system`,`description`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (20,'batch.movie.allowed.playtime','200000','SM','影片允许播放时间，单位是分钟','admin','2011-03-14 17:27:33','user','2011-09-14 15:36:37');
insert  into `t_system_config`(`id`,`name`,`value`,`system`,`description`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (22,'cms.published.count','10','CMS','同时发布的节目的个数','admin','2011-03-28 13:53:56','user','2011-06-17 13:32:16');

UNLOCK TABLES;

/*Table structure for table `t_system_log` */

DROP TABLE IF EXISTS `t_system_log`;

CREATE TABLE `t_system_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `thread_name` varchar(255) DEFAULT NULL,
  `logger_name` varchar(255) DEFAULT NULL,
  `log_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `level` varchar(20) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_system_log` */

LOCK TABLES `t_system_log` WRITE;

insert  into `t_system_log`(`id`,`thread_name`,`logger_name`,`log_time`,`level`,`message`) values (1,'7976026@qtp-15655788-8','DBLogBO','2012-02-11 14:40:59','INFO','user:登录系统！');

UNLOCK TABLES;

/*Table structure for table `t_system_role` */

DROP TABLE IF EXISTS `t_system_role`;

CREATE TABLE `t_system_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_system_role` */

LOCK TABLES `t_system_role` WRITE;

insert  into `t_system_role`(`id`,`name`) values (1,'管理员');

UNLOCK TABLES;

/*Table structure for table `t_system_role_authority` */

DROP TABLE IF EXISTS `t_system_role_authority`;

CREATE TABLE `t_system_role_authority` (
  `role_id` bigint(20) DEFAULT NULL,
  `authority_id` bigint(20) DEFAULT NULL,
  KEY `fk_reference_22` (`role_id`),
  KEY `fk_reference_25` (`authority_id`),
  CONSTRAINT `fk_reference_22` FOREIGN KEY (`role_id`) REFERENCES `t_system_role` (`id`),
  CONSTRAINT `fk_reference_25` FOREIGN KEY (`authority_id`) REFERENCES `t_system_authority` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_system_role_authority` */

LOCK TABLES `t_system_role_authority` WRITE;

insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (1,1);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (1,2);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (1,3);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (1,4);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (1,5);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (1,6);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (1,7);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (1,8);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (1,9);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (1,10);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (1,11);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (1,12);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (1,13);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (1,14);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (1,15);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (1,16);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (1,17);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (1,18);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (1,19);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (1,20);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (1,21);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (1,22);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (1,23);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (1,24);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (1,25);

UNLOCK TABLES;

/*Table structure for table `t_system_user` */

DROP TABLE IF EXISTS `t_system_user`;

CREATE TABLE `t_system_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(255) DEFAULT NULL,
  `sha_password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone_number` varchar(50) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `company_id` bigint(20) DEFAULT '0',
  `login_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_modify_by` varchar(50) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ak_key_2` (`login_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `t_system_user` */

LOCK TABLES `t_system_user` WRITE;

insert  into `t_system_user`(`id`,`login_name`,`sha_password`,`name`,`email`,`phone_number`,`status`,`company_id`,`login_time`,`version`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (1,'admin','d033e22ae348aeb5660fc2140aec35850c4da997','Admin','darkmi@126.com','','enabled',1,'2011-03-26 11:38:54',13,'admin','2009-07-11 08:52:25','admin','2011-10-10 15:02:23');
insert  into `t_system_user`(`id`,`login_name`,`sha_password`,`name`,`email`,`phone_number`,`status`,`company_id`,`login_time`,`version`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (3,'user1','b3daa77b4c04a9551b8781d03191fe098f325e67','user1','user1@126.com','13811215910',NULL,2,NULL,1,'admin','2012-04-26 10:31:45','admin','2012-04-26 17:25:17');

UNLOCK TABLES;

/*Table structure for table `t_system_user_role` */

DROP TABLE IF EXISTS `t_system_user_role`;

CREATE TABLE `t_system_user_role` (
  `user_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  KEY `fk_reference_20` (`user_id`),
  KEY `fk_reference_21` (`role_id`),
  CONSTRAINT `fk_reference_20` FOREIGN KEY (`user_id`) REFERENCES `t_system_user` (`id`),
  CONSTRAINT `fk_reference_21` FOREIGN KEY (`role_id`) REFERENCES `t_system_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_system_user_role` */

LOCK TABLES `t_system_user_role` WRITE;

insert  into `t_system_user_role`(`user_id`,`role_id`) values (1,1);
insert  into `t_system_user_role`(`user_id`,`role_id`) values (3,1);

UNLOCK TABLES;

/*Table structure for table `t_task` */

DROP TABLE IF EXISTS `t_task`;

CREATE TABLE `t_task` (
  `company_id` bigint(20) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `task_id` varchar(255) DEFAULT NULL,
  `task_name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `state` tinyint(1) DEFAULT '0',
  `path` varchar(255) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_modify_by` varchar(50) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_task` */

LOCK TABLES `t_task` WRITE;

insert  into `t_task`(`company_id`,`id`,`task_id`,`task_name`,`description`,`state`,`path`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (NULL,1,NULL,'第一份作业规程',NULL,0,NULL,'user','2012-04-09 23:55:28',NULL,NULL);

UNLOCK TABLES;

/*Table structure for table `t_task_chapter` */

DROP TABLE IF EXISTS `t_task_chapter`;

CREATE TABLE `t_task_chapter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `chapter_id` varchar(255) DEFAULT NULL,
  `chapter_name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `state` tinyint(1) DEFAULT '0',
  `task_id` bigint(20) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_modify_by` varchar(50) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_task_chapter` */

LOCK TABLES `t_task_chapter` WRITE;

insert  into `t_task_chapter`(`id`,`chapter_id`,`chapter_name`,`description`,`state`,`task_id`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (1,'1','1','1',0,1,'darkmi',NULL,NULL,NULL);

UNLOCK TABLES;

/*Table structure for table `t_template` */

DROP TABLE IF EXISTS `t_template`;

CREATE TABLE `t_template` (
  `company_id` bigint(20) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `template_name` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `state` tinyint(1) DEFAULT '0',
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_modify_by` varchar(50) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `t_template` */

LOCK TABLES `t_template` WRITE;

insert  into `t_template`(`company_id`,`id`,`template_name`,`path`,`description`,`state`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (1,1,'默认模板','001','默认模板。',0,'user','2012-04-07 16:05:28',NULL,NULL);

UNLOCK TABLES;

/*Table structure for table `t_template_chapter` */

DROP TABLE IF EXISTS `t_template_chapter`;

CREATE TABLE `t_template_chapter` (
  `template_id` bigint(20) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `chapter_id` varchar(255) DEFAULT NULL,
  `chapter_name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `state` tinyint(1) DEFAULT '0',
  `parent_id` bigint(20) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_modify_by` varchar(50) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `t_template_chapter` */

LOCK TABLES `t_template_chapter` WRITE;

insert  into `t_template_chapter`(`template_id`,`file_name`,`id`,`chapter_id`,`chapter_name`,`description`,`state`,`parent_id`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (1,'01-01.doc',1,'1','1','1',0,1,'darkmi',NULL,NULL,NULL);
insert  into `t_template_chapter`(`template_id`,`file_name`,`id`,`chapter_id`,`chapter_name`,`description`,`state`,`parent_id`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (1,'01-02.doc',2,'2','2','2',0,1,NULL,NULL,NULL,NULL);
insert  into `t_template_chapter`(`template_id`,`file_name`,`id`,`chapter_id`,`chapter_name`,`description`,`state`,`parent_id`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (1,'02-01.doc',3,'3','3','3',0,1,NULL,NULL,NULL,NULL);
insert  into `t_template_chapter`(`template_id`,`file_name`,`id`,`chapter_id`,`chapter_name`,`description`,`state`,`parent_id`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (1,'03-01.doc',4,'4','4','4',0,1,NULL,NULL,NULL,NULL);
insert  into `t_template_chapter`(`template_id`,`file_name`,`id`,`chapter_id`,`chapter_name`,`description`,`state`,`parent_id`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (1,'03-02.doc',5,'5','5','5',0,1,NULL,NULL,NULL,NULL);

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
