-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 24, 2024 at 07:53 PM
-- Server version: 8.0.37
-- PHP Version: 8.2.12

-- Create the database if it doesn't exist
CREATE DATABASE IF NOT EXISTS bd_lab;

-- Select the database
USE bd_lab;


SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bd_lab`
--

-- --------------------------------------------------------

--
-- Table structure for table `articulo`
--

CREATE TABLE IF NOT EXISTS `articulo` (
  `Id_Articulo` int NOT NULL,
  `Nombre` varchar(50) DEFAULT NULL,
  `Precio` decimal(10,2) DEFAULT NULL,
  `TipoArticulo` set('Pizza','Bocadillo','Complementario') NOT NULL,
  `Descripcion` varchar(255) DEFAULT NULL,
  `AVAILABLE` set('A','I','*') NOT NULL DEFAULT 'A',
  `CREATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATE` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `bocadillo`
--

CREATE TABLE IF NOT EXISTS `bocadillo` (
  `Id_Bocadillo` int NOT NULL,
  `Id_TamanhoBocadillo` int DEFAULT NULL,
  `Id_Articulo` int DEFAULT NULL,
  `AVAILABLE` set('A','I','*') NOT NULL DEFAULT 'A',
  `CREATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATE` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `bocadillo_ingrediente`
--

CREATE TABLE IF NOT EXISTS `bocadillo_ingrediente` (
  `Id_Bocadillo_Ingrediente` int NOT NULL,
  `Id_Bocadillo` int DEFAULT NULL,
  `Id_Ingrediente` int DEFAULT NULL,
  `AVAILABLE` set('A','I','*') NOT NULL DEFAULT 'A',
  `CREATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATE` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `cliente`
--

CREATE TABLE IF NOT EXISTS `cliente` (
  `DNI_Cliente` varchar(20) NOT NULL,
  `Nombres` varchar(50) DEFAULT NULL,
  `Apellidos` varchar(50) DEFAULT NULL,
  `Direccion` varchar(255) DEFAULT NULL,
  `Telefono` varchar(20) DEFAULT NULL,
  `Descripcion` varchar(255) DEFAULT NULL,
  `AVAILABLE` set('A','I','*') NOT NULL DEFAULT 'A',
  `CREATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATE` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ingrediente`
--

