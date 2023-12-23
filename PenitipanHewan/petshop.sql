-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 23, 2023 at 12:54 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `petshop`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `username`, `password`) VALUES
(1, 'admin', 'andrew123');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `customer_id` int(100) NOT NULL,
  `pet_Id` int(100) NOT NULL,
  `breed` varchar(100) NOT NULL,
  `sex` varchar(100) NOT NULL,
  `age` int(100) NOT NULL,
  `quantity` int(100) NOT NULL,
  `price` double NOT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `customer_id`, `pet_Id`, `breed`, `sex`, `age`, `quantity`, `price`, `date`) VALUES
(1, 1, 6, 'babi', 'Female', 23, 4, 400000, '2023-12-23'),
(2, 1, 1, 'Buaya', 'Male', 12, 2, 0, '2023-12-23'),
(3, 1, 3, 'naga', 'Female', 12, 2, 24, '2023-12-23'),
(4, 1, 6, 'babi', 'Female', 23, 1, 100000, '2023-12-23'),
(5, 1, 6, 'babi', 'Female', 23, 2, 200000, '2023-12-23'),
(6, 1, 6, 'babi', 'Female', 23, 1, 100000, '2023-12-23'),
(7, 2, 4, 'dinosaurus', 'Male', 43, 1, 43111, '2023-12-23'),
(8, 3, 1, 'Buaya', 'Male', 12, 1, 0, '2023-12-23'),
(9, 3, 1, 'Buaya', 'Male', 12, 1, 0, '2023-12-23'),
(10, 3, 3, 'naga', 'Female', 12, 1, 12, '2023-12-23'),
(11, 3, 4, 'dinosaurus', 'Male', 43, 1, 43111, '2023-12-23'),
(12, 3, 4, 'dinosaurus', 'Male', 43, 1, 43111, '2023-12-23'),
(13, 3, 6, 'babi', 'Female', 23, 1, 100000, '2023-12-23'),
(14, 4, 1, 'Buaya', 'Male', 12, 3, 0, '2023-12-23'),
(15, 4, 2, 'gajah', 'Male', 34, 3, 0, '2023-12-23'),
(16, 4, 4, 'dinosaurus', 'Male', 43, 2, 86222, '2023-12-23'),
(17, 5, 3, 'naga', 'Female', 12, 1, 12, '2023-12-23'),
(18, 5, 1, 'Ayam', 'Male', 3, 1, 10000, '2023-12-23'),
(19, 6, 1, 'Ayam', 'Male', 3, 1, 10000, '2023-12-23');

-- --------------------------------------------------------

--
-- Table structure for table `customer_info`
--

CREATE TABLE `customer_info` (
  `id` int(11) NOT NULL,
  `customer_id` int(100) NOT NULL,
  `total` double NOT NULL,
  `date` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer_info`
--

INSERT INTO `customer_info` (`id`, `customer_id`, `total`, `date`) VALUES
(1, 1, 700024, '2023-12-23'),
(2, 2, 700024, '2023-12-23'),
(3, 1, 700024, '2023-12-23'),
(4, 2, 700024, '2023-12-23'),
(5, 1, 800024, '2023-12-23'),
(6, 2, 43111, '2023-12-23'),
(7, 3, 86234, '2023-12-23'),
(8, 4, 86234, '2023-12-23'),
(9, 3, 186234, '2023-12-23'),
(10, 4, 86222, '2023-12-23'),
(11, 5, 10012, '2023-12-23');

-- --------------------------------------------------------

--
-- Table structure for table `pet`
--

CREATE TABLE `pet` (
  `id` int(11) NOT NULL,
  `pet_Id` int(100) NOT NULL,
  `breed` varchar(100) NOT NULL,
  `sex` varchar(100) NOT NULL,
  `age` int(100) NOT NULL,
  `price` double NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pet`
--

INSERT INTO `pet` (`id`, `pet_Id`, `breed`, `sex`, `age`, `price`, `date`) VALUES
(14, 1, 'Ayam', 'Male', 3, 10000, '2023-12-23'),
(15, 2, 'Anjing', 'Male', 5, 30000, '2023-12-23'),
(16, 3, 'Poodle', 'Female', 4, 12000, '2023-12-23');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer_info`
--
ALTER TABLE `customer_info`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pet`
--
ALTER TABLE `pet`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `customer_info`
--
ALTER TABLE `customer_info`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `pet`
--
ALTER TABLE `pet`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
