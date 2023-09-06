-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 09, 2023 at 03:02 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `forsa_touaad`
--

-- --------------------------------------------------------

--
-- Table structure for table `bid`
--

CREATE TABLE `bid` (
  `id_bid` int(11) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  `id_listing` int(11) DEFAULT NULL,
  `bid_amount` int(11) NOT NULL,
  `entry_date` date DEFAULT current_timestamp(),
  `entry_time` time DEFAULT current_timestamp(),
  `type` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bid`
--

INSERT INTO `bid` (`id_bid`, `id_user`, `id_listing`, `bid_amount`, `entry_date`, `entry_time`, `type`) VALUES
(6, 57, 2, 202, '2023-02-24', '13:21:34', 'active'),
(7, 56, 3, 699, '2023-02-24', '13:21:40', 'active'),
(10, 63, 3, 500, '2023-03-07', '13:23:38', 'active'),
(11, 63, 4, 5000, '2023-03-07', '16:38:57', 'active'),
(14, 57, 4, 50000, '2023-03-07', '18:30:37', 'buyout'),
(15, 58, 2, 740, '2023-03-07', '21:58:07', 'active'),
(16, 57, 2, 1000, '2023-03-08', '20:17:24', 'active'),
(18, 58, 3, 700, '2023-03-09', '00:19:11', 'active'),
(20, 63, 3, 702, '2023-03-09', '00:33:33', 'active'),
(21, 58, 3, 703, '2023-03-09', '00:33:47', 'cancelled');

-- --------------------------------------------------------

--
-- Table structure for table `debit_card`
--

CREATE TABLE `debit_card` (
  `card_number` int(11) NOT NULL,
  `card_type` varchar(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `cvv` int(11) NOT NULL,
  `exp_date` date NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `livraison`
--

CREATE TABLE `livraison` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `adresse` varchar(50) NOT NULL,
  `etat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `livraison`
--

INSERT INTO `livraison` (`id`, `nom`, `adresse`, `etat`) VALUES
(5, 'KGHJ', 'BVFGH', 'GHJU'),
(6, '15', 'petit ariana', 'livré'),
(7, '15', 'petit ariana', 'livré');

-- --------------------------------------------------------

--
-- Table structure for table `livreur`
--

CREATE TABLE `livreur` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `tel` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `livreur`
--

INSERT INTO `livreur` (`id`, `nom`, `prenom`, `tel`) VALUES
(4, 'ranya', 'matrouh', '50625093'),
(6, 'foulen', 'ben foulen', '22312817'),
(7, 'med', 'med', '54883264'),
(8, 'med', 'med', '54883264');

-- --------------------------------------------------------

--
-- Table structure for table `point_relais`
--

CREATE TABLE `point_relais` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `adresse` varchar(50) NOT NULL,
  `etat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `reclamation`
--

CREATE TABLE `reclamation` (
  `id_reclamation` int(11) NOT NULL,
  `categorie` varchar(255) NOT NULL,
  `objet` varchar(255) NOT NULL,
  `texte` varchar(255) NOT NULL,
  `etat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reclamation`
--

INSERT INTO `reclamation` (`id_reclamation`, `categorie`, `objet`, `texte`, `etat`) VALUES
(1, 'produit', 'fake image', 'azertyuiopqsdfghjkl', 0),
(2, 'vendeur', 'details manquant', 'nbvcxwmlkjhgfdspoij', 0),
(4, 'site', 'problem technique', 'azsdcezfdvbretyhj,ujkll', 1),
(5, 'livraison', 'livraison retarde', 'nbvcxwqsdfghytrezzzzzsdcpl', 1),
(6, 'produit', 'produit trucke', 'njiyhbvcxwqaze', 1),
(7, 'vendeur', 'faux profile', 'njiioppmlkjjhh', 1),
(8, 'livraison', 'livraison annule', 'mploikjuyhnb', 0);

-- --------------------------------------------------------

--
-- Table structure for table `reponse`
--

CREATE TABLE `reponse` (
  `id_reponse` int(11) NOT NULL,
  `type_reponse` varchar(255) NOT NULL,
  `texte_reponse` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reponse`
--

INSERT INTO `reponse` (`id_reponse`, `type_reponse`, `texte_reponse`) VALUES
(1, 'en cours', 'c\'est noté'),
(2, 'traite', 'votre reclamationa ete prise en consideration'),
(3, 'traite', 'hadheka lmawjoud'),
(4, 'traite', 'ochrob wl tayer garnek'),
(5, 'en cours', 'c\'est noté');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(180) NOT NULL,
  `roles` longtext DEFAULT NULL COMMENT '(DC2Type:json)',
  `password` varchar(255) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `adresse` varchar(300) DEFAULT NULL,
  `num` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `date_inscrit_u` datetime DEFAULT current_timestamp(),
  `desc_u` varchar(300) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `psudo` varchar(150) NOT NULL,
  `token` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `roles`, `password`, `fullname`, `adresse`, `num`, `image`, `date_inscrit_u`, `desc_u`, `code`, `psudo`, `token`) VALUES
