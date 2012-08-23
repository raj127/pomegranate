/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.1.28-rc-community : Database - osdms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`osdms` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `osdms`;

/*Table structure for table `t_specification_chapter` */

DROP TABLE IF EXISTS `t_specification_chapter`;

CREATE TABLE `t_specification_chapter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `tree_index` varchar(100) DEFAULT NULL,
  `sort` int(11) DEFAULT '0',
  `is_leaf` tinyint(1) DEFAULT '0',
  `state` tinyint(1) DEFAULT NULL,
  `content` text,
  `version` int(11) DEFAULT '0',
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  `last_modify_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reference_54` (`parent_id`),
  CONSTRAINT `fk_reference_54` FOREIGN KEY (`parent_id`) REFERENCES `t_specification_chapter` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

/*Data for the table `t_specification_chapter` */

LOCK TABLES `t_specification_chapter` WRITE;

insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (1,'全部分类',NULL,'0001',0,0,NULL,NULL,0,'admin','2012-06-13 17:09:06','2012-06-13 17:09:50','admin');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (2,'煤矿安全规程',1,'0001-0001',0,0,NULL,NULL,5,'admin','2012-06-13 17:09:06','2012-06-13 17:09:50','admin');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (3,'煤矿安全技术操作规程',1,'0001-0002',0,0,NULL,NULL,0,'admin','2012-06-13 17:09:06','2012-06-13 17:09:50','admin');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (4,'第一编 总则',2,'0001-0001-0001',0,0,NULL,NULL,14,'admin','2012-06-13 17:09:06','2012-08-10 16:20:43','系统');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (5,'第二编 井工部分',2,'0001-0001-0002',0,0,NULL,NULL,10,'admin','2012-06-13 17:09:06','2012-08-10 16:20:43','系统');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (6,'第三编 露天部分',2,'0001-0001-0003',0,0,NULL,NULL,10,'admin','2012-06-13 17:09:06','2012-08-10 16:20:43','系统');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (7,'第四编 职业危害',2,'0001-0001-0004',0,0,NULL,NULL,10,'admin','2012-06-13 17:09:06','2012-08-10 16:20:43','系统');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (8,'（2006）',2,'0001-0001-0005',0,1,NULL,NULL,10,'admin','2012-06-13 17:09:06','2012-08-10 16:20:43','系统');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (9,'（2009）',2,'0001-0001-0006',0,1,NULL,NULL,10,'admin','2012-06-13 17:09:06','2012-08-10 16:20:43','系统');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (10,'（2010）',2,'0001-0001-0007',0,1,NULL,NULL,10,'admin','2012-06-13 17:09:06','2012-08-10 16:20:43','系统');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (11,'第一条',4,'0001-0001-0001-0001',0,1,NULL,'为保障煤矿安全生产和职工人身安全，防止煤矿事故，根据《煤炭法》、《矿山安全法》和《煤矿安全监察条例》，制定本规程。',8,'admin','2012-06-13 17:09:06','2012-08-10 16:28:29','系统');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (12,'第二条',4,'0001-0001-0001-0002',0,1,NULL,'在中华人民共和国领域从事煤炭生产和煤矿建设活动，必须遵守本规程。',8,'admin','2012-06-13 17:09:06','2012-08-10 16:28:29','系统');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (13,'第三条',4,'0001-0001-0001-0003',0,1,NULL,'煤矿企业必须遵守国家有关安全生产的法律、法规、规章、规程、标准和技术规范。\r\n煤矿企业必须建立、健全各级领导安全生产责任制、职能机构安全生产责任制、岗位人员安全生产责任制。\r\n煤矿企业应建立、健全安全目标管理制度、安全奖惩制度、安全技术措施审批制度、安全隐患排查制度、安全检查制度、安全办公会议等制度。\r\n煤矿企业必须建立各种设备、设施检查维修制度，定期进行检查维修，并做好记录。\r\n',8,'admin','2012-06-13 17:09:06','2012-08-10 16:28:29','系统');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (14,'第四条',4,'0001-0001-0001-0004',0,1,NULL,'煤矿企业必须设置安全生产机构，配备适应工作需要的安全生产人员和装备。',8,'admin','2012-06-13 17:09:06','2012-08-10 16:28:29','系统');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (15,'第五条',4,'0001-0001-0001-0005',0,1,NULL,'煤矿安全工作必须实行群众监督。煤矿企业必须支持群众安全监督组织的活动，发挥职工群众安全监督作用。\r\n职工有权制止违章作业，拒绝违章指挥；当工作地点出现险情时，有权立即停止作业，撤到安全地点；当险情没有得到处理不能保证人身安全时，有权拒绝作业。\r\n',8,'admin','2012-06-13 17:09:06','2012-08-10 16:28:29','系统');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (16,'第六条',4,'0001-0001-0001-0006',0,1,NULL,'煤矿企业必须对职工进行安全培训。未经安全培训的，不得上岗作业。\r\n矿务局（公司）局长（经理）、矿长必须具备安全专业知识，具有领导安全生产和处理煤矿事故的能力，并经依法培训合格，取得安全任职资格证书。\r\n特种作业人员必须按国家有关规定培训合格，取得操作资格证书。',8,'admin','2012-06-13 17:09:06','2012-08-10 16:28:29','系统');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (17,'第七条',4,'0001-0001-0001-0007',0,1,NULL,'煤矿使用的涉及安全生产的产品，必须取得煤矿矿用产品安全标志。未取得煤矿矿用产品安全标志的，不得使用。\r\n试验涉及安全生产的新技术、新工艺、新设备、新材料前，必须经过论证、安全性能检验和鉴定，并制定安全措施。\r\n',8,'admin','2012-06-13 17:09:06','2012-08-10 16:28:29','系统');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (18,'第八条',4,'0001-0001-0001-0008',0,1,NULL,'煤矿企业在编制生产建设长远发展规划和年度生产建设计划时，必须编制安全技术发展规划和安全技术措施计划。安全技术措施所需费用、材料和设备等必须列入企业财务、供应计划。',8,'admin','2012-06-13 17:09:06','2012-08-10 16:28:29','系统');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (19,'第九条',4,'0001-0001-0001-0009',0,1,NULL,'煤矿企业必须编制年度灾害预防和处理计划，并根据具体情况及时修改。灾害预防和处理计划由矿长负责组织实施。\r\n煤矿企业每年必须至少组织1次矿井救灾演习。',8,'admin','2012-06-13 17:09:06','2012-08-10 16:28:29','系统');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (20,'第十条',4,'0001-0001-0001-0010',0,1,NULL,'入井人员必须戴安全帽、随身携带自救器和矿灯，严禁携带烟草和点火物品，严禁穿化纤衣服，入井前严禁喝酒。\r\n煤矿企业必须建立入井检身制度和出入井人员清点制度。',8,'admin','2012-06-13 17:09:06','2012-08-10 16:28:29','系统');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (21,'第十一条',4,'0001-0001-0001-0011',0,1,NULL,'煤矿企业应有创伤急救系统为其服务。创伤急救系统应配备救护车辆、急救器材、急救装备和药品等。',8,'admin','2012-06-13 17:09:06','2012-08-10 16:28:29','系统');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (22,'第十二条',4,'0001-0001-0001-0012',0,1,NULL,'井工煤矿必须及时填绘反映实际情况的下列图纸：\r\n（一）矿井地质和水文地质图。\r\n（二）井上、下对照图。\r\n（三）巷道布置图。\r\n（四）采掘工程平面图。\r\n（五）通风系统图。\r\n（六）井下运输系统图。\r\n（七）安全监测装备布置图。\r\n（八）排水、防尘、防火注浆、压风、充填、抽放瓦斯等管路系统图。\r\n（九）井下通信系统图。\r\n（十）井上、下配电系统图和井下电气设备布置图。\r\n（十一）井下避灾路线图。',8,'admin','2012-06-13 17:09:06','2012-08-10 16:28:29','系统');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (23,'第十三条',4,'0001-0001-0001-0013',0,1,NULL,'露天煤矿必须及时填绘反映实际情况的下列图纸：\r\n（一）地形地质图。\r\n（二）工程地质平面图、断面图，综合水文地质平面图。\r\n（三）采剥工程平面图、断面图。\r\n（四）排土工程平面图。\r\n（五）运输系统图。\r\n（六）输配电系统图。\r\n（七）通信系统图。\r\n（八）防排水系统及排水设备布置图。\r\n（九）边坡监测系统平面图、断面图。\r\n（十）井工老空与露天矿平面对照图。',8,'admin','2012-06-13 17:09:06','2012-08-10 16:28:29','系统');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (24,'第十四条',4,'0001-0001-0001-0014',0,1,NULL,'煤矿发生事故后，煤矿企业主要负责人和技术负责人必须立即采取措施组织抢救，矿长负责抢救指挥，并按有关规定及时上报。',8,'admin','2012-06-13 17:09:06','2012-08-10 16:28:29','系统');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (25,'第一章',5,'0001-0001-0002-0001',0,0,NULL,NULL,0,'admin','2012-06-13 17:09:06','2012-06-13 17:09:50','admin');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (26,'第一节',25,'0001-0001-0002-0001-0001',0,0,NULL,NULL,0,'admin','2012-06-13 17:09:06','2012-06-13 17:09:50','admin');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (27,'第十五条',26,'0001-0001-0002-0001-0001-0001',0,1,NULL,'单项工程、单位工程开工前，必须编制施工组织设计和作业规程，并组织每个工作人员学习。',0,'admin','2012-06-13 17:09:06','2012-06-13 17:09:50','admin');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (28,'第十六条',26,'0001-0001-0002-0001-0001-0002',0,1,NULL,'开凿平硐、斜井和立井时，自井口到坚硬岩层之间的井巷必须砌碹，并向坚硬岩层内至少延深5m。\r\n在山坡下开凿斜井和平硐时，井口顶、侧必须构筑挡墙和防洪水沟。',0,'admin','2012-06-13 17:09:06','2012-06-13 17:09:50','admin');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (29,'第十七条',26,'0001-0001-0002-0001-0001-0001',0,1,NULL,'掘进井巷和硐室时，必须采取湿式钻眼、冲洗井壁巷帮、水炮泥、爆破喷雾、装岩（煤）洒水和净化风流等综合防尘措施。\r\n冻结法凿井和在遇水膨胀的岩层中掘进不能采用湿式钻眼时，可采用干式钻眼，但必须采取捕尘措施，并使用个体防尘保护用品。',0,'admin','2012-06-13 17:09:06','2012-06-13 17:09:50','admin');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (30,'第十八条',26,'0001-0001-0002-0001-0001-0001',0,1,NULL,'每个生产矿井必须至少有2个能行人的通达地面的安全出口，各个出口间的距离不得小于30m。\r\n采用中央式通风系统的新建和改扩建矿井，设计中应规定井田边界附近的安全出口。当井田一翼走向较长、矿井发生灾害不能保证人员安全撤出时，必须掘出井田边界附近的安全出口。\r\n井下每一个水平到上一个水平和各个采区都必须至少有2个便于行人的安全出口，并与通达地面的安全出口相连接。未建成2个安全出口的水平或采区严禁生产。\r\n井巷交岔点，必须设置路标，标明所在地点，指明通往安全出口的方向。井下工作人员必须熟悉通往安全出口的路线。\r\n',0,'admin','2012-06-13 17:09:06','2012-06-13 17:09:50','admin');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (31,'第十九条',26,'0001-0001-0002-0001-0001-0001',0,1,NULL,'对于通达地面的安全出口和2个水平之间的安全出口，倾角等于或小于45°时，必须设置人行道，并根据倾角大小和实际需要设置扶手、台阶或梯道。倾角大于45°时，必须设置梯道间或梯子间，斜井梯道间必须分段错开设置，每段斜长不得大于10m；立井梯子间中的梯子角度不得大于80°，相邻2个平台的垂直距离不得大于8m。\r\n安全出口应经常清理、维护，保持畅通。',0,'admin','2012-06-13 17:09:06','2012-06-13 17:09:50','admin');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (32,'第二十条',26,'0001-0001-0002-0001-0001-0001',0,1,NULL,'主要绞车道不得兼作人行道。提升量不大，保证行车时不行人的，不受此限。',0,'admin','2012-06-13 17:09:06','2012-06-13 17:09:50','admin');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (33,'第二十一条',26,'0001-0001-0002-0001-0001-0001',0,1,NULL,'巷道净断面必须满足行人、运输、通风和安全设施及设备安装、检修、施工的需要，并符合下列要求：\r\n（一）主要运输巷和主要风巷的净高，自轨面起不得低于2m。架线电机车运输巷的净高必须符合本规程第三百五十六条和第三百五十七条的有关要求。\r\n（二）采区（包括盘区，以下各条同）内的上山、下山和平巷的净高不得低于2m，薄煤层内的不得低于1.8m。\r\n采煤工作面运输巷、回风巷及采区内的溜煤眼等的净断面或净高，由煤矿企业统一规定。\r\n巷道净断面的设计，必须按支护最大允许变形后的断面计算。\r\n',0,'admin','2012-06-13 17:09:06','2012-06-13 17:09:50','admin');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (34,'第二十二条',26,'0001-0001-0002-0001-0001-0001',0,1,NULL,'运输巷两侧（包括管、线、电缆）与运输设备最突出部分之间的距离，应符合下列要求：\r\n（一）新建矿井、生产矿井新掘运输巷的一侧，从巷道道碴面起1.6m的高度内，必须留有宽0.8m（综合机械化采煤矿井为1m）以上的人行道，管道吊挂高度不得低于1.8m；巷道另一侧的宽度不得小于0.3m（综合机械化采煤矿井为0.5m）。巷道内安设输送机时，输送机与巷帮支护的距离不得小于0.5m；输送机机头和机尾处与巷帮支护的距离应满足设备检查和维修的需要，并不得小于0.7m。巷道内移动变电站或平板车上综采设备的最突出部分，与巷帮支护的距离不得小于0.3m。\r\n（二）生产矿井已有巷道人行道的宽度不符合本条第一款第（一）项的要求时，必须在巷道的一侧设置躲避硐，2个躲避硐之间的距离不得超过40m。躲避硐宽度不得小于1.2m，深度不得小于0.7m，高度不得小于1.8m，躲避硐内严禁堆积物料。\r\n（三）在人车停车地点的巷道上下人侧，从巷道道碴面起1.6m的高度内，必须留有宽1m以上的人行道，管道吊挂高度不得低于1.8m。\r\n',0,'admin','2012-06-13 17:09:06','2012-06-13 17:09:50','admin');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (35,'第二十三条',26,'0001-0001-0002-0001-0001-0001',0,1,NULL,'在双轨运输巷中，2列列车最突出部分之间的距离，对开时不得小于0.2m，采区装载点不得小于0.7m，矿车摘挂钩地点不得小于1m。车辆最突出部分与巷道两侧距离，必须符合本规程第二十二条的要求。\r\n',0,'admin','2012-06-13 17:09:06','2012-06-13 17:09:50','admin');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (36,'第二十四条',26,'0001-0001-0002-0001-0001-0001',0,1,NULL,'采区结束回撤设备时，必须编制专门措施，加强通风、瓦斯、防火管理。',0,'admin','2012-06-13 17:09:06','2012-06-13 17:09:50','admin');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (37,'第二节',25,'0001-0001-0002-0001-0002',0,0,NULL,NULL,0,'admin','2012-06-13 17:09:06','2012-06-13 17:09:50','admin');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (38,'第二十五条',37,'0001-0001-0002-0001-0002-0001',0,1,NULL,'凿井期间，井口工作范围必须用栅栏围住，人员进出地点必须安装栅栏门；井口必须设置封口盘和井盖门，井盖门的两端必须安装栅栏，封口盘和井盖门必须坚固严密，并采用不燃性材料。\r\n',0,'admin','2012-06-13 17:09:06','2012-06-13 17:09:50','admin');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (39,'第二十六条',37,'0001-0001-0002-0001-0002-0002',0,1,NULL,'采用普通凿井法施工时，立井的永久或临时支护到井筒工作面的距离及防止片帮的措施必须根据岩性、水文地质条件和施工工艺在作业规程中明确规定。\r\n',0,'admin','2012-06-13 17:09:06','2012-06-13 17:09:50','admin');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (40,'第二十七条',37,'0001-0001-0002-0001-0002-0003',0,1,NULL,'立井井筒穿过表土层、砂层、松软岩层或煤层时，必须有专门措施。采用井圈或其他临时支护时，临时支护必须安全可靠、紧靠工作面，并及时进行永久支护。在建立永久支护前，每班应派专人观测地面沉降和临时支护后面的井帮变化情况；发现危险预兆时，必须立即停止工作，撤出人员，进行处理。\r\n',0,'admin','2012-06-13 17:09:06','2012-06-13 17:09:50','admin');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (41,'第二十八条',37,'0001-0001-0002-0001-0002-0004',0,1,NULL,'立井永久支护的质量必须符合设计要求。岩帮与支护之间必须填满灌实。井壁出水时必须采取导水或堵水等措施。\r\n',0,'admin','2012-06-13 17:09:06','2012-06-13 17:09:50','admin');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (42,'第二十九条',37,'0001-0001-0002-0001-0002-0005',0,1,NULL,'采用钻井法开凿立井井筒必须遵守下列规定：\r\n（一）钻井的设计与施工最终位置必须通过风化带，并向不透水的稳定基岩至少延深5m。\r\n（二）钻井期间，采用封口平台时，必须将井口封盖严密；采用井口梁时，必须有可靠的防坠措施。\r\n（三）钻井过程中，护壁泥浆的各项参数必须定时测定，发现问题立即调整。井筒内的泥浆面，必须保持高于地下静止水位。\r\n（四）钻井时必须测定井筒的偏斜度。偏斜超过规定时，必须及时纠正。井筒偏斜度及测点的间距必须在施工组织设计中明确规定。钻井完毕后，必须绘制井筒的纵横剖面图，井筒中心线和截面必须符合设计要求。\r\n（五）预制井壁的质量，必须逐节检查鉴定。井壁连接部位必须有可靠的防蚀、防水措施，合格后方可下沉井壁。\r\n（六）井壁下沉完成后，必须检查井壁偏斜度，只有符合要求后方可进行壁后充填，壁后充填必须密实。充填材料必须经过试验，满足强度和凝固时间的要求，并保证能够置换出泥浆。开凿沉井井壁的底部或开掘马头门之前，必须检查破壁处及其上方15～30m范围内壁后的充填质量，发现不合格时，必须采取可靠的补救措施。\r\n（七）开凿沉井井壁的底部和开掘马头门采用爆破作业时，必须制定安全措施。\r\n',0,'admin','2012-06-13 17:09:06','2012-06-13 17:09:50','admin');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (43,'第三十条',37,'0001-0001-0002-0001-0002-0006',0,1,NULL,'采用冻结法开凿立井井筒应遵守下列规定：\r\n（一）冻结深度应穿过风化带延深至稳定的基岩10m以上。基岩段涌水较大时，应加深冻结深度。\r\n（二）钻进冻结孔时，必须测定钻孔的方向和偏斜度，测斜的最大间隔不得超过30m，并绘制冻结孔实际偏斜平面位置图，偏斜度超过规定时，必须及时纠正。因钻孔偏斜影响冻结效果时，必须补孔。\r\n（三）地质检查钻孔不得打在冻结的井筒内。水文观测钻孔偏斜不得超出井筒，深度不得超过冻结段下部隔水层。\r\n（四）冻结管应采用无缝钢管焊接或螺纹连接，冻结管下入钻孔后应进行试漏，发现异常时，必须及时处理。\r\n（五）开始冻结后，必须经常观察水文观测孔的水位变化。只有在水文孔冒水7天、水量正常，确认冻结壁已交圈后，方可进行试挖。冻结和开凿过程中，要经常检查盐水温度和流量、井帮温度和位移，以及井帮和工作面渗漏盐水等情况。检查应有详细记录，发现异常，必须及时处理。\r\n（六）开凿表土层冻结段时，可以采用爆破作业，但必须制定安全技术措施。\r\n（七）掘进施工过程中，必须有防止冻结壁变形、片帮、掉石、断管等安全措施。\r\n（八）生根壁座应设在含水较少的稳定坚硬的基岩中。\r\n（九）只有在永久井壁施工全部完成后，方可停止冻结。\r\n（十）梁窝的设计和施工必须有防止漏水的措施。\r\n（十一）不论冻结管能否提拔回收，对全孔必须及时用水泥砂浆或混凝土全部充满填实。\r\n冻结站必须用不燃性材料建筑，并应有通风装置。应经常测定站内空气中氨气，氨的浓度不得超过0.004％。站内严禁烟火，并必须备有急救和消防器材。\r\n氨瓶和氨罐必须经过试验，合格后方准使用；在运输、使用和存放期间，应有安全措施。\r\n',0,'admin','2012-06-13 17:09:06','2012-06-13 17:09:50','admin');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (44,'第三十一条',37,'0001-0001-0002-0001-0002-0007',0,1,NULL,'立井井筒穿过含水岩层或破碎带，采用地面或工作面预注浆法进行堵水或加固时，应遵守下列规定：\r\n（一）注浆施工前，必须编制注浆工程设计。\r\n（二）注浆段长度必须大于注浆的含水岩层的厚度，并深入不透水岩层或硬岩层5～10m。井底的设计位置在注浆的含水岩层内时，注浆深度必须大于井深10m。\r\n（三）地面预注浆的钻孔，每钻进40m必须测斜1次，钻孔偏斜率不得超过0.5％。\r\n（四）注浆前，必须进行注浆泵和输送管路系统的耐压试验。试验压力必须达到最大注浆压力的1.5倍，试验时间不得小于15min，无异常情况后，方可使用。\r\n（五）注浆过程中，注浆压力突然上升时，必须停止注浆泵运转，卸压后方可处理。\r\n（六）每次注浆后，应至少停歇30min，方可提拔止浆塞，以防高压浆顶出钻杆。\r\n（七）冬季注浆施工时，注浆站和地面输浆管路，必须采取防冻措施。\r\n（八）井筒工作面预注浆前，在注浆的含水岩层上方，必须按设计要求设置止浆岩帽或混凝土止浆垫。含水岩层厚度大，需采用分段注浆和掘砌时，对每一注浆段，必须按设计要求设置止浆岩帽或混凝土止浆垫。岩帽和混凝土止浆垫的结构形式和厚度应根据最大注浆压力、岩石性质和工作条件确定。混凝土止浆垫由井壁支承时，应对井壁强度进行验算。\r\n（九）孔口管必须按设计孔位埋设牢固，并安设高压阀门。注浆前，必须对止浆垫和孔口管进行耐压试验，试验压力必须大于注浆压力1MPa。\r\n（十）钻注浆孔时，钻机必须安设牢固。取芯钻进时，应使用能够防止顶出钻具的钻头；无芯钻进时，可使用三翼钻头，以防承压水顶出钻具。\r\n（十一）井内应设吊泵，及时排除井底积水。当钻进注浆孔时，如井筒涌水量接近吊泵额定排水能力，必须停止钻进，提取钻具，关闭高压阀门，及时注浆。\r\n（十二）注浆站设在地面时，井上、下必须有可靠的通信联系。\r\n（十三）制浆和注浆的工作人员，应佩戴防护眼镜和口罩，水泥搅拌房内应采取防尘措施。\r\n（十四）注浆结束后，必须检查注浆效果，合格后，方可开凿井筒。\r\n',0,'admin','2012-06-13 17:09:06','2012-06-13 17:09:50','admin');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (45,'第三十二条',37,'0001-0001-0002-0001-0002-0008',0,1,NULL,'立井井筒漏水量每小时超过6m3或漏水中含砂，采用井壁注浆堵水时，必须编制施工组织设计并遵守下列规定：\r\n（一）井壁必须有承受最大注浆压力的强度。\r\n（二）井筒在流砂层部位时，注浆孔深度必须小于井壁厚度200mm。井筒采用双层井壁支护时，注浆孔应穿过内壁进入外壁100mm。当井壁破裂必须采用破壁注浆时，必须制定专门措施。\r\n（三）注浆管必须固结在井壁中，并装有阀门。钻孔可能发生涌砂时，应采取套管法或其他安全措施。采用套管法注浆时，必须对套管的固结强度进行耐压试验，只有达到注浆终压力后，方可使用。\r\n（四）在罐笼顶上进行钻孔注浆作业时，必须安设工作盘和注浆管路安全阀，作业人员必须佩带保险带，并在井口设专职值班人员。\r\n（五）井上、下都必须有可靠的通信设施，升降注浆作业吊盘或工作盘时，必须得到值班人员的允许。\r\n（六）井筒内进行钻孔注浆作业时，井底不得有人。注浆中必须观察井壁，发现问题必须停止作业，及时处理。\r\n（七）钻孔时应经常检查孔内涌水量和含砂量。涌水量较大或涌水中含砂时，必须停止钻进，及时注浆；钻孔中无水时，必须及时严密封孔。\r\n（八）注浆管露出井壁的管端与提升容器之间的间隙，必须符合本规程第三百八十七条的有关规定。\r\n',0,'admin','2012-06-13 17:09:06','2012-06-13 17:09:50','admin');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (46,'第三十三条',37,'0001-0001-0002-0001-0002-0009',0,1,NULL,'开凿或延深立井的施工组织设计中，必须有吊盘、保护盘以及凿岩、抓岩、出矸等设备的设置、运行、维修的安全措施。',0,'admin','2012-06-13 17:09:06','2012-06-13 17:09:50','admin');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (47,'第三十四条',37,'0001-0001-0002-0001-0002-0010',0,1,NULL,'开凿或延深立井时，井筒内必须设有在提升设备发生故障时专供人员出井的安全设施。',0,'admin','2012-06-13 17:09:06','2012-06-13 17:09:50','admin');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (48,'第三十五条',37,'0001-0001-0002-0001-0002-0011',0,1,NULL,'工作人员在下列情况下必须佩带保险带：\r\n（一）乘吊桶或随吊盘升降时。\r\n（二）在井架上或井筒内的悬吊设备上作业时。\r\n（三）拆除保险盘或掘凿保护岩柱时。\r\n（四）在井圈上清理浮矸时。\r\n（五）在倒矸台上围栏外作业时。\r\n保险带必须定期按有关规定试验。保险带必须拴在牢固的构件上。每次使用前必须检查，发现损坏时，必须立即更换。\r\n',0,'admin','2012-06-13 17:09:06','2012-06-13 17:09:50','admin');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (49,'第三十六条',37,'0001-0001-0002-0001-0002-0012',0,1,NULL,'开凿或延深立井时，井筒内每个工作地点必须设置独立的信号装置。掘进和砌壁平行作业时，从吊盘和掘进工作面所发出的信号，必须有明显的区别。\r\n井内和井口的信号必须由专职信号工发送。除紧急停车外，严禁不经过井口信号工直接从井内向绞车房发送信号。井内作业人员必须熟悉并会发送信号。\r\n井口、井底信号工应在吊罐提起适当高度后，先发暂停信号，进行稳罐；待吊罐稳定，清理罐底附着物后，才能发出下降或提升信号。信号工必须目接、目送吊罐安全通过责任段。\r\n',0,'admin','2012-06-13 17:09:06','2012-06-13 17:09:50','admin');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (50,'第三十七条',37,'0001-0001-0002-0001-0002-0013',0,1,NULL,'安装井架或井架上的设备时必须盖严井口。装备井筒与安装井架及井架上的设备平行作业时，井口掩盖装置必须坚固可靠，能承受井架上坠落物的冲击。',0,'admin','2012-06-13 17:09:06','2012-06-13 17:09:50','admin');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (51,'第三十八条',37,'0001-0001-0002-0001-0002-0014',0,1,NULL,NULL,0,'admin','2012-06-13 17:09:06','2012-06-13 17:09:50','admin');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (52,'第三十九条',37,'0001-0001-0002-0001-0002-0015',0,1,NULL,NULL,0,'admin','2012-06-13 17:09:06','2012-06-13 17:09:50','admin');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (53,'第四十条',37,'0001-0001-0002-0001-0002-0016',0,1,NULL,NULL,0,'admin','2012-06-13 17:09:06','2012-06-13 17:09:50','admin');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (54,'第四十一条',37,'0001-0001-0002-0001-0002-0017',0,1,NULL,NULL,0,'admin','2012-06-13 17:09:06','2012-06-13 17:09:50','admin');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (55,'第四十二条',37,'0001-0001-0002-0001-0002-0018',0,1,NULL,NULL,0,'admin','2012-06-13 17:09:06','2012-06-13 17:09:50','admin');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (56,'第四十三条',37,'0001-0001-0002-0001-0002-0019',0,1,NULL,NULL,0,'admin','2012-06-13 17:09:06','2012-06-13 17:09:50','admin');
insert  into `t_specification_chapter`(`id`,`name`,`parent_id`,`tree_index`,`sort`,`is_leaf`,`state`,`content`,`version`,`create_by`,`create_time`,`last_modify_time`,`last_modify_by`) values (57,'第四十四条',37,'0001-0001-0002-0001-0002-0020',0,1,NULL,NULL,0,'admin','2012-06-13 17:09:06','2012-06-13 17:09:50','admin');

UNLOCK TABLES;

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
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(255) DEFAULT NULL,
  `folder` varchar(255) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_system_company` */

