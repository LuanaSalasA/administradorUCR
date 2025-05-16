CREATE DATABASE  IF NOT EXISTS `actividades_academicas` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `actividades_academicas`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: actividades_academicas
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;actividadperfil
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `actividad`
--

DROP TABLE IF EXISTS `actividad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actividad` (
  `id` int NOT NULL AUTO_INCREMENT,
  `imagen` varchar(70) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `estado` varchar(45) NOT NULL,
  `etiqueta` varchar(15) NOT NULL,
  `fecha_hora_realizar_actividad` datetime NOT NULL,
  `fecha_hora_registro_actividad` datetime NOT NULL,
  `categoria` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actividad`
--

LOCK TABLES `actividad` WRITE;
/*!40000 ALTER TABLE `actividad` DISABLE KEYS */;
/*!40000 ALTER TABLE `actividad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carrera`
--

DROP TABLE IF EXISTS `carrera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carrera` (
  `codigo` varchar(10) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `descripcion` varchar(250) NOT NULL,
  `perfil_profesional` varchar(250) NOT NULL,
  `mercado_laboral` varchar(250) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carrera`
--

LOCK TABLES `carrera` WRITE;
/*!40000 ALTER TABLE `carrera` DISABLE KEYS */;
/*!40000 ALTER TABLE `carrera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
  `id` int NOT NULL,
  `categoria` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ciclo`
--

DROP TABLE IF EXISTS `ciclo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ciclo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(10) NOT NULL,
  `inicio_clases` date NOT NULL,
  `fin_clases` date NOT NULL,
  `inicio_evaluaciones` date NOT NULL,
  `fin_evaluaciones` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ciclo`
--

LOCK TABLES `ciclo` WRITE;
/*!40000 ALTER TABLE `ciclo` DISABLE KEYS */;
/*!40000 ALTER TABLE `ciclo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curso`
--

DROP TABLE IF EXISTS `curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `curso` (
  `sigla` int NOT NULL,
  `bloque` varchar(5) NOT NULL,
  `horas_trabajo` int NOT NULL,
  `horas_lectivas` int NOT NULL,
  `cantidad_creditos` int NOT NULL,
  `modalidad` varchar(20) NOT NULL,
  `descripcion` varchar(150) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`sigla`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso`
--

LOCK TABLES `curso` WRITE;
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curso_has_grupo`
--

DROP TABLE IF EXISTS `curso_has_grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `curso_has_grupo` (
  `curso_sigla` int NOT NULL,
  `grupo_id` int NOT NULL,
  PRIMARY KEY (`curso_sigla`,`grupo_id`),
  KEY `fk_curso_has_grupo_grupo1_idx` (`grupo_id`),
  KEY `fk_curso_has_grupo_curso1_idx` (`curso_sigla`),
  CONSTRAINT `fk_curso_has_grupo_curso1` FOREIGN KEY (`curso_sigla`) REFERENCES `curso` (`sigla`),
  CONSTRAINT `fk_curso_has_grupo_grupo1` FOREIGN KEY (`grupo_id`) REFERENCES `grupo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso_has_grupo`
--

LOCK TABLES `curso_has_grupo` WRITE;
/*!40000 ALTER TABLE `curso_has_grupo` DISABLE KEYS */;
/*!40000 ALTER TABLE `curso_has_grupo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curso_imparte_ciclo`
--

DROP TABLE IF EXISTS `curso_imparte_ciclo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `curso_imparte_ciclo` (
  `curso_sigla` int NOT NULL,
  `ciclo_id` int NOT NULL,
  PRIMARY KEY (`curso_sigla`,`ciclo_id`),
  KEY `fk_curso_has_ciclo_ciclo1_idx` (`ciclo_id`),
  KEY `fk_curso_has_ciclo_curso1_idx` (`curso_sigla`),
  CONSTRAINT `fk_curso_has_ciclo_ciclo1` FOREIGN KEY (`ciclo_id`) REFERENCES `ciclo` (`id`),
  CONSTRAINT `fk_curso_has_ciclo_curso1` FOREIGN KEY (`curso_sigla`) REFERENCES `curso` (`sigla`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso_imparte_ciclo`
--

LOCK TABLES `curso_imparte_ciclo` WRITE;
/*!40000 ALTER TABLE `curso_imparte_ciclo` DISABLE KEYS */;
/*!40000 ALTER TABLE `curso_imparte_ciclo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `etiquetas`
--

DROP TABLE IF EXISTS `etiquetas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `etiquetas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `etiqueta` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `etiquetas`
--

LOCK TABLES `etiquetas` WRITE;
/*!40000 ALTER TABLE `etiquetas` DISABLE KEYS */;
/*!40000 ALTER TABLE `etiquetas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evaluaciones`
--

DROP TABLE IF EXISTS `evaluaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evaluaciones` (
  `porcentaje` decimal(3,0) NOT NULL,
  `tipo` varchar(15) NOT NULL,
  `tiempo_estimado` int NOT NULL,
  `actividad_id` int NOT NULL,
  PRIMARY KEY (`actividad_id`),
  CONSTRAINT `fk_evaluaciones_actividad1` FOREIGN KEY (`actividad_id`) REFERENCES `actividad` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluaciones`
--

LOCK TABLES `evaluaciones` WRITE;
/*!40000 ALTER TABLE `evaluaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `evaluaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo`
--

DROP TABLE IF EXISTS `grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `numero_grupo` int NOT NULL,
  `ciclo_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_grupo_ciclo1_idx` (`ciclo_id`),
  CONSTRAINT `fk_grupo_ciclo1` FOREIGN KEY (`ciclo_id`) REFERENCES `ciclo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo`
--

LOCK TABLES `grupo` WRITE;
/*!40000 ALTER TABLE `grupo` DISABLE KEYS */;
/*!40000 ALTER TABLE `grupo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo_has_usuario`
--

DROP TABLE IF EXISTS `grupo_has_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupo_has_usuario` (
  `grupo_id` int NOT NULL,
  `usuario_id` int NOT NULL,
  PRIMARY KEY (`grupo_id`,`usuario_id`),
  KEY `fk_grupo_has_usuario_usuario1_idx` (`usuario_id`),
  KEY `fk_grupo_has_usuario_grupo1_idx` (`grupo_id`),
  CONSTRAINT `fk_grupo_has_usuario_grupo1` FOREIGN KEY (`grupo_id`) REFERENCES `grupo` (`id`),
  CONSTRAINT `fk_grupo_has_usuario_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo_has_usuario`
--

LOCK TABLES `grupo_has_usuario` WRITE;
/*!40000 ALTER TABLE `grupo_has_usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `grupo_has_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `opciones`
--

DROP TABLE IF EXISTS `opciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `opciones` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(15) NOT NULL,
  `descripcion` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opciones`
--

LOCK TABLES `opciones` WRITE;
/*!40000 ALTER TABLE `opciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `opciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `opciones_has_perfil`
--

DROP TABLE IF EXISTS `opciones_has_perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `opciones_has_perfil` (
  `opciones_id` int NOT NULL,
  `perfil_id` int NOT NULL,
  PRIMARY KEY (`opciones_id`,`perfil_id`),
  KEY `fk_opciones_has_perfil_perfil1_idx` (`perfil_id`),
  KEY `fk_opciones_has_perfil_opciones1_idx` (`opciones_id`),
  CONSTRAINT `fk_opciones_has_perfil_opciones1` FOREIGN KEY (`opciones_id`) REFERENCES `opciones` (`id`),
  CONSTRAINT `fk_opciones_has_perfil_perfil1` FOREIGN KEY (`perfil_id`) REFERENCES `perfil` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opciones_has_perfil`
--

LOCK TABLES `opciones_has_perfil` WRITE;
/*!40000 ALTER TABLE `opciones_has_perfil` DISABLE KEYS */;
/*!40000 ALTER TABLE `opciones_has_perfil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfil`
--

DROP TABLE IF EXISTS `perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perfil` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(20) NOT NULL,
  `descripcion` varchar(80) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfil`
--

LOCK TABLES `perfil` WRITE;
/*!40000 ALTER TABLE `perfil` DISABLE KEYS */;
/*!40000 ALTER TABLE `perfil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfil_pertenece_usuario`
--

DROP TABLE IF EXISTS `perfil_pertenece_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perfil_pertenece_usuario` (
  `perfil_id` int NOT NULL,
  `usuario_id` int NOT NULL,
  PRIMARY KEY (`perfil_id`,`usuario_id`),
  KEY `fk_perfil_has_usuario_usuario1_idx` (`usuario_id`),
  KEY `fk_perfil_has_usuario_perfil1_idx` (`perfil_id`),
  CONSTRAINT `fk_perfil_has_usuario_perfil1` FOREIGN KEY (`perfil_id`) REFERENCES `perfil` (`id`),
  CONSTRAINT `fk_perfil_has_usuario_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfil_pertenece_usuario`
--

LOCK TABLES `perfil_pertenece_usuario` WRITE;
/*!40000 ALTER TABLE `perfil_pertenece_usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `perfil_pertenece_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plan_estudios`
--

DROP TABLE IF EXISTS `plan_estudios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plan_estudios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(250) NOT NULL,
  `cantidad_creditos` int NOT NULL,
  `fecha_aprobacion` date NOT NULL,
  `fecha_vigor` date NOT NULL,
  `carrera_codigo` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_plan_estudios_carrera1_idx` (`carrera_codigo`),
  CONSTRAINT `fk_plan_estudios_carrera1` FOREIGN KEY (`carrera_codigo`) REFERENCES `carrera` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan_estudios`
--

LOCK TABLES `plan_estudios` WRITE;
/*!40000 ALTER TABLE `plan_estudios` DISABLE KEYS */;
/*!40000 ALTER TABLE `plan_estudios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plan_estudios_has_curso`
--

DROP TABLE IF EXISTS `plan_estudios_has_curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plan_estudios_has_curso` (
  `plan_estudios_id` int NOT NULL,
  `curso_sigla` int NOT NULL,
  PRIMARY KEY (`plan_estudios_id`,`curso_sigla`),
  KEY `fk_plan_estudios_has_curso_curso1_idx` (`curso_sigla`),
  KEY `fk_plan_estudios_has_curso_plan_estudios1_idx` (`plan_estudios_id`),
  CONSTRAINT `fk_plan_estudios_has_curso_curso1` FOREIGN KEY (`curso_sigla`) REFERENCES `curso` (`sigla`),
  CONSTRAINT `fk_plan_estudios_has_curso_plan_estudios1` FOREIGN KEY (`plan_estudios_id`) REFERENCES `plan_estudios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan_estudios_has_curso`
--

LOCK TABLES `plan_estudios_has_curso` WRITE;
/*!40000 ALTER TABLE `plan_estudios_has_curso` DISABLE KEYS */;
/*!40000 ALTER TABLE `plan_estudios_has_curso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recomendaciones`
--

DROP TABLE IF EXISTS `recomendaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recomendaciones` (
  `id` int NOT NULL,
  `periodo` varchar(45) NOT NULL,
  `numero_actividades` varchar(45) NOT NULL,
  `horas_recomendadas` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recomendaciones`
--

LOCK TABLES `recomendaciones` WRITE;
/*!40000 ALTER TABLE `recomendaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `recomendaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recordatorio`
--

DROP TABLE IF EXISTS `recordatorio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recordatorio` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `hora_envio` time NOT NULL,
  `tipo` varchar(30) NOT NULL,
  `mensaje_enviado` varchar(70) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recordatorio`
--

LOCK TABLES `recordatorio` WRITE;
/*!40000 ALTER TABLE `recordatorio` DISABLE KEYS */;
/*!40000 ALTER TABLE `recordatorio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recordatorio_has_usuario`
--

DROP TABLE IF EXISTS `recordatorio_has_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recordatorio_has_usuario` (
  `recordatorio_id` int NOT NULL,
  `usuario_id` int NOT NULL,
  PRIMARY KEY (`recordatorio_id`,`usuario_id`),
  KEY `fk_recordatorio_has_usuario_usuario1_idx` (`usuario_id`),
  KEY `fk_recordatorio_has_usuario_recordatorio1_idx` (`recordatorio_id`),
  CONSTRAINT `fk_recordatorio_has_usuario_recordatorio1` FOREIGN KEY (`recordatorio_id`) REFERENCES `recordatorio` (`id`),
  CONSTRAINT `fk_recordatorio_has_usuario_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recordatorio_has_usuario`
--

LOCK TABLES `recordatorio_has_usuario` WRITE;
/*!40000 ALTER TABLE `recordatorio_has_usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `recordatorio_has_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recordatorio_notifica_actividad`
--

DROP TABLE IF EXISTS `recordatorio_notifica_actividad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recordatorio_notifica_actividad` (
  `recordatorio_id` int NOT NULL,
  `actividad_id` int NOT NULL,
  PRIMARY KEY (`recordatorio_id`,`actividad_id`),
  KEY `fk_recordatorio_has_actividad_actividad1_idx` (`actividad_id`),
  KEY `fk_recordatorio_has_actividad_recordatorio1_idx` (`recordatorio_id`),
  CONSTRAINT `fk_recordatorio_has_actividad_actividad1` FOREIGN KEY (`actividad_id`) REFERENCES `actividad` (`id`),
  CONSTRAINT `fk_recordatorio_has_actividad_recordatorio1` FOREIGN KEY (`recordatorio_id`) REFERENCES `recordatorio` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recordatorio_notifica_actividad`
--

LOCK TABLES `recordatorio_notifica_actividad` WRITE;
/*!40000 ALTER TABLE `recordatorio_notifica_actividad` DISABLE KEYS */;
/*!40000 ALTER TABLE `recordatorio_notifica_actividad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `apellido` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `carnet` varchar(7) DEFAULT NULL,
  `telefono` varchar(15) NOT NULL,
  `contraseña` varchar(20) NOT NULL,
  `nombre_usuario` varchar(30) NOT NULL,
  `correo` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_crea_recordatorio`
--

DROP TABLE IF EXISTS `usuario_crea_recordatorio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_crea_recordatorio` (
  `usuario_id` int NOT NULL,
  `recordatorio_id` int NOT NULL,
  PRIMARY KEY (`usuario_id`,`recordatorio_id`),
  KEY `fk_usuario_has_recordatorio_recordatorio1_idx` (`recordatorio_id`),
  KEY `fk_usuario_has_recordatorio_usuario1_idx` (`usuario_id`),
  CONSTRAINT `fk_usuario_has_recordatorio_recordatorio1` FOREIGN KEY (`recordatorio_id`) REFERENCES `recordatorio` (`id`),
  CONSTRAINT `fk_usuario_has_recordatorio_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_crea_recordatorio`
--

LOCK TABLES `usuario_crea_recordatorio` WRITE;
/*!40000 ALTER TABLE `usuario_crea_recordatorio` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario_crea_recordatorio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_participa_actividad`
--

DROP TABLE IF EXISTS `usuario_participa_actividad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_participa_actividad` (
  `usuario_id` int NOT NULL,
  `actividad_id` int NOT NULL,
  PRIMARY KEY (`usuario_id`,`actividad_id`),
  KEY `fk_usuario_has_actividad_actividad2_idx` (`actividad_id`),
  KEY `fk_usuario_has_actividad_usuario1_idx` (`usuario_id`),
  CONSTRAINT `fk_usuario_has_actividad_actividad2` FOREIGN KEY (`actividad_id`) REFERENCES `actividad` (`id`),
  CONSTRAINT `fk_usuario_has_actividad_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_participa_actividad`
--

LOCK TABLES `usuario_participa_actividad` WRITE;
/*!40000 ALTER TABLE `usuario_participa_actividad` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario_participa_actividad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_registra_actividad`
--

DROP TABLE IF EXISTS `usuario_registra_actividad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_registra_actividad` (
  `usuario_id` int NOT NULL,
  `actividad_id` int NOT NULL,
  PRIMARY KEY (`usuario_id`,`actividad_id`),
  KEY `fk_usuario_has_actividad_actividad1_idx` (`actividad_id`),
  KEY `fk_usuario_has_actividad_usuario_idx` (`usuario_id`),
  CONSTRAINT `fk_usuario_has_actividad_actividad1` FOREIGN KEY (`actividad_id`) REFERENCES `actividad` (`id`),
  CONSTRAINT `fk_usuario_has_actividad_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_registra_actividad`
--

LOCK TABLES `usuario_registra_actividad` WRITE;
/*!40000 ALTER TABLE `usuario_registra_actividad` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario_registra_actividad` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-04 13:39:16