(56, 'mariem@esprit.tn', '[\"ROLE_USER\"]', '$2y$10$bJ.jajH4e9xgEjuLe59AeOadJLI1ahwlntdtxCpwvROANiQ.3.BKq', 'mariem', 'manar2', NULL, 'null', '2023-02-20 12:25:17', 'unrivaled', '0', 'demona', NULL),
(57, 'mourad@esprit.tn', '[\"ROLE_USER\"]', '$2y$10$hcEzgyOVB3wkXgzO9RYxLeXggyTeukiEUaV1xIt16DM8nPU9dzeWa', 'mourad', 'manar3', NULL, 'null', '2023-02-20 12:28:50', '', '0', 'momo', NULL),
(58, 'ahmed@esprit.tn', '[\"ROLE_SUPERADMIN\"]', '$2y$10$85Ufe/wjXqX7cmKVO5/PF.awz/Sp7zeP3SbdZNkd1BQjm//0b/sbK', 'ahmed', 'zahra3', NULL, NULL, '2023-02-20 12:39:42', NULL, '1', 'bopp', NULL),
(63, 'yassine@esprit.tn', '[\"ROLE_ADMIN\"]', '$2y$10$Uo05ecR/aJ6mAs5RIIuB6O9uvfNDf2eD9zMQXBI.qrqoBaZL808fG', 'yassine', 'manar3', NULL, NULL, '2023-02-20 23:17:47', NULL, '1', 'gigo', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bid`
--
ALTER TABLE `bid`
  ADD PRIMARY KEY (`id_bid`),
  ADD KEY `user fk` (`id_user`),
  ADD KEY `listing fk` (`id_listing`);

--
-- Indexes for table `livraison`
--
ALTER TABLE `livraison`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `livreur`
--
ALTER TABLE `livreur`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `point_relais`
--
ALTER TABLE `point_relais`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`id_reclamation`);

--
-- Indexes for table `reponse`
--
ALTER TABLE `reponse`
  ADD PRIMARY KEY (`id_reponse`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQ_8D93D649E7927C74` (`email`),
  ADD UNIQUE KEY `UNIQ_8D93D649C8DC6071` (`psudo`);
ALTER TABLE `user` ADD FULLTEXT KEY `IDX_8D93D649C8DC6071` (`psudo`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bid`
--
ALTER TABLE `bid`
  MODIFY `id_bid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `livraison`
--
ALTER TABLE `livraison`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `livreur`
--
ALTER TABLE `livreur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `point_relais`
--
ALTER TABLE `point_relais`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `id_reclamation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `reponse`
--
ALTER TABLE `reponse`
  MODIFY `id_reponse` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bid`
--
ALTER TABLE `bid`
  ADD CONSTRAINT `listing fk` FOREIGN KEY (`id_listing`) REFERENCES `listing` (`id_listing`),
  ADD CONSTRAINT `user fk` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
