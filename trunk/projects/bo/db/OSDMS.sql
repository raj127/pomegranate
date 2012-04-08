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
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8;

/*Data for the table `t_system_authority` */

insert  into `t_system_authority`(`id`,`name`) values (1,'浏览用户'),(2,'修改用户'),(3,'浏览角色'),(4,'修改角色'),(5,'浏览首页'),(6,'修改密码'),(7,'修改FTP'),(8,'浏览FTP'),(9,'修改配置'),(10,'浏览配置'),(11,'删除日志'),(12,'浏览日志'),(13,'修改内容供应商'),(14,'浏览内容供应商'),(15,'修改内容分类'),(16,'浏览内容分类'),(17,'下线电影'),(18,'发布电影'),(19,'审核电影'),(20,'修改电影'),(21,'浏览电影'),(22,'下线KTV'),(23,'发布KTV'),(24,'审核KTV'),(25,'修改KTV'),(26,'浏览KTV'),(27,'修改电视剧'),(28,'浏览电视剧'),(29,'下线电视剧集'),(30,'发布电视剧集'),(31,'审核电视剧集'),(32,'修改电视剧集'),(33,'浏览电视剧集'),(34,'浏览内容状态列表'),(35,'浏览历史记录'),(36,'浏览内容存储状态'),(37,'修改频道'),(38,'浏览频道'),(39,'修改节目'),(40,'浏览节目'),(41,'浏览录制列表'),(42,'浏览时移状态列表'),(43,'导入节目单'),(44,'浏览录制历史'),(45,'修改编码器'),(46,'浏览编码器'),(47,'浏览时移存储状态'),(48,'浏览录制队列'),(49,'浏览频道队列'),(50,'生成OFFERING'),(51,'浏览元数据'),(52,'修改点播供应商'),(53,'浏览点播供应商'),(54,'修改产品'),(55,'浏览产品'),(56,'修改业务'),(57,'浏览业务'),(58,'修改点播分类'),(59,'浏览点播分类'),(60,'浏览海报列表'),(61,'修改OFFERING'),(62,'浏览OFFERING历史'),(63,'浏览OFFERING'),(64,'浏览授权管理'),(65,'修改视频服务器'),(66,'同步视频服务器文件'),(67,'同步CS数据'),(68,'浏览视频服务器'),(69,'修改网卡'),(70,'浏览网卡'),(71,'浏览ServiceGroup队列'),(72,'修改ServiceGroup'),(73,'浏览ServiceGroup'),(74,'修改ipqam'),(75,'浏览ipqam'),(76,'修改ts'),(77,'浏览ts'),(78,'修改PageServer'),(79,'浏览PageServer'),(80,'删除SRM会话'),(81,'浏览SRM会话'),(82,'浏览页面发布'),(83,'删除推荐'),(84,'上传图片'),(85,'修改风格'),(86,'浏览风格'),(87,'修改分组'),(88,'浏览分组'),(89,'修改模板'),(90,'浏览模板'),(91,'浏览报表'),(92,'回传服务');

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

