/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.0.45-community-nt : Database - myrose
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

/*Table structure for table `t_chapter` */

DROP TABLE IF EXISTS `t_chapter`;

CREATE TABLE `t_chapter` (
  `id` bigint(20) NOT NULL auto_increment,
  `chapter_id` varchar(255) default NULL,
  `chapter_name` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  `state` tinyint(1) default '0',
  `parent_id` bigint(20) default NULL,
  `create_by` varchar(50) default NULL,
  `create_time` datetime default NULL,
  `last_modify_by` varchar(50) default NULL,
  `last_modify_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_chapter` */

insert  into `t_chapter`(`id`,`chapter_id`,`chapter_name`,`description`,`state`,`parent_id`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (1,'1','1','1',0,1,'darkmi',NULL,NULL,NULL);

/*Table structure for table `t_system_authority` */

DROP TABLE IF EXISTS `t_system_authority`;

CREATE TABLE `t_system_authority` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8;

/*Data for the table `t_system_authority` */

insert  into `t_system_authority`(`id`,`name`) values (1,'系统管理'),(2,'用户管理'),(3,'用户浏览'),(4,'用户修改'),(5,'角色管理'),(6,'角色浏览'),(7,'角色修改'),(8,'配置管理'),(9,'配置浏览'),(10,'配置修改'),(11,'日志管理'),(12,'日志浏览'),(13,'日志修改'),(14,'模板管理'),(15,'模板浏览'),(16,'模板修改'),(17,'任务管理'),(18,'任务浏览'),(19,'任务修改'),(20,'作业规程编制'),(21,'作业规程审批'),(22,'作业规程查询'),(23,'作业规程快讯'),(24,'在线帮助'),(25,'首页浏览');

/*Table structure for table `t_system_config` */

DROP TABLE IF EXISTS `t_system_config`;

CREATE TABLE `t_system_config` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(50) default NULL,
  `value` varchar(255) default NULL,
  `system` varchar(20) default NULL,
  `description` varchar(255) default NULL,
  `create_by` varchar(50) default NULL,
  `create_time` datetime default NULL,
  `last_modify_by` varchar(50) default NULL,
  `last_modify_time` datetime default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `ak_key_2` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

/*Data for the table `t_system_config` */

insert  into `t_system_config`(`id`,`name`,`value`,`system`,`description`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (1,'cs.dispatch.interval','200','CS','配置流多长时间启动','admin','2011-03-14 17:12:53','admin','2011-03-24 09:56:54'),(4,'cs.nvs.address','192.168.14.208','CS','nvs的地址','admin','2011-03-14 17:17:56','admin','2011-05-09 14:14:35'),(10,'ens.lease','3','ENS','租期','admin','2011-03-14 17:23:37','admin','2011-03-14 17:23:42'),(15,'sm.video.server.ip','192.168.14.204','SM','视频服务器的ip','admin','2011-03-14 17:25:49','user','2011-10-26 14:54:28'),(16,'sm.video.server.port','1554','SM','视频服务器的端口','admin','2011-03-14 17:26:06','user','2011-10-26 14:54:40'),(18,'batch.rtm.program.keep.day','7','RTM','节目保存多长时间','admin','2011-03-14 17:27:17','user','2011-06-24 09:23:28'),(19,'batch.rtm.program.history.keep.day','30','RTM','节目历史保存多长时间','admin','2011-03-14 17:27:33','admin','2011-03-24 16:47:35'),(20,'batch.movie.allowed.playtime','200000','SM','影片允许播放时间，单位是分钟','admin','2011-03-14 17:27:33','user','2011-09-14 15:36:37'),(22,'cms.published.count','10','CMS','同时发布的节目的个数','admin','2011-03-28 13:53:56','user','2011-06-17 13:32:16');

/*Table structure for table `t_system_log` */

DROP TABLE IF EXISTS `t_system_log`;

CREATE TABLE `t_system_log` (
  `id` bigint(20) NOT NULL auto_increment,
  `thread_name` varchar(255) default NULL,
  `logger_name` varchar(255) default NULL,
  `log_time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `level` varchar(20) default NULL,
  `message` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_system_log` */

insert  into `t_system_log`(`id`,`thread_name`,`logger_name`,`log_time`,`level`,`message`) values (1,'7976026@qtp-15655788-8','DBLogBO','2012-02-11 14:40:59','INFO','user:登录系统！');

/*Table structure for table `t_system_role` */

DROP TABLE IF EXISTS `t_system_role`;

CREATE TABLE `t_system_role` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `t_system_role` */

insert  into `t_system_role`(`id`,`name`) values (1,'管理员');

/*Table structure for table `t_system_role_authority` */

DROP TABLE IF EXISTS `t_system_role_authority`;

CREATE TABLE `t_system_role_authority` (
  `role_id` bigint(20) default NULL,
  `authority_id` bigint(20) default NULL,
  KEY `fk_reference_22` (`role_id`),
  KEY `fk_reference_25` (`authority_id`),
  CONSTRAINT `fk_reference_22` FOREIGN KEY (`role_id`) REFERENCES `t_system_role` (`id`),
  CONSTRAINT `fk_reference_25` FOREIGN KEY (`authority_id`) REFERENCES `t_system_authority` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_system_role_authority` */

insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21),(1,22),(1,23),(1,24),(1,25);

/*Table structure for table `t_system_user` */

DROP TABLE IF EXISTS `t_system_user`;

CREATE TABLE `t_system_user` (
  `id` bigint(20) NOT NULL auto_increment,
  `email` varchar(255) default NULL,
  `login_name` varchar(255) default NULL,
  `name` varchar(255) default NULL,
  `sha_password` varchar(255) default NULL,
  `status` varchar(20) default NULL,
  `version` int(11) default '0',
  `login_time` datetime default NULL,
  `create_by` varchar(50) default NULL,
  `create_time` datetime default NULL,
  `last_modify_by` varchar(50) default NULL,
  `last_modify_time` datetime default NULL,
  `phone_number` varchar(50) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `ak_key_2` (`login_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_system_user` */

insert  into `t_system_user`(`id`,`email`,`login_name`,`name`,`sha_password`,`status`,`version`,`login_time`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`,`phone_number`) values (1,'darkmi@126.com','admin','Admin','d033e22ae348aeb5660fc2140aec35850c4da997','enabled',13,'2011-03-26 11:38:54','admin','2009-07-11 08:52:25','user','2011-10-10 15:02:23','');

/*Table structure for table `t_system_user_role` */

DROP TABLE IF EXISTS `t_system_user_role`;

CREATE TABLE `t_system_user_role` (
  `user_id` bigint(20) default NULL,
  `role_id` bigint(20) default NULL,
  KEY `fk_reference_20` (`user_id`),
  KEY `fk_reference_21` (`role_id`),
  CONSTRAINT `fk_reference_20` FOREIGN KEY (`user_id`) REFERENCES `t_system_user` (`id`),
  CONSTRAINT `fk_reference_21` FOREIGN KEY (`role_id`) REFERENCES `t_system_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_system_user_role` */

insert  into `t_system_user_role`(`user_id`,`role_id`) values (1,1);

/*Table structure for table `t_task` */

DROP TABLE IF EXISTS `t_task`;

CREATE TABLE `t_task` (
  `id` bigint(20) NOT NULL auto_increment,
  `task_id` varchar(255) default NULL,
  `task_name` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  `state` tinyint(1) default '0',
  `path` varchar(255) default NULL,
  `create_by` varchar(50) default NULL,
  `create_time` datetime default NULL,
  `last_modify_by` varchar(50) default NULL,
  `last_modify_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_task` */

insert  into `t_task`(`id`,`task_id`,`task_name`,`description`,`state`,`path`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (1,NULL,'第一份作业规程',NULL,0,NULL,'user','2012-04-09 23:55:28',NULL,NULL);

/*Table structure for table `t_template` */

DROP TABLE IF EXISTS `t_template`;

CREATE TABLE `t_template` (
  `id` bigint(20) NOT NULL auto_increment,
  `template_name` varchar(255) default NULL,
  `path` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  `state` tinyint(1) default '0',
  `create_by` varchar(50) default NULL,
  `create_time` datetime default NULL,
  `last_modify_by` varchar(50) default NULL,
  `last_modify_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `t_template` */

insert  into `t_template`(`id`,`template_name`,`path`,`description`,`state`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (1,'默认模板',NULL,'默认模板。',0,'user','2012-04-07 16:05:28',NULL,NULL),(14,'aaab',NULL,NULL,0,'user','2012-04-07 16:06:07',NULL,NULL),(15,'xxx',NULL,'xxx',0,'user','2012-04-07 16:09:52',NULL,NULL);

/*Table structure for table `t_template_chapter` */

DROP TABLE IF EXISTS `t_template_chapter`;

CREATE TABLE `t_template_chapter` (
  `id` bigint(20) NOT NULL auto_increment,
  `chapter_id` varchar(255) default NULL,
  `chapter_name` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  `state` tinyint(1) default '0',
  `parent_id` bigint(20) default NULL,
  `create_by` varchar(50) default NULL,
  `create_time` datetime default NULL,
  `last_modify_by` varchar(50) default NULL,
  `last_modify_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_template_chapter` */

insert  into `t_template_chapter`(`id`,`chapter_id`,`chapter_name`,`description`,`state`,`parent_id`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (1,'1','1','1',0,1,'darkmi',NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
