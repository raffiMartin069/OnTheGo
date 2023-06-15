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
-- Table structure for table `pricelist`
--

DROP TABLE IF EXISTS `pricelist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pricelist` (
  `id` int NOT NULL AUTO_INCREMENT,
  `from_location` varchar(50) DEFAULT NULL,
  `to_location` varchar(45) DEFAULT NULL,
  `btype` varchar(45) DEFAULT NULL,
  `set_price` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pricelist`
--

LOCK TABLES `pricelist` WRITE;
/*!40000 ALTER TABLE `pricelist` DISABLE KEYS */;
INSERT INTO `pricelist` VALUES (5,'Cebu City','Talisay','NON AC','28'),(6,'Cebu City','Minganilla','NON AC','33'),(7,'Cebu City','Naga','NON AC','41'),(8,'Cebu City','San Fernando','NON AC','68'),(9,'Cebu City','Carcar','NON AC','69'),(10,'Cebu City','Sibonga','NON AC','86'),(11,'Cebu City','Argao','NON AC','116'),(12,'Cebu City','Cebu City','NON AC','12'),(13,'Argao','Sibonga','NON AC','40'),(14,'Argao','Carcar','NON AC','57'),(15,'Argao','San Fernando','NON AC','59'),(16,'Argao','Naga','NON AC','85'),(17,'Argao','Minganilla','NON AC','94'),(18,'Argao','Talisay','NON AC','98'),(19,'Argao','Cebu City','NON AC','116'),(20,'Argao','Argao','NON AC','12'),(21,'Cebu City','Talisay','AC','20'),(22,'Cebu City','Minganilla','AC','26'),(23,'Cebu City','Naga','AC','50'),(24,'Cebu City','San Fernando','AC','70'),(25,'Cebu City','Carcar','AC','75'),(26,'Cebu City','Argao','AC','121'),(27,'Argao','Sibonga','AC','45'),(28,'Argao','Carcar','AC','60'),(29,'Argao','San Fernando','AC','75'),(30,'Argao','Minganilla','AC','100'),(31,'Argao','Talisay','AC','110'),(32,'Argao','Cebu City','AC','120'),(33,'Argao','Argao','AC','15'),(34,'Cebu City','Cebu City','AC','15');
/*!40000 ALTER TABLE `pricelist` ENABLE KEYS */;
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
