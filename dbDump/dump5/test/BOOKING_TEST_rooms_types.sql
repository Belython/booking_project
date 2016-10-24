-- MySQL dump 10.13  Distrib 5.7.9, for Win32 (AMD64)
--
-- Host: localhost    Database: BOOKING_TEST
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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms_types`
--

LOCK TABLES `rooms_types` WRITE;
/*!40000 ALTER TABLE `rooms_types` DISABLE KEYS */;
INSERT INTO `rooms_types` VALUES (1,'Standard Twin',2,200,'¬\í\0sr\0java.util.HashSetºD…•–¸·4\0\0xpw\0\0\0?@\0\0\0\0\0t\0wi-fit\0safex','1'),(2,'Standard Double',2,300,'¬\í\0sr\0java.util.HashSetºD…•–¸·4\0\0xpw\0\0\0?@\0\0\0\0\0t\0wi-fit\0safex','1'),(3,'Family',3,400,'¬\í\0sr\0java.util.HashSetºD…•–¸·4\0\0xpw\0\0\0?@\0\0\0\0\0t\0wi-fit\0safex','1'),(4,'Standard Single',1,500,'¬\í\0sr\0java.util.HashSetºD…•–¸·4\0\0xpw\0\0\0?@\0\0\0\0\0t\0wi-fit\0safex','1'),(5,'Huge',4,600,'¬\í\0sr\0java.util.HashSetºD…•–¸·4\0\0xpw\0\0\0?@\0\0\0\0\0t\0wi-fit\0safex','1'),(6,'Family',3,400,'¬\í\0sr\0java.util.HashSetºD…•–¸·4\0\0xpw\0\0\0?@\0\0\0\0\0t\0showert\0wi-fit\0safet\0soapx','active'),(7,'Family',5,400,'¬\í\0sr\0java.util.HashSetºD…•–¸·4\0\0xpw\0\0\0?@\0\0\0\0\0t\0showert\0wi-fit\0safet\0soapx','active'),(8,'Huge',5,350,'¬\í\0sr\0java.util.HashSetºD…•–¸·4\0\0xpw\0\0\0?@\0\0\0\0\0t\0wi-fit\0safet\0soapx','active'),(9,'Huge',2,350,'¬\í\0sr\0java.util.HashSetºD…•–¸·4\0\0xpw\0\0\0?@\0\0\0\0\0t\0wi-fit\0safet\0soapx','active'),(10,'Huge',6,750,'¬\í\0sr\0java.util.HashSetºD…•–¸·4\0\0xpw\0\0\0?@\0\0\0\0\0t\0wi-fit\0safet\0profitt\0soapx','active'),(11,'Standard Twin',6,750,'¬\í\0sr\0java.util.HashSetºD…•–¸·4\0\0xpw\0\0\0?@\0\0\0\0\0t\0wi-fit\0safet\0profitt\0soapx','active'),(12,'Standard Twin',6,750,'¬\í\0sr\0java.util.HashSetºD…•–¸·4\0\0xpw\0\0\0?@\0\0\0\0\0t\0wi-fit\0safet\0profitx','active'),(13,'Standard Twin',4,250,'¬\í\0sr\0java.util.HashSetºD…•–¸·4\0\0xpw\0\0\0?@\0\0\0\0\0t\0toilett\0wi-fit\0safet\0profitx','active'),(14,'Huge',2,350,'¬\í\0sr\0java.util.HashSetºD…•–¸·4\0\0xpw\0\0\0?@\0\0\0\0\0t\0toilett\0wi-fit\0safet\0profitx','active'),(15,'Huge',2,450,'¬\í\0sr\0java.util.HashSetºD…•–¸·4\0\0xpw\0\0\0?@\0\0\0\0\0t\0toilett\0wi-fit\0safet\0profitx','active'),(16,'Family',3,500,'¬\í\0sr\0java.util.HashSetºD…•–¸·4\0\0xpw\0\0\0?@\0\0\0\0\0t\0toilett\0wi-fit\0basikt\0safet\0profitx','active');
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

-- Dump completed on 2016-10-25  1:58:16
