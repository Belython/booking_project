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
INSERT INTO `rooms_types` VALUES (1,'Standard Twin',2,200,'¨\Ì\0sr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0t\0wi-fit\0safex','active'),(2,'Standard Double',2,300,'¨\Ì\0sr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0t\0wi-fit\0safex','active'),(3,'Family',3,400,'¨\Ì\0sr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0t\0wi-fit\0safex','active'),(4,'Standard Single',1,500,'¨\Ì\0sr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0t\0wi-fit\0safex','active'),(5,'Huge',4,600,'¨\Ì\0sr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0t\0wi-fit\0safex','active'),(6,'Standard Twin',3,300,'¨\Ì\0sr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0t\0wi-fit\0safet\0soapx','active'),(7,'Standard Twin',4,400,'¨\Ì\0sr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0t\0showert\0wi-fit\0safet\0soapx','active'),(8,'Family',4,500,'¨\Ì\0sr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0t\0showert\0wi-fit\0safet\0soapx','active'),(9,'Family',3,500,'¨\Ì\0sr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0t\0showert\0wi-fit\0safet\0soapx','active'),(10,'Family',3,400,'¨\Ì\0sr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0t\0showert\0wi-fit\0safet\0soapx','active'),(11,'–ë–æ–ª—å—à–æ–π',10,2000,'¨\Ì\0sr\0java.util.TreeSet›òPìï\Ìá[\0\0xppw\0\0\0t\0–≤–æ–æ–±—â–µ –≤—Å–µx','active'),(12,'–ë–æ–ª—å—à–æ–π',10,2000,'¨\Ì\0sr\0java.util.TreeSet›òPìï\Ìá[\0\0xppw\0\0\0t\0–≤–æ–æ–±—â–µ –≤—Å–µx','active'),(13,'–û–≥—Ä–æ–º–Ω—ã–π',20,4000,'¨\Ì\0sr\0java.util.TreeSet›òPìï\Ìá[\0\0xppw\0\0\0t\0—Ç–æ–∂–µ –≤—Å–µx','active'),(14,'–û–≥—Ä–æ–º–Ω—ã–π',20,4000,'¨\Ì\0sr\0java.util.TreeSet›òPìï\Ìá[\0\0xppw\0\0\0t\0—Ç–æ–∂–µ –≤—Å–µx','active'),(15,'–û–≥—Ä–æ–º–Ω—ã–π',20,4000,'¨\Ì\0sr\0java.util.TreeSet›òPìï\Ìá[\0\0xppw\0\0\0t\0—Ç–æ–∂–µ –≤—Å–µx','active'),(16,'–ö—Ä–æ—Ö–æ—Ç–Ω—ã–π',1,10,'¨\Ì\0sr\0java.util.TreeSet›òPìï\Ìá[\0\0xppw\0\0\0t\0–Ω–∏—á–µ–≥–æx','active'),(17,'–ö—Ä–æ—Ö–æ—Ç–Ω—ã–π',1,10,'¨\Ì\0sr\0java.util.TreeSet›òPìï\Ìá[\0\0xppw\0\0\0t\0–Ω–∏—á–µ–≥–æx','active'),(18,'–ö—Ä–æ—Ö–æ—Ç–Ω—ã–π',1,10,'¨\Ì\0sr\0java.util.TreeSet›òPìï\Ìá[\0\0xppw\0\0\0t\0–Ω–∏—á–µ–≥–æx','active'),(19,'–°—Ä–µ–¥–Ω–∏–π',2,100,'¨\Ì\0sr\0java.util.TreeSet›òPìï\Ìá[\0\0xppw\0\0\0t\0\r–∫–æ–µ-—á—Ç–æx','active'),(20,'–°—Ä–µ–¥–Ω–∏–π',2,100,'¨\Ì\0sr\0java.util.TreeSet›òPìï\Ìá[\0\0xppw\0\0\0t\0\r–∫–æ–µ-—á—Ç–æx','active'),(21,'–°—Ä–µ–¥–Ω–∏–π',2,100,'¨\Ì\0sr\0java.util.TreeSet›òPìï\Ìá[\0\0xppw\0\0\0t\0\r–∫–æ–µ-—á—Ç–æx','active');
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
