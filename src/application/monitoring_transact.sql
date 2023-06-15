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
-- Table structure for table `transact`
--

DROP TABLE IF EXISTS `transact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transact` (
  `transact_id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int DEFAULT NULL,
  `time_log` timestamp NULL DEFAULT NULL,
  `from_loc` varchar(45) DEFAULT NULL,
  `to_loc` varchar(45) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  `time` varchar(45) DEFAULT NULL,
  `btype` varchar(45) DEFAULT NULL,
  `fare` varchar(45) DEFAULT NULL,
  `book_fee` varchar(45) DEFAULT NULL,
  `total` varchar(45) DEFAULT NULL,
  `gach_num` varchar(45) DEFAULT NULL,
  `ref_num` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`transact_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transact`
--

LOCK TABLES `transact` WRITE;
/*!40000 ALTER TABLE `transact` DISABLE KEYS */;
INSERT INTO `transact` VALUES (1,0,NULL,'Cebu City','Cebu City','2023-05-11','4 AM','AC','15.0','10.0','25.0',NULL,NULL),(2,0,NULL,'Cebu City','Cebu City','2023-06-01','5 AM','NON AC','12.0','10.0','22.0',NULL,NULL),(3,0,NULL,'Argao','Carcar','2023-06-01','4 AM','NON AC','57.0','10.0','67.0',NULL,NULL),(4,0,NULL,'Argao','Carcar','2023-05-11','4 AM','AC','60.0','10.0','70.0',NULL,NULL),(5,0,NULL,'Cebu City','Carcar','2023-06-01','5 AM','AC','75.0','10.0','85.0',NULL,NULL),(6,0,NULL,'Cebu City','Carcar','2023-05-31','5 AM','NON AC','69.0','10.0','79.0',NULL,NULL),(7,0,NULL,'Cebu City','Cebu City','2023-06-01','5 AM','AC','15.0','10.0','25.0',NULL,NULL),(8,0,NULL,'Cebu City','Cebu City','2023-06-01','5 AM','AC','15.0','10.0','25.0',NULL,NULL),(9,0,NULL,'Cebu City','Carcar','2023-05-31','5 AM','NON AC','69.0','10.0','79.0',NULL,NULL),(10,0,NULL,'Cebu City','Carcar','2023-06-01','5 AM','NON AC','69.0','10.0','79.0',NULL,NULL),(11,11,NULL,'Cebu City','Cebu City','2023-05-30','7 PM','AC','15.0','10.0','25.0',NULL,NULL),(12,0,NULL,'Cebu City','Cebu City','2023-06-14','6 PM','AC','15.0','10.0','25.0',NULL,NULL),(13,11,NULL,'Cebu City','Carcar','2023-06-01','7 PM','AC','75.0','10.0','85.0',NULL,NULL),(14,0,NULL,'Argao','Carcar','2023-06-02','7 PM','AC','60.0','10.0','70.0',NULL,NULL),(15,0,NULL,'Cebu City','Argao','2023-06-02','7 PM','AC','121.0','10.0','131.0','123123','123123'),(16,0,NULL,'Cebu City','Carcar','2023-06-02','7 PM','AC','75.0','10.0','85.0',NULL,NULL),(17,0,NULL,'Argao','Argao','2023-06-02','6 PM','AC','15.0','10.0','25.0',NULL,NULL),(18,1,NULL,'Cebu City','Argao','2023-06-01','8 PM','AC','121.0','10.0','131.0',NULL,NULL),(19,11,NULL,'Argao','Carcar','2023-06-01','8 PM','AC','60.0','10.0','70.0','09300291992','1234'),(20,11,NULL,'Argao','Carcar','2023-06-02','6 PM','AC','60.0','10.0','70.0','0930029192','12312');
/*!40000 ALTER TABLE `transact` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-01 11:20:26
