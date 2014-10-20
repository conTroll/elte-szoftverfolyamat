CREATE DATABASE  IF NOT EXISTS `szoftverfolyamat` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `szoftverfolyamat`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: szoftverfolyamat
-- ------------------------------------------------------
-- Server version	5.6.21-log

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
-- Table structure for table `advertisement`
--

DROP TABLE IF EXISTS `advertisement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advertisement` (
  `advertisementId` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) NOT NULL,
  `link` varchar(128) DEFAULT NULL,
  `text` text NOT NULL,
  PRIMARY KEY (`advertisementId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advertisement`
--

LOCK TABLES `advertisement` WRITE;
/*!40000 ALTER TABLE `advertisement` DISABLE KEYS */;
/*!40000 ALTER TABLE `advertisement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `channel_profile_data`
--

DROP TABLE IF EXISTS `channel_profile_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `channel_profile_data` (
  `credentialId` int(11) NOT NULL,
  `email` varchar(128) NOT NULL,
  `name` varchar(128) NOT NULL,
  `description` varchar(256) NOT NULL,
  `open` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`credentialId`),
  CONSTRAINT `fk_credential_channel` FOREIGN KEY (`credentialId`) REFERENCES `user_credential` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `channel_profile_data`
--

LOCK TABLES `channel_profile_data` WRITE;
/*!40000 ALTER TABLE `channel_profile_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `channel_profile_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `channel_subscribers`
--

DROP TABLE IF EXISTS `channel_subscribers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `channel_subscribers` (
  `channelId` int(11) NOT NULL,
  `subscriberId` int(11) NOT NULL,
  KEY `fk_channel_idx` (`channelId`),
  KEY `fk_subscriber_idx` (`subscriberId`),
  CONSTRAINT `fk_channel` FOREIGN KEY (`channelId`) REFERENCES `channel_profile_data` (`credentialId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_subscriber` FOREIGN KEY (`subscriberId`) REFERENCES `user_profile_data` (`credentialId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `channel_subscribers`
--

LOCK TABLES `channel_subscribers` WRITE;
/*!40000 ALTER TABLE `channel_subscribers` DISABLE KEYS */;
/*!40000 ALTER TABLE `channel_subscribers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `commentId` int(11) NOT NULL AUTO_INCREMENT,
  `text` text NOT NULL,
  `author` int(11) NOT NULL,
  `post` int(11) NOT NULL,
  PRIMARY KEY (`commentId`),
  KEY `fk_comment_author_idx` (`author`),
  KEY `fk_post_idx` (`post`),
  CONSTRAINT `fk_comment_author` FOREIGN KEY (`author`) REFERENCES `user_credential` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_post` FOREIGN KEY (`post`) REFERENCES `post` (`postId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `messageId` int(11) NOT NULL AUTO_INCREMENT,
  `text` text NOT NULL,
  `senderId` int(11) NOT NULL,
  `recipientId` int(11) NOT NULL,
  PRIMARY KEY (`messageId`),
  KEY `fk_sender_idx` (`senderId`),
  KEY `fk_recipient_idx` (`recipientId`),
  CONSTRAINT `fk_recipient` FOREIGN KEY (`recipientId`) REFERENCES `user_profile_data` (`credentialId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sender` FOREIGN KEY (`senderId`) REFERENCES `user_profile_data` (`credentialId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post` (
  `postId` int(11) NOT NULL AUTO_INCREMENT,
  `text` text NOT NULL,
  `author` int(11) NOT NULL,
  PRIMARY KEY (`postId`),
  KEY `fk_author_idx` (`author`),
  CONSTRAINT `fk_post_author` FOREIGN KEY (`author`) REFERENCES `user_credential` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_connections`
--

DROP TABLE IF EXISTS `user_connections`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_connections` (
  `userId_1` int(11) NOT NULL,
  `userId_2` int(11) NOT NULL,
  KEY `fk_userid_1_idx` (`userId_1`),
  KEY `fk_userid_2_idx` (`userId_2`),
  CONSTRAINT `fk_userid_1` FOREIGN KEY (`userId_1`) REFERENCES `user_profile_data` (`credentialId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_userid_2` FOREIGN KEY (`userId_2`) REFERENCES `user_profile_data` (`credentialId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_connections`
--

LOCK TABLES `user_connections` WRITE;
/*!40000 ALTER TABLE `user_connections` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_connections` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_credential`
--

DROP TABLE IF EXISTS `user_credential`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_credential` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `passwordHash` varchar(128) NOT NULL,
  `enabled` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_credential`
--

LOCK TABLES `user_credential` WRITE;
/*!40000 ALTER TABLE `user_credential` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_credential` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_profile_data`
--

DROP TABLE IF EXISTS `user_profile_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_profile_data` (
  `credentialId` int(11) NOT NULL,
  `email` varchar(128) NOT NULL,
  `fullName` varchar(128) NOT NULL,
  `shortName` varchar(32) NOT NULL,
  `publicLocation` tinyint(1) NOT NULL DEFAULT '1',
  `location` varchar(128) NOT NULL,
  `publicJobAndWorkplace` tinyint(1) NOT NULL DEFAULT '1',
  `job` varchar(128) NOT NULL,
  `workplace` varchar(128) DEFAULT NULL,
  `publicBirthday` tinyint(1) NOT NULL DEFAULT '1',
  `birthday` date NOT NULL,
  PRIMARY KEY (`credentialId`),
  KEY `fk_user_credential_idx` (`credentialId`),
  CONSTRAINT `fk_credential_user` FOREIGN KEY (`credentialId`) REFERENCES `user_credential` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_profile_data`
--

LOCK TABLES `user_profile_data` WRITE;
/*!40000 ALTER TABLE `user_profile_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_profile_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `ROLE` varchar(45) NOT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `uni_username_role` (`ROLE`,`userId`),
  KEY `fk_user_credential_idx` (`userId`),
  CONSTRAINT `fk_user_credential` FOREIGN KEY (`userId`) REFERENCES `user_credential` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-10-20 13:20:20
