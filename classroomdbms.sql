-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: classroomdbms
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `announcements`
--

DROP TABLE IF EXISTS `announcements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `announcements` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `courseId` varchar(100) NOT NULL,
  `faculty_emailId` varchar(45) NOT NULL,
  `timestamp` varchar(100) NOT NULL,
  `message` varchar(1000) NOT NULL,
  `attachment_type` varchar(50) NOT NULL,
  `attachment_url` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `announcements`
--

LOCK TABLES `announcements` WRITE;
/*!40000 ALTER TABLE `announcements` DISABLE KEYS */;
INSERT INTO `announcements` VALUES (6,'CSN - 361','ketan.gupta@gmail.com','2017.11.05.22.52.41','There will be no class tomorrow','N/A','N/A');
/*!40000 ALTER TABLE `announcements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assignments`
--

DROP TABLE IF EXISTS `assignments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assignments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `courseId` varchar(100) NOT NULL,
  `faculty_emailId` varchar(100) NOT NULL,
  `timestamp` varchar(100) NOT NULL,
  `deadline` varchar(100) NOT NULL,
  `assignment_details` varchar(1000) NOT NULL,
  `attachment_type` varchar(45) NOT NULL,
  `attachment_url` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignments`
--

LOCK TABLES `assignments` WRITE;
/*!40000 ALTER TABLE `assignments` DISABLE KEYS */;
INSERT INTO `assignments` VALUES (7,'CSN - 361','ketan.gupta@gmail.com','2017.11.05.22.51.37','12-11-2017','Do Tutorial -1','PDF','http://github.com/tutorial-1');
/*!40000 ALTER TABLE `assignments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classroomdetails`
--

DROP TABLE IF EXISTS `classroomdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classroomdetails` (
  `courseId` varchar(15) NOT NULL,
  `faculty_emailId` varchar(100) NOT NULL,
  `course_name` varchar(200) NOT NULL,
  `lecture_timing` varchar(200) NOT NULL,
  `tutorial_timing` varchar(200) NOT NULL,
  `marks_distribution` varchar(200) NOT NULL,
  `attendance_rule` varchar(200) NOT NULL,
  `grading_rule` varchar(200) NOT NULL,
  `lecture_hall` varchar(200) NOT NULL,
  `literature_link` varchar(200) NOT NULL,
  PRIMARY KEY (`courseId`),
  KEY `faculty_emailId_idx` (`faculty_emailId`),
  CONSTRAINT `faculty_emailId` FOREIGN KEY (`faculty_emailId`) REFERENCES `facultydetails` (`emailId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classroomdetails`
--

LOCK TABLES `classroomdetails` WRITE;
/*!40000 ALTER TABLE `classroomdetails` DISABLE KEYS */;
INSERT INTO `classroomdetails` VALUES ('CSN - 361','ketan.gupta@gmail.com','Networks Lab','Friday, 10:00 AM to 1:00PM','N/A','50 ETE, 25 MTE, 25 LAB','75%','O, A, B+, B, C+, C, D, F','LHC - 204','http://github.com/csn-361');
/*!40000 ALTER TABLE `classroomdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currentuser`
--

DROP TABLE IF EXISTS `currentuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `currentuser` (
  `id` varchar(100) NOT NULL,
  `emailId` varchar(100) NOT NULL,
  `type` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currentuser`
--

LOCK TABLES `currentuser` WRITE;
/*!40000 ALTER TABLE `currentuser` DISABLE KEYS */;
/*!40000 ALTER TABLE `currentuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facultydetails`
--

DROP TABLE IF EXISTS `facultydetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `facultydetails` (
  `firstName` varchar(100) NOT NULL,
  `lastName` varchar(100) NOT NULL,
  `emailId` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `designation` varchar(100) NOT NULL,
  `phoneNumber` varchar(15) NOT NULL,
  PRIMARY KEY (`emailId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facultydetails`
--

LOCK TABLES `facultydetails` WRITE;
/*!40000 ALTER TABLE `facultydetails` DISABLE KEYS */;
INSERT INTO `facultydetails` VALUES ('Ketan','Gupta','ketan.gupta@gmail.com','qwertyuiop','H.O.D.','8097654321');
/*!40000 ALTER TABLE `facultydetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `speakouts`
--

DROP TABLE IF EXISTS `speakouts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `speakouts` (
  `timestamp` varchar(70) NOT NULL,
  `emailId` varchar(50) NOT NULL,
  `message` varchar(900) NOT NULL,
  PRIMARY KEY (`timestamp`,`emailId`,`message`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `speakouts`
--

LOCK TABLES `speakouts` WRITE;
/*!40000 ALTER TABLE `speakouts` DISABLE KEYS */;
INSERT INTO `speakouts` VALUES ('2017.11.05.23.12.23','harjot.oberai@gmail.com','Hello from student');
/*!40000 ALTER TABLE `speakouts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studentcoursedetails`
--

DROP TABLE IF EXISTS `studentcoursedetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `studentcoursedetails` (
  `emailId` varchar(100) NOT NULL,
  `courseId` varchar(100) NOT NULL,
  PRIMARY KEY (`courseId`,`emailId`),
  KEY `emailId_idx` (`emailId`),
  CONSTRAINT `emailId` FOREIGN KEY (`emailId`) REFERENCES `studentdetails` (`emailId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentcoursedetails`
--

LOCK TABLES `studentcoursedetails` WRITE;
/*!40000 ALTER TABLE `studentcoursedetails` DISABLE KEYS */;
INSERT INTO `studentcoursedetails` VALUES ('harjot.oberai@gmail.com','CSN - 361');
/*!40000 ALTER TABLE `studentcoursedetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studentdetails`
--

DROP TABLE IF EXISTS `studentdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `studentdetails` (
  `firstName` varchar(500) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `emailId` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `dob` varchar(45) NOT NULL,
  `phoneNumber` varchar(15) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `college` varchar(100) NOT NULL,
  PRIMARY KEY (`emailId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentdetails`
--

LOCK TABLES `studentdetails` WRITE;
/*!40000 ALTER TABLE `studentdetails` DISABLE KEYS */;
INSERT INTO `studentdetails` VALUES ('Harjot','Singh','harjot.oberai@gmail.com','qwertyuiop','11-12-1997','9087654321','Male','IIT, Roorkee');
/*!40000 ALTER TABLE `studentdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studentsubmissiondetails`
--

DROP TABLE IF EXISTS `studentsubmissiondetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `studentsubmissiondetails` (
  `emailId` varchar(100) NOT NULL,
  `assignmentId` int(11) NOT NULL,
  `timestamp` varchar(100) NOT NULL,
  `submission` varchar(1000) NOT NULL,
  PRIMARY KEY (`emailId`,`assignmentId`),
  KEY `assignmentId_idx` (`assignmentId`),
  CONSTRAINT `assignmentId` FOREIGN KEY (`assignmentId`) REFERENCES `assignments` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentsubmissiondetails`
--

LOCK TABLES `studentsubmissiondetails` WRITE;
/*!40000 ALTER TABLE `studentsubmissiondetails` DISABLE KEYS */;
INSERT INTO `studentsubmissiondetails` VALUES ('harjot.oberai@gmail.com',7,'2017.11.05.23.05.48','http://github.com/solution-tutorial-1');
/*!40000 ALTER TABLE `studentsubmissiondetails` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-05 23:38:26
