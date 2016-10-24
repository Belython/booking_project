-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: booking
-- ------------------------------------------------------
-- Server version	5.6.31-log

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
-- Table structure for table `rooms_types`
--

DROP TABLE IF EXISTS `rooms_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rooms_types` (
  `ROOM_TYPE_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ROOM_TYPE` varchar(15) NOT NULL,
  `MAX_PERSONS` int(11) NOT NULL,
  `PRICE_PER_NIGHT` bigint(20) NOT NULL,
  `FACILITIES` blob NOT NULL,
  `ROOM_TYPE_STATUS` varchar(45) NOT NULL,
  PRIMARY KEY (`ROOM_TYPE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=144 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms_types`
--

LOCK TABLES `rooms_types` WRITE;
/*!40000 ALTER TABLE `rooms_types` DISABLE KEYS */;
INSERT INTO `rooms_types` VALUES (1,'Standard Twin',2,200,'�\�\0sr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0wi-fit\0safex','active'),(2,'Standard Double',2,300,'�\�\0sr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0wi-fit\0safex','active'),(3,'Family',3,400,'�\�\0sr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0wi-fit\0safex','active'),(4,'Standard Single',1,500,'�\�\0sr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0wi-fit\0safex','active'),(5,'Huge',4,600,'�\�\0sr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0wi-fit\0safex','active'),(6,'Standard Twin',3,300,'�\�\0sr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0wi-fit\0safet\0soapx','active'),(7,'Standard Twin',4,400,'�\�\0sr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0showert\0wi-fit\0safet\0soapx','active'),(8,'Family',4,500,'�\�\0sr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0showert\0wi-fit\0safet\0soapx','active'),(9,'Family',3,500,'�\�\0sr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0showert\0wi-fit\0safet\0soapx','active'),(10,'Family',3,400,'�\�\0sr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0showert\0wi-fit\0safet\0soapx','active'),(11,'Большой',10,2000,'�\�\0sr\0java.util.TreeSetݘP��\�[\0\0xppw\0\0\0t\0вообще всеx','active'),(12,'Большой',10,2000,'�\�\0sr\0java.util.TreeSetݘP��\�[\0\0xppw\0\0\0t\0вообще всеx','active'),(13,'Огромный',20,4000,'�\�\0sr\0java.util.TreeSetݘP��\�[\0\0xppw\0\0\0t\0тоже всеx','active'),(14,'Огромный',20,4000,'�\�\0sr\0java.util.TreeSetݘP��\�[\0\0xppw\0\0\0t\0тоже всеx','active'),(15,'Огромный',20,4000,'�\�\0sr\0java.util.TreeSetݘP��\�[\0\0xppw\0\0\0t\0тоже всеx','active'),(16,'Крохотный',1,10,'�\�\0sr\0java.util.TreeSetݘP��\�[\0\0xppw\0\0\0t\0ничегоx','active'),(17,'Крохотный',1,10,'�\�\0sr\0java.util.TreeSetݘP��\�[\0\0xppw\0\0\0t\0ничегоx','active'),(18,'Крохотный',1,10,'�\�\0sr\0java.util.TreeSetݘP��\�[\0\0xppw\0\0\0t\0ничегоx','active'),(19,'Средний',2,100,'�\�\0sr\0java.util.TreeSetݘP��\�[\0\0xppw\0\0\0t\0\rкое-чтоx','active'),(20,'Средний',2,100,'�\�\0sr\0java.util.TreeSetݘP��\�[\0\0xppw\0\0\0t\0\rкое-чтоx','active'),(21,'Средний',2,100,'�\�\0sr\0java.util.TreeSetݘP��\�[\0\0xppw\0\0\0t\0\rкое-чтоx','active');
/*!40000 ALTER TABLE `rooms_types` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-14 16:41:11
