-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: gestion_tickets
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `person`
--
DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `birthdate` date NOT NULL,
  `document_number` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_lastname` varchar(255) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `second_lastname` varchar(255) DEFAULT NULL,
  `secondname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKlg3krm9dihgjfnbi7wgw7eowq` (`document_number`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (5,'Berastegui','2001-05-19','1003189162','devmf19@gmail.com','Martinez','Francisco','3024327035','Torreglosa','Arturo'),(8,'berastegui','2006-01-28','1003189161','test@mail.com','Martinez','Jorge','3013811301','Torreglosa','Mario'),(10,'berastegui','2001-02-07','1003189163','test2@mail.com','Martinez','Jeferson','3013811302','Medrano','David');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` enum('DISABLED','SUPPORT','USER','ADMIN') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'USER'),(2,'SUPPORT'),(3,'DISABLED'),(4,'ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `details` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `username` varchar(255) NOT NULL,
  `person_id` bigint NOT NULL,
  `ticket_status_id` bigint NOT NULL,
  `ticket_type_id` bigint NOT NULL,
  `assigned_person_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKova7mpgrn7uwci6ixwhdmblnv` (`person_id`),
  KEY `FKa9f07c2ils5vvpe2qog21qrs7` (`ticket_status_id`),
  KEY `FKicdt7jp8dniw1fw6y8y0u9gcr` (`ticket_type_id`),
  CONSTRAINT `FKa9f07c2ils5vvpe2qog21qrs7` FOREIGN KEY (`ticket_status_id`) REFERENCES `ticket_status` (`id`),
  CONSTRAINT `FKicdt7jp8dniw1fw6y8y0u9gcr` FOREIGN KEY (`ticket_type_id`) REFERENCES `ticket_type` (`id`),
  CONSTRAINT `FKova7mpgrn7uwci6ixwhdmblnv` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (2,'2024-12-10 18:58:45.096426','Al momento de generar un reporte en la ruta de inventarios el sistema se cierra','Error en la ruta de inventariosS','2024-12-10 19:19:59.110324','fmartinez',5,4,1,5);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_management`
--

DROP TABLE IF EXISTS `ticket_management`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket_management` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `username` varchar(255) NOT NULL,
  `person_id` bigint NOT NULL,
  `ticket_id` bigint NOT NULL,
  `ticket_status_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbtdgue0gmj6ldmdlw2jtdt8ft` (`person_id`),
  KEY `FKmtbtt4wh6bvrxfw3gv8hm3djn` (`ticket_id`),
  KEY `FKs9n6g09pd40h3hik6qybu4tbk` (`ticket_status_id`),
  CONSTRAINT `FKbtdgue0gmj6ldmdlw2jtdt8ft` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`),
  CONSTRAINT `FKmtbtt4wh6bvrxfw3gv8hm3djn` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`id`),
  CONSTRAINT `FKs9n6g09pd40h3hik6qybu4tbk` FOREIGN KEY (`ticket_status_id`) REFERENCES `ticket_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_management`
--

LOCK TABLES `ticket_management` WRITE;
/*!40000 ALTER TABLE `ticket_management` DISABLE KEYS */;
INSERT INTO `ticket_management` VALUES (5,'Se gestionó el caso. Por favor validar y comentar cualquier inquietud en este mismo ticket','2024-12-10 19:19:04.171357','fmartinez',5,2,3),(6,'Se cerró el caso','2024-12-10 19:19:59.108277','fmartinez',5,2,4);
/*!40000 ALTER TABLE `ticket_management` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_status`
--

DROP TABLE IF EXISTS `ticket_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket_status` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` enum('ACTIVE','ATTENDING','CLOSED','RESOLVED') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_status`
--

LOCK TABLES `ticket_status` WRITE;
/*!40000 ALTER TABLE `ticket_status` DISABLE KEYS */;
INSERT INTO `ticket_status` VALUES (1,'ACTIVE'),(2,'ATTENDING'),(3,'RESOLVED'),(4,'CLOSED');
/*!40000 ALTER TABLE `ticket_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_type`
--

DROP TABLE IF EXISTS `ticket_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket_type` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` enum('BUG','REQUEST') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_type`
--

LOCK TABLES `ticket_type` WRITE;
/*!40000 ALTER TABLE `ticket_type` DISABLE KEYS */;
INSERT INTO `ticket_type` VALUES (1,'BUG'),(2,'REQUEST');
/*!40000 ALTER TABLE `ticket_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `person_id` bigint DEFAULT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKsb8bbouer5wak8vyiiy4pf2bx` (`username`),
  UNIQUE KEY `UKskugu4aa786hx4o05wn6cs7q0` (`person_id`),
  KEY `FKir5g7yucydevmmc84i788jp79_idx` (`role_id`),
  CONSTRAINT `FKir5g7yucydevmmc84i788jp79` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`),
  CONSTRAINT `FKir5g7yucydevmmc84i788jp7921` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'$2a$10$JWJo0zK28gQLyoc/lQZ2NuAbEHN4tC3WqSLk.oAe9Wemq3PzbHwxu','fmartinez',5,4),(2,'$2a$10$yns6oVpvLOJsCN/rxbx22O6Cd1HO4/wrIFPxq38k57MfGxx1KtDJe','JM1003189161',8,2),(3,'$2a$10$iRK3jeqZFdtk4KWI8qn3jeKKvwOQhAsIMp0saO0ZN.J.5nvOeRJhi','JM1003189163',10,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-10 20:04:48
