# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.19)
# Database: library
# Generation Time: 2017-12-24 09:33:57 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table admin
# ------------------------------------------------------------

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `admin_id` varchar(15) NOT NULL,
  `password` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;

INSERT INTO `admin` (`admin_id`, `password`)
VALUES
	('20170001','111111');

/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table book_info
# ------------------------------------------------------------

DROP TABLE IF EXISTS `book_info`;

CREATE TABLE `book_info` (
  `book_id` varchar(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `author` varchar(50) NOT NULL,
  `publish` varchar(30) NOT NULL,
  `ISBN` varchar(13) NOT NULL,
  `language` varchar(10) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `pubdate` date DEFAULT NULL,
  `class_id` int(11) DEFAULT NULL,
  `pressmark` int(11) DEFAULT NULL,
  `state` smallint(6) DEFAULT '1',
  `link` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `book_info` WRITE;
/*!40000 ALTER TABLE `book_info` DISABLE KEYS */;

INSERT INTO `book_info` (`book_id`, `name`, `author`, `publish`, `ISBN`, `language`, `price`, `pubdate`, `class_id`, `pressmark`, `state`, `link`)
VALUES
	('10000001','大雪中的山庄','东野圭吾 ','北京十月文艺出版社','9787530216835','中文',35.00,'2017-06-01',8,13,1,'https://book.douban.com/subject/27037148/'),
	('10000002','大雪中的山庄','东野圭吾','北京十月文艺出版社','9787530216835','中文',35.00,'2017-06-01',8,13,-1,'https://book.douban.com/subject/27037148/'),
	('10000003','三生三世十里桃花','唐七公子 ','沈阳出版社','9787544138000','中文',26.80,'2009-01-06',6,2,0,'https://book.douban.com/subject/3426869/'),
	('10000004','何以笙箫默','顾漫 ','朝华出版社','9787505414709','中文',15.00,'2007-04-03',6,2,-1,'https://book.douban.com/subject/1461903/'),
	('10000005','11处特工皇妃','潇湘冬儿','江苏文艺出版社','9787539943893','中文',74.80,'2011-05-05',6,2,1,'https://book.douban.com/subject/6139592/'),
	('10000006','人类简史','[以色列] 尤瓦尔·赫拉利 ','中信出版社','9787508647357','英文',68.00,'2014-11-01',10,3,1,'https://book.douban.com/subject/25985021/'),
	('10000007','明朝那些事儿（1-9）','当年明月 ','中国海关出版社','9787801656087','中文',358.20,'2009-04-06',10,3,0,'https://book.douban.com/subject/3674537/'),
	('10000010','经济学原理（上下）','[美] 曼昆 ','机械工业出版社','9787111126768','英文',88.00,'2003-08-05',5,5,1,'https://book.douban.com/subject/1028842/'),
	('1223','数据库','123','bjtu','123','中文',10.00,'2017-11-11',2,1,1,'http'),
	('123','小王子','12','12','12','12',12.00,'1990-01-01',12,123,1,'http'),
	('50000004','方向','马克-安托万·马修 ','后浪丨北京联合出版公司','9787020125265','中文',99.80,'2017-04-01',8,13,1,'https://book.douban.com/subject/26975609/'),
	('50000005','画的秘密','马克-安托万·马修 ','北京联合出版公司·后浪出版公司','9787550265608','中文',60.00,'2016-01-01',8,13,0,'https://book.douban.com/subject/26662689/'),
	('50000007','造彩虹的人','东野圭吾 ','北京十月文艺出版社','9787530216859','中文',39.50,'2017-06-01',8,13,1,'https://book.douban.com/subject/27037149/'),
	('50000008','控方证人','阿加莎·克里斯蒂 ','新星出版社','9787513325745','中文',35.00,'2017-05-01',8,12,1,'https://book.douban.com/subject/27021110/'),
	('50000009','少有人走的路','M·斯科特·派克 ','吉林文史出版社','9787807023777','中文',26.00,'2007-01-01',8,12,0,'https://book.douban.com/subject/1775691/'),
	('50000010','追寻生命的意义','[奥] 维克多·弗兰克 ','新华出版社','9787501162734','中文',12.00,'2003-01-01',8,16,0,'https://book.douban.com/subject/1135734/'),
	('50000011','秘密花园','乔汉娜·贝斯福 ','北京联合出版公司','9787550252585','中文',42.00,'2015-06-01',8,18,1,'https://book.douban.com/subject/26376603/');

/*!40000 ALTER TABLE `book_info` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table class_info
# ------------------------------------------------------------

DROP TABLE IF EXISTS `class_info`;

CREATE TABLE `class_info` (
  `class_id` int(11) NOT NULL AUTO_INCREMENT,
  `class_name` varchar(45) NOT NULL,
  PRIMARY KEY (`class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

LOCK TABLES `class_info` WRITE;
/*!40000 ALTER TABLE `class_info` DISABLE KEYS */;

INSERT INTO `class_info` (`class_id`, `class_name`)
VALUES
	(1,'哲学'),
	(2,'社会科学总论'),
	(3,'政治法律'),
	(4,'军事'),
	(5,'经济'),
	(6,'文化'),
	(7,'语言'),
	(8,'文学'),
	(9,'艺术'),
	(10,'历史地理'),
	(11,'自然科学总论'),
	(12,' 数理科学和化学'),
	(13,'天文学、地球科学'),
	(14,'生物科学'),
	(15,'医药、卫生'),
	(16,'农业科学'),
	(17,'工业技术'),
	(18,'交通运输'),
	(19,'航空、航天'),
	(20,'环境科学'),
	(21,'综合');

/*!40000 ALTER TABLE `class_info` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table lend_list
# ------------------------------------------------------------

DROP TABLE IF EXISTS `lend_list`;

CREATE TABLE `lend_list` (
  `sernum` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` varchar(20) NOT NULL DEFAULT '',
  `reader_id` varchar(11) NOT NULL,
  `lend_date` date DEFAULT NULL,
  `back_date` date DEFAULT NULL,
  `deadline` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`sernum`)
) ENGINE=InnoDB AUTO_INCREMENT=2015040169 DEFAULT CHARSET=utf8;

LOCK TABLES `lend_list` WRITE;
/*!40000 ALTER TABLE `lend_list` DISABLE KEYS */;

INSERT INTO `lend_list` (`sernum`, `book_id`, `reader_id`, `lend_date`, `back_date`, `deadline`)
VALUES
	(40139,'10000001','15280001','2017-03-15','2017-12-21','2017-04-15'),
	(40140,'10000003','15280001','2017-06-10','2017-12-07','2017-07-10'),
	(40141,'10000006','15280001','2017-06-12','2017-09-02','2017-07-12'),
	(40142,'50000004','15280001','2017-03-15','2017-09-03','2017-04-15'),
	(40143,'50000005','15280003','2017-06-15',NULL,'2017-07-15'),
	(40145,'10000001','15280001','2017-09-02','2017-12-21','2017-10-02'),
	(40149,'10000001','15281124','2017-12-06','2017-12-21','2018-03-06'),
	(40150,'10000003','15281124','2017-12-06','2017-12-07','2018-03-06'),
	(40158,'50000010','15280004','2017-12-07',NULL,'2018-01-07'),
	(40159,'10000002','15281124','2017-12-09','2017-12-09','2018-03-09'),
	(40160,'10000010','15281124','2017-12-09','2017-12-09','2018-03-09'),
	(40161,'10000007','15280001','2017-12-09',NULL,'2018-01-09'),
	(40162,'50000009','15280001','2017-12-09',NULL,'2018-01-09'),
	(40163,'10000001','15281124','2017-12-09','2017-12-21','2018-03-09'),
	(40164,'10000003','15281124','2017-12-09',NULL,'2018-03-09'),
	(40165,'10000002','15281124','2017-12-09',NULL,'2018-03-09'),
	(40166,'10000004','15281124','2017-12-09',NULL,'2018-03-09'),
	(2015040167,'10000001','15280001','2017-12-21','2017-12-21','2018-01-21'),
	(2015040168,'123','15280001','2017-12-21','2017-12-21','2018-01-21');

/*!40000 ALTER TABLE `lend_list` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table reader_card
# ------------------------------------------------------------

DROP TABLE IF EXISTS `reader_card`;

CREATE TABLE `reader_card` (
  `reader_id` varchar(11) NOT NULL,
  `name` varchar(16) NOT NULL DEFAULT '',
  `passwd` varchar(15) NOT NULL DEFAULT '111111',
  `card_state` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`reader_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `reader_card` WRITE;
/*!40000 ALTER TABLE `reader_card` DISABLE KEYS */;

INSERT INTO `reader_card` (`reader_id`, `name`, `passwd`, `card_state`)
VALUES
	('15280001','张华','111111',1),
	('15280002','王小伟','111111',1),
	('15280003','王莞尔','111111',1),
	('15280004','张明华','111111',1),
	('15280005','李一琛','111111',1),
	('15280006','李二飞','111111',1);

/*!40000 ALTER TABLE `reader_card` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table reader_info
# ------------------------------------------------------------

DROP TABLE IF EXISTS `reader_info`;

CREATE TABLE `reader_info` (
  `reader_id` varchar(11) NOT NULL,
  `name` varchar(16) NOT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `telcode` varchar(11) DEFAULT '',
  PRIMARY KEY (`reader_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `reader_info` WRITE;
/*!40000 ALTER TABLE `reader_info` DISABLE KEYS */;

INSERT INTO `reader_info` (`reader_id`, `name`, `sex`, `birth`, `address`, `telcode`)
VALUES
	('15280001','张华','男','1997-01-01','北京','12345678912'),
	('15280002','王小伟','男','1997-02-01','北京市','12345678909'),
	('15280003','王莞尔','女','1997-04-15','浙江省杭州市','12345678908'),
	('15280004','张明华','男','1996-08-29','陕西省西安市','12345678907'),
	('15280005','李一琛','男','1996-01-01','江苏省苏州市','15123659875'),
	('15280006','李二飞','男','1996-05-03','山东省青岛市','15369874123');

/*!40000 ALTER TABLE `reader_info` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
