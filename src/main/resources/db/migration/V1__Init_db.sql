CREATE DATABASE  IF NOT EXISTS `kanban` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `kanban`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: kanban
-- ------------------------------------------------------
-- Server version	5.7.9-log

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
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customers` (
  `id` int(20) NOT NULL,
  `customer_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) NOT NULL,
  `sequence_next_hi_value` int(20) DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `note_objects`
--

DROP TABLE IF EXISTS `note_objects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `note_objects` (
  `note_obj_id` int(20) NOT NULL AUTO_INCREMENT,
  `note_obj_content` varchar(255) DEFAULT NULL,
  `note_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`note_obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notes`
--

DROP TABLE IF EXISTS `notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notes` (
  `note_id` int(20) NOT NULL AUTO_INCREMENT,
  `note_creator_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`note_id`),
  KEY `FK2t9ngfw0wpgb82lc1v2po6ouq` (`note_creator_id`),
  CONSTRAINT `FK2t9ngfw0wpgb82lc1v2po6ouq` FOREIGN KEY (`note_creator_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notes_note_assigned_to`
--

DROP TABLE IF EXISTS `notes_note_assigned_to`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notes_note_assigned_to` (
  `note_note_id` int(20) NOT NULL,
  `note_assigned_to_id` int(20) NOT NULL,
  UNIQUE KEY `UK_n35pxvgpobidkyfmh268epi10` (`note_assigned_to_id`),
  KEY `FK2jn234rn3jo1gsvuafckt814d` (`note_note_id`),
  CONSTRAINT `FK2jn234rn3jo1gsvuafckt814d` FOREIGN KEY (`note_note_id`) REFERENCES `notes` (`note_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notes_note_data`
--

DROP TABLE IF EXISTS `notes_note_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notes_note_data` (
  `note_note_id` int(20) NOT NULL,
  `note_data_note_obj_id` int(20) NOT NULL,
  UNIQUE KEY `UK_feivrmdmkyl0enh0kt0wc25f0` (`note_data_note_obj_id`),
  KEY `FKoistn2fpm3np9luojjhggy7pn` (`note_note_id`),
  CONSTRAINT `FKmjn45gja35kmi0ss03bb9g79t` FOREIGN KEY (`note_data_note_obj_id`) REFERENCES `note_objects` (`note_obj_id`),
  CONSTRAINT `FKoistn2fpm3np9luojjhggy7pn` FOREIGN KEY (`note_note_id`) REFERENCES `notes` (`note_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `projects`
--

DROP TABLE IF EXISTS `projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `projects` (
  `id` int(20) NOT NULL,
  `project_description` varchar(255) DEFAULT NULL,
  `project_title` varchar(255) DEFAULT NULL,
  `project_customer_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfraqgwq0afgegsyuv7k9rmeg1` (`project_customer_id`),
  CONSTRAINT `FKfraqgwq0afgegsyuv7k9rmeg1` FOREIGN KEY (`project_customer_id`) REFERENCES `customers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `projects_project_sprints`
--

DROP TABLE IF EXISTS `projects_project_sprints`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `projects_project_sprints` (
  `project_id` int(20) NOT NULL,
  `project_sprints_id` int(20) NOT NULL,
  UNIQUE KEY `UK_mc1sr77g4qv6jou22r6cswv6m` (`project_sprints_id`),
  KEY `FKchbtcgbsb9m0wkx9fvvy72nsj` (`project_id`),
  CONSTRAINT `FKchbtcgbsb9m0wkx9fvvy72nsj` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`),
  CONSTRAINT `FKgnh5iqkbqsq4kssefl523p6tv` FOREIGN KEY (`project_sprints_id`) REFERENCES `sprints` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `projects_project_tasks`
--

DROP TABLE IF EXISTS `projects_project_tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `projects_project_tasks` (
  `project_id` int(20) NOT NULL,
  `project_tasks_id` int(20) NOT NULL,
  UNIQUE KEY `UK_q26ij2ymdos33rcqc6bhtlhyu` (`project_tasks_id`),
  KEY `FKbhqsi6st5fx6tcw45ttcxtsp9` (`project_id`),
  CONSTRAINT `FKbhqsi6st5fx6tcw45ttcxtsp9` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`),
  CONSTRAINT `FKjnl0hk2aorjkl514s0nenwmri` FOREIGN KEY (`project_tasks_id`) REFERENCES `tasks` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `projects_project_users`
--

DROP TABLE IF EXISTS `projects_project_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `projects_project_users` (
  `project_id` int(20) NOT NULL,
  `project_users_id` int(20) NOT NULL,
  UNIQUE KEY `UK_ed0lj06vn12cgb7wvjkrhkna3` (`project_users_id`),
  KEY `FK5gaise1h4hp6c6dtokimvryco` (`project_id`),
  CONSTRAINT `FK5gaise1h4hp6c6dtokimvryco` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`),
  CONSTRAINT `FKthu171d9hmy4o87rwlyrxsqhf` FOREIGN KEY (`project_users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sprints`
--

DROP TABLE IF EXISTS `sprints`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sprints` (
  `id` int(20) NOT NULL,
  `sprint_end_date` datetime DEFAULT NULL,
  `sprint_start_date` datetime DEFAULT NULL,
  `sprint_title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sprints_sprint_tasks`
--

DROP TABLE IF EXISTS `sprints_sprint_tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sprints_sprint_tasks` (
  `sprint_id` int(20) NOT NULL,
  `sprint_tasks_id` int(20) NOT NULL,
  UNIQUE KEY `UK_d5sl37kunpilxb5g35bqll1vf` (`sprint_tasks_id`),
  KEY `FKf4ifpde4dljl0f4xafphgxsg4` (`sprint_id`),
  CONSTRAINT `FK8rg5ynnqx06733oxi6pkaaw4v` FOREIGN KEY (`sprint_tasks_id`) REFERENCES `tasks` (`id`),
  CONSTRAINT `FKf4ifpde4dljl0f4xafphgxsg4` FOREIGN KEY (`sprint_id`) REFERENCES `sprints` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tasks` (
  `id` int(20) NOT NULL,
  `task_description` varchar(255) DEFAULT NULL,
  `task_end_date` datetime DEFAULT NULL,
  `task_estimated_timeh` int(11) NOT NULL,
  `task_estimated_timem` int(11) NOT NULL,
  `task_start_date` datetime DEFAULT NULL,
  `task_title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tasks_task_assigned_users`
--

DROP TABLE IF EXISTS `tasks_task_assigned_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tasks_task_assigned_users` (
  `task_id` int(20) NOT NULL,
  `task_assigned_users_id` int(20) NOT NULL,
  UNIQUE KEY `UK_91ahac9lr4q60273xw7excg3p` (`task_assigned_users_id`),
  KEY `FKp0joj940wfsaftfdmovl0lgrj` (`task_id`),
  CONSTRAINT `FKbqyu05wrmenl4duc0upu3vvg2` FOREIGN KEY (`task_assigned_users_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKp0joj940wfsaftfdmovl0lgrj` FOREIGN KEY (`task_id`) REFERENCES `tasks` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `user_creation_date` datetime DEFAULT NULL,
  `user_first_name` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_sur_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