CREATE TABLE IF NOT EXISTS `ingrediente` (
  `Id_Ingrediente` int NOT NULL,
  `Nombre` varchar(50) DEFAULT NULL,
  `Descripcion` varchar(255) DEFAULT NULL,
  `AVAILABLE` set('A','I','*') NOT NULL DEFAULT 'A',
  `CREATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATE` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pedido`
--

CREATE TABLE IF NOT EXISTS `pedido` (
  `Id_Pedido` int NOT NULL,
  `FechaPedido` datetime DEFAULT NULL,
  `TotalPedido` decimal(10,2) DEFAULT NULL,
  `Nro_TipoPedido` int DEFAULT NULL,
  `DNI_Repartidor` varchar(20) DEFAULT NULL,
  `DNI_Cliente` varchar(20) DEFAULT NULL,
  `AVAILABLE` set('A','I','*') NOT NULL DEFAULT 'A',
  `CREATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATE` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pizza`
--

CREATE TABLE IF NOT EXISTS `pizza` (
  `Id_Pizza` int NOT NULL,
  `PrecioIngrediente` decimal(10,2) DEFAULT NULL,
  `Nro_TamanhoPizza` int DEFAULT NULL,
  `Id_Articulo` int DEFAULT NULL,
  `AVAILABLE` set('A','I','*') NOT NULL DEFAULT 'A',
  `CREATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATE` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pizza_ingrediente`
--

CREATE TABLE IF NOT EXISTS `pizza_ingrediente` (
  `Id_Pizza_Ingrediente` int NOT NULL,
  `Id_Pizza` int DEFAULT NULL,
  `Id_Ingrediente` int DEFAULT NULL,
  `AVAILABLE` set('A','I','*') NOT NULL DEFAULT 'A',
  `CREATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATE` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `productoestrella`
--

CREATE TABLE IF NOT EXISTS `productoestrella` (
  `Nro_ProductoEstrella` int NOT NULL,
  `Nombre` varchar(50) DEFAULT NULL,
  `Id_Articulo` int DEFAULT NULL,
  `AVAILABLE` set('A','I','*') NOT NULL DEFAULT 'A',
  `CREATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATE` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `productoestrella_ingrediente`
--

CREATE TABLE IF NOT EXISTS `productoestrella_ingrediente` (
  `Id_ProductoEstrella_Ingrediente` int NOT NULL,
  `Id_Ingrediente` int DEFAULT NULL,
  `Nro_ProductoEstrella` int DEFAULT NULL,
  `AVAILABLE` set('A','I','*') NOT NULL DEFAULT 'A',
  `CREATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATE` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `repartidor`
--

CREATE TABLE IF NOT EXISTS `repartidor` (
  `DNI_Repartidor` varchar(20) NOT NULL,
  `Nombre` varchar(50) DEFAULT NULL,
  `Id_Scooter` int DEFAULT NULL,
  `AVAILABLE` set('A','I','*') NOT NULL DEFAULT 'A',
  `CREATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATE` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `repostaje`
--

CREATE TABLE IF NOT EXISTS `repostaje` (
  `Id_Repostaje` int NOT NULL,
  `FechaRepostaje` datetime DEFAULT NULL,
  `CosteRepostaje` decimal(10,2) DEFAULT NULL,
  `Id_Scooter` int DEFAULT NULL,
  `AVAILABLE` set('A','I','*') NOT NULL DEFAULT 'A',
  `CREATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATE` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `scooter`
--

CREATE TABLE IF NOT EXISTS `scooter` (
  `Id_Scooter` int NOT NULL,
  `Anho_Scooter` year DEFAULT NULL,
  `Descripcion` varchar(255) DEFAULT NULL,
  `AVAILABLE` set('A','I','*') NOT NULL DEFAULT 'A',
  `CREATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATE` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tamanhobocadillo`
--

CREATE TABLE IF NOT EXISTS `tamanhobocadillo` (
  `Nro_TamanhoBocadillo` int NOT NULL,
  `Nombre` varchar(50) DEFAULT NULL,
  `Descripcion` varchar(255) DEFAULT NULL,
  `AVAILABLE` set('A','I','*') NOT NULL DEFAULT 'A',
  `CREATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATE` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tamanhopizza`
--

CREATE TABLE IF NOT EXISTS `tamanhopizza` (
  `Nro_TamanhoPizza` int NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Descripcion` varchar(255) DEFAULT NULL,
  `AVAILABLE` set('A','I','*') NOT NULL DEFAULT 'A',
  `CREATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATE` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tamanhopizza`
--

INSERT INTO `tamanhopizza` (`Nro_TamanhoPizza`, `Nombre`, `Descripcion`, `AVAILABLE`, `CREATE`, `UPDATE`) VALUES
(1, 'Pequeña (Small)', '10 pulgadas. Por lo general, de forma redonda con un borde más compacto y proporcional al tamaño.', 'A', '2024-06-18 15:17:00', '2024-06-19 22:49:38'),
(2, 'Mediana', 'Una pizza mediana', 'A', '2024-06-18 16:53:39', '2024-06-19 23:04:57'),
(3, 'Grande (Large)', '14 pulgadas. Redonda y más amplia que las anteriores, con un borde más ancho y espacio suficiente para varios ingredientes.', 'A', '2024-06-18 16:56:48', '2024-06-19 22:50:17');

-- --------------------------------------------------------

--
-- Table structure for table `tipopedido`
--

CREATE TABLE `tipopedido` (
  `Nro_TipoPedido` int NOT NULL,
  `ClasePedido` varchar(50) DEFAULT NULL,
  `Incremento_PedidoDomicilio` decimal(10,2) DEFAULT NULL,
  `Minimo_PedidoDomicilio` decimal(10,2) DEFAULT NULL,
  `Descripcion` varchar(255) DEFAULT NULL,
  `AVAILABLE` set('A','I','*') NOT NULL DEFAULT 'A',
  `CREATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATE` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `venta`
--

CREATE TABLE `venta` (
  `Id_Venta` int NOT NULL,
  `Fecha` datetime DEFAULT NULL,
  `Total` decimal(10,2) DEFAULT NULL,
  `Descripcion` varchar(255) DEFAULT NULL,
  `AVAILABLE` set('A','I','*') NOT NULL DEFAULT 'A',
  `CREATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATE` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `venta_articulo`
--

CREATE TABLE `venta_articulo` (
  `Id_Venta_Articulo` int NOT NULL,
  `Id_Articulo` int DEFAULT NULL,
  `Id_Venta` int DEFAULT NULL,
  `AVAILABLE` set('A','I','*') NOT NULL DEFAULT 'A',
  `CREATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATE` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `venta_pedido`
--

CREATE TABLE `venta_pedido` (
  `Id_Venta_Pedido` int NOT NULL,
  `Id_Pedido` int DEFAULT NULL,
  `Id_Venta` int DEFAULT NULL,
  `AVAILABLE` set('A','I','*') NOT NULL DEFAULT 'A',
  `CREATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATE` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `articulo`
--
ALTER TABLE `articulo`
  ADD PRIMARY KEY (`Id_Articulo`);

--
-- Indexes for table `bocadillo`
--
ALTER TABLE `bocadillo`
  ADD PRIMARY KEY (`Id_Bocadillo`),
  ADD KEY `Id_TamanhoBocadillo` (`Id_TamanhoBocadillo`),
  ADD KEY `Id_Articulo` (`Id_Articulo`);

--
-- Indexes for table `bocadillo_ingrediente`
--
ALTER TABLE `bocadillo_ingrediente`
  ADD PRIMARY KEY (`Id_Bocadillo_Ingrediente`),
  ADD KEY `Id_Bocadillo` (`Id_Bocadillo`),
  ADD KEY `Id_Ingrediente` (`Id_Ingrediente`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`DNI_Cliente`);

--
-- Indexes for table `ingrediente`
--
ALTER TABLE `ingrediente`
  ADD PRIMARY KEY (`Id_Ingrediente`);

--
-- Indexes for table `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`Id_Pedido`),
  ADD KEY `DNI_Cliente` (`DNI_Cliente`),
  ADD KEY `DNI_Repartidor` (`DNI_Repartidor`),
  ADD KEY `Nro_TipoPedido` (`Nro_TipoPedido`);

--
-- Indexes for table `pizza`
--
ALTER TABLE `pizza`
  ADD PRIMARY KEY (`Id_Pizza`),
  ADD KEY `Nro_TamanhoPizza` (`Nro_TamanhoPizza`),
  ADD KEY `Id_Articulo` (`Id_Articulo`);

--
-- Indexes for table `pizza_ingrediente`
--
ALTER TABLE `pizza_ingrediente`
  ADD PRIMARY KEY (`Id_Pizza_Ingrediente`),
  ADD KEY `Id_Pizza` (`Id_Pizza`),
  ADD KEY `Id_Ingrediente` (`Id_Ingrediente`);

--
-- Indexes for table `productoestrella`
--
ALTER TABLE `productoestrella`
  ADD PRIMARY KEY (`Nro_ProductoEstrella`),
  ADD KEY `Id_Articulo` (`Id_Articulo`);

--
-- Indexes for table `productoestrella_ingrediente`
--
ALTER TABLE `productoestrella_ingrediente`
  ADD PRIMARY KEY (`Id_ProductoEstrella_Ingrediente`),
  ADD KEY `Id_Ingrediente` (`Id_Ingrediente`),
  ADD KEY `Nro_ProductoEstrella` (`Nro_ProductoEstrella`);

--
-- Indexes for table `repartidor`
--
ALTER TABLE `repartidor`
  ADD PRIMARY KEY (`DNI_Repartidor`),
  ADD KEY `Id_Scooter` (`Id_Scooter`);

--
-- Indexes for table `repostaje`
--
ALTER TABLE `repostaje`
  ADD PRIMARY KEY (`Id_Repostaje`),
  ADD KEY `Id_Scooter` (`Id_Scooter`);

--
-- Indexes for table `scooter`
--
ALTER TABLE `scooter`
  ADD PRIMARY KEY (`Id_Scooter`);

--
-- Indexes for table `tamanhobocadillo`
--
ALTER TABLE `tamanhobocadillo`
  ADD PRIMARY KEY (`Nro_TamanhoBocadillo`);

--
-- Indexes for table `tamanhopizza`
--
ALTER TABLE `tamanhopizza`
  ADD PRIMARY KEY (`Nro_TamanhoPizza`);

--
-- Indexes for table `tipopedido`
--
ALTER TABLE `tipopedido`
  ADD PRIMARY KEY (`Nro_TipoPedido`);

--
-- Indexes for table `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`Id_Venta`);

--
-- Indexes for table `venta_articulo`
--
ALTER TABLE `venta_articulo`
  ADD PRIMARY KEY (`Id_Venta_Articulo`),
  ADD KEY `Id_Venta` (`Id_Venta`),
  ADD KEY `Id_Articulo` (`Id_Articulo`);

--
-- Indexes for table `venta_pedido`
--
ALTER TABLE `venta_pedido`
  ADD PRIMARY KEY (`Id_Venta_Pedido`),
  ADD KEY `Id_Venta` (`Id_Venta`),
  ADD KEY `Id_Pedido` (`Id_Pedido`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `articulo`
--
ALTER TABLE `articulo`
  MODIFY `Id_Articulo` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `bocadillo`
--
ALTER TABLE `bocadillo`
  MODIFY `Id_Bocadillo` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `bocadillo_ingrediente`
--
ALTER TABLE `bocadillo_ingrediente`
  MODIFY `Id_Bocadillo_Ingrediente` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ingrediente`
--
ALTER TABLE `ingrediente`
  MODIFY `Id_Ingrediente` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pedido`
--
ALTER TABLE `pedido`
  MODIFY `Id_Pedido` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pizza`
--
ALTER TABLE `pizza`
  MODIFY `Id_Pizza` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pizza_ingrediente`
--
ALTER TABLE `pizza_ingrediente`
  MODIFY `Id_Pizza_Ingrediente` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `productoestrella`
--
ALTER TABLE `productoestrella`
  MODIFY `Nro_ProductoEstrella` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `productoestrella_ingrediente`
--
ALTER TABLE `productoestrella_ingrediente`
  MODIFY `Id_ProductoEstrella_Ingrediente` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `repostaje`
--
ALTER TABLE `repostaje`
  MODIFY `Id_Repostaje` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `scooter`
--
ALTER TABLE `scooter`
  MODIFY `Id_Scooter` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tamanhobocadillo`
--
ALTER TABLE `tamanhobocadillo`
  MODIFY `Nro_TamanhoBocadillo` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tamanhopizza`
--
ALTER TABLE `tamanhopizza`
  MODIFY `Nro_TamanhoPizza` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `tipopedido`
--
ALTER TABLE `tipopedido`
  MODIFY `Nro_TipoPedido` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `venta`
--
ALTER TABLE `venta`
  MODIFY `Id_Venta` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `venta_articulo`
--
ALTER TABLE `venta_articulo`
  MODIFY `Id_Venta_Articulo` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `venta_pedido`
--
ALTER TABLE `venta_pedido`
  MODIFY `Id_Venta_Pedido` int NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bocadillo`
--
ALTER TABLE `bocadillo`
  ADD CONSTRAINT `bocadillo_ibfk_1` FOREIGN KEY (`Id_TamanhoBocadillo`) REFERENCES `tamanhobocadillo` (`Nro_TamanhoBocadillo`),
  ADD CONSTRAINT `bocadillo_ibfk_2` FOREIGN KEY (`Id_Articulo`) REFERENCES `articulo` (`Id_Articulo`);

--
-- Constraints for table `bocadillo_ingrediente`
--
ALTER TABLE `bocadillo_ingrediente`
  ADD CONSTRAINT `bocadillo_ingrediente_ibfk_1` FOREIGN KEY (`Id_Bocadillo`) REFERENCES `bocadillo` (`Id_Bocadillo`),
  ADD CONSTRAINT `bocadillo_ingrediente_ibfk_2` FOREIGN KEY (`Id_Ingrediente`) REFERENCES `ingrediente` (`Id_Ingrediente`);

--
-- Constraints for table `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`DNI_Cliente`) REFERENCES `cliente` (`DNI_Cliente`),
  ADD CONSTRAINT `pedido_ibfk_2` FOREIGN KEY (`DNI_Repartidor`) REFERENCES `repartidor` (`DNI_Repartidor`),
  ADD CONSTRAINT `pedido_ibfk_3` FOREIGN KEY (`Nro_TipoPedido`) REFERENCES `tipopedido` (`Nro_TipoPedido`);

--
-- Constraints for table `pizza`
--
ALTER TABLE `pizza`
  ADD CONSTRAINT `pizza_ibfk_1` FOREIGN KEY (`Nro_TamanhoPizza`) REFERENCES `tamanhopizza` (`Nro_TamanhoPizza`),
  ADD CONSTRAINT `pizza_ibfk_2` FOREIGN KEY (`Id_Articulo`) REFERENCES `articulo` (`Id_Articulo`);

--
-- Constraints for table `pizza_ingrediente`
--
ALTER TABLE `pizza_ingrediente`
  ADD CONSTRAINT `pizza_ingrediente_ibfk_1` FOREIGN KEY (`Id_Pizza`) REFERENCES `pizza` (`Id_Pizza`),
  ADD CONSTRAINT `pizza_ingrediente_ibfk_2` FOREIGN KEY (`Id_Ingrediente`) REFERENCES `ingrediente` (`Id_Ingrediente`);

--
-- Constraints for table `productoestrella`
--
ALTER TABLE `productoestrella`
  ADD CONSTRAINT `productoestrella_ibfk_1` FOREIGN KEY (`Id_Articulo`) REFERENCES `articulo` (`Id_Articulo`);

--
-- Constraints for table `productoestrella_ingrediente`
--
ALTER TABLE `productoestrella_ingrediente`
  ADD CONSTRAINT `productoestrella_ingrediente_ibfk_1` FOREIGN KEY (`Id_Ingrediente`) REFERENCES `ingrediente` (`Id_Ingrediente`),
  ADD CONSTRAINT `productoestrella_ingrediente_ibfk_2` FOREIGN KEY (`Nro_ProductoEstrella`) REFERENCES `productoestrella` (`Nro_ProductoEstrella`);

--
-- Constraints for table `repartidor`
--
ALTER TABLE `repartidor`
  ADD CONSTRAINT `repartidor_ibfk_1` FOREIGN KEY (`Id_Scooter`) REFERENCES `scooter` (`Id_Scooter`);

--
-- Constraints for table `repostaje`
--
ALTER TABLE `repostaje`
  ADD CONSTRAINT `repostaje_ibfk_1` FOREIGN KEY (`Id_Scooter`) REFERENCES `scooter` (`Id_Scooter`);

--
-- Constraints for table `venta_articulo`
--
ALTER TABLE `venta_articulo`
  ADD CONSTRAINT `venta_articulo_ibfk_1` FOREIGN KEY (`Id_Venta`) REFERENCES `venta` (`Id_Venta`),
  ADD CONSTRAINT `venta_articulo_ibfk_2` FOREIGN KEY (`Id_Articulo`) REFERENCES `articulo` (`Id_Articulo`);

--
-- Constraints for table `venta_pedido`
--
ALTER TABLE `venta_pedido`
  ADD CONSTRAINT `venta_pedido_ibfk_1` FOREIGN KEY (`Id_Venta`) REFERENCES `venta` (`Id_Venta`),
  ADD CONSTRAINT `venta_pedido_ibfk_2` FOREIGN KEY (`Id_Pedido`) REFERENCES `pedido` (`Id_Pedido`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
