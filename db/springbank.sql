-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Gegenereerd op: 11 jul 2017 om 13:55
-- Serverversie: 5.7.18-0ubuntu0.16.04.1
-- PHP-versie: 7.0.18-0ubuntu0.16.04.1

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
  `balance` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Gegevens worden geëxporteerd voor tabel `bank_account`
--

INSERT INTO `bank_account` (`account_id`, `holder_user_id`, `balance`) VALUES
(6, 3, '238322'),
(7, 4, '23'),
(8, 4, '1000000000');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `card`
--

CREATE TABLE `card` (
  `card_id` int(11) NOT NULL,
  `card_number` int(4) NOT NULL,
  `bank_account_id` int(11) NOT NULL,
  `expiration_date` date NOT NULL,
  `pin` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Gegevens worden geëxporteerd voor tabel `card`
--

INSERT INTO `card` (`card_id`, `card_number`, `bank_account_id`, `expiration_date`, `pin`) VALUES
(6, 901, 6, '2022-07-05', 5434),
(7, 6095, 7, '2022-07-05', 6957),
(8, 9053, 8, '2022-07-05', 1671);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `iban`
--

CREATE TABLE `iban` (
  `bank_account_id` int(11) NOT NULL,
  `iban` varchar(34) NOT NULL COMMENT 'IBAN consists of up to 34 alphanumeric characters'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Gegevens worden geëxporteerd voor tabel `iban`
--

INSERT INTO `iban` (`bank_account_id`, `iban`) VALUES
(6, 'NL86SPRI0752582963'),
(7, 'NL83SPRI0114480386'),
(8, 'NL15SPRI0749536255');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `transaction`
--

CREATE TABLE `transaction` (
  `transaction_id` int(11) NOT NULL,
  `source_bank_account_iban` varchar(34) DEFAULT NULL,
  `target_bank_account_iban` varchar(34) DEFAULT NULL,
  `target_account_id` int(11) NOT NULL,
  `source_account_id` int(11) NOT NULL,
  `amount` decimal(10,0) NOT NULL,
  `message` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Gegevens worden geëxporteerd voor tabel `transaction`
--

INSERT INTO `transaction` (`transaction_id`, `source_bank_account_iban`, `target_bank_account_iban`, `target_account_id`, `source_account_id`, `amount`, `message`) VALUES
(1, '', '', 1, 2, '10', 'Hier heb je geld'),
(2, NULL, NULL, 2, 1, '10', 'string'),
(3, NULL, NULL, 2, 1, '10', 'string'),
(4, NULL, NULL, 2, 1, '10', 'string');

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
  `username` varchar(50) NOT NULL,
  `password` varchar(300) NOT NULL,
  `email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Gegevens worden geëxporteerd voor tabel `user`
--

INSERT INTO `user` (`user_id`, `name`, `surname`, `initials`, `date_of_birth`, `bsn`, `street_address`, `telephone_number`, `username`, `password`, `email`) VALUES
(2, 'Sven', 'Konings', 'S.', '1970-01-01', '123456789', 'Brink 123, Ons Dorp', '0612345678', 'sven@konings.nl', 'sven', 'info@svenkonings.nl'),
(3, 'Bernard', 'Hinault', 'B.', '1954-10-14', '', 'Yffiniac', '0612345678', 'bernard@hinault.fr', '', ''),
(4, 'Dagobert', 'Duck', 'D.', '1947-12-01', '', 'Pakhuislaan 1, 1234AB Duckstad', '05312312312', 'dagobertduck@gmail.com', '', ''),
(11, 'Duck', 'Donald', 'D', '1954-02-19', '571376046', '1313 Webfoot Walk, Duckburg', '+316 12345678', 'duckd', 'kwikkwekkwak', 'donald@gmail.com');

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
-- Gegevens worden geëxporteerd voor tabel `user_bank_account`
--

INSERT INTO `user_bank_account` (`user_bank_account_id`, `user_id`, `bank_account_id`) VALUES
(4, 4, 7);

--
-- Indexen voor geëxporteerde tabellen
--

--
-- Indexen voor tabel `bank_account`
--
ALTER TABLE `bank_account`
  ADD PRIMARY KEY (`account_id`),
  ADD KEY `holder_user_id` (`holder_user_id`),
  ADD KEY `account_id` (`account_id`);

--
-- Indexen voor tabel `card`
--
ALTER TABLE `card`
  ADD PRIMARY KEY (`card_id`),
  ADD KEY `card_id` (`card_id`),
  ADD KEY `bank_account_id` (`bank_account_id`);

--
-- Indexen voor tabel `iban`
--
ALTER TABLE `iban`
  ADD PRIMARY KEY (`bank_account_id`),
  ADD UNIQUE KEY `bankAccountId` (`bank_account_id`);

--
-- Indexen voor tabel `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`transaction_id`);

--
-- Indexen voor tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`,`username`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexen voor tabel `user_bank_account`
--
ALTER TABLE `user_bank_account`
  ADD PRIMARY KEY (`user_bank_account_id`),
  ADD KEY `FK32bq0igatas1bm9d7cj3emdmn` (`bank_account_id`),
  ADD KEY `FKjn71dv46t5vlrjbqroobqfxcl` (`user_id`);

--
-- AUTO_INCREMENT voor geëxporteerde tabellen
--

--
-- AUTO_INCREMENT voor een tabel `bank_account`
--
ALTER TABLE `bank_account`
  MODIFY `account_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT voor een tabel `card`
--
ALTER TABLE `card`
  MODIFY `card_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT voor een tabel `transaction`
--
ALTER TABLE `transaction`
  MODIFY `transaction_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT voor een tabel `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT voor een tabel `user_bank_account`
--
ALTER TABLE `user_bank_account`
  MODIFY `user_bank_account_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- Beperkingen voor geëxporteerde tabellen
--

--
-- Beperkingen voor tabel `bank_account`
--
ALTER TABLE `bank_account`
  ADD CONSTRAINT `FK8jud4i10ibvbti0xtla8e88h5` FOREIGN KEY (`holder_user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FKce76ojmu9t1kc6b49nylpa2ho` FOREIGN KEY (`account_id`) REFERENCES `bank_account` (`account_id`);

--
-- Beperkingen voor tabel `card`
--
ALTER TABLE `card`
  ADD CONSTRAINT `card_ibfk_1` FOREIGN KEY (`bank_account_id`) REFERENCES `bank_account` (`account_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Beperkingen voor tabel `iban`
--
ALTER TABLE `iban`
  ADD CONSTRAINT `iban_ibfk_1` FOREIGN KEY (`bank_account_id`) REFERENCES `bank_account` (`account_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Beperkingen voor tabel `user_bank_account`
--
ALTER TABLE `user_bank_account`
  ADD CONSTRAINT `FK32bq0igatas1bm9d7cj3emdmn` FOREIGN KEY (`bank_account_id`) REFERENCES `bank_account` (`account_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FKjn71dv46t5vlrjbqroobqfxcl` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
