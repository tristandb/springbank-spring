-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `springbank`
--

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `bank_account`
--

CREATE TABLE `bank_account` (
  `account_id` int(11) NOT NULL,
  `holder_user_id` int(11) NOT NULL,
  `iban` varchar(34) NOT NULL COMMENT 'IBAN consists of up to 34 alphanumeric characters',
  `balance` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `card`
--

CREATE TABLE `card` (
  `card_id` int(11) NOT NULL,
  `card_number` int(4) NOT NULL,
  `bank_account_id` int(11) NOT NULL,
  `expiration_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `transaction`
--

CREATE TABLE `transaction` (
  `transaction_id` int(11) NOT NULL,
  `source_bank_account_iban` varchar(34) NOT NULL,
  `target_bank_account_iban` varchar(34) NOT NULL,
  `target_account_id` int(11) NOT NULL,
  `source_account_id` int(11) NOT NULL,
  `amount` decimal(10,0) NOT NULL,
  `message` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `surname` varchar(50) NOT NULL,
  `initials` varchar(10) NOT NULL,
  `date_of_birth` date NOT NULL,
  `bsn` varchar(9) NOT NULL COMMENT 'Burger Service Nummer',
  `street_address` varchar(100) NOT NULL,
  `telephone_number` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `user_bank_account`
--

CREATE TABLE `user_bank_account` (
  `user_bank_account_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `bank_account_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Many-to-many relationship between user and bank_account';

--
-- Indexen voor geëxporteerde tabellen
--

--
-- Indexen voor tabel `bank_account`
--
ALTER TABLE `bank_account`
  ADD PRIMARY KEY (`account_id`),
  ADD UNIQUE KEY `iban` (`iban`),
  ADD KEY `holder_user_id` (`holder_user_id`),
  ADD KEY `account_id` (`account_id`);

--
-- Indexen voor tabel `card`
--
ALTER TABLE `card`
  ADD PRIMARY KEY (`card_id`),
  ADD KEY `card_id` (`card_id`);

--
-- Indexen voor tabel `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`transaction_id`);

--
-- Indexen voor tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`,`email`);

--
-- Indexen voor tabel `user_bank_account`
--
ALTER TABLE `user_bank_account`
  ADD PRIMARY KEY (`user_bank_account_id`);

--
-- AUTO_INCREMENT voor geëxporteerde tabellen
--

--
-- AUTO_INCREMENT voor een tabel `bank_account`
--
ALTER TABLE `bank_account`
  MODIFY `account_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT voor een tabel `card`
--
ALTER TABLE `card`
  MODIFY `card_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT voor een tabel `transaction`
--
ALTER TABLE `transaction`
  MODIFY `transaction_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT voor een tabel `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT voor een tabel `user_bank_account`
--
ALTER TABLE `user_bank_account`
  MODIFY `user_bank_account_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
