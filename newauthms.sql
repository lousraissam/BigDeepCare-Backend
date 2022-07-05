-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 17 juin 2022 à 00:06
-- Version du serveur :  10.4.14-MariaDB
-- Version de PHP : 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `newauthms`
--

-- --------------------------------------------------------

--
-- Structure de la table `patient_gly_id`
--

CREATE TABLE `patient_gly_id` (
  `patient_id` bigint(20) NOT NULL,
  `glycemie_ids` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `dtype` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `cin` varchar(255) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `blood_type` int(11) DEFAULT NULL,
  `date_naissance` datetime DEFAULT NULL,
  `device_key` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `sexe` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`dtype`, `id`, `password`, `role`, `state`, `telephone`, `username`, `cin`, `adresse`, `age`, `blood_type`, `date_naissance`, `device_key`, `nom`, `prenom`, `sexe`, `type`, `parent_id`) VALUES
('UserDAO', 1, '$2a$10$q.73oSMIpykP9BX9EinZ2.VFtQ4aqnZzcskaNn8MJQZVzfRBenflq', 'ROLE_ADMIN', 0, '07986931', 'salah', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('Patient', 3, '$2a$10$JN9M6Dw6JNF/n019TtlvXO/NexFI76vm8PGp5AEw94jeU0Eo8TSbe', 'ROLE_PATIENT', 0, '07986931', 'toufik', NULL, NULL, NULL, NULL, NULL, 'G2OAY3I', NULL, NULL, NULL, NULL, 5),
('UserDAO', 4, '$2a$10$Sdo8UHCiw0jQ0bC46Sx2N.qNyyT6RHaVcisNdRNrFxZKrjqsFLtPC', 'ROLE_MEDECIN', 0, '07986931', 'issam', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('UserDAO', 5, '$2a$10$YGeeD7mz07G7uTLPrMIxCeHpseYf6Dvnk.RXtBSxWhacsnPMlyncu', 'ROLE_MEDECIN', 0, '07986930', 'hakim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('UserDAO', 6, '$2a$10$b.tIK7zMrJ8mDuJgSqy8u.tyUTz00M7tkaKBmDBVSOz9IKkjAe3.W', 'ROLE_AIDE_SOIGNANT', 0, '07986931', 'salima', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('Patient', 8, '$2a$10$DFlndNvCrKgS7M9oOl16/eNGQG2HR4y/x4nHtL.MvgiVHmIGyZw..', 'ROLE_PATIENT', 0, '07986931', 'koukou', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('Patient', 9, '$2a$10$KWG2H/z3dpGNymVGGbkbfeSB9czvPlYyOKoDHxQFYH8f2ESWXd6L2', 'ROLE_PATIENT', 0, '07986931', 'mohamed', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('Patient', 10, '$2a$10$HqEpyEZOqEGv6FdG6Var/OY7oA/8J5ZxSNOodsx4Zj8xOn1nHjww6', 'ROLE_PATIENT', 0, '07986931', 'amin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `patient_gly_id`
--
ALTER TABLE `patient_gly_id`
  ADD KEY `FKsn16hy7yfvv0it7soyu0e9spj` (`patient_id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4k8a1qa0wofm01aijepmu0d4g` (`parent_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `patient_gly_id`
--
ALTER TABLE `patient_gly_id`
  ADD CONSTRAINT `FKsn16hy7yfvv0it7soyu0e9spj` FOREIGN KEY (`patient_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FK4k8a1qa0wofm01aijepmu0d4g` FOREIGN KEY (`parent_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
