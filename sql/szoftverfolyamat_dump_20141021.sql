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
-- Table structure for table `advertisements`
--

DROP TABLE IF EXISTS `advertisements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advertisements` (
  `advertisement_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) NOT NULL,
  `link` varchar(128) DEFAULT NULL,
  `text` text NOT NULL,
  PRIMARY KEY (`advertisement_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advertisements`
--

LOCK TABLES `advertisements` WRITE;
/*!40000 ALTER TABLE `advertisements` DISABLE KEYS */;
/*!40000 ALTER TABLE `advertisements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `channel_profile_data`
--

DROP TABLE IF EXISTS `channel_profile_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `channel_profile_data` (
  `credential_id` int(11) NOT NULL,
  `email` varchar(128) NOT NULL,
  `name` varchar(128) NOT NULL,
  `description` varchar(256) NOT NULL,
  `open` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`credential_id`),
  CONSTRAINT `fk_channel_credential_id` FOREIGN KEY (`credential_id`) REFERENCES `user_credentials` (`credential_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
  `channel_credential_id` int(11) NOT NULL,
  `subscriber_credential_id` int(11) NOT NULL,
  PRIMARY KEY (`channel_credential_id`,`subscriber_credential_id`),
  KEY `fk_subscribers_user_credential_id_idx` (`subscriber_credential_id`),
  CONSTRAINT `fk_subscribers_channel_credential_id` FOREIGN KEY (`channel_credential_id`) REFERENCES `channel_profile_data` (`credential_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_subscribers_user_credential_id` FOREIGN KEY (`subscriber_credential_id`) REFERENCES `user_profile_data` (`credential_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments` (
  `comment_id` int(11) NOT NULL,
  `text` text NOT NULL,
  `credential_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `fk_comment_credential_id_idx` (`credential_id`),
  KEY `fk_post_id_idx` (`post_id`),
  CONSTRAINT `fk_comment_credential_id` FOREIGN KEY (`credential_id`) REFERENCES `user_credentials` (`credential_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_id` FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `text` text NOT NULL,
  `sender_credential_id` int(11) NOT NULL,
  `recipient_credential_id` int(11) NOT NULL,
  PRIMARY KEY (`message_id`),
  KEY `fk_sender_credential_id_idx` (`sender_credential_id`),
  KEY `fk_recipient_credential_id_idx` (`recipient_credential_id`),
  CONSTRAINT `fk_recipient_credential_id` FOREIGN KEY (`recipient_credential_id`) REFERENCES `user_profile_data` (`credential_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sender_credential_id` FOREIGN KEY (`sender_credential_id`) REFERENCES `user_profile_data` (`credential_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `posts` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `text` text NOT NULL,
  `credential_id` int(11) NOT NULL,
  PRIMARY KEY (`post_id`),
  KEY `fk_post_credential_id_idx` (`credential_id`),
  CONSTRAINT `fk_post_credential_id` FOREIGN KEY (`credential_id`) REFERENCES `user_credentials` (`credential_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_connections`
--

DROP TABLE IF EXISTS `user_connections`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_connections` (
  `credential_id_1` int(11) NOT NULL,
  `credential_id_2` int(11) NOT NULL,
  PRIMARY KEY (`credential_id_1`,`credential_id_2`),
  KEY `fk_user_connections_credential_id_2_idx` (`credential_id_2`),
  CONSTRAINT `fk_user_connections_credential_id_1` FOREIGN KEY (`credential_id_1`) REFERENCES `user_profile_data` (`credential_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_connections_credential_id_2` FOREIGN KEY (`credential_id_2`) REFERENCES `user_profile_data` (`credential_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
-- Table structure for table `user_credentials`
--

DROP TABLE IF EXISTS `user_credentials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_credentials` (
  `credential_id` int(11) NOT NULL AUTO_INCREMENT,
  `password_hash` varchar(128) NOT NULL,
  `enabled` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`credential_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_credentials`
--

LOCK TABLES `user_credentials` WRITE;
/*!40000 ALTER TABLE `user_credentials` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_credentials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_profile_data`
--

DROP TABLE IF EXISTS `user_profile_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_profile_data` (
  `credential_id` int(11) NOT NULL,
  `email` varchar(128) NOT NULL,
  `full_name` varchar(128) NOT NULL,
  `short_name` varchar(32) NOT NULL,
  `public_location` tinyint(1) NOT NULL DEFAULT '1',
  `location` varchar(128) NOT NULL,
  `public_job_and_workplace` tinyint(1) NOT NULL DEFAULT '1',
  `job` varchar(128) NOT NULL,
  `workplace` varchar(128) DEFAULT NULL,
  `public_birthday` tinyint(1) NOT NULL DEFAULT '1',
  `birthday` date NOT NULL,
  PRIMARY KEY (`credential_id`),
  KEY `fk_user_credential_idx` (`credential_id`),
  CONSTRAINT `fk_user_credential_id` FOREIGN KEY (`credential_id`) REFERENCES `user_credentials` (`credential_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
  `credential_id` int(11) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `uni_credential_id_role` (`role`,`credential_id`),
  KEY `fk_credential_id_idx` (`credential_id`),
  CONSTRAINT `fk_credential_id` FOREIGN KEY (`credential_id`) REFERENCES `user_credentials` (`credential_id`)
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

-- Dump completed on 2014-10-21 13:01:50