insert  into `t_system_config`(`id`,`name`,`value`,`system`,`description`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (1,'cs.dispatch.interval','200','CS','配置流多长时间启动','admin','2011-03-14 17:12:53','admin','2011-03-24 09:56:54'),(2,'cs.program.number','2','CS','发送的节目号','admin','2011-03-14 17:16:10',NULL,NULL),(3,'cs.pid','48','CS','发送的pid','admin','2011-03-14 17:17:32',NULL,NULL),(4,'cs.nvs.address','192.168.14.208','CS','nvs的地址','admin','2011-03-14 17:17:56','admin','2011-05-09 14:14:35'),(10,'ens.lease','3','ENS','租期','admin','2011-03-14 17:23:37','admin','2011-03-14 17:23:42'),(11,'rtm.startup.delay','500','RTM','时移多长时间启动','admin','2011-03-14 17:24:27',NULL,NULL),(12,'rtm.duration','5','RTM','时移多长时间失效','admin','2011-03-14 17:24:50',NULL,NULL),(13,'rtm.providerId','yongxin','RTM','时移的供应商编号','admin','2011-03-14 17:25:08',NULL,NULL),(14,'rtm.provider','永新','RTM','时移的供应商','admin','2011-03-14 17:25:29',NULL,NULL),(15,'sm.video.server.ip','192.168.14.204','SM','视频服务器的ip','admin','2011-03-14 17:25:49','user','2011-10-26 14:54:28'),(16,'sm.video.server.port','1554','SM','视频服务器的端口','admin','2011-03-14 17:26:06','user','2011-10-26 14:54:40'),(17,'sm.video.server.play.port','8060','SM','视频服务器的播放的端口','admin','2011-03-14 17:26:28',NULL,NULL),(18,'batch.rtm.program.keep.day','7','RTM','节目保存多长时间','admin','2011-03-14 17:27:17','user','2011-06-24 09:23:28'),(19,'batch.rtm.program.history.keep.day','30','RTM','节目历史保存多长时间','admin','2011-03-14 17:27:33','admin','2011-03-24 16:47:35'),(20,'batch.movie.allowed.playtime','200000','SM','影片允许播放时间，单位是分钟','admin','2011-03-14 17:27:33','user','2011-09-14 15:36:37'),(22,'cms.published.count','10','CMS','同时发布的节目的个数','admin','2011-03-28 13:53:56','user','2011-06-17 13:32:16'),(24,'ps.ivr.url','http://192.168.14.206/portal/index.html','PS','IVR flex页面地址',NULL,NULL,'user','2011-06-20 11:05:06'),(25,'ps.bit.rate','4000','PS',' 视频输出带宽，单位为kbps，无符号',NULL,NULL,NULL,NULL),(26,'ps.frame.rate','25','PS','视频帧率：1-60fps，取整数',NULL,NULL,NULL,NULL),(27,'ps.audio.encoding.formats','1','PS','音频类型',NULL,NULL,NULL,NULL),(28,'ps.video.encoding.formats','1','PS','视频类型',NULL,NULL,NULL,NULL),(29,'ps.ope.id','0','PS','运营商ID',NULL,NULL,NULL,NULL),(30,'ps.sms.id','1','PS',' SMS ID',NULL,NULL,NULL,NULL),(31,'ps.emm.pid','212','PS','EMM PID',NULL,NULL,NULL,NULL),(32,'ps.phone.url','http://192.168.14.206/mobVod/index.html','PS','智能终端 flex页面访问地址',NULL,NULL,'user','2011-07-11 16:17:13'),(33,'ps.es.pid.a','104','PS','直播频道的音频pid',NULL,NULL,NULL,NULL),(34,'ps.es.pid.v','103','PS','直播频道的视频pid',NULL,NULL,NULL,NULL),(35,'ps.pcr.pid','103','PS','直播频道的pcr pid',NULL,NULL,NULL,NULL),(36,'ps.live.frequency','459000','PS','直播频道的频率',NULL,NULL,NULL,NULL),(37,'srm.playclient.port','8060','SM',NULL,NULL,NULL,NULL,NULL),(41,'ps.modulation','3','PS',NULL,NULL,NULL,NULL,NULL),(42,'ps.symbol','6875','PS',NULL,NULL,NULL,NULL,NULL),(45,'ens.sync.method','0','ENS','',NULL,NULL,'user','2011-08-15 09:41:46'),(46,'sm.ipqam.type','0','SM','',NULL,NULL,'user','2011-08-15 09:41:31'),(47,'ps.ecm.pid','321','PS','直播频道的ecm pid',NULL,NULL,NULL,NULL),(48,'ps.bookmark.time','1000','PS',NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `t_system_ftp` */

DROP TABLE IF EXISTS `t_system_ftp`;

CREATE TABLE `t_system_ftp` (
  `id` bigint(20) NOT NULL auto_increment,
  `ftp_id` varchar(50) default NULL,
  `name` varchar(50) default NULL,
  `address` varchar(100) default NULL,
  `user_name` varchar(50) default NULL,
  `user_pass` varchar(50) default NULL,
  `port` int(11) default '0',
  `state` tinyint(1) default '0',
  `path` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  `system` varchar(20) default NULL,
  `create_by` varchar(50) default NULL,
  `create_time` datetime default NULL,
  `last_modify_by` varchar(50) default NULL,
  `last_modify_time` datetime default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `ak_key_2` (`ftp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_system_ftp` */

insert  into `t_system_ftp`(`id`,`ftp_id`,`name`,`address`,`user_name`,`user_pass`,`port`,`state`,`path`,`description`,`system`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (1,'192.168.14.204','192.168.14.204','192.168.14.204','root','123456',21,1,'','','CMS','user','2011-08-08 10:43:55','user','2011-08-16 09:33:41'),(2,'192.168.14.20421','192.168.14.204:21','192.168.14.204','root','123456',21,1,'','','ODM','user','2011-08-08 10:45:12','user','2011-08-16 09:33:20'),(3,'192.168.14.90','192.168.14.90','192.168.14.90','root','123456',21,1,'','','PPS','user','2011-08-08 10:56:16','user','2011-09-05 18:05:47');

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

/*Table structure for table `t_system_memcached_key` */

DROP TABLE IF EXISTS `t_system_memcached_key`;

CREATE TABLE `t_system_memcached_key` (
  `id` bigint(20) NOT NULL auto_increment,
  `memcached_key` varchar(255) default NULL,
  `create_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_system_memcached_key` */

insert  into `t_system_memcached_key`(`id`,`memcached_key`,`create_time`) values (1,'srm-session:84679203-8165662149','2011-12-06 19:12:01');

/*Table structure for table `t_system_role` */

DROP TABLE IF EXISTS `t_system_role`;

CREATE TABLE `t_system_role` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `t_system_role` */

insert  into `t_system_role`(`id`,`name`) values (1,'管理员'),(2,'内容编辑'),(3,'审核管理员'),(4,'时移管理员'),(5,'注入管理员'),(6,'点播管理员'),(7,'授权管理员'),(8,'SRM会话管理员'),(9,'页面发布管理员'),(10,'报表管理员'),(11,'回传服务管理员');

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

insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (2,1),(2,3),(2,5),(2,6),(2,8),(2,10),(2,12),(2,13),(2,14),(2,15),(2,16),(2,17),(2,18),(2,20),(2,21),(2,22),(2,23),(2,25),(2,26),(2,27),(2,28),(2,29),(2,30),(2,32),(2,33),(2,34),(2,35),(2,36),(4,5),(4,6),(4,8),(4,10),(4,12),(4,37),(4,38),(4,39),(4,40),(4,41),(4,42),(4,43),(4,44),(4,45),(4,46),(4,47),(4,48),(4,49),(5,5),(5,6),(5,8),(5,10),(5,12),(5,50),(5,51),(6,1),(6,5),(6,6),(6,8),(6,10),(6,12),(6,52),(6,53),(6,54),(6,55),(6,56),(6,57),(6,58),(6,59),(6,60),(6,61),(6,62),(6,63),(7,1),(7,5),(7,8),(7,12),(7,64),(3,5),(3,6),(3,8),(3,10),(3,16),(3,19),(3,21),(3,24),(3,26),(3,28),(3,31),(3,33),(3,34),(3,35),(3,36),(3,15),(8,1),(8,5),(8,8),(8,10),(8,12),(8,65),(8,66),(8,67),(8,68),(8,69),(8,70),(8,71),(8,72),(8,73),(8,74),(8,75),(8,76),(8,77),(8,78),(8,79),(8,80),(8,81),(9,1),(9,5),(9,6),(9,10),(9,12),(9,82),(9,83),(9,84),(9,85),(9,86),(9,87),(9,88),(9,89),(9,90),(10,91),(11,92),(1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,78),(1,79);

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

insert  into `t_system_user`(`id`,`email`,`login_name`,`name`,`sha_password`,`status`,`version`,`login_time`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`,`phone_number`) values (1,'darkmi@126.com','admin','Admin','d033e22ae348aeb5660fc2140aec35850c4da997','enabled',13,'2011-03-26 11:38:54','admin','2009-07-11 08:52:25','user','2011-10-10 15:02:23',''),(2,'darkmi@126.com','user','darkmi','12dea96fec20593566ab75692c9949596833adc9','enabled',13,'2011-03-26 11:37:39','admin','2009-07-11 08:52:25','user','2012-03-14 23:53:18','13811215910, 13811215910');

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

insert  into `t_system_user_role`(`user_id`,`role_id`) values (2,2),(2,4),(2,5),(2,6),(2,7),(2,8),(2,9),(2,10),(2,11),(2,1),(2,3),(1,1);

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `t_task` */

insert  into `t_task`(`id`,`task_id`,`task_name`,`description`,`state`,`path`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (2,NULL,'aaa',NULL,0,NULL,'user','2012-02-12 15:27:50',NULL,NULL),(3,NULL,'bbb',NULL,0,NULL,'user','2012-02-12 15:29:26',NULL,NULL),(4,NULL,'第一章  概  况',NULL,0,NULL,'user','2012-03-18 22:58:06',NULL,NULL),(5,NULL,'第二章  巷道位置及地质水文情况',NULL,0,NULL,'user','2012-03-18 22:58:32',NULL,NULL),(6,NULL,'第三章	巷道布置及支护说明',NULL,0,NULL,'user','2012-03-18 22:59:13',NULL,NULL),(7,NULL,'第四章	施工工艺',NULL,0,NULL,'user','2012-03-18 22:59:25',NULL,NULL),(8,NULL,'第五章 劳动组织及主要技术经济指标',NULL,0,NULL,'user','2012-03-18 22:59:35',NULL,NULL),(9,NULL,'第六章 生产系统',NULL,0,NULL,'user','2012-03-18 22:59:44',NULL,NULL),(10,NULL,'第七章 安全技术措施',NULL,0,NULL,'user','2012-03-18 22:59:53',NULL,NULL),(11,NULL,'第八章 灾害应急措施及避灾路线',NULL,0,NULL,'user','2012-03-18 23:00:01',NULL,NULL);

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

insert  into `t_template`(`id`,`template_name`,`path`,`description`,`state`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (13,'aaa',NULL,NULL,0,'user','2012-04-07 16:05:28',NULL,NULL),(14,'aaab',NULL,NULL,0,'user','2012-04-07 16:06:07',NULL,NULL),(15,'xxx',NULL,'xxx',0,'user','2012-04-07 16:09:52',NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
