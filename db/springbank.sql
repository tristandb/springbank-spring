-- MySQL dump 10.13  Distrib 5.7.19, for Win64 (x86_64)
--
-- Host: localhost    Database: springbank
-- ------------------------------------------------------
-- Server version	5.7.19

USE springbank;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bank_account`
--

DROP TABLE IF EXISTS `bank_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bank_account` (
  `account_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `balance` double DEFAULT NULL,
  `holder_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`account_id`),
  KEY `FK8jud4i10ibvbti0xtla8e88h5` (`holder_user_id`),
  CONSTRAINT `FK8jud4i10ibvbti0xtla8e88h5` FOREIGN KEY (`holder_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_account`
--

LOCK TABLES `bank_account` WRITE;
/*!40000 ALTER TABLE `bank_account` DISABLE KEYS */;
INSERT INTO `bank_account` VALUES (6,238322,3),(7,23,4),(8,1000000000,4);
/*!40000 ALTER TABLE `bank_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `card`
--

DROP TABLE IF EXISTS `card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `card` (
  `card_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `card_number` varchar(255) DEFAULT NULL,
  `expiration_date` date DEFAULT NULL,
  `pin` varchar(255) DEFAULT NULL,
  `bank_account_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`card_id`),
  UNIQUE KEY `UK9kj2dtyu7a4w0jh34p95o6yqd` (`bank_account_id`,`card_number`),
  KEY `FKl4gbym62l738id056y12rt6q6` (`user_id`),
  CONSTRAINT `FK87o2vhvm08af0vd5y0sp9mccd` FOREIGN KEY (`bank_account_id`) REFERENCES `bank_account` (`account_id`),
  CONSTRAINT `FKl4gbym62l738id056y12rt6q6` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card`
--

LOCK TABLES `card` WRITE;
/*!40000 ALTER TABLE `card` DISABLE KEYS */;
INSERT INTO `card` VALUES (1,'0901','2022-07-05','5434',6,3),(2,'6095','2022-07-05','6957',7,4),(3,'9053','2022-07-05','1671',8,4),(4,'5630','2022-08-08','3465',7,3);
/*!40000 ALTER TABLE `card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iban`
--

DROP TABLE IF EXISTS `iban`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iban` (
  `iban_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `iban` varchar(255) DEFAULT NULL,
  `bank_account_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`iban_id`),
  UNIQUE KEY `UK_2w6dmssco96gvn3l6b4igkul4` (`iban`),
  UNIQUE KEY `UK_pm6uut04sgelgdkhgb90a0tn7` (`bank_account_id`),
  CONSTRAINT `FK9gaor8on4tt4xk5mr3r7yj1is` FOREIGN KEY (`bank_account_id`) REFERENCES `bank_account` (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iban`
--

LOCK TABLES `iban` WRITE;
/*!40000 ALTER TABLE `iban` DISABLE KEYS */;
INSERT INTO `iban` VALUES (1,'NL86SPRI0752582963',6),(2,'NL83SPRI0114480386',7),(3,'NL15SPRI0749536255',8);
/*!40000 ALTER TABLE `iban` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction` (
  `transaction_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `target_name` varchar(255) DEFAULT NULL,
  `source_account_id` bigint(20) DEFAULT NULL,
  `target_account_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `FK14pfnxb2ly7iuu3w84q3nhmin` (`source_account_id`),
  KEY `FK4wmdsx0mrxc47wfv1wpi78u1` (`target_account_id`),
  CONSTRAINT `FK14pfnxb2ly7iuu3w84q3nhmin` FOREIGN KEY (`source_account_id`) REFERENCES `bank_account` (`account_id`),
  CONSTRAINT `FK4wmdsx0mrxc47wfv1wpi78u1` FOREIGN KEY (`target_account_id`) REFERENCES `bank_account` (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,100,'2017-08-08 00:00:00','Hier heb je geld','J. Jannes',7,6),(2,10,'2017-08-02 00:00:00','Doe er iets leuks mee','K. Ham',6,7),(3,50,'2017-07-02 00:00:00','cadeau','H. Oost',7,8),(4,25,'2017-08-01 00:00:00','dinges','H. Oost',6,8);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bsn` varchar(255) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `initials` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `street_address` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `telephone_number` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_e9nea19uafpjcwt3920ksylhf` (`bsn`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'32432435','1954-10-14','d@d.nl','B.','Bernard','dd','Yffiniac','Hinault','0612345678','bernard@hinault.fr'),(2,'123456789','1970-01-01','info@svenkonings.nl','S.','Sven','sven','Brink 123, Ons Dorp','Konings','0612345678','sven@konings.nl'),(3,'435456553','1947-12-01','xyz@test.nl','D.','Dagobert','dagdag','Pakhuislaan 1, 1234AB Duckstad','Duck','05312312312','dagobertduck@gmail.com'),(4,'640673175','1954-02-19','donald@gmail.com','D','Duck','kwikkwekkwak','1313 Webfoot Walk, Duckburg','Donald','+316 12345678','fakeduckd');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_bank_account`
--

DROP TABLE IF EXISTS `user_bank_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_bank_account` (
  `account_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`account_id`,`user_id`),
  KEY `FKjn71dv46t5vlrjbqroobqfxcl` (`user_id`),
  CONSTRAINT `FKch3sk0ceohb3w4gajaop2vwbj` FOREIGN KEY (`account_id`) REFERENCES `bank_account` (`account_id`),
  CONSTRAINT `FKjn71dv46t5vlrjbqroobqfxcl` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_bank_account`
--

LOCK TABLES `user_bank_account` WRITE;
/*!40000 ALTER TABLE `user_bank_account` DISABLE KEYS */;
INSERT INTO `user_bank_account` VALUES (6,3),(7,3),(7,4),(8,4);
/*!40000 ALTER TABLE `user_bank_account` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-09 19:58:45
