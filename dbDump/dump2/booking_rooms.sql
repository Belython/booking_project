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
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rooms` (
  `ROOM_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `HOTEL_ID` bigint(20) NOT NULL,
  `ROOM_TYPE_ID` bigint(20) NOT NULL,
  `ROOM_NUMBER` int(11) NOT NULL,
  `BOOKED_DATES` blob NOT NULL,
  `ROOM_STATUS` varchar(45) NOT NULL,
  PRIMARY KEY (`ROOM_ID`),
  KEY `ROOM_TYPE_ID_idx` (`ROOM_TYPE_ID`),
  KEY `HOTEL_ROOM_KEY_idx` (`HOTEL_ID`),
  CONSTRAINT `HOTEL_ROOM_KEY` FOREIGN KEY (`HOTEL_ID`) REFERENCES `hotels` (`HOTEL_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ROOM_TYPE_ROOM_KEY` FOREIGN KEY (`ROOM_TYPE_ID`) REFERENCES `rooms_types` (`ROOM_TYPE_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (1,1,3,1,'�\�\0sr\0java.util.TreeMap��>-%j\�\0L\0\ncomparatort\0Ljava/util/Comparator;xppw\0\0\0sr\0java.lang.Long;�\�̏#\�\0J\0valuexr\0java.lang.Number����\��\0\0xp\0\0NA��sq\0~\0\0\0N�Āx','deleted'),(2,1,4,2,'�\�\0sr\0java.util.TreeMap��>-%j\�\0L\0\ncomparatort\0Ljava/util/Comparator;xppw\0\0\0sr\0java.lang.Long;�\�̏#\�\0J\0valuexr\0java.lang.Number����\��\0\0xp\0\0NA��sq\0~\0\0\0N�Āx','active'),(3,2,5,3,'','deleted'),(4,2,4,4,'','active'),(5,2,5,5,'','active'),(6,1,2,6,'�\�\0p','active'),(7,2,3,7,'','active'),(8,1,4,8,'�\�\0sr\0java.util.TreeMap��>-%j\�\0L\0\ncomparatort\0Ljava/util/Comparator;xppw\0\0\0sr\0java.lang.Long;�\�̏#\�\0J\0valuexr\0java.lang.Number����\��\0\0xp\0\0NA��sq\0~\0\0\0N�Āx','deleted'),(9,1,4,9,'','active'),(10,2,3,20,'','active'),(11,1,4,45,'','active'),(12,1,4,25,'','active'),(13,1,4,25,'','active'),(14,1,4,25,'','active'),(15,1,4,42,'','active'),(16,1,4,42,'','active'),(17,1,4,58,'','active'),(18,1,4,54,'','active'),(19,1,4,58,'','active'),(20,1,4,21,'','active'),(21,1,4,22,'','active'),(22,1,4,13,'','active'),(23,1,4,14,'','active'),(24,1,4,15,'','active'),(25,1,4,29,'','active'),(26,1,4,12,'','active'),(27,1,4,118,'','active');
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-23 15:36:49