LOCK TABLES `t_system_company` WRITE;

insert  into `t_system_company`(`id`,`company_name`,`folder`,`email`,`phone_number`,`status`,`version`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (0,'默认','default','darkmi@126.com','13811215910','NORMAL',0,'admin','2012-04-25 16:42:29',NULL,NULL);
insert  into `t_system_company`(`id`,`company_name`,`folder`,`email`,`phone_number`,`status`,`version`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (1,'北京矿业集团','beijingkuangyejituan','22@126.com','13811215910','NORMAL',0,'admin','2012-04-25 11:29:45',NULL,NULL);
insert  into `t_system_company`(`id`,`company_name`,`folder`,`email`,`phone_number`,`status`,`version`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (2,'河北矿业集团','hebeikuangyejituan','11@126.com','13811215910',NULL,0,'admin','2012-04-26 19:23:56',NULL,NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_system_role` */

LOCK TABLES `t_system_role` WRITE;

insert  into `t_system_role`(`id`,`name`) values (1,'管理员');
insert  into `t_system_role`(`id`,`name`) values (2,'系统管理');

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
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (2,1);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (2,2);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (2,3);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (2,4);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (2,5);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (2,6);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (2,7);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (2,8);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (2,9);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (2,10);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (2,11);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (2,12);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (2,13);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (2,14);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (2,15);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (2,16);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (2,17);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (2,18);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (2,19);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (2,20);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (2,21);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (2,22);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (2,23);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (2,24);
insert  into `t_system_role_authority`(`role_id`,`authority_id`) values (2,25);

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_system_user` */

LOCK TABLES `t_system_user` WRITE;

insert  into `t_system_user`(`id`,`login_name`,`sha_password`,`name`,`email`,`phone_number`,`status`,`company_id`,`login_time`,`version`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (1,'admin','d033e22ae348aeb5660fc2140aec35850c4da997','Admin','darkmi@126.com','','enabled',0,'2011-03-26 11:38:54',13,'admin','2009-07-11 08:52:25','admin','2011-10-10 15:02:23');
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
  `create_type` varchar(8) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `task_name` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `state` char(6) DEFAULT 'NORMAL',
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_modify_by` varchar(50) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `t_task` */

LOCK TABLES `t_task` WRITE;

insert  into `t_task`(`create_type`,`id`,`task_name`,`path`,`company_id`,`description`,`state`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (NULL,6,'第一份作业规程','/company/default/task/diyifenzuoyeguicheng/',0,'第一份作业规程','NORMAL','admin','2012-05-14 17:20:07',NULL,NULL);
insert  into `t_task`(`create_type`,`id`,`task_name`,`path`,`company_id`,`description`,`state`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values ('TEMPLATE',7,'第二份作业规程','/company/default/task/dierfenzuoyeguicheng/',0,'第二份作业规程。','NORMAL','admin','2012-05-14 19:16:07',NULL,NULL);
insert  into `t_task`(`create_type`,`id`,`task_name`,`path`,`company_id`,`description`,`state`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values ('NEW',8,'111333',NULL,NULL,'111333',NULL,'admin','2012-08-21 16:51:57','admin','2012-08-21 16:52:09');

UNLOCK TABLES;

/*Table structure for table `t_task_chapter` */

DROP TABLE IF EXISTS `t_task_chapter`;

CREATE TABLE `t_task_chapter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `chapter_id` varchar(255) DEFAULT NULL,
  `chapter_name` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `task_id` bigint(20) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT '0',
  `display_order` int(10) DEFAULT '0',
  `description` varchar(255) DEFAULT NULL,
  `state` char(6) DEFAULT 'NORMAL',
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_modify_by` varchar(50) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

/*Data for the table `t_task_chapter` */

LOCK TABLES `t_task_chapter` WRITE;

insert  into `t_task_chapter`(`id`,`chapter_id`,`chapter_name`,`file_name`,`task_id`,`parent_id`,`display_order`,`description`,`state`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (21,NULL,'第一章','',6,0,1,'1','NORMAL','admin','2012-05-14 17:20:07',NULL,NULL);
insert  into `t_task_chapter`(`id`,`chapter_id`,`chapter_name`,`file_name`,`task_id`,`parent_id`,`display_order`,`description`,`state`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (22,NULL,'第一章第一节','01-01.docx',6,21,2,'3','NORMAL','admin','2012-05-14 17:20:07',NULL,NULL);
insert  into `t_task_chapter`(`id`,`chapter_id`,`chapter_name`,`file_name`,`task_id`,`parent_id`,`display_order`,`description`,`state`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (23,NULL,'第一章第二节','01-02.docx',6,21,3,'4','NORMAL','admin','2012-05-14 17:20:07',NULL,NULL);
insert  into `t_task_chapter`(`id`,`chapter_id`,`chapter_name`,`file_name`,`task_id`,`parent_id`,`display_order`,`description`,`state`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (24,NULL,'第二章','',6,0,4,'2','NORMAL','admin','2012-05-14 17:20:07',NULL,NULL);
insert  into `t_task_chapter`(`id`,`chapter_id`,`chapter_name`,`file_name`,`task_id`,`parent_id`,`display_order`,`description`,`state`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (25,NULL,'第二章第一节','02-01.docx',6,24,5,'5','NORMAL','admin','2012-05-14 17:20:07',NULL,NULL);
insert  into `t_task_chapter`(`id`,`chapter_id`,`chapter_name`,`file_name`,`task_id`,`parent_id`,`display_order`,`description`,`state`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (26,NULL,'第一章','',7,0,1,'1','NORMAL','admin','2012-05-14 19:16:07',NULL,NULL);
insert  into `t_task_chapter`(`id`,`chapter_id`,`chapter_name`,`file_name`,`task_id`,`parent_id`,`display_order`,`description`,`state`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (27,NULL,'第一章第一节','01-01.docx',7,26,2,'3','NORMAL','admin','2012-05-14 19:16:07',NULL,NULL);
insert  into `t_task_chapter`(`id`,`chapter_id`,`chapter_name`,`file_name`,`task_id`,`parent_id`,`display_order`,`description`,`state`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (28,NULL,'第一章第二节','01-02.docx',7,26,3,'4','NORMAL','admin','2012-05-14 19:16:07',NULL,NULL);
insert  into `t_task_chapter`(`id`,`chapter_id`,`chapter_name`,`file_name`,`task_id`,`parent_id`,`display_order`,`description`,`state`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (29,NULL,'第二章','',7,0,4,'2','NORMAL','admin','2012-05-14 19:16:07',NULL,NULL);
insert  into `t_task_chapter`(`id`,`chapter_id`,`chapter_name`,`file_name`,`task_id`,`parent_id`,`display_order`,`description`,`state`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (30,NULL,'第二章第一节','02-01.docx',7,29,5,'5','NORMAL','admin','2012-05-14 19:16:07',NULL,NULL);

UNLOCK TABLES;

/*Table structure for table `t_template` */

DROP TABLE IF EXISTS `t_template`;

CREATE TABLE `t_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `template_name` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `state` char(6) DEFAULT 'NORMAL',
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_modify_by` varchar(50) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_template` */

LOCK TABLES `t_template` WRITE;

insert  into `t_template`(`id`,`template_name`,`path`,`company_id`,`description`,`state`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`) values (1,'默认模板','/company/default/template/default/',0,'系统自带的默认模板。','NORMAL','admin','2012-04-07 16:05:28','admin',NULL);

UNLOCK TABLES;

/*Table structure for table `t_template_chapter` */

DROP TABLE IF EXISTS `t_template_chapter`;

CREATE TABLE `t_template_chapter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `chapter_id` varchar(255) DEFAULT NULL,
  `chapter_name` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `template_id` bigint(20) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT '0',
  `display_order` int(10) DEFAULT '0',
  `description` varchar(255) DEFAULT NULL,
  `state` char(6) DEFAULT 'NORMAL',
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_modify_by` varchar(50) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  `is_leaf` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `t_template_chapter` */

LOCK TABLES `t_template_chapter` WRITE;

insert  into `t_template_chapter`(`id`,`chapter_id`,`chapter_name`,`file_name`,`template_id`,`parent_id`,`display_order`,`description`,`state`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`,`is_leaf`) values (1,'1','第一章','',1,0,1,'1','NORMAL','admin',NULL,'admin','2012-08-23 18:18:35',0);
insert  into `t_template_chapter`(`id`,`chapter_id`,`chapter_name`,`file_name`,`template_id`,`parent_id`,`display_order`,`description`,`state`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`,`is_leaf`) values (2,'2','第二章','',1,0,4,'2','NORMAL','admin',NULL,'admin',NULL,0);
insert  into `t_template_chapter`(`id`,`chapter_id`,`chapter_name`,`file_name`,`template_id`,`parent_id`,`display_order`,`description`,`state`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`,`is_leaf`) values (3,'3','第一章第一节','01-01.docx',1,1,2,'3','NORMAL','admin',NULL,'admin',NULL,1);
insert  into `t_template_chapter`(`id`,`chapter_id`,`chapter_name`,`file_name`,`template_id`,`parent_id`,`display_order`,`description`,`state`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`,`is_leaf`) values (4,'4','第一章第二节','01-02.docx',1,1,3,'4','NORMAL','admin',NULL,'admin',NULL,1);
insert  into `t_template_chapter`(`id`,`chapter_id`,`chapter_name`,`file_name`,`template_id`,`parent_id`,`display_order`,`description`,`state`,`create_by`,`create_time`,`last_modify_by`,`last_modify_time`,`is_leaf`) values (5,'5','第二章第一节','02-01.docx',1,2,5,'5','NORMAL','admin',NULL,'admin','2012-05-03 11:28:03',1);

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
