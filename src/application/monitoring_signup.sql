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
-- Table structure for table `signup`
--

DROP TABLE IF EXISTS `signup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `signup` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `contact_num` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `signup`
--

LOCK TABLES `signup` WRITE;
/*!40000 ALTER TABLE `signup` DISABLE KEYS */;
INSERT INTO `signup` VALUES (1,'Yron','Padilla','eyon','123452','yronpadilla@gmail','12341512'),(2,'Rafael','Mae','rafael','846465456','rafaelmae@gmail','654654654564'),(3,'christin','mae','chris','164654654','chris@gmail','156415615'),(4,'yron','gwapo','eyon1','16546545','yron@gmail','465465464'),(5,'chris','mae','chris','646541564','chris@gmail','4165465'),(6,'Arthur','Padilla','arthur','12345','arthur@gmail.com','65416546514'),(7,'Natoy','Mahal','natoy','654123natoy','natoy@gmailc.om','44614654564'),(8,'Natoy1','mahhal','natoy2','123456','natoy@gmail.com','46541231'),(9,'Hannah','Mae','hannah1','123451','hannah@gmail.com','0923902932'),(10,'christian','gibo','chris2','123452','chris@gmail.com','029910239'),(11,'Yron','Padilla','eyon123','123456','yronpadilla@gmail.com','09300291992');
/*!40000 ALTER TABLE `signup` ENABLE KEYS */;
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
