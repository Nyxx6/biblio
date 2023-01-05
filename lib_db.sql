-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 05, 2023 at 05:21 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `lib_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `pseudo` varchar(20) NOT NULL,
  `pwd` varchar(20) NOT NULL,
  `atdate` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `pseudo`, `pwd`, `atdate`) VALUES
(1, 'admin', 'admin', '2022-12-28 08:04:44');

-- --------------------------------------------------------

--
-- Table structure for table `author`
--

CREATE TABLE `author` (
  `id` int(11) NOT NULL,
  `name1` varchar(20) NOT NULL,
  `name2` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `author`
--

INSERT INTO `author` (`id`, `name1`, `name2`) VALUES
(1, 'Jules', 'Verne'),
(2, 'Agatha', 'Christie'),
(3, 'Charles', 'Bukowski'),
(4, 'Charle', 'Dickens'),
(5, 'Hugo', 'Victor'),
(6, 'Inconnu', 'Inconnu');

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `id` int(11) NOT NULL,
  `type` enum('Livre','Dissertation','Magazine') NOT NULL,
  `ISBN` int(11) DEFAULT 0,
  `position` varchar(10) NOT NULL,
  `title` varchar(50) NOT NULL,
  `atdate` timestamp NOT NULL DEFAULT current_timestamp(),
  `id_edit` int(11) DEFAULT NULL,
  `id_sec` int(11) NOT NULL,
  `available` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`id`, `type`, `ISBN`, `position`, `title`, `atdate`, `id_edit`, `id_sec`, `available`) VALUES
(1, 'Livre', 123456789, 'R1', 'Le Dernier Jour d\'un condamné', '2023-01-02 14:35:14', 1, 8, 0),
(2, 'Livre', 1, 'R0', 'Black book', '2023-01-02 15:02:51', 1, 1, 0),
(3, 'Magazine', 0, 'R3', 'La présence du numérique', '2023-01-04 18:37:57', 2, 6, 0),
(4, 'Dissertation', 0, 'R2', 'Conception d\'un site e-commece', '2023-01-04 20:30:45', 2, 6, 1);

-- --------------------------------------------------------

--
-- Table structure for table `book_auth`
--

CREATE TABLE `book_auth` (
  `id_book` int(11) NOT NULL,
  `id_auth` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `book_auth`
--

INSERT INTO `book_auth` (`id_book`, `id_auth`) VALUES
(1, 5),
(2, 1),
(2, 2),
(2, 3),
(2, 4),
(3, 6),
(4, 6);

-- --------------------------------------------------------

--
-- Table structure for table `borrowed`
--

CREATE TABLE `borrowed` (
  `id` int(11) NOT NULL,
  `id_member` int(11) NOT NULL,
  `id_book` int(11) NOT NULL,
  `atdate` timestamp NOT NULL DEFAULT current_timestamp(),
  `duedate` date NOT NULL,
  `returnedat` datetime DEFAULT NULL,
  `condition` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `borrowed`
--

INSERT INTO `borrowed` (`id`, `id_member`, `id_book`, `atdate`, `duedate`, `returnedat`, `condition`) VALUES
(2, 1, 2, '2023-01-03 23:00:00', '2023-01-19', '2023-01-19 00:00:00', 'Neuf'),
(3, 1, 2, '2023-01-03 23:00:00', '2023-01-19', NULL, NULL),
(7, 1, 1, '2023-01-03 23:00:00', '2023-01-19', NULL, NULL),
(8, 1, 3, '2023-01-03 23:00:00', '2023-01-19', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `editor`
--

CREATE TABLE `editor` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `editor`
--

INSERT INTO `editor` (`id`, `name`) VALUES
(1, 'Frantz Fanon'),
(2, 'Epresse');

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE `member` (
  `id` int(11) NOT NULL,
  `name1` varchar(20) NOT NULL,
  `name2` varchar(20) NOT NULL,
  `birthd` date NOT NULL,
  `atdate` timestamp NOT NULL DEFAULT current_timestamp(),
  `id_type` int(11) NOT NULL,
  `nborrowed` tinyint(4) NOT NULL DEFAULT 0,
  `state` tinyint(1) NOT NULL DEFAULT 1 COMMENT '1:good,0:blocked',
  `nodelays` tinyint(4) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`id`, `name1`, `name2`, `birthd`, `atdate`, `id_type`, `nborrowed`, `state`, `nodelays`) VALUES
(1, 'John', 'Doe', '1999-01-01', '2022-12-28 08:04:08', 3, 3, 1, 0),
(2, 'Ziane', 'Ceryne', '2002-12-29', '2023-01-02 09:35:03', 1, 0, 1, 0),
(3, 'Belkalem', 'Sabrina', '2002-12-29', '2023-01-02 09:38:52', 1, 0, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `member_type`
--

CREATE TABLE `member_type` (
  `id` int(11) NOT NULL,
  `type` varchar(20) NOT NULL,
  `nbmax` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `member_type`
--

INSERT INTO `member_type` (`id`, `type`, `nbmax`) VALUES
(1, 'Etudiant', 5),
(2, 'Enseignant', 3),
(3, 'Bibliothécaire', 3);

-- --------------------------------------------------------

--
-- Table structure for table `section`
--

CREATE TABLE `section` (
  `id` int(11) NOT NULL,
  `label` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `section`
--

INSERT INTO `section` (`id`, `label`) VALUES
(1, 'Généralités'),
(2, 'Philosophie'),
(3, 'Religion'),
(4, 'Langage'),
(5, 'Sciences pures'),
(6, 'Techniques'),
(7, 'Arts'),
(8, 'Littérature'),
(9, 'Histoire-Géographie'),
(10, 'Sciences sociales'),
(11, 'Mathématique'),
(12, ''),
(13, '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `author`
--
ALTER TABLE `author`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`id`),
  ADD KEY `book_edt` (`id_edit`),
  ADD KEY `book_sec` (`id_sec`);

--
-- Indexes for table `book_auth`
--
ALTER TABLE `book_auth`
  ADD PRIMARY KEY (`id_book`,`id_auth`),
  ADD KEY `id_auth` (`id_auth`);

--
-- Indexes for table `borrowed`
--
ALTER TABLE `borrowed`
  ADD PRIMARY KEY (`id`),
  ADD KEY `borrowed_memb` (`id_member`),
  ADD KEY `borrowed_book` (`id_book`);

--
-- Indexes for table `editor`
--
ALTER TABLE `editor`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`id`),
  ADD KEY `member_type` (`id_type`);

--
-- Indexes for table `member_type`
--
ALTER TABLE `member_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `section`
--
ALTER TABLE `section`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `author`
--
ALTER TABLE `author`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `book`
--
ALTER TABLE `book`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `borrowed`
--
ALTER TABLE `borrowed`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `editor`
--
ALTER TABLE `editor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `member_type`
--
ALTER TABLE `member_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `section`
--
ALTER TABLE `section`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `member_admin` FOREIGN KEY (`id`) REFERENCES `member` (`id`);

--
-- Constraints for table `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `book_edt` FOREIGN KEY (`id_edit`) REFERENCES `editor` (`id`),
  ADD CONSTRAINT `book_sec` FOREIGN KEY (`id_sec`) REFERENCES `section` (`id`);

--
-- Constraints for table `book_auth`
--
ALTER TABLE `book_auth`
  ADD CONSTRAINT `book_auth_ibfk_1` FOREIGN KEY (`id_auth`) REFERENCES `author` (`id`),
  ADD CONSTRAINT `book_auth_ibfk_2` FOREIGN KEY (`id_book`) REFERENCES `book` (`id`);

--
-- Constraints for table `borrowed`
--
ALTER TABLE `borrowed`
  ADD CONSTRAINT `borrowed_book` FOREIGN KEY (`id_book`) REFERENCES `book` (`id`),
  ADD CONSTRAINT `borrowed_memb` FOREIGN KEY (`id_member`) REFERENCES `member` (`id`);

--
-- Constraints for table `member`
--
ALTER TABLE `member`
  ADD CONSTRAINT `member_type` FOREIGN KEY (`id_type`) REFERENCES `member_type` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
