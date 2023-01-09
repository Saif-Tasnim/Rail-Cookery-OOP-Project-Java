-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 02, 2023 at 12:24 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `train_food_aoop_project`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin_staff`
--

CREATE TABLE `admin_staff` (
  `name` varchar(30) NOT NULL,
  `massage` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin_staff`
--

INSERT INTO `admin_staff` (`name`, `massage`) VALUES
('Admin', 'Is Anyone?'),
('Admin', 'We have some complains against your job'),
('saif', 'okay be ensure the info is correct');

-- --------------------------------------------------------

--
-- Table structure for table `food_history`
--

CREATE TABLE `food_history` (
  `Id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price_per` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `food_history`
--

INSERT INTO `food_history` (`Id`, `name`, `quantity`, `price_per`) VALUES
(1, 'Burger', 41, 90),
(2, 'Sandwich', 14, 90),
(3, 'Chips', 10, 20),
(4, 'Chocolate', 0, 12),
(5, 'Drinks', 10, 25),
(6, 'Fries', 11, 65);

-- --------------------------------------------------------

--
-- Table structure for table `food_list`
--

CREATE TABLE `food_list` (
  `Id` int(11) NOT NULL,
  `phone_no` varchar(15) NOT NULL,
  `status` varchar(25) NOT NULL,
  `item_name` varchar(30) NOT NULL,
  `item_quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `food_list`
--

INSERT INTO `food_list` (`Id`, `phone_no`, `status`, `item_name`, `item_quantity`) VALUES
(29, '01877669501', 'Done', 'Burger', 1),
(30, '01877669501', 'Pending', 'Burger', 2),
(31, '01708655889', 'Done', 'Drink', 2),
(32, '01708655889', 'Done', 'Fry', 1),
(33, '01708655889', 'Done', 'Drink', 2),
(34, '01708655889', 'Done', 'Fry', 1),
(35, '01683685480', 'Pending', 'Burger', 1),
(36, '01631569705', 'Done', 'Burger', 1),
(37, '01631569705', 'Done', 'Drink', 1),
(38, '01877669501', 'Pending', 'Burger', 1),
(39, '01877669501', 'Pending', 'Burger', 4),
(40, '01877669501', 'Pending', 'Chocolate', 14);

-- --------------------------------------------------------

--
-- Table structure for table `list`
--

CREATE TABLE `list` (
  `Id` int(11) NOT NULL,
  `phone_no` varchar(15) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` float NOT NULL,
  `s_id` int(11) NOT NULL,
  `s_name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `list`
--

INSERT INTO `list` (`Id`, `phone_no`, `quantity`, `price`, `s_id`, `s_name`) VALUES
(1, '01631569705', 1, 103.5, 101, 'saif tasnim'),
(2, '01877669501', 1, 103.5, 101, 'saif tasnim'),
(3, '01537737101', 3, 235.75, 102, 'Abdul MuhitChowdhury'),
(4, '01757426888', 3, 155.25, 102, 'Abdul MuhitChowdhury'),
(5, '01877669501', 5, 517.5, 101, 'saif tasnim'),
(6, '01877669501', 1, 103.5, 102, 'Abdul Muhit Chowdhury'),
(7, '01708655889', 3, 132.25, 101, 'saif tasnim'),
(8, '01631569705', 2, 132.25, 101, 'saif tasnim');

-- --------------------------------------------------------

--
-- Table structure for table `massage`
--

CREATE TABLE `massage` (
  `name` varchar(300) NOT NULL,
  `massage` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `massage`
--

INSERT INTO `massage` (`name`, `massage`) VALUES
('saif', 'dg'),
('saif', 'hi'),
('saif', 'hlw'),
('Abdul Muhit', 'ok'),
('saif', 'I am Staff');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `phone_no` varchar(30) NOT NULL,
  `price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `place_order`
--

CREATE TABLE `place_order` (
  `phone_no` varchar(15) NOT NULL,
  `cmp_name` varchar(5) NOT NULL,
  `seat_no` int(11) NOT NULL,
  `total_quantity` int(11) NOT NULL,
  `total_price` float NOT NULL,
  `status` varchar(20) NOT NULL,
  `Payment` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `place_order`
--

INSERT INTO `place_order` (`phone_no`, `cmp_name`, `seat_no`, `total_quantity`, `total_price`, `status`, `Payment`) VALUES
('01683685480', 'CHA', 10, 1, 103.5, 'Pending', 'COD'),
('01757426888', 'KA', 1, 2, 207, 'Preparing', 'COD'),
('01877669501', 'THA', 12, 18, 607.2, 'Pending', 'COD');

-- --------------------------------------------------------

--
-- Table structure for table `staff_table`
--

CREATE TABLE `staff_table` (
  `staff_id` int(11) NOT NULL,
  `f_name` varchar(30) NOT NULL,
  `l_name` varchar(30) NOT NULL,
  `nid_no` varchar(15) NOT NULL,
  `phn_no` varchar(11) NOT NULL,
  `email` varchar(30) NOT NULL,
  `pass` varchar(15) NOT NULL,
  `village` varchar(20) NOT NULL,
  `thana` varchar(20) NOT NULL,
  `post` varchar(20) NOT NULL,
  `district` varchar(20) NOT NULL,
  `picture` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `staff_table`
--

INSERT INTO `staff_table` (`staff_id`, `f_name`, `l_name`, `nid_no`, `phn_no`, `email`, `pass`, `village`, `thana`, `post`, `district`, `picture`) VALUES
(102, 'Abdul Muhit', 'Chowdhury', '9176256738', '01677889501', 'muhit@gmail.com', '1234', 'mohanogor', 'rampura', 'rampura', 'dhaka', 0x6e756c6c),
(101, 'saif', 'tasnim', '9165256836', '01877669501', 'saif@gmail.com', '1234', 'kashipur', 'chagolnaiya', 'kashipur', 'feni', 0x6e756c6c);

-- --------------------------------------------------------

--
-- Table structure for table `user_admin`
--

CREATE TABLE `user_admin` (
  `name` varchar(30) NOT NULL,
  `massage` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_admin`
--

INSERT INTO `user_admin` (`name`, `massage`) VALUES
('Saif', 'hy'),
('Saif', 'hy'),
('Saif', 'hlw'),
('Saif', 'saif'),
('Saif', 'hi'),
('Saif', 'sakib'),
('Saif', 'Hello'),
('Admin', 'okay'),
('saif', 'check the other staff work fast'),
('Abdul Muhit', 'yssss hii');

-- --------------------------------------------------------

--
-- Table structure for table `user_staff`
--

CREATE TABLE `user_staff` (
  `name` varchar(30) NOT NULL,
  `massage` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_staff`
--

INSERT INTO `user_staff` (`name`, `massage`) VALUES
('Saif', 'hey'),
('Saif', 'free?'),
('Saif', 'now?'),
('Saif', 'Where is My Order From Seat No 4 Compartment KA'),
('saif', 'it has already shipped sir, Your Product is on the way'),
('Taseen', 'I want a cup of tea'),
('saif (Staff) ', 'sorry sir, it is not available'),
('Sanim', 'i need a seat');

-- --------------------------------------------------------

--
-- Table structure for table `user_table`
--

CREATE TABLE `user_table` (
  `user_Id` int(11) NOT NULL,
  `user_first_name` varchar(30) DEFAULT NULL,
  `user_last_name` varchar(30) DEFAULT NULL,
  `user_phone_no` varchar(11) DEFAULT NULL,
  `user_email` varchar(40) DEFAULT NULL,
  `user_password` varchar(40) DEFAULT NULL,
  `Image` longblob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_table`
--

INSERT INTO `user_table` (`user_Id`, `user_first_name`, `user_last_name`, `user_phone_no`, `user_email`, `user_password`, `Image`) VALUES
(4, 'Saif', 'Tasnim', '01877669501', 'abc@gmail.com', '12341234', ''),
(5, 'Sanim', 'Chy', '01939535539', 'sanim@gmail.com', '67896789', ''),
(6, 'soha', 'afsara', '01631569705', 'sdh@wst', '12345678', ''),
(7, 'saif', 'tasnim', '01939535538', 'saiftasnim8@gmail.com', 'saif1234', ''),
(8, 'tasnim', 'sakib', '01554315359', 'sakib007@gmail.com', 'sakib1234', ''),
(9, 'sabib', 'chy', '01727642058', 'sabib717@gmail.com', 'sabib1234', ''),
(10, 'Abdullah', 'Al Sakib', '01775332747', 'sakib@gmail.com', '12341234', 0x6e756c6c),
(11, 'Abdul Muhit', 'Chowdhury', '01683685480', 'muhit@gmail.com', '12345678', 0x6e756c6c),
(12, 'Tasnim Sakib', 'Chowdhury', '01556473348', 'tsc@gmail.com', 'sa12ki34b5', 0x6e756c6c),
(13, 'Ukhyang ', 'marma', '01553668277', 'ukyangetra1@gmail.com', '12345678', 0x6e756c6c),
(14, 'Arafat', 'Hossain', '01752636355', 'mondol@gmail.com', 'mondol1234', 0x6e756c6c),
(15, 'tazwan', 'TALHA', '01976374093', 'tazwantalha000@gmail.com', 'asdfghjkl', 0x6e756c6c),
(16, 'Taseen', 'Tahmeed', '01708655889', 'ttahmeed201220@gmail.com', 'taseen1234', 0x6e756c6c),
(17, 'Iftekhar', 'Ahmed', '01537737101', 'ifty@gmail.com', 'ifty1234', 0x6e756c6c),
(18, 'Tanzid ', 'Ahsan', '01757426888', 'tanzid@gmail.com', '12345678', 0x6e756c6c);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `food_history`
--
ALTER TABLE `food_history`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `food_list`
--
ALTER TABLE `food_list`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `list`
--
ALTER TABLE `list`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `place_order`
--
ALTER TABLE `place_order`
  ADD PRIMARY KEY (`phone_no`);

--
-- Indexes for table `staff_table`
--
ALTER TABLE `staff_table`
  ADD PRIMARY KEY (`phn_no`);

--
-- Indexes for table `user_table`
--
ALTER TABLE `user_table`
  ADD PRIMARY KEY (`user_Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `food_history`
--
ALTER TABLE `food_history`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `food_list`
--
ALTER TABLE `food_list`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `list`
--
ALTER TABLE `list`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `user_table`
--
ALTER TABLE `user_table`
  MODIFY `user_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
