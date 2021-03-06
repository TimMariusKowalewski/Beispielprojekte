-- MariaDB dump 10.18  Distrib 10.5.7-MariaDB, for Win64 (AMD64)
--
-- Host: djnemashome.de    Database: kunden_daten
-- ------------------------------------------------------
-- Server version	10.4.14-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `buchung`
--

DROP TABLE IF EXISTS `buchung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buchung` (
  `id_buchung` int(11) NOT NULL AUTO_INCREMENT,
  `id_kunde` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_buchung`),
  UNIQUE KEY `id_kunde` (`id_kunde`) USING BTREE,
  CONSTRAINT `buchung_ibfk_1` FOREIGN KEY (`id_kunde`) REFERENCES `kunde` (`id_kunden`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buchung`
--

LOCK TABLES `buchung` WRITE;
/*!40000 ALTER TABLE `buchung` DISABLE KEYS */;
INSERT INTO `buchung` VALUES (14,24);
/*!40000 ALTER TABLE `buchung` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buchung_parkplatz`
--

DROP TABLE IF EXISTS `buchung_parkplatz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buchung_parkplatz` (
  `id_buchung_parkplatz` int(11) NOT NULL AUTO_INCREMENT,
  `id_buchung` int(11) NOT NULL,
  `id_parkplatz` int(11) NOT NULL,
  `begin` date NOT NULL,
  `end` date NOT NULL,
  PRIMARY KEY (`id_buchung_parkplatz`),
  KEY `id_buchung` (`id_buchung`),
  KEY `id_parkplatz` (`id_parkplatz`),
  CONSTRAINT `buchung_parkplatz_ibfk_1` FOREIGN KEY (`id_buchung`) REFERENCES `buchung` (`id_buchung`),
  CONSTRAINT `buchung_parkplatz_ibfk_2` FOREIGN KEY (`id_parkplatz`) REFERENCES `parkplatz` (`id_parkplatz`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buchung_parkplatz`
--

LOCK TABLES `buchung_parkplatz` WRITE;
/*!40000 ALTER TABLE `buchung_parkplatz` DISABLE KEYS */;
INSERT INTO `buchung_parkplatz` VALUES (5,14,1,'2020-10-31','2020-11-01'),(6,14,2,'2020-10-31','2020-11-01');
/*!40000 ALTER TABLE `buchung_parkplatz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buchung_zimmer`
--

DROP TABLE IF EXISTS `buchung_zimmer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buchung_zimmer` (
  `id_buchung_zimmer` int(11) NOT NULL AUTO_INCREMENT,
  `id_buchung` int(11) NOT NULL,
  `id_zimmer` int(11) NOT NULL,
  `begin` date NOT NULL,
  `end` date NOT NULL,
  PRIMARY KEY (`id_buchung_zimmer`),
  KEY `id_buchung` (`id_buchung`),
  KEY `id_zimmer` (`id_zimmer`),
  CONSTRAINT `buchung_zimmer_ibfk_1` FOREIGN KEY (`id_buchung`) REFERENCES `buchung` (`id_buchung`),
  CONSTRAINT `buchung_zimmer_ibfk_2` FOREIGN KEY (`id_zimmer`) REFERENCES `zimmer` (`id_zimmer`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buchung_zimmer`
--

LOCK TABLES `buchung_zimmer` WRITE;
/*!40000 ALTER TABLE `buchung_zimmer` DISABLE KEYS */;
INSERT INTO `buchung_zimmer` VALUES (1,14,1,'2020-10-31','2020-11-01'),(2,14,2,'2020-10-31','2020-11-01');
/*!40000 ALTER TABLE `buchung_zimmer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buchungsleistung`
--

DROP TABLE IF EXISTS `buchungsleistung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buchungsleistung` (
  `id_buchungsleistung` int(11) NOT NULL AUTO_INCREMENT,
  `id_buchung` int(11) NOT NULL,
  `id_leistung` int(11) NOT NULL,
  `begin` date NOT NULL,
  `end` date NOT NULL,
  PRIMARY KEY (`id_buchungsleistung`),
  KEY `id_buchung` (`id_buchung`),
  KEY `id_leistung` (`id_leistung`),
  CONSTRAINT `buchungsleistung_ibfk_1` FOREIGN KEY (`id_buchung`) REFERENCES `buchung` (`id_buchung`),
  CONSTRAINT `buchungsleistung_ibfk_2` FOREIGN KEY (`id_leistung`) REFERENCES `leistung` (`id_leistung`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buchungsleistung`
--

LOCK TABLES `buchungsleistung` WRITE;
/*!40000 ALTER TABLE `buchungsleistung` DISABLE KEYS */;
/*!40000 ALTER TABLE `buchungsleistung` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kunde`
--

DROP TABLE IF EXISTS `kunde`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kunde` (
  `id_kunden` int(11) NOT NULL AUTO_INCREMENT,
  `vorname` text NOT NULL,
  `nachname` text NOT NULL,
  `gebdatum` date NOT NULL,
  `strasse` text NOT NULL,
  `haus_nr` text NOT NULL,
  `plz` int(11) NOT NULL,
  `ort` text NOT NULL,
  `land` text NOT NULL,
  `email` text NOT NULL,
  `tel` int(11) NOT NULL,
  PRIMARY KEY (`id_kunden`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kunde`
--

LOCK TABLES `kunde` WRITE;
/*!40000 ALTER TABLE `kunde` DISABLE KEYS */;
INSERT INTO `kunde` VALUES (1,'Asalia','Eldar','0000-00-00','','',0,'','','',0),(24,'Timmi','Kowalewski','1981-12-31','Teststra├ƒe','23',11111,'Essen','Deutschland','asd@asd.de',123456),(25,'Max','M├╝ller','1990-11-23','Teststra├ƒe','456',45133,'Essen','Deutschland','mueller@muellermilch.com',1527829789);
/*!40000 ALTER TABLE `kunde` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leistung`
--

DROP TABLE IF EXISTS `leistung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `leistung` (
  `id_leistung` int(11) NOT NULL AUTO_INCREMENT,
  `id_leistungsgruppe` int(11) NOT NULL,
  `beschreibung` text NOT NULL,
  `preis` float NOT NULL,
  PRIMARY KEY (`id_leistung`),
  KEY `id_leistungsgruppe` (`id_leistungsgruppe`),
  CONSTRAINT `leistung_ibfk_1` FOREIGN KEY (`id_leistungsgruppe`) REFERENCES `leistungsgruppe` (`id_leistungsgruppe`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leistung`
--

LOCK TABLES `leistung` WRITE;
/*!40000 ALTER TABLE `leistung` DISABLE KEYS */;
INSERT INTO `leistung` VALUES (1,1,'Standardparkplatz',10),(2,2,'Standardkinderbetreuung',20),(3,3,'Fr├╝hst├╝ck',8),(4,3,'Mittagessen',10),(5,3,'Abendessen',12),(6,3,'Fr├╝hst├╝ck & Mittagessen',15),(7,3,'Fr├╝hst├╝ck & Abendessen',17.9),(8,3,'Mittagessen & Abendessen',19.9),(9,3,'All Inclusive',25),(10,4,'Schwimmbad',5),(11,4,'Sauna',10),(12,4,'Massage',20),(13,5,'Wandern',20),(14,5,'Bootsfahrt',15),(15,5,'Museum',9),(16,6,'Tennis',15),(17,6,'Fu├ƒball',8),(18,6,'Basketball',8);
/*!40000 ALTER TABLE `leistung` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leistung_zimmer`
--

DROP TABLE IF EXISTS `leistung_zimmer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `leistung_zimmer` (
  `leistung_zimmer_id` int(11) NOT NULL AUTO_INCREMENT,
  `leistung_id` int(11) NOT NULL,
  `zimmer_id` int(11) NOT NULL,
  `buchung_id` int(11) NOT NULL,
  PRIMARY KEY (`leistung_zimmer_id`),
  KEY `leistung_zimmer_ibfk_1` (`leistung_id`),
  KEY `leistung_zimmer_ibfk_2` (`zimmer_id`),
  KEY `leistung_zimmer_ibfk_3` (`buchung_id`),
  CONSTRAINT `leistung_zimmer_ibfk_1` FOREIGN KEY (`leistung_id`) REFERENCES `leistung` (`id_leistung`),
  CONSTRAINT `leistung_zimmer_ibfk_2` FOREIGN KEY (`zimmer_id`) REFERENCES `zimmer` (`id_zimmer`),
  CONSTRAINT `leistung_zimmer_ibfk_3` FOREIGN KEY (`buchung_id`) REFERENCES `buchung` (`id_buchung`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leistung_zimmer`
--

LOCK TABLES `leistung_zimmer` WRITE;
/*!40000 ALTER TABLE `leistung_zimmer` DISABLE KEYS */;
INSERT INTO `leistung_zimmer` VALUES (4,1,2,14),(5,2,2,14),(6,3,2,14);
/*!40000 ALTER TABLE `leistung_zimmer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leistungsgruppe`
--

DROP TABLE IF EXISTS `leistungsgruppe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `leistungsgruppe` (
  `id_leistungsgruppe` int(11) NOT NULL AUTO_INCREMENT,
  `beschreibung` text NOT NULL,
  PRIMARY KEY (`id_leistungsgruppe`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leistungsgruppe`
--

LOCK TABLES `leistungsgruppe` WRITE;
/*!40000 ALTER TABLE `leistungsgruppe` DISABLE KEYS */;
INSERT INTO `leistungsgruppe` VALUES (1,'Parken'),(2,'Kinderbetreuung'),(3,'Essen & Trinken'),(4,'Wellness'),(5,'Freizeitaktivit├ñten'),(6,'Sport');
/*!40000 ALTER TABLE `leistungsgruppe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mitarbeiter`
--

DROP TABLE IF EXISTS `mitarbeiter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mitarbeiter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `benutzername` text NOT NULL,
  `passwort` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `benutzername` (`benutzername`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mitarbeiter`
--

LOCK TABLES `mitarbeiter` WRITE;
/*!40000 ALTER TABLE `mitarbeiter` DISABLE KEYS */;
INSERT INTO `mitarbeiter` VALUES (7,'test','8cb0360e039f00a2d4595f604c4a3407280c0c487158ff174eb9c7ddcb46884549029cdf7600dd585aaace222e62f03c25151cfd2ed77e2917e7ab514c55468b'),(8,'martin','8cb0360e039f00a2d4595f604c4a3407280c0c487158ff174eb9c7ddcb46884549029cdf7600dd585aaace222e62f03c25151cfd2ed77e2917e7ab514c55468b'),(9,'lala','8cb0360e039f00a2d4595f604c4a3407280c0c487158ff174eb9c7ddcb46884549029cdf7600dd585aaace222e62f03c25151cfd2ed77e2917e7ab514c55468b'),(10,'waschbecken','bc30ecc758c63691c69e66d846326670924bff9609b5febf39c1e86cca8fb72783ad5e5b7076359e6d709bc58cd379ade1495945f8591e9876cb2634e43139a6'),(11,'testperson','4dff4ea340f0a823f15d3f4f01ab62eae0e5da579ccb851f8db9dfe84c58b2b37b89903a740e1ee172da793a6e79d560e5f7f9bd058a12a280433ed6fa46510a'),(12,'testperson12345','62670d1e1eea06b6c975e12bc8a16131b278f6d7bcbe017b65f854c58476baba86c2082b259fd0c1310935b365dc40f609971b6810b065e528b0b60119e69f61'),(13,'eqwe','ba3253876aed6bc22d4a6ff53d8406c6ad864195ed144ab5c87621b6c233b548baeae6956df346ec8c17f5ea10f35ee3cbc514797ed7ddd3145464e2a0bab413'),(14,'test2','8cb0360e039f00a2d4595f604c4a3407280c0c487158ff174eb9c7ddcb46884549029cdf7600dd585aaace222e62f03c25151cfd2ed77e2917e7ab514c55468b'),(15,'test3','8cb0360e039f00a2d4595f604c4a3407280c0c487158ff174eb9c7ddcb46884549029cdf7600dd585aaace222e62f03c25151cfd2ed77e2917e7ab514c55468b'),(16,'lalilu','8cb0360e039f00a2d4595f604c4a3407280c0c487158ff174eb9c7ddcb46884549029cdf7600dd585aaace222e62f03c25151cfd2ed77e2917e7ab514c55468b');
/*!40000 ALTER TABLE `mitarbeiter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parkplatz`
--

DROP TABLE IF EXISTS `parkplatz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parkplatz` (
  `id_parkplatz` int(11) NOT NULL AUTO_INCREMENT,
  `parkplatznummer` int(11) NOT NULL,
  PRIMARY KEY (`id_parkplatz`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parkplatz`
--

LOCK TABLES `parkplatz` WRITE;
/*!40000 ALTER TABLE `parkplatz` DISABLE KEYS */;
INSERT INTO `parkplatz` VALUES (1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9),(10,10);
/*!40000 ALTER TABLE `parkplatz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `id_status` int(11) NOT NULL AUTO_INCREMENT,
  `beschreibung` text NOT NULL,
  PRIMARY KEY (`id_status`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'Reserviert'),(2,'Best├ñtigt'),(3,'Storniert');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status_history`
--

DROP TABLE IF EXISTS `status_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status_history` (
  `id_status_history` int(11) NOT NULL AUTO_INCREMENT,
  `id_status` int(11) NOT NULL,
  `id_buchung` int(11) NOT NULL,
  `begin` date NOT NULL,
  `end` date NOT NULL,
  PRIMARY KEY (`id_status_history`),
  KEY `id_buchung` (`id_buchung`),
  KEY `id_status` (`id_status`),
  CONSTRAINT `status_history_ibfk_1` FOREIGN KEY (`id_buchung`) REFERENCES `buchung` (`id_buchung`),
  CONSTRAINT `status_history_ibfk_2` FOREIGN KEY (`id_status`) REFERENCES `status` (`id_status`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_history`
--

LOCK TABLES `status_history` WRITE;
/*!40000 ALTER TABLE `status_history` DISABLE KEYS */;
INSERT INTO `status_history` VALUES (5,1,14,'2020-10-31','2020-11-01');
/*!40000 ALTER TABLE `status_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zimmer`
--

DROP TABLE IF EXISTS `zimmer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zimmer` (
  `id_zimmer` int(11) NOT NULL AUTO_INCREMENT,
  `zimmernummer` int(11) NOT NULL,
  `zimmertyp` text NOT NULL,
  PRIMARY KEY (`id_zimmer`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zimmer`
--

LOCK TABLES `zimmer` WRITE;
/*!40000 ALTER TABLE `zimmer` DISABLE KEYS */;
INSERT INTO `zimmer` VALUES (1,1,'EZ'),(2,2,'EZ'),(3,3,'EZ'),(4,4,'DZ'),(5,5,'DZ'),(6,6,'DZ');
/*!40000 ALTER TABLE `zimmer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-05  9:19:17
