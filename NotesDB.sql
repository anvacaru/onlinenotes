CREATE DATABASE  IF NOT EXISTS `notesdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `notesdb`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: notesdb
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `notes`
--

DROP TABLE IF EXISTS `notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `aPwd` text,
  `ePwd` text,
  `url` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=291 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notes`
--

LOCK TABLES `notes` WRITE;
/*!40000 ALTER TABLE `notes` DISABLE KEYS */;
INSERT INTO `notes` VALUES (289,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse nec semper eros. Phasellus eget congue tellus. Maecenas dictum molestie quam, ac placerat ante. Donec ligula magna, dignissim at sapien non, fringilla semper nulla. Vivamus justo sapien, tincidunt ut lectus eget, fermentum dignissim nisi. Pellentesque sed lacinia nunc, placerat posuere massa. Sed vitae aliquet lacus. Nulla sed purus mattis, varius ligula ut, posuere enim. Praesent gravida dolor sit amet gravida facilisis. Ut massa libero, interdum eget quam ac, fermentum consectetur massa. Suspendisse sagittis convallis sapien. Donec eget ultricies lorem. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Duis egestas nisi rhoncus pulvinar maximus. Nam a odio eget enim lacinia aliquam semper et urna.\r\n\r\nSed id ex nec nisl consectetur eleifend et in metus. Phasellus eleifend nulla magna, a suscipit nulla pharetra finibus. Vestibulum id massa luctus, finibus mauris eu, volutpat augue. Cras non efficitur ligula. Curabitur molestie ex at mauris scelerisque luctus. Maecenas id convallis ligula, at euismod nisi. Sed sem erat, euismod quis fringilla hendrerit, gravida ac justo. Donec id purus at nibh mattis rutrum. Quisque rutrum ullamcorper elit, in vestibulum tortor eleifend id. Cras varius sed elit quis auctor. Morbi suscipit felis eu semper facilisis. Praesent sit amet felis vitae leo ullamcorper suscipit ac eget nulla. Fusce ac dui sem. Nulla sem turpis, cursus nec venenatis quis, viverra vitae eros.\r\n\r\nInteger placerat orci eget sagittis fermentum. Vestibulum vitae lobortis nulla, eget consequat mauris. Proin arcu risus, semper egestas tincidunt at, molestie ac augue. Aenean nec iaculis metus, eget porta magna. Phasellus non blandit urna, eu auctor dui. Integer non maximus nunc, a egestas tellus. Maecenas sem dolor, congue eu ultrices non, lacinia in augue. Aenean aliquet imperdiet eros id rhoncus. Integer vulputate lorem et tortor hendrerit tincidunt. Vivamus laoreet fermentum enim quis vulputate. Nulla vel felis odio. Nunc in convallis elit.\r\n\r\nSed in mauris placerat, sagittis purus ac, sagittis metus. Phasellus gravida pharetra nunc, in tempus eros ultricies non. Integer pellentesque libero justo. Curabitur bibendum neque ante, at pharetra diam semper malesuada. Etiam vel tempor dui. Curabitur velit arcu, rhoncus in risus nec, finibus efficitur urna. Mauris eget turpis porta, volutpat dui ultricies, congue felis. Sed justo felis, consequat ut dolor ut, hendrerit ultricies nulla. Curabitur tincidunt tincidunt neque, et luctus leo convallis et. Sed fermentum dictum justo, quis semper risus vehicula vitae. Aliquam ac viverra est. Sed in elementum eros. Morbi dignissim arcu velit, sit amet convallis purus dictum et. Phasellus commodo quam cursus turpis condimentum, in vulputate ipsum sollicitudin. Vivamus cursus suscipit nibh in consectetur. Pellentesque et elit in lorem fermentum facilisis a in elit.\r\n\r\nPellentesque et urna quis magna pulvinar lacinia vitae porttitor enim. Nunc suscipit ullamcorper lectus ac egestas. Aenean ac condimentum quam. Curabitur maximus posuere lectus sed imperdiet. Etiam sodales varius suscipit. Nulla sed consectetur leo. Maecenas tempus tellus elit, ac mollis tortor elementum vitae. Morbi laoreet purus sem, et blandit nisi efficitur ut. Nullam finibus eros suscipit lorem aliquam venenatis. Integer ac magna ut dolor tempor finibus eu eget nibh. Donec sit amet nisi mauris. Maecenas elit arcu, ullamcorper quis erat non, ullamcorper aliquam arcu. Nullam eget tortor vitae erat consequat vestibulum ac at ligula.','access','edit','PzBorQsJ'),(290,'asdasdasda','','','zDNWZWot');
/*!40000 ALTER TABLE `notes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-24 19:39:08
