-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: monitoring
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customer_log`
--

DROP TABLE IF EXISTS `customer_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_log` (
  `acc_num` int NOT NULL AUTO_INCREMENT,
  `customer_id` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `time_log` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`acc_num`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_log`
--

LOCK TABLES `customer_log` WRITE;
/*!40000 ALTER TABLE `customer_log` DISABLE KEYS */;
INSERT INTO `customer_log` VALUES (1,NULL,'eyon','2023-05-25 14:37:12'),(2,NULL,'eyon','2023-05-25 14:49:37'),(3,NULL,'eyon','2023-05-25 14:53:03'),(4,NULL,'eyon','2023-05-25 15:30:14'),(5,NULL,'eyon','2023-05-25 15:31:52'),(6,NULL,'eyon','2023-05-25 15:34:36'),(7,NULL,'eyon','2023-05-25 15:36:38'),(8,NULL,'eyon','2023-05-25 15:39:53'),(9,NULL,'eyon','2023-05-25 15:44:25'),(10,NULL,'eyon','2023-05-25 15:49:02'),(11,NULL,'eyon','2023-05-25 15:49:36'),(12,NULL,'eyon','2023-05-25 15:51:03'),(13,NULL,'eyon','2023-05-25 15:53:19'),(14,NULL,'eyon','2023-05-25 15:56:08'),(15,NULL,'eyon','2023-05-25 16:14:35'),(16,NULL,'eyon','2023-05-25 16:36:35'),(17,NULL,'eyon','2023-05-28 23:49:29'),(18,NULL,'eyon','2023-05-28 23:52:16'),(19,NULL,'eyon','2023-05-28 23:55:55'),(20,NULL,'eyon','2023-05-30 02:50:14'),(21,NULL,'eyon','2023-05-30 02:54:18'),(22,NULL,'eyon','2023-05-30 03:02:39'),(23,NULL,'eyon','2023-05-30 03:03:00'),(24,NULL,'eyon','2023-05-30 03:03:33'),(25,NULL,'eyon','2023-05-30 03:03:51'),(26,NULL,'eyon','2023-05-30 06:24:21'),(27,NULL,'eyon','2023-05-30 06:26:59'),(28,NULL,'eyon','2023-05-30 06:29:21'),(29,NULL,'eyon','2023-05-30 06:36:08'),(30,NULL,'eyon','2023-05-30 06:52:43'),(31,NULL,'eyon','2023-05-30 06:56:42'),(32,NULL,'eyon','2023-05-30 06:58:11'),(33,NULL,'eyon','2023-05-30 07:02:40'),(34,NULL,'chris2','2023-05-30 07:05:47'),(35,NULL,'eyon','2023-05-31 04:58:28'),(36,'11','eyon123','2023-05-31 06:05:39'),(37,'11','eyon123','2023-05-31 06:06:32'),(38,'1','eyon','2023-05-31 06:31:13'),(39,'1','eyon','2023-05-31 06:34:31'),(40,'1','eyon','2023-05-31 06:38:45'),(41,'1','eyon','2023-05-31 06:40:49'),(42,'1','eyon','2023-05-31 06:43:33'),(43,'1','eyon','2023-05-31 06:45:28'),(44,'1','eyon','2023-05-31 06:51:36'),(45,'1','eyon','2023-05-31 06:59:30'),(46,'1','eyon','2023-05-31 07:00:41'),(47,'1','eyon','2023-05-31 07:01:15'),(48,'1','eyon','2023-05-31 07:03:08'),(49,'1','eyon','2023-05-31 07:06:27'),(50,'1','eyon','2023-05-31 07:13:15'),(51,'1','eyon','2023-05-31 07:22:07'),(52,'1','eyon','2023-05-31 07:25:11'),(53,'1','eyon','2023-05-31 07:38:31'),(54,'1','eyon','2023-05-31 07:40:38'),(55,'1','eyon','2023-05-31 07:41:16'),(56,'1','eyon','2023-05-31 07:42:42'),(57,'1','eyon','2023-05-31 07:43:12'),(58,'1','eyon','2023-05-31 07:45:07'),(59,'1','eyon','2023-05-31 07:49:20'),(60,'1','eyon','2023-05-31 07:50:35'),(61,'1','eyon','2023-05-31 07:53:06'),(62,'1','eyon','2023-05-31 07:55:09'),(63,'1','eyon','2023-05-31 08:11:34'),(64,'1','eyon','2023-05-31 08:34:19'),(65,'1','eyon','2023-05-31 08:48:18'),(66,'1','eyon','2023-05-31 08:49:17'),(67,'1','eyon','2023-05-31 08:59:36'),(68,'1','eyon','2023-05-31 09:05:55'),(69,'1','eyon','2023-05-31 09:07:49'),(70,'1','eyon','2023-05-31 09:08:32'),(71,'1','eyon','2023-05-31 09:08:54'),(72,'1','eyon','2023-05-31 09:09:20'),(73,'1','eyon','2023-05-31 09:10:18'),(74,'1','eyon','2023-05-31 09:10:47'),(75,'1','eyon','2023-05-31 09:22:07'),(76,'1','eyon','2023-05-31 09:28:08'),(77,'1','eyon','2023-05-31 09:38:18'),(78,'11','eyon123','2023-05-31 09:39:23'),(79,'1','eyon','2023-05-31 09:58:30');
/*!40000 ALTER TABLE `customer_log` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-01 11:20:27
