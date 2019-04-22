CREATE DATABASE  IF NOT EXISTS `cashmachine` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `cashmachine`;
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: cashmachine
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `check`
--

DROP TABLE IF EXISTS `check`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `check` (
  `idCheck` int(11) NOT NULL AUTO_INCREMENT,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`idCheck`),
  UNIQUE KEY `idCheck_UNIQUE` (`idCheck`)
) ENGINE=InnoDB AUTO_INCREMENT=905260 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `check`
--

LOCK TABLES `check` WRITE;
/*!40000 ALTER TABLE `check` DISABLE KEYS */;
INSERT INTO `check` VALUES (30004,75),(30861,16),(36859,236),(39512,18),(42413,98),(44537,300),(45264,66),(45416,135),(47352,152),(405294,130);
/*!40000 ALTER TABLE `check` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `check_has_product`
--

DROP TABLE IF EXISTS `check_has_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `check_has_product` (
  `check_idCheck` int(11) NOT NULL,
  `product_idProduct` int(11) NOT NULL,
  `product_attributeValue` int(11) NOT NULL,
  PRIMARY KEY (`check_idCheck`,`product_idProduct`),
  KEY `fk_check_has_product_product1_idx` (`product_idProduct`),
  KEY `fk_check_has_product_check1_idx` (`check_idCheck`),
  CONSTRAINT `fk_check_has_product_check1` FOREIGN KEY (`check_idCheck`) REFERENCES `check` (`idCheck`),
  CONSTRAINT `fk_check_has_product_product1` FOREIGN KEY (`product_idProduct`) REFERENCES `product` (`idProduct`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `check_has_product`
--

LOCK TABLES `check_has_product` WRITE;
/*!40000 ALTER TABLE `check_has_product` DISABLE KEYS */;
INSERT INTO `check_has_product` VALUES (30004,8,5),(30861,3,2),(36859,4,2),(36859,5,3),(39512,11,1),(42413,3,1),(42413,10,1),(44537,1,3),(44537,2,2),(44537,9,4),(45264,5,3),(45264,8,2),(45416,4,1),(45416,7,1),(47352,2,4),(47352,6,1),(47352,9,2),(405294,2,2),(405294,10,1),(405294,358341,1);
/*!40000 ALTER TABLE `check_has_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `product` (
  `idProduct` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`idProduct`),
  UNIQUE KEY `idProducts_UNIQUE` (`idProduct`)
) ENGINE=InnoDB AUTO_INCREMENT=957439 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'bread',10),(2,'salt',5),(3,'water',9),(4,'cake',100),(5,'cola',12),(6,'parsley',2),(7,'butter',35),(8,'snickers',15),(9,'biscuit',65),(10,'candies',90),(11,'donut',18),(79437,'chips',25),(358341,'tomato',24),(794098,'orange',27),(916593,'banana',25),(957438,'pork',94);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_attribute`
--

DROP TABLE IF EXISTS `product_attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `product_attribute` (
  `attribute_name` enum('weight','quantity') NOT NULL,
  `attribute_value` int(11) NOT NULL,
  `idProduct` int(11) NOT NULL,
  PRIMARY KEY (`idProduct`),
  KEY `fk_product_attribute_product_idx` (`idProduct`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_attribute`
--

LOCK TABLES `product_attribute` WRITE;
/*!40000 ALTER TABLE `product_attribute` DISABLE KEYS */;
INSERT INTO `product_attribute` VALUES ('quantity',37,1),('weight',114,2),('quantity',24,3),('quantity',18,4),('quantity',10,5),('quantity',18,6),('quantity',48,7),('quantity',68,8),('weight',55,9),('weight',19,10),('quantity',0,11),('quantity',37,79437),('weight',31,358341),('weight',24,794098),('weight',46,916593),('weight',15,957438);
/*!40000 ALTER TABLE `product_attribute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `worker`
--

DROP TABLE IF EXISTS `worker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `worker` (
  `idWorker` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`idWorker`),
  UNIQUE KEY `idWorker_UNIQUE` (`idWorker`)
) ENGINE=InnoDB AUTO_INCREMENT=2073916712 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `worker`
--

LOCK TABLES `worker` WRITE;
/*!40000 ALTER TABLE `worker` DISABLE KEYS */;
INSERT INTO `worker` VALUES (1,'Dima','cashier','dimaC1','1234'),(2,'Vasya','cashier','vasyaC2','1234'),(3,'Olya','cashier','olyaC3','1234'),(4,'Kolya','highcashier','kolyaHC1','1234'),(5,'Ivan','productmaker','ivanPM1','1234'),(6,'Katya','productmaker','katyaPM2','1234'),(469761,'admin','admin','admin','admin');
/*!40000 ALTER TABLE `worker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `worker_has_check`
--

DROP TABLE IF EXISTS `worker_has_check`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `worker_has_check` (
  `worker_idWorker` int(11) NOT NULL,
  `check_idCheck` int(11) NOT NULL,
  PRIMARY KEY (`worker_idWorker`,`check_idCheck`),
  KEY `fk_worker_has_check_check1_idx` (`check_idCheck`),
  KEY `fk_worker_has_check_worker1_idx` (`worker_idWorker`),
  CONSTRAINT `fk_worker_has_check_check1` FOREIGN KEY (`check_idCheck`) REFERENCES `check` (`idCheck`),
  CONSTRAINT `fk_worker_has_check_worker1` FOREIGN KEY (`worker_idWorker`) REFERENCES `worker` (`idWorker`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `worker_has_check`
--

LOCK TABLES `worker_has_check` WRITE;
/*!40000 ALTER TABLE `worker_has_check` DISABLE KEYS */;
INSERT INTO `worker_has_check` VALUES (3,30004),(3,30861),(3,36859),(3,39512),(1,42413),(1,44537),(1,45264),(1,45416),(3,47352),(2,405294);
/*!40000 ALTER TABLE `worker_has_check` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-22 16:35:01
