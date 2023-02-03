CREATE DATABASE  IF NOT EXISTS `justpoteito` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `justpoteito`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: justpoteito
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `cooks`
--

DROP TABLE IF EXISTS `cooks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cooks` (
  `id` int NOT NULL AUTO_INCREMENT,
  `born_date` date DEFAULT NULL,
  `last_names` varchar(140) DEFAULT NULL,
  `michelin_stars` int DEFAULT NULL,
  `name` varchar(70) DEFAULT NULL,
  `nationality` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cooks`
--

LOCK TABLES `cooks` WRITE;
/*!40000 ALTER TABLE `cooks` DISABLE KEYS */;
INSERT INTO `cooks` (`id`, `born_date`, `last_names`, `michelin_stars`, `name`, `nationality`) VALUES (1,'1979-03-02','Berasategui',3,'Martin','Española'),(2,'1961-05-04','Arguiñano',2,'Carlos','Española'),(3,'1959-09-18','Arzac',4,'Juan Mari','Española');
/*!40000 ALTER TABLE `cooks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cooks_dishes`
--

DROP TABLE IF EXISTS `cooks_dishes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cooks_dishes` (
  `cook_id` int NOT NULL,
  `dish_id` int NOT NULL,
  KEY `Fk_dish_id` (`dish_id`),
  KEY `Fk_cook_id1` (`cook_id`),
  CONSTRAINT `Fk_cook_id1` FOREIGN KEY (`cook_id`) REFERENCES `cooks` (`id`),
  CONSTRAINT `Fk_dish_id` FOREIGN KEY (`dish_id`) REFERENCES `dishes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cooks_dishes`
--

LOCK TABLES `cooks_dishes` WRITE;
/*!40000 ALTER TABLE `cooks_dishes` DISABLE KEYS */;
/*!40000 ALTER TABLE `cooks_dishes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuisinetype`
--

DROP TABLE IF EXISTS `cuisinetype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuisinetype` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(70) DEFAULT NULL,
  `subtype` varchar(120) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuisinetype`
--

LOCK TABLES `cuisinetype` WRITE;
/*!40000 ALTER TABLE `cuisinetype` DISABLE KEYS */;
INSERT INTO `cuisinetype` (`id`, `name`, `subtype`, `image`) VALUES (1,'Italiana','Mediterranea','./src/main/resources/images/cuisineTypes/comidaItaliana'),(2,'China','Asaitica','./src/main/resources/images/cuisineTypes/comidaChina'),(3,'Alemana','Europea del norte','./src/main/resources/images/cuisineTypes/comidaAlemana'),(4,'Española','Mediterranea','./src/main/resources/images/cuisineTypes/comidaEspaniola'),(5,'Japonesa','Asiatica','./src/main/resources/images/cuisineTypes/comidaJaponesa'),(6,'Francesa','Europea del norte','./src/main/resources/images/cuisineTypes/comidaFrancesa'),(7,'Noruega','Europea del norte','./src/main/resources/images/cuisineTypes/comidaNoruega'),(8,'Brasileña','Sudamericana','./src/main/resources/images/cuisineTypes/comidaBrasilenia'),(9,'Mexicana','Centroamericana','./src/main/resources/images/cuisineTypes/comidaMexicana');
/*!40000 ALTER TABLE `cuisinetype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dishes`
--

DROP TABLE IF EXISTS `dishes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dishes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cuisine_type_id` int DEFAULT NULL,
  `name` varchar(70) DEFAULT NULL,
  `prep_time` int DEFAULT NULL,
  `subtype` varchar(70) DEFAULT NULL,
  `cook_id` int DEFAULT NULL,
  `recipe` varchar(2000) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cuisine_type_id` (`cuisine_type_id`),
  KEY `fk_cook_id` (`cook_id`),
  CONSTRAINT `fk_cook_id` FOREIGN KEY (`cook_id`) REFERENCES `cooks` (`id`),
  CONSTRAINT `fk_cuisine_type_id` FOREIGN KEY (`cuisine_type_id`) REFERENCES `cuisinetype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dishes`
--

LOCK TABLES `dishes` WRITE;
/*!40000 ALTER TABLE `dishes` DISABLE KEYS */;
INSERT INTO `dishes` (`id`, `cuisine_type_id`, `name`, `prep_time`, `subtype`, `cook_id`, `recipe`, `image`) VALUES (1,1,'Macarrones con tomate',20,'Segundo plato',1,'1-.Cocer los macarrones a fuego máximo durante 10 minutos\\n2-. Añadir salsa de tomate','./src/main/resources/images/dishes/macarronesConTomate'),(2,1,'Canelones boloñesa',120,'Segundo plato',2,'1-. Freir la carne picada\n2-. Cocer las bases de los canelones\n3-. Preparar la salsa bechamel\n4-. Preparar la salsa de tomate\n5-. Mezclar todo y añadir queso rallado por encima\n6-. Hornear a 200 grados durante 120 minutos','./src/main/resources/images/dishes/canelonesCarne'),(3,2,'Arroz Tres delicias',40,'Primer plato',2,'1-. Cocer el arroz y los guisantes\n2-. Sofreir el jamón y las gambas\n3-. Preparar la tortilla\n4-. Mezclar todo','./src/main/resources/images/dishes/arrozTresDelicias'),(4,2,'Pato a la naranja',60,'Segundo plato',3,'1-. Exprimir 2 naranjas y cortar otras dos en rodajas\n2-. Poner el pato en una bandeja de horno y añadir las naranjas y el zumo\n3-. Hornear a 120 grados durante 90 minutos','./src/main/resources/images/dishes/patoNaranja');
/*!40000 ALTER TABLE `dishes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredient_dish`
--

DROP TABLE IF EXISTS `ingredient_dish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredient_dish` (
  `dish_id` int NOT NULL,
  `ingredient_id` int NOT NULL,
  `amount` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`dish_id`,`ingredient_id`),
  KEY `fk_ingredient_id2` (`ingredient_id`),
  CONSTRAINT `fk_dish_id2` FOREIGN KEY (`dish_id`) REFERENCES `dishes` (`id`),
  CONSTRAINT `fk_ingredient_id2` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredients` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredient_dish`
--

LOCK TABLES `ingredient_dish` WRITE;
/*!40000 ALTER TABLE `ingredient_dish` DISABLE KEYS */;
INSERT INTO `ingredient_dish` (`dish_id`, `ingredient_id`, `amount`) VALUES (1,1,'150ml'),(1,2,'400g'),(2,1,'90g'),(2,3,'300g'),(2,4,'150g'),(2,5,'300ml'),(3,6,'250g'),(3,7,'50g'),(3,8,'30g'),(3,9,'50g'),(3,12,'50ml'),(3,13,'1 pellizco'),(3,14,'3 dientes'),(4,10,'300g'),(4,11,'30g');
/*!40000 ALTER TABLE `ingredient_dish` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredients`
--

DROP TABLE IF EXISTS `ingredients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredients` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `type` varchar(150) DEFAULT NULL,
  `image` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredients`
--

LOCK TABLES `ingredients` WRITE;
/*!40000 ALTER TABLE `ingredients` DISABLE KEYS */;
INSERT INTO `ingredients` (`id`, `name`, `type`, `image`) VALUES (1,'Tomate','Vegetal','./src/main/resources/images/ingredients/tomate'),(2,'Macarrones','Pasta','./src/main/resources/images/ingredients/macarrones'),(3,'Carne picada','Carne','./src/main/resources/images/ingredients/carnePicada'),(4,'Bases de canelones','Pasta','./src/main/resources/images/ingredients/basesCanelones'),(5,'Salsa bechamel','Salsa','./src/main/resources/images/ingredients/bechamel'),(6,'Arroz','Cereal','./src/main/resources/images/ingredients/006'),(7,'Guisantes','Verdura','./src/main/resources/images/ingredients/guisantes'),(8,'Jamon','Carne','./src/main/resources/images/ingredients/jamonSerrano'),(9,'Gambas','Marisco','./src/main/resources/images/ingredients/gambas'),(10,'Pato','Carne','./src/main/resources/images/ingredients/pato'),(11,'Naranja','Fruta','./src/main/resources/images/ingredients/naranja'),(12,'Salsa de tomate','Vegetal','./src/main/resources/images/ingredients/salsaTomate'),(13,'Pimienta','Especia','./src/main/resources/images/ingredients/pimienta'),(14,'Ajo','Vegetal','./src/main/resources/images/ingredients/ajo'),(15,'Tofu','Vegetal','./src/main/resources/images/ingredients/tofu'),(16,'Queso','Lacteo','./src/main/resources/images/ingredients/queso'),(17,'Chili','Especia','./src/main/resources/images/ingredients/chili'),(18,'Jugo de limon','Fruta','./src/main/resources/images/ingredients/jugoLimon'),(19,'Pomelo','Fruta','./src/main/resources/images/ingredients/pomelo');
/*!40000 ALTER TABLE `ingredients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(70) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`id`, `name`) VALUES (1,'ADMIN'),(2,'USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `Fk_role_id` (`role_id`),
  CONSTRAINT `Fk_role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `Fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (2,1),(5,1),(7,1),(4,2),(6,2),(8,2),(9,2);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(70) DEFAULT NULL,
  `is_enabled` tinyint(1) DEFAULT '1',
  `name` varchar(70) DEFAULT NULL,
  `password` varchar(70) DEFAULT NULL,
  `surnames` varchar(120) DEFAULT NULL,
  `user_name` varchar(70) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UK_k8d0f2n7n88w1a16yhua64onx` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `email`, `is_enabled`, `name`, `password`, `surnames`, `user_name`, `image`) VALUES (2,'david.pereiroam@elorrieta-errekamari.com',1,'David','8cb2237d0679ca88db6464eac60da96345513964','Pereiro Amez','Deivid1',NULL),(4,'estibaliz.mares@gmail.com',1,'Estibaliz','8cb2237d0679ca88db6464eac60da96345513964','Martin Escribano','Estichan',NULL),(5,'fabiana.silva@elorrieta-errekamari.com',1,'Fabiana','8cb2237d0679ca88db6464eac60da96345513964','Silva','LaFabiana',NULL),(6,'txaber.olabesa@elorrieta-errekamari.com',1,'Txaber','8cb2237d0679ca88db6464eac60da96345513964','Olabe Sanchez','Txabo',NULL),(7,'user@gmail.com',1,'user1','08066989cef410a372b22bc7eaab1c0000e0abab','user1','user1',NULL),(8,'user2@gmail.com',1,'user2','4c49f4acebde9e48772d146e27a79f1b85199003','user2','user2',NULL),(9,'user3@gmail.com',1,'user3','31d6a455a96b6d2f1ac5930ccc45e3aa03216bd5','user3','user3',NULL),(13,NULL,0,NULL,NULL,NULL,NULL,'./src/main/resources/images/users/13.png');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-03 19:37:50
