-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: booking
-- ------------------------------------------------------
-- Server version	5.7.12-log

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
-- Table structure for table `bill_rooms`
--

DROP TABLE IF EXISTS `bill_rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bill_rooms` (
  `bill_id` bigint(20) NOT NULL,
  `room_id` bigint(20) NOT NULL,
  PRIMARY KEY (`bill_id`,`room_id`),
  KEY `FK_1_idx` (`room_id`),
  CONSTRAINT `FK_1` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`room_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_2` FOREIGN KEY (`bill_id`) REFERENCES `bills` (`bill_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_rooms`
--

LOCK TABLES `bill_rooms` WRITE;
/*!40000 ALTER TABLE `bill_rooms` DISABLE KEYS */;
INSERT INTO `bill_rooms` VALUES (1,20);
/*!40000 ALTER TABLE `bill_rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bills`
--

DROP TABLE IF EXISTS `bills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bills` (
  `bill_id` bigint(20) NOT NULL,
  `bill_status` varchar(255) DEFAULT NULL,
  `booking_date` bigint(20) DEFAULT NULL,
  `check_in_date` bigint(20) DEFAULT NULL,
  `check_out_date` bigint(20) DEFAULT NULL,
  `payment_amount` double DEFAULT NULL,
  `total_persons` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`bill_id`),
  KEY `USER_BILLS` (`user_id`),
  CONSTRAINT `USER_BILLS` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bills`
--

LOCK TABLES `bills` WRITE;
/*!40000 ALTER TABLE `bills` DISABLE KEYS */;
INSERT INTO `bills` VALUES (1,'notPaid',1482271200000,1481493600000,1482357600000,2500,2,1);
/*!40000 ALTER TABLE `bills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facility_translations`
--

DROP TABLE IF EXISTS `facility_translations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `facility_translations` (
  `factility_translation_id` bigint(20) NOT NULL,
  `facility_name` varchar(255) DEFAULT NULL,
  `facility_id` bigint(20) DEFAULT NULL,
  `language_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`factility_translation_id`),
  KEY `FK_7dgk0hf7g7s5pkjmnb2m3md8h` (`facility_id`),
  KEY `FACILITY_LANGUAGES` (`language_id`),
  CONSTRAINT `FACILITY_LANGUAGES` FOREIGN KEY (`language_id`) REFERENCES `languages` (`language_id`),
  CONSTRAINT `FK_7dgk0hf7g7s5pkjmnb2m3md8h` FOREIGN KEY (`facility_id`) REFERENCES `facilitys` (`facility_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facility_translations`
--

LOCK TABLES `facility_translations` WRITE;
/*!40000 ALTER TABLE `facility_translations` DISABLE KEYS */;
INSERT INTO `facility_translations` VALUES (1,'Wi-fi',1,1),(2,'Wi-fi',1,2),(3,'Safe',2,1),(4,'Сейф',2,2),(5,'Mirror',3,1),(6,'Зеркало',3,2);
/*!40000 ALTER TABLE `facility_translations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facilitys`
--

DROP TABLE IF EXISTS `facilitys`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `facilitys` (
  `facility_id` bigint(20) NOT NULL,
  `facility_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`facility_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facilitys`
--

LOCK TABLES `facilitys` WRITE;
/*!40000 ALTER TABLE `facilitys` DISABLE KEYS */;
INSERT INTO `facilitys` VALUES (1,'active'),(2,'active'),(3,'active');
/*!40000 ALTER TABLE `facilitys` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel_translations`
--

DROP TABLE IF EXISTS `hotel_translations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hotel_translations` (
  `hotel_translation_id` bigint(20) NOT NULL,
  `hotel_name` varchar(255) DEFAULT NULL,
  `language_id` bigint(20) DEFAULT NULL,
  `hotel_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`hotel_translation_id`),
  KEY `LOCATION_LANGUAGES` (`language_id`),
  KEY `FK_9i0agit09hfq91c8vya3kdg7x` (`hotel_id`),
  CONSTRAINT `FK_9i0agit09hfq91c8vya3kdg7x` FOREIGN KEY (`hotel_id`) REFERENCES `hotels` (`hotel_id`),
  CONSTRAINT `LOCATION_LANGUAGES` FOREIGN KEY (`language_id`) REFERENCES `languages` (`language_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel_translations`
--

LOCK TABLES `hotel_translations` WRITE;
/*!40000 ALTER TABLE `hotel_translations` DISABLE KEYS */;
INSERT INTO `hotel_translations` VALUES (1,'Belarus hotel',1,1),(2,'Беларусь отель',NULL,NULL),(3,'U fontana',1,2),(4,'У фонтана',2,2),(5,'Hermitage hotel',1,3),(6,'Hermitage отель',2,3),(7,'Serebryaniy Taler Hotel ',1,4),(8,'Серебряный Талер',2,4),(9,'Arena Hotel',1,5),(10,'Отель Арена Минск',NULL,NULL),(11,'Hotel Minsk',1,6),(12,'Отель Минск',2,6),(15,'Hotel Yubileiny',1,8),(16,'Отель Юбилейный',2,8),(17,'Gubernsky Hotel',1,9),(18,'Отель Губернский',NULL,NULL),(19,'Zvezda Hotel',1,10),(20,'Отель Звезда',2,10),(21,'Отель Арена Минск',2,5),(22,'Отель Губернский',NULL,NULL),(23,'Отель Губернский',NULL,NULL),(24,'Отель Губернский',NULL,NULL),(25,'Отель Губернский',NULL,NULL),(26,'Отель Губернский',NULL,NULL),(27,'Беларусь отель',NULL,NULL),(28,'Беларусь отель',2,1),(29,'Отель Губернский',2,9);
/*!40000 ALTER TABLE `hotel_translations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotels`
--

DROP TABLE IF EXISTS `hotels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hotels` (
  `hotel_id` bigint(20) NOT NULL,
  `hotel_status` varchar(255) DEFAULT NULL,
  `location_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`hotel_id`),
  KEY `HOTEL_LOCATIONS` (`location_id`),
  CONSTRAINT `HOTEL_LOCATIONS` FOREIGN KEY (`location_id`) REFERENCES `locations` (`location_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotels`
--

LOCK TABLES `hotels` WRITE;
/*!40000 ALTER TABLE `hotels` DISABLE KEYS */;
INSERT INTO `hotels` VALUES (1,'active',1),(2,'active',1),(3,'active',2),(4,'active',2),(5,'active',1),(6,'active',1),(8,'active',1),(9,'active',1),(10,'active',1);
/*!40000 ALTER TABLE `hotels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `languages`
--

DROP TABLE IF EXISTS `languages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `languages` (
  `language_id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`language_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `languages`
--

LOCK TABLES `languages` WRITE;
/*!40000 ALTER TABLE `languages` DISABLE KEYS */;
INSERT INTO `languages` VALUES (1,'EN'),(2,'RU');
/*!40000 ALTER TABLE `languages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location_translations`
--

DROP TABLE IF EXISTS `location_translations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location_translations` (
  `location_transalte_id` bigint(20) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `language_id` bigint(20) DEFAULT NULL,
  `location_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`location_transalte_id`),
  KEY `FK_machxapr7jy5mhfiarj7e2vt6` (`language_id`),
  KEY `FK_pkiw304sg7k5b36nxa6sxco1i` (`location_id`),
  CONSTRAINT `FK_machxapr7jy5mhfiarj7e2vt6` FOREIGN KEY (`language_id`) REFERENCES `languages` (`language_id`),
  CONSTRAINT `FK_pkiw304sg7k5b36nxa6sxco1i` FOREIGN KEY (`location_id`) REFERENCES `locations` (`location_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location_translations`
--

LOCK TABLES `location_translations` WRITE;
/*!40000 ALTER TABLE `location_translations` DISABLE KEYS */;
INSERT INTO `location_translations` VALUES (1,'Minsk','Belarus',1,1),(2,'Минск','Беларусь',NULL,NULL),(3,'Brest','Belarus',1,2),(4,'Брест','Беларусь',2,2),(5,'Vitebsk','Belarus',1,3),(6,'Витебск','Беларусь',2,3),(7,'Mogilev','Belarus',1,4),(8,'Могилев','Беларусь',2,4),(9,'Grodno','Belarus',1,5),(10,'Гродно','Беларусь',2,5),(11,'Gomel','Belarus',1,6),(12,'Гомель','Беларусь',2,6),(13,'Минск','Беларусь',NULL,NULL),(14,'Минск','Беларусь',NULL,NULL),(15,'Минск','Беларусь',NULL,NULL),(16,'Минск','Беларусь',NULL,NULL),(17,'Минск','Беларусь',NULL,NULL),(18,'Минск','Беларусь',NULL,NULL),(19,'Минск','Беларусь',NULL,NULL),(20,'Минск','Беларусь',NULL,NULL),(21,'Минск','Беларусь',2,1);
/*!40000 ALTER TABLE `location_translations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locations`
--

DROP TABLE IF EXISTS `locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `locations` (
  `location_id` bigint(20) NOT NULL,
  `location_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`location_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locations`
--

LOCK TABLES `locations` WRITE;
/*!40000 ALTER TABLE `locations` DISABLE KEYS */;
INSERT INTO `locations` VALUES (1,'active'),(2,'active'),(3,'active'),(4,'active'),(5,'active'),(6,'active');
/*!40000 ALTER TABLE `locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_type_facilities`
--

DROP TABLE IF EXISTS `room_type_facilities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_type_facilities` (
  `room_type_id` bigint(20) NOT NULL,
  `facility_id` bigint(20) NOT NULL,
  PRIMARY KEY (`room_type_id`,`facility_id`),
  KEY `FK_2h1fegvdlbrby21tgc2ijqpgg` (`facility_id`),
  CONSTRAINT `FK_2h1fegvdlbrby21tgc2ijqpgg` FOREIGN KEY (`facility_id`) REFERENCES `facilitys` (`facility_id`),
  CONSTRAINT `FK_q2yg2t00eao0ly7ilyuof1lyk` FOREIGN KEY (`room_type_id`) REFERENCES `room_types` (`room_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_type_facilities`
--

LOCK TABLES `room_type_facilities` WRITE;
/*!40000 ALTER TABLE `room_type_facilities` DISABLE KEYS */;
INSERT INTO `room_type_facilities` VALUES (1,2),(2,2),(4,2),(1,3),(3,3),(4,3);
/*!40000 ALTER TABLE `room_type_facilities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_type_translations`
--

DROP TABLE IF EXISTS `room_type_translations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_type_translations` (
  `room_type_translation_id` bigint(20) NOT NULL,
  `room_type_name` varchar(255) DEFAULT NULL,
  `language_id` bigint(20) DEFAULT NULL,
  `room_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`room_type_translation_id`),
  KEY `ROOM_TYPE_LANGUAGES` (`language_id`),
  KEY `ROOM_TYPE_TRANSLATIONS` (`room_type_id`),
  CONSTRAINT `ROOM_TYPE_LANGUAGES` FOREIGN KEY (`language_id`) REFERENCES `languages` (`language_id`),
  CONSTRAINT `ROOM_TYPE_TRANSLATIONS` FOREIGN KEY (`room_type_id`) REFERENCES `room_types` (`room_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_type_translations`
--

LOCK TABLES `room_type_translations` WRITE;
/*!40000 ALTER TABLE `room_type_translations` DISABLE KEYS */;
INSERT INTO `room_type_translations` VALUES (1,'Family room',1,1),(2,'Семейный номер',NULL,NULL),(3,'Family room',1,2),(4,'Семейный номер',2,2),(5,'Standard room',1,3),(6,'Стандартный номер',NULL,NULL),(7,'Single room',1,4),(8,'Одноместный номер',2,4),(9,'Семейный номер',NULL,NULL),(10,'Стандартный номер',NULL,NULL),(11,'Стандартный номер',NULL,NULL),(12,'Стандартный номер',NULL,NULL),(13,'Стандартный номер',NULL,NULL),(14,'Стандартный номер',NULL,NULL),(15,'Семейный номер',NULL,NULL),(16,'Семейный номер',2,1),(17,'Стандартный номер',2,3);
/*!40000 ALTER TABLE `room_type_translations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_types`
--

DROP TABLE IF EXISTS `room_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_types` (
  `room_type_id` bigint(20) NOT NULL,
  `max_persons` int(11) DEFAULT NULL,
  `price_per_night` double DEFAULT NULL,
  `room_type_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`room_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_types`
--

LOCK TABLES `room_types` WRITE;
/*!40000 ALTER TABLE `room_types` DISABLE KEYS */;
INSERT INTO `room_types` VALUES (1,4,400,'active'),(2,3,300,'active'),(3,2,250,'active'),(4,1,150,'active');
/*!40000 ALTER TABLE `room_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rooms` (
  `room_id` bigint(20) NOT NULL,
  `room_number` int(11) DEFAULT NULL,
  `room_status` varchar(255) DEFAULT NULL,
  `hotel_id` bigint(20) DEFAULT NULL,
  `room_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`room_id`),
  KEY `HOTEL_ROOMS` (`hotel_id`),
  KEY `ROOM_TYPE_ROOMS` (`room_type_id`),
  CONSTRAINT `HOTEL_ROOMS` FOREIGN KEY (`hotel_id`) REFERENCES `hotels` (`hotel_id`),
  CONSTRAINT `ROOM_TYPE_ROOMS` FOREIGN KEY (`room_type_id`) REFERENCES `room_types` (`room_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (1,1,'active',1,1),(2,2,'active',1,1),(3,3,'active',1,1),(4,1,'active',2,1),(5,2,'active',2,3),(6,3,'active',2,2),(7,4,'active',3,4),(8,5,'active',3,1),(9,1,'active',4,2),(10,2,'active',4,1),(11,1,'active',5,1),(12,2,'active',5,4),(13,1,'active',6,3),(14,2,'active',6,2),(17,2,'active',8,3),(18,8,'active',8,2),(19,15,'active',9,2),(20,143,'active',9,3),(21,2,'active',10,2),(22,4,'active',10,4);
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `user_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'belython@gmail.com','Дмитрий','Канарский','belython','123456','ROLE_USER','active'),(2,'dsfsdfds','dfdf','sdfsdfsd','bosaksd','sdffsdds','ROLE_ADMIN','active'),(3,'petya@gmail.com','Петя','Красавец','petro','1111111111','ROLE_USER','active'),(4,'sd','ffasd','dsf','sd','sfad','ROLE_USER','active');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-21 19:56:08
